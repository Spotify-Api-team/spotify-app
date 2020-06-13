const memberList = document.getElementById('memberList');
document.getElementById('memberList').style.visibility= "hidden";

const imageFooter= document.getElementById('currentSongImg');

var intervalID = window.setInterval(getMembers, 15000);
var intervalID = window.setInterval(getCurrentSong, 1000);


function getMembers(){


    var address= getExactAddress();
    $.ajax({
            type: "GET",
            url: address + "/getMembers",
            contentType: "application/Json",
            success: function (response) {
                //console.log("got the members");
                //console.log(response);
                displayMembers(response);
                document.getElementById('memberList').style.visibility= "visible";
            },
            error: function (jqXhr, textStatus, errorMessage) {
                                console.log("error" + errorMessage);
            }
        });
}



const displayMembers = (members) => {
    const htmlString = members
        .map((member) => {
            return `
            <li class="member">
                <h2>${member.fname}</h2>
            </li>
        `;
        })
        .join('');
    memberList.innerHTML = htmlString;
};


function getCurrentSong(){

    var address= getExactAddress();
    $.ajax({
            type: "GET",
            url: address +"/currentSong",
            contentType: "application/Json",
            success: function (response) {
                //console.log("got the currentSOng");
                //console.log(response);
                displayCurrentSong(response);
            },
            error: function (jqXhr, textStatus, errorMessage) {
                                console.log("error" + errorMessage);
            }
        });
}
function displayCurrentSong(song){

    var x = document.createElement("IMG");
    x.setAttribute("src",src = song.image.url );
    document.getElementById('currentSongImg').src=x.src;
    document.getElementById('currentSong').innerHTML=song.name+'by'+song.artist;
}