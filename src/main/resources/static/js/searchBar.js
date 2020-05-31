const searchBar = document.getElementById('searchBar');
const songList = document.getElementById('songList');
document.getElementById('songList').style.visibility= "hidden";

//if now key up do not show <ul>


searchBar.addEventListener('keyup',(e)=>{

    document.getElementById('songList').style.visibility= "visible";
    if(searchBar.value==''){
        document.getElementById('songList').style.visibility= "hidden";
        console.log("hidden");
    }
    //console.log("search bar value " +searchBar.value);

    console.log(e.target.value);
    const val= e.target.value;
    if(val.length>1){
        searchFunction(val);
        //songList = generateList(val);
    }


});



//used in the event listner for api call
function searchFunction(e){

    console.log('e='+e);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            //console.log(response);
            window.token = response;
        }
    });

    var params = {
        q:e,
        type:"track",
        limit: "3"
    };


    $.ajax({
        type: "GET",
        url: "https://api.spotify.com/v1/search?"+$.param(params),
        dataType: "json",
        contentType:"application/json",
        headers:{
            "Authorization": "Bearer "+window.token
        },
        success: function(response){
            console.log(response);

            var i=0;
            while(i<3){
                //console.log(response.tracks.items[i].name);
                console.log(response.tracks.items[i]);
                displaySongs(response.tracks.items);
                i = i+1;
            }
            //console.log(response.tracks.items[0].name);


        }

    });
}

const displaySongs = (songs) => {
    const htmlString = songs
        .map((song) => {
            return `
            <li class="track" id="${song.id}" onclick = "songClick(this.id)" >
                <h2>${song.name}</h2>
                <img src="${song.album.images[2].url}">
            </li>
        `;
        })
        .join('');
    songList.innerHTML = htmlString;


};

/*
 * When the song searched is clicked add to que
 *
 * @param the id of the song clicked
 */
function songClick(id){

    //getting the users token
     $.ajax({
            type: "GET",
            url: "http://localhost:8080/token",
            dataType: "text",
            async: false,  //waits until this ajax call is done
            success: function(response){
                console.log(response);
                window.token = response;
            },
            error: function(){
                console.log("error")
            }

     });



        //getting the device id
     var deviceId;
     $.ajax({

        type: "GET",
        url: "http://localhost:8080/deviceId",
        dataType: "text",
        async: false,
        success: function(response){
            deviceId = response;
            console.log(deviceId);
        }

     });

     console.log('member'+window.token);


     //add song to queue or start song if no device is active
     $.ajax({
        type: "POST",
        url: "https://api.spotify.com/v1/me/player/queue?uri=spotify:track:"+id,
        contentType: "application/json",
        headers:{
            'Authorization': 'Bearer ' + window.token
        },
        success: function(response){
            console.log("queue success");
        },
        error: function(xhr, status, error){

            if(xhr.status == 404){//starting song if not active device
                $.ajax({
                    type: "PUT",
                    url: "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId,
                    dataType: "json",
                    contentType: "application/json",
                    headers:{
                        'Authorization': 'Bearer ' + window.token
                    },
                    data:JSON.stringify({
                        "uris":["spotify:track:" + id]
                    }),
                    success: function(response){
                        console.log("play success");
                    }
                });
            }
        }

     });
}







