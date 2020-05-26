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
            <li class="track" onclick = "songClick()">
                <h2>${song.name}</h2>
                <img src="${song.album.images[2].url}">
            </li>
        `;
        })
        .join('');
    songList.innerHTML = htmlString;
};

function songClick(){

    alert("test");

}



/*
function displaySongs(song){
    console.log(song.name);
}*/



