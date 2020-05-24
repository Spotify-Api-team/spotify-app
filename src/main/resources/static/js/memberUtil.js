function queueSong(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        async: false,  //waits until this ajax call is done
        success: function(response){
            console.log(response);
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

    console.log('member'+window.token);


    $.ajax({
        type: "POST",
        url: "https://api.spotify.com/v1/me/player/queue?uri=spotify:track:2NeyJbL3ROKCjRkAjs77ya",
        contentType: "application/json",
        headers:{
            'Authorization': 'Bearer ' + window.token
        },
        success: function(response){
            console.log("queue success");
        }
    });
}