import apps.DbConnect;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;

@WebServlet(name = "ConfirmSubmission",urlPatterns = {"/ConfirmSubmission"})
public class ConfirmSubmission extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String Email=request.getParameter("email");
        String Username=request.getParameter("username");
        String TextTitle=request.getParameter("text");
        String PicDesc=request.getParameter("picDesc");
        String VidDesc=request.getParameter("vidDesc");
        String ImageC=request.getParameter("imageC");
        String ImageB=request.getParameter("imageB");
        String video=request.getParameter("video");
        String colorB =request.getParameter("colorB");
        String colorT =request.getParameter("colorT");

        DbConnect db = new DbConnect();
        if (db.nexists(Email)) {
            db.setData(Email, Username, TextTitle, PicDesc, VidDesc, ImageC, ImageB, video, colorB, colorT);
            response.sendRedirect("starter.jsp");
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("<h1>Email already in queue!</h1>");
        }
    }
}
