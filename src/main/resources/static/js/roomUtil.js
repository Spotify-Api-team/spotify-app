const memberList = document.getElementById('memberList');
document.getElementById('memberList').style.visibility= "hidden";

var intervalID = window.setInterval(getMembers, 15000);


function getMembers(){

    $.ajax({
            type: "GET",
            url: "http://localhost:8080/getMembers",
            contentType: "application/Json",
            success: function (response) {
                console.log("got the members");
                console.log(response);
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

