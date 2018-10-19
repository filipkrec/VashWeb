import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.io.File;

@WebServlet(name = "PreviewServlet",urlPatterns = {"/Preview"})
public class PreviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory itemfactory = new DiskFileItemFactory();
        itemfactory.setSizeThreshold(400000000);
        ServletFileUpload upload = new ServletFileUpload(itemfactory);
        PrintWriter out = response.getWriter();
        ArrayList<String> errors = new ArrayList<>();
        HashMap<String, String> mapa = new HashMap<>();
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    mapa.put(name, value);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            out.println("Itemwrite fail");
        }
        String email = mapa.get("email");
        String picDesc = mapa.get("picDesc");
        String vidDesc = mapa.get("vidDesc");
        String username = mapa.get("name");
        String kod = mapa.get("code");
        String recoded = mapa.get("rcd");
        String text = mapa.get("text");
        String colorT = mapa.get("colorT");
        String colorB = mapa.get("colorB");
        String imageB = mapa.get("imageB");
        String imageC = mapa.get("imageC");
        String video = mapa.get("video");
        if(email == null || username == null || colorT == null || colorB ==null || imageB == null || imageC == null
                || video == null){
            errors.add("Empty important field,fill username/colors/images/video!");
        }
        if(video != null){ video = convertVid(video);
        if(video.equals("error")) { errors.add("<h1>Video Error</h1>");}}
        String presentation = mapa.get("present");
        if (recode(kod) != Integer.parseInt(recoded)) errors.add("<h1>Wrong code!</h1>");
        if (username == null) errors.add("<h1>No username</h1>");
        if (errors.size() == 0) {
            ServletContext context = getServletContext();
            request.setAttribute("imageB", imageB);
            request.setAttribute("imageC", imageC);
            request.setAttribute("name", username);
            request.setAttribute("text", text);
            request.setAttribute("colorT", colorT);
            request.setAttribute("colorB", colorB);
            request.setAttribute("video",video);
            request.setAttribute("picDesc", picDesc);
            request.setAttribute("vidDesc",vidDesc);
            request.setAttribute("email",email);
            if (imageB != null && presentation.equals("stretch")) {
                presentation =
                        "background-image: url('" + imageB + "'); background-repeat: no-repeat;" +
                "background-size:100% 100%;";
            } else if (presentation.equals("tile")) {
                presentation =
                        "background-image: url('" + imageB + "'); background-size:100px 100px;" +
                                "background-repeat: repeat";
            } else
                presentation = "background-color:" + colorB;
            request.setAttribute("present", presentation);
            RequestDispatcher rd = context.getRequestDispatcher("/Preview.jsp");
            rd.forward(request, response);
        } else {
            for (String i : errors) {
                out.println(i);
            }
        }
    }

    private int recode(String kod) {
        int rtrn = 0;
        for (int i = 0; i < kod.length(); i++) {
            rtrn += (int)kod.charAt(i);
        }
        return ((rtrn / 12) * 15 + 123) / 4;
    }

    private String convertVid(String vid){
        if(vid.contains("?v=")){
            String ID=vid.substring(vid.indexOf("?v=")+3,vid.indexOf("?v=")+3+11);
            ID = "https://www.youtube.com/embed/" +
                     ID +"?autoplay=1?&mute=1playlist=" + ID + "&loop=1?&controls=0";
            return ID;
        }
        else return "error";
    }


}
