const searchBar = document.getElementById('searchBar');
const songList = document.getElementById('songList');

searchBar.addEventListener('keyup',(e)=>{
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
            var array=[];
            var i=0;
            while(i<3){
                //console.log(response.tracks.items[i].name);
                //array = array.concat(response.track.items[i].name);
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

function songClick(id){

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
                var errorMessage = xhr.status + ': ' + xhr.statusText
                alert('Error - ' + errorMessage);
                if(xhr.status == 404){
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






