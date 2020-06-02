/**
 * Pauses a user's playback
 *
 */
function pausePlayback(){


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            console.log(response);
            window.token = response;
        }

    });

    console.log(window.token);

    $.ajax({
        type: "PUT",
        url: "https://api.spotify.com/v1/me/player/pause",
        dataType: "json",
        contentType: "application/json",
        headers:{
            'Authorization': 'Bearer ' + window.token
        }

    });
}

/**
 * Starts user playback with ashes
 *
 */
function startPlayback(){


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            window.token = response;
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

    console.log(deviceId);


    $.ajax({
            type: "PUT",
            url: "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId,
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + window.token
            },
            data:JSON.stringify({

                "uris":["spotify:track:2NeyJbL3ROKCjRkAjs77ya"]
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

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            window.token = response;
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

    console.log(deviceId);


    $.ajax({
            type: "PUT",
            url: "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId,
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + window.token
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

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            window.token = response;
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

    //console.log(deviceId);


    $.ajax({
            type: "POST",
            url: "https://api.spotify.com/v1/me/player/next?device_id=" + deviceId,
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + window.token
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
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/token",
            dataType: "text",
            success: function(response){
                window.token = response;
            }

        });
        $.ajax({
            type: "GET",
            url: "https://api.spotify.com/v1/me/player",
            dataType: "json",
            //contentType: "application/json",
            headers:{
                'Authorization': 'Bearer ' + window.token
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
           // eturn false;

        });

    });
 });

