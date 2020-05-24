const searchBar = document.getElementById('searchBar');

searchBar.addEventListener('keyup',(e)=>{
    console.log(e.target.value);
    const val= e.target.value;
    if(val.length>1){
    searchFunction(val);
    }

});



//used in the event listner for api call
function searchFunction(e){

    console.log('e='+e);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/token",
        dataType: "text",
        success: function(response){
            //console.log(response);
            window.token = response;
        }
    });

    var params = {
        q:e,
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
        }

    });
}





