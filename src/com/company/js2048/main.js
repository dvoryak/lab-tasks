document.addEventListener("DOMContentLoaded",function() {
    var x = 0;
    var y = 0;
    document.addEventListener("keydown", function (e) {


        console.log(e);

        if(e.key === "ArrowLeft") {
            x = x - 5;
            document.querySelector(".cell").style = "left : " + x + "vh;" + "top : " + y + "vh;";
        }
        if(e.key === "ArrowRight") {
            x = x + 5;
            document.querySelector(".cell").style = "left : " + x + "vh;" + "top : " + y + "vh;";
        }
        if(e.key === "ArrowUp") {
            y = y - 5;
            document.querySelector(".cell").style = "left : " + x + "vh;" + "top : " + y + "vh;";
        }
        if(e.key === "ArrowDown") {
            y = y + 5;
            document.querySelector(".cell").style = "left : " + x + "vh;" + "top : " + y + "vh;";
        }

    });


});