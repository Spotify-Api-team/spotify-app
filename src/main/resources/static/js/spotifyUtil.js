/**
 * Pauses a users playback
 *
 *@param {String} authorization token for user
*/
function pausePlayback(token){

    $.ajax({
        type: "PUT",
        url: "https://api.spotify.com/v1/me/player/pause",
        dataType: "json",
        contentType: "application/json",
        headers:{
            'Authorization': 'Bearer' + token
        }

    })
}

/**
 * Starts or resumes a users playback
 *
 *@param {String} authorization token for user
*/
function startPlayback(token){
    $.ajax({
            type: "PUT",
            url: "https://api.spotify.com/v1/me/player/play",
            dataType: "json",
            contentType: "application/json",
            headers:{
                'Authorization': 'Bearer' + token
            }

        })
}