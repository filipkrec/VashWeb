<%--
  Created by IntelliJ IDEA.
  User: Fico
  Date: 6/22/2018
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<body>
<section id="contact" class="container dark second">
        <div class="content">
                <form id="submitForm" method="POST">
                    <div style="margin-left:35%;margin-top:15%">
                        E-mail: <input type="text" name="email" class="txt requiredField email">
                            <input type="submit" name="emailButton" class="mailbutton" value="Validate" formaction="MailConfirm"/>
                        </div>
                </form>
            </div>
    <div class="full"></div>
</section>
<script>
    function title(){
        document.getElementById("title").innerHTML = "Validate";}
    window.onload = title();
</script>
</body>
</html>