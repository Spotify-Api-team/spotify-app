
var intervalID = window.setInterval(checkQueue, 1000);


function checkQueue(){

    var address= getExactAddress();
    $.ajax({
        type: "GET",
        url: address+"/getSong",
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
    var address= getExactAddress();

    $.ajax({

        type: "POST",
        url: address+"/confirmedSong",
        contentType: "application/Json",
        data: JSON.stringify(song),
        success: function(response){

           console.log("added to officail queue")

        },
        error: function(error){
        }
    });

}



