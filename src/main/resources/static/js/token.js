

/*could be use for future
function getUrlVars(){
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    console.log(vars);
    return vars;
}
*/





var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var code = urlParams.get("code");

console.log(code);


//our apps data
var clientId = 'b1e9cb8d5176473fb39f5e7aca4eaae9';
var clientSecret = '4589c5631d33441199f7722186905fe0';

//Base 64 encoded string that contains
var encodedData = window.btoa(clientId + ':' + clientSecret);


var intervalID = window.setInterval(refreshToken, 3300000);

$.ajax({
    type: "POST",
    contentType: 'application/x-www-form-urlencoded', //input
    dataType: "json", //output
    url: 'https://accounts.spotify.com/api/token',
    headers: {
        'Authorization': 'Basic ' + encodedData
    },
    data: $.param({   //puts in form of application/x-www-form-urlencoded
        code: code,
        redirect_uri: 'http://localhost:8080/Room',
        grant_type: 'authorization_code'
    }),
    success: function (response) {
        console.log(response);

        //set global variable
        window.token= response.access_token;
        window.refreshtoken = response.refresh_token;
        console.log(window.token);
        console.log(window.refreshtoken);

        data = {
        "token": window.token
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/token",
            contentType: "application/Json",
            data: window.token,
            error: function (jqXhr, textStatus, errorMessage) {
                                console.log("error" + errorMessage);
            }
        });


        //window.location.replace("http://localhost:8080/Room");
    },
    error: function (jqXhr, textStatus, errorMessage) {
        console.log("errorToken" + errorMessage);

    }
});





//https://developer.spotify.com/documentation/ios/guides/token-swap-and-refresh/
function refreshToken(){
    $.ajax({
        type: "POST",
        dataType: "json", //output
        url: 'https://accounts.spotify.com/api/token',
        headers:{
            'Authorization': 'Basic ' + encodedData
        },
        data: $.param({
            grant_type: 'refresh_token',
            refresh_token: window.refreshtoken
        }),
        success: function (response) {
            console.log(response);
            window.token= response.access_token;
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log("errorRefreshToken" + errorMessage);

        }
    });
}



getUserData(window.token);
function getUserData(accessToken) {
    return $.ajax({
        url: 'https://api.spotify.com/v1/me',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        }
    });
}

function getToken(){

    return window.token;
}


//intilizies that browser has a spotify device
window.onSpotifyWebPlaybackSDKReady = () => {
    const player = new Spotify.Player({
        name: 'Jukbox',
        getOAuthToken: cb => { cb(window.token); }
    });

    // Error handling
    player.addListener('initialization_error', ({ message }) => { console.error(message); });
    player.addListener('authentication_error', ({ message }) => { console.error(message); });
    player.addListener('account_error', ({ message }) => { console.error(message); });
    player.addListener('playback_error', ({ message }) => { console.error(message); });

    // Playback status updates
    player.addListener('player_state_changed', state => { console.log(state); });

    // Ready
    player.addListener('ready', ({ device_id }) => {
        console.log('Ready with Device ID', device_id);
    });

    // Not Ready
    player.addListener('not_ready', ({ device_id }) => {
        console.log('Device ID has gone offline', device_id);
    });

    // Connect to the player!
    player.connect();
};