
var intervalID = window.setInterval(checkQueue, 1000);


function checkQueue(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/getSong",
        dataType: "json",
        success: function(response){
            queueSong(response);
        },
        error: function(error){
            //console.log("error in checkqueue");
            //console.log(error);
        }
    });

}

function sendToQueue(song){

    console.log(song);

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/confirmedSong",
        contentType: "application/Json",
        data: JSON.stringify(song),
        success: function(response){

           console.log("added to officail queue")

        },
        error: function(error){
        }
    });

}



