<%--
  Created by IntelliJ IDEA.
  User: Fico
  Date: 6/16/2018
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp" %>
<div style="${imageB};background-color: ${colorB};z-index:-1;${display}">
    <div class="content">
        <div class="grid-container" style="color:${colorT};background-color:${colorB};border-style:solid;border-color:${colorT};">
            <div id="grid-item1"><h1>${text}</h1></div>
            <div id="grid-item2" style="font-size:12px;border-style:solid;border-color:${colorT};">
                <div id="clockdiv" style="font-size:15px;"></div><br/>
                <p style="font-size:36px;">Queue</p>${queue}</div>
            <div id="grid-item3">${picDesc}</div>
            <div id="grid-item4"><img src="${imageC}" height="350" width="350"
                                      style="border-style:solid;border-color:${colorT};"></div>
            <div id="grid-item5">${vidDesc}</div>
            <div id="grid-item6"><iframe width="360" height="270" src="${video}"></iframe></div>
        </div>
    </div>
</div>
<form id="switch" method="POST" style="display:none" action="Switch">
    <input type="text" value="0" id="flag" name="flag">
</form>
<form id="grab" name="grab" style="display:none" method="POST" action="Grab"></form>
<script>
    var startDate = new Date(${time});
    function timer(startDate){
        var time = startDate - new Date();
        var seconds = Math.floor( (time/1000) % 60 );
        var minutes = Math.floor( (time/1000/60) % 60 );
        var hours = Math.floor( (time/(1000*60*60)) % 24 );
        return {
            'total': time,
            'hours': hours,
            'minutes': minutes,
            'seconds': seconds
        };
    }

    function initializeClock(id,startDate){
        var clock = document.getElementById(id);
        setInterval(function(){
            var t = timer(startDate);
            clock.innerHTML =
                t.hours + ':' +
                t.minutes + ':' +
                t.seconds;
            if(t.total<=0){
                if("${display}" != "display:none;") {
                    document.getElementById("flag").value = "1";
                    document.getElementById("switch").submit();
                }
            }
        },1000);
    }
    function grab(){
        if("${grabflag}" != "grabbed")
        {
            document.getElementById("grab").submit();
        }
    }
    function title(){document.getElementById("title").innerHTML = "Home";}
    window.onload = title();
    window.onload = grab();
    window.onload = initializeClock('clockdiv',startDate);
</script>
</body>
</html>
