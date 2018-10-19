<%--
  Created by IntelliJ IDEA.
  User: Fico
  Date: 6/25/2018
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="header.jsp" %>
<div style="${present};background-color: ${colorB};z-index:-1;${display}">
<div class="content">
    <div class="grid-container"
         style="color:${colorT};background-color:${colorB};border-style:solid;border-color:${colorT};">
        <div id="grid-item1">
            <div id="clockdiv" style="font-size:15px;margin-left:30%;"></div>
            <h1>${text}</h1></div>
        <div id="grid-item2" style="font-size:12px;border-style:solid;border-color:${colorT};">
            <p style="font-size:36px;">Queue</p>${queue}</div>
        <div id="grid-item3">${picDesc}</div>
        <div id="grid-item4"><img src="${imageC}" height="350" width="350"
                                  style="border-style:solid;border-color:${colorT};"></div>
        <div id="grid-item5">${vidDesc}</div>
        <div id="grid-item6">
            <iframe width="360" height="270" src="${video}"></iframe>
        </div>
    </div>
    <input type="button" onclick="submit();" value="Submit" class="subbutton" name="subbutton"
           style="width:100px;height:25px;margin-left:45%;">
</div>
</div>
    <form id="form" style="display:none;" method="POST" action="ConfirmSubmission">
        <input type="text" name="username" value=${name}>
        <input type="text" name="email" value="${email}">
        <input type="text" name="text" value="${text}">
        <input type="text" name="colorT" value="${colorT}">
        <input type="text" name="colorB" value="${colorB}">
        <input type="text" value="${present}" name="imageB">
        <input type="text" value="${imageC}" name="imageC">
        <input type="text" name="video" value="${video}">
        <input type="text" name="vidDesc" value="${vidDesc}">
        <input type="text" name="picDesc" value="${picDesc}">
    </form>
    <script>
        function submit(){
            document.getElementById("form").submit();
        }
    function title(){
    document.getElementById("title").innerHTML = "Preview";}
    window.onload = title();
    </script>
    </body>
    </html>

