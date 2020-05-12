
//un used as of now
$(document).ready(function() {
    $("Form.test").on("submit", function () {
        console.log("working");
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
            url: "http://localhost:8080/createRoom",
            dataType: "json",
            data: JSON.stringify(data)
        });

        return false;
    });
});