<%--
  Created by IntelliJ IDEA.
  User: Fico
  Date: 6/18/2018
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<body>
<section id="contact" class="container dark second">
    <div class="content">
        <div id="contact-form">
            <form id="submitForm" method="POST" action="Preview" enctype="multipart/form-data">
                <div class="column-half">
                    <div class="formblock">
                        Email: &nbsp ${email}
                        <input type="text" style="display:none;" name="rcd" value="${recoded}">
                    </div>
                    <div class="formblock">
                        Code:&nbsp &nbsp <input type="text" name="code" class="txt2 requiredField"></br>
                    </div>
                    <div class="formblock">
                        Display name:<input type="text" name="name" class="txt requiredField "><br>
                    </div>
                    <div class="formblock">
                        Text Title:&nbsp &nbsp <input type="text" name="text" class="txt requiredField"></br>
                    </div>
                    <div class="formblock">
                        Image Text:<br/><textarea rows="4" cols="5" name="picDesc" form="submitForm"
                                            class="txtarea"></textarea><br/>
                    </div>
                    <div class="formblock">
                        Video Text:<br/><textarea rows="4" cols="5" name="vidDesc" form="submitForm"
                                            class="txtarea"></textarea><br/>
                    </div>
                    <div class="formblock">
                        Color:<br/><br/>
                        <table style="width:370px;vertical-align:middle;horizontal-align:middle;color:white;height:15px;">
                            <tr>
                                <td>Text
                                <td><input type="color" name="colorT" class="color" value="#000000">
                                <td>Background
                                <td><input type="color" name="colorB" class="color" value="#FFFFFF">
                            </tr>
                        </table>
                    </div>
                    <table style="width:500px;vertical-align:bottom;horizontal-align:middle;color:white;height:155px;">
                        <tr>
                            <td>Background image Link: &nbsp</td>
                            <td>
                                <div class="fileinputs">
                                    <input type="text" name="imageB">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Background image presentation: &nbsp</td>
                            <td>
                                <select name="present">
                                    <option value="stretch">Stretch</option>
                                    <option value="tile">Tile</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Center Image Link: &nbsp</td>
                            <td>
                                <input type="text" name="imageC">
                            </td>
                        </tr>
                        <tr>
                            <div class="formblock">
                                <td>Youtube link:</td>
                                <td><input type="text" name="video"></td>
                            </div>
                        </tr>
                    </table>
                    <input type="text" value="${email}" name="email" style="display:none;">
                    <input type="submit" value="Preview" class="subbutton" name="subbutton"
                           style="width:100px;height:25px;">
                </div>
                <div class="column-half last">
                    <img src="Theme/images/examp%20-%20Copy.jpg" style="width:500px;height:282x;">
                </div>
            </form>
        </div>
    </div>
</section>
<script>
    function title() {
        document.getElementById("title").innerHTML = "Create";
    }

    window.onload = title();
</script>
</body>
</html>
</body>
</html>
