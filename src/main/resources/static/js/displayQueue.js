
var intervalID = window.setInterval(updateQueue, 1000);

function updateQueue(){

    songs = null;
    var address= getExactAddress();

    $.ajax({
            type: "GET",
            url: address+"/confirmedSong",
            dataType: "json",
            success: function(response){
                //console.log(response);
                displayQueue(response);
                //document.getElementById('queue').style.visibility="visible";
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
                <img src="${song.image.url}" style = "float left; width: 30px; height 30px; padding-right: 5px;">
                <div class="info"> ${song.name} </div>
            </li>
        `;
        })
        .join('');
    queue.innerHTML = htmlStringQueue;
};