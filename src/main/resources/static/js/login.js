function testF() {
    $(document).ready(function () {
        $("Form.one").on("submit", function () {

            console.log("console log working");
            var address= getExactAddress();
            console.log(address);

            var that = $(this), //that reference current object we are working with
                //url = that.attr('action'),
                //type = that.attr('method'),
                data = {};

            that.find("[name]").each(function (index, value) {
                var that = $(this),
                    name = that.attr("name"),
                    value = that.val();
                data[name] = value;
            });
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: address+"/createRoom",
                data: JSON.stringify(data),
                success: function (response) {
                    console.log("success");

                    //spotify login if post is successful
                    var params = {
                        response_type: "code",
                        client_id: "b1e9cb8d5176473fb39f5e7aca4eaae9",
                        redirect_uri: address+"/Room",
                        scope: "streaming user-read-email user-read-private user-modify-playback-state user-read-playback-state",
                        show_dialog: "true"
                    };
                    window.location = 'https://accounts.spotify.com/authorize?' +
                        $.param(params);


                },
                error: function (jqXhr, textStatus, errorMessage) {
                    console.log("error" + errorMessage);
                }
            });
            return false;
        });
    });


    $(document).ready(function () {
        $("Form.two").on("submit", function () {
             var address= getExactAddress();

            var that = $(this), //that reference current object we are working with
                //url = that.attr('action'),
                //type = that.attr('method'),
                data = {};

            that.find("[name]").each(function (index, value) {
                var that = $(this),
                    name = that.attr("name"),
                    value = that.val();
                data[name] = value;
            });

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: address+"/joiningRoom",
                data: JSON.stringify(data),
                success: function (response) {
                    console.log("success");
                    window.location.replace(address+"/JoinRoom");
                },
                error: function (jqXhr, textStatus, errorMessage) {
                    console.log("error" + errorMessage);
                    alert("Looks like the room password you entered does not exist :( ");
                }
            });
            return false;
        });
    });

}