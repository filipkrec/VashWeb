import apps.DbConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "MailConfirm", urlPatterns = {"/MailConfirm"})
public class MailConfirm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String toEmail = request.getParameter("email");
            DbConnect db = new DbConnect();
            PrintWriter out = response.getWriter();
            if (!emailCheck(toEmail)) {
                out.println("<h1 style=\" text-align:center; \">Invalid Email<h1>");
            }
            else if (!db.nexists(toEmail)){
                out.println("<h1 style=\" text-align:center; \">Email exists in queue!<h1>");
            }
            else {
                String kod = code();
                response.setContentType("text/html;charset=UTF-8");
                String subject = "VashWEB Validation Code";
                String user = "yourmail@gmail.com"; //your email sender gmail account
                String pass = "yourpass"; //matching password
                apps.SendMail.send(toEmail,subject, kod, user, pass);
                request.setAttribute("email",toEmail);
                request.setAttribute("recoded",recode(kod));
                RequestDispatcher rd = request.getRequestDispatcher("Create.jsp");
                rd.forward(request,response);
            }

    }

    private String code() {
        String rtrn = new String("000000");
        char[] rtrnChars = rtrn.toCharArray();
        for (int i = 0; i < 6; i++) {
            Random rn = new Random();
            char c = (char) ((rn.nextInt() % 26) + 97);
            rtrnChars[i] = c;
        }
        rtrn = String.valueOf(rtrnChars);
        return rtrn;
    }

    private int recode(String kod) {
        int rtrn = 0;
        for (int i = 0; i < kod.length(); i++) {
            rtrn += kod.charAt(i);
        }
        return ((rtrn / 12) * 15 + 123) / 4;
    }

    private boolean emailCheck(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        String temp = email.substring(email.indexOf("@"));
        if (!temp.contains(".")) return false;
        return true;
    }
}

