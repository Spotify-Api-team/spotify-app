/**
 * Pauses a users playback
 *
 *@param {String} authorization token for user
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

    })
}

/**
 * Starts or resumes a users playback
 *
 *@param {String} authorization token for user
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
                console.log("play success");
            }

    });
}

function startSong(){


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            window.token = response;
        }

    })


    $.ajax({
        type: "GET",
        url: "https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl",
        dataType: 'json',
        contentType: "application/json",
        headers:{
            'Authorization': 'Bearer ' + window.token
        },
        success: function (response) {
            console.log(response);

        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log(errorMessage);

        }
    })


}
