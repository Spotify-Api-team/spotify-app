function searchFunction(){


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            console.log(response);
            window.token = response;
        }
    });

    var params = {
        q:"Wun",
        type:"track",
        limit: "3"
    };

    $.ajax({
        type: "GET",
        url: "https://api.spotify.com/v1/search?"+$.param(params),
        dataType: "json",
        contentType:"application/json",
        headers:{
            "Authorization": "Bearer "+window.token
        },
        success: function(response){
            console.log(response);
            window.location.replace("http://localhost:8080/Search");
        }

    });
}

//in the owner.html page
function getSearchHTML() {
    window.location.replace("http://localhost:8080/Search")
}