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
                <img src="${song.album.images[2].url}" style = "float left;" >
                <div id = "info">${song.name} by ${song.artists[0].name} </div>
            </li>
        `;
        })
        .join('');
    songList.innerHTML = htmlString;
};

/*
 * When the song searched send to queue in backend
 *
 * @param the id of the song clicked
 */
function songClick(id){

    var song = getSongFromId(id);
    var simpleSong = convertSong(song);

    console.log(JSON.stringify(simpleSong));

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/queueSong",
        contentType: "application/Json",
        data: JSON.stringify(simpleSong),
        success: function(response){
            console.log("sent to TBAqueue");
        },
        error: function(jqXhr, textStatus, errorMessage){
            console.log(errorMessage);
        }
    });

}
