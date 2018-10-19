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
import java.util.concurrent.TimeUnit;

@WebServlet(name = "Grab",urlPatterns = {"/Grab"})
public class Grab extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            QueueValues[] QV = new QueueValues[12];
            DbConnect db = new DbConnect();
            QV = db.getValues();
            String picDesc = QV[4].getString();
            String vidDesc = QV[5].getString();
            String username = QV[2].getString();
            String text = QV[3].getString();
            String colorT = QV[10].getString();
            String colorB = QV[9].getString();
            String imageB = QV[6].getString();
            String imageC = QV[7].getString();
            String video = QV[8].getString();
            long time = QV[11].getLong();
            String timeS = String.valueOf(time);
            String grabbed = "grabbed";


            String[] queue = db.getQueue();
            String queuestring = "";
            for(int i =0;queue[i]!=null;++i)
            {
                queuestring += (i+1) + ". " + queue[i] + "<br/>";
            }

            ServletContext context = getServletContext();
            request.setAttribute("queue",queuestring);
            request.setAttribute("imageB", imageB);
            request.setAttribute("imageC", imageC);
            request.setAttribute("name", username);
            request.setAttribute("text", text);
            request.setAttribute("colorT", colorT);
            request.setAttribute("colorB", colorB);
            request.setAttribute("video",video);
            request.setAttribute("picDesc", picDesc);
            request.setAttribute("vidDesc",vidDesc);
            request.setAttribute("time",timeS);
            request.setAttribute("grabflag",grabbed);
            RequestDispatcher rd = context.getRequestDispatcher("/starter.jsp");
            rd.forward(request, response);
        }
        catch(Exception e){
            ServletContext context = getServletContext();
            try{
            if(request.getAttribute("switchflag").equals("switched"));
            {
                String switched = "switched";
                request.setAttribute("switchflag",switched);
            }
            } catch(Exception E){}
            String grab = "grabbed";
            request.setAttribute("display","display:none;");
            request.setAttribute("grabflag",grab);
            RequestDispatcher rd = context.getRequestDispatcher("/starter.jsp");
            rd.forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
