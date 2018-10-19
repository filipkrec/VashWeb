import apps.DbConnect;
import apps.QueueValues;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Switch",urlPatterns = {"/Switch"})
public class Switch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            try {
                if(request.getParameter("flag").equals("1")) {
                    DbConnect db = new DbConnect();
                    QueueValues [] QV = db.getValues();
                    Date sada = new Date();
                    if(QV[11].getLong() < sada.getTime()){
                    if(db.checkCurrent()) db.setCurrent();}
                    String switched = "switched";
                    request.setAttribute("switchflag", switched);
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/Grab");
                    rd.forward(request, response);
                }
                else {PrintWriter out = response.getWriter();
                out.println(" JEBEMTI BOGA");}
            } catch (Exception e) {
                PrintWriter out = response.getWriter();
                out.println("Error");
            }
        }
    }
}
