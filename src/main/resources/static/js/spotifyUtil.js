
function getToken(){
    var token;

     $.ajax({
            type: "GET",
            url: "http://localhost:8080/token",
            dataType: "text",
            async: false,
            success: function(response){
                console.log(response);
                token = response;
            }

     });

     return token;

}


function getDeviceId(){

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

    return deviceId;

}


function getSongFromId(id){

    var token = getToken();
    var song;

    $.ajax({
        type: "GET",
        url: "https://api.spotify.com/v1/tracks/" + id  ,
        contentType: "application/json",
        async: false,
        headers:{
            'Authorization': 'Bearer ' + token
        },
        success: function(response){
            song = response;
            console.log(response);

        }

    });

    return song;

}


/**
 * Pauses a user's playback
 *
 */
function pausePlayback(){


    var token = getToken();

    console.log(token);

    $.ajax({
        type: "PUT",
        url: "https://api.spotify.com/v1/me/player/pause",
        dataType: "json",
        contentType: "application/json",
        headers:{
            'Authorization': 'Bearer ' + token
        }

    });
}

/**
 * Starts user playback
 *
 */
function startPlayback(id){


    var token = getToken();

    var deviceId = getDeviceId();

    $.ajax({
            type: "PUT",
            url: "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId,
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + token
            },
            data:JSON.stringify({

                "uris":["spotify:track:" + id ]
            }),
            success: function(response){
                console.log("start play sucess");
            }

    });
}

/**
 * Resumes a user's playback
 *
 */
function resumePlayback(){

    var token = getToken();

    var deviceId = getDeviceId();

    $.ajax({
            type: "PUT",
            url: "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId,
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + token
            },
            success: function(response){
                console.log("resume");
            }

    });
}


function displaySearch(){
    window.location.replace("/Search");
}


function skipSong(){

    var token = getToken();

    var deviceId = getDeviceId();


    $.ajax({
            type: "POST",
            url: "https://api.spotify.com/v1/me/player/next?device_id=" + deviceId,
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + token
            },
            success: function(response){
                console.log("skipped");
            }

    });

}

//pause play button
// returns the json with the current playback information, simple scope your call on this returned object to get more info
$(document).ready(function () {
        $("#PausePlay").on("click", function () {
        //put the button in here to resolve issue of the js script running before the html was able to render
        const buttonP = document.getElementById("PausePlay");

        console.log("the buttonP should be bellow");
        console.log(buttonP);
        console.log("in the playback info function");

        var token = getToken();

        $.ajax({
            type: "GET",
            url: "https://api.spotify.com/v1/me/player",
            dataType: "json",
            //contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + token
            },
            success: function(response){
                console.log("got playback info");
                console.log(response);
                //return response;
                console.log("test");
                console.log("this is var"+response.is_playing);
                if(response==null || response.is_playing==false){
                    buttonP.innerHTML= "Pause";
                    resumePlayback();

                }
                else{
                    buttonP.innerHTML= "Play";
                    pausePlayback();
                }
            }

        });

    });
 });


 function queueSong(song){


    //getting the users token
    var token = getToken();

    //getting the device id
    var deviceId = getDeviceId();

    var id = song.id;

    //add song to queue or start song if no device is active
    $.ajax({
        type: "POST",
        url: "https://api.spotify.com/v1/me/player/queue?uri=spotify:track:"+id,
        contentType: "application/json",
        async: false,
        headers:{
            'Authorization': 'Bearer ' + token
        },
        success: function(response){
            console.log("queue success");

            //send to backed queue
            sendToQueue(song);

        },
        error: function(xhr, status, error){

            if(xhr.status == 404){ //starting song if not active device
                startPlayback(id);
                sendToQueue(song);
            }
        }

    });


 }

