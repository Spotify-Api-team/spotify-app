
var intervalID = window.setInterval(updateQueue, 1000);

function updateQueue(){

    songs = null;

    $.ajax({
            type: "GET",
            url: "http://localhost:8080/confirmedSong",
            dataType: "json",
            success: function(response){
                console.log(response);
                displayQueue(response);
            },
            error: function(error){
                //console.log("error in checkqueue");
                //console.log(error);
            }
    });

}

const displayQueue = (songs) => {
    const htmlStringQueue = songs
        .map((song) => {
            return `
            <li class="queueTrack" id="${song.id}"  >
                <img src="${song.image.url}" style = "float left; width: 30px; height 30px;">
                <div class="info"> ${song.name} </div>
            </li>
        `;
        })
        .join('');
    queue.innerHTML = htmlStringQueue;
};