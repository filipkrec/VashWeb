package apps;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DbConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    public DbConnect(){
        String Url = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=QueueDB;user=VashWeb;password=Metallica636;";
        try {
            con = DriverManager.getConnection(Url);
            st = con.createStatement();
        } catch (Exception e) {
        }
    }
    public void setData(String Email, String Username,
                        String TextTitle, String PicDesc,
                        String VidDesc, String ImageC,String ImageB,
                        String video,String colorB,String colorT) {
        try {
            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO Queue " +
                            "Values (?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1,getIndex());
            pst.setString(2,Email);
            pst.setString(3,Username);
            pst.setString(4,TextTitle);
            pst.setString(5,PicDesc);
            pst.setString(6,VidDesc);
            pst.setString(7,ImageB);
            pst.setString(8,ImageC);
            pst.setString(9,video);
            pst.setString(10,colorB);
            pst.setString(11,colorT);
            pst.setLong(12,timeCalc());
            pst.execute();
        }
        catch(Exception ex){System.out.println(ex);}
    }

    private int getIndex(){
        try {
            ResultSet rst;
            rst = st.executeQuery("SELECT MAX(UserID) AS Last FROM Queue");
            rst.next();
            return (rst.getInt("Last") + 1);
        }
        catch(Exception ex){System.out.println(ex);}
        return -1;
    }
    public int getCurrent(){
        try{
            ResultSet rst;
            rst = st.executeQuery("SELECT * FROM [QueueDB].[dbo].[Current]");
            rst.next();
            return (rst.getInt("CurrentID"));
        }
        catch(Exception ex){System.out.println(ex);}
        return -1;
    }
    public boolean checkCurrent(){
        try{
            ResultSet rst = st.executeQuery("SELECT * FROM [QueueDB].[dbo].[Current]");
            rst.next();
            int y = rst.getInt("CurrentID");
            try {
                rst = st.executeQuery("SELECT * FROM Queue " +
                        "WHERE UserID=" + y);
            }
            catch(Exception ex){return false;}
            return true;
        }
        catch(Exception ex){System.out.println(ex);}
        return false;
    }
    public void setCurrent(){
        try {
            ResultSet rst;
            rst = st.executeQuery("SELECT * FROM [QueueDB].[dbo].[Current]");
            rst.next();
            int banana = rst.getInt("CurrentID");
            ++banana;
            PreparedStatement pstx = con.prepareStatement(
                    "UPDATE [QueueDB].[dbo].[Current] SET CurrentID = ? ");
            pstx.setInt(1,banana);
            pstx.execute();
        }
        catch(Exception ex){System.out.println(ex);}
    }


        public QueueValues[] getValues(){
        try{
            ResultSet rst;
            rst = st.executeQuery("SELECT * FROM Queue " +
                    "WHERE UserID =" + getCurrent());
            QueueValues[] QV = new QueueValues[12];
            rst.next();
            QV[0] = new QueueValues(null,rst.getInt("UserID"),0);
            QV[1] = new QueueValues(rst.getString("Email"),0,0);
            QV[2] = new QueueValues(rst.getString("Username"),0,0);
            QV[3] = new QueueValues(rst.getString("TextTitle"),0,0);
            QV[4] = new QueueValues(rst.getString("PicDesc"),0,0);
            QV[5] = new QueueValues(rst.getString("VidDesc"),0,0);
            QV[6] = new QueueValues(rst.getString("ImageB"),0,0);
            QV[7] = new QueueValues(rst.getString("ImageC"),0,0);
            QV[8] = new QueueValues(rst.getString("video"),0,0);
            QV[9] = new QueueValues(rst.getString("colorB"),0,0);
            QV[10] = new QueueValues(rst.getString("colorT"),0,0);
            QV[11] = new QueueValues("non",0,rst.getLong("ReservedTime"));
            return QV;
        }
        catch(Exception ex){System.out.println(ex);}
        return null;
    }

    private long timeCalc(){
            try {
                ResultSet rst;
                rst = st.executeQuery("SELECT MAX(UserID) As Last FROM Queue");
                rst.next();
                int x = rst.getInt("Last");
                rst = st.executeQuery("SELECT * FROM [QueueDB].[dbo].[Current]");
                rst.next();
                int y = rst.getInt("CurrentID");
               int queueL = x-y;
               int reservedTime;
               java.util.Date date = new java.util.Date();
               if(queueL < 10) {
                   reservedTime=(24*60);
               }
               else if(queueL < 20) {
                   reservedTime= (12 * 60);
               }
               else if(queueL < 40){
                   reservedTime= (6*60);
               }
               else if(queueL < 80){
                   reservedTime= (3*60);
               }
               else if(queueL < 180){
                   reservedTime= 90;
               }
               else reservedTime= 30;
               rst = st.executeQuery("SELECT ReservedTime FROM Queue WHERE UserID =" + x);
               rst.next();
               java.util.Date newDate = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(reservedTime));
               return newDate.getTime();
            }
            catch(Exception ex){System.out.println(ex);}
            return 0;
    }
    public boolean nexists(String email){
        try {
            ResultSet rst;
            rst = st.executeQuery("SELECT * FROM [QueueDB].[dbo].[Current]");
            rst.next();
            int y = rst.getInt(1);
            rst = st.executeQuery("Select Count(Email) AS Cnt FROM Queue WHERE Email='" + email + "'"
            + " AND UserID >= " + y);
            rst.next();
            if (rst.getInt("Cnt") == 0)
                return true;
        }
        catch(Exception ex){System.out.println(ex);}
        return false;
    }

    public String[] getQueue(){

        String [] Q = new String[50];
        try{
            ResultSet rst;
            rst = st.executeQuery("SELECT * FROM [QueueDB].[dbo].[Current]");
            rst.next();
            int y = rst.getInt(1);
            int i =0;
            rst = st.executeQuery("SELECT Username FROM Queue WHERE UserID >=" + y);
            while(rst.next()){
                if (i == 50) break;
                Q[i] = rst.getString("Username");
                ++i;
            }
            return Q;
        }
        catch(Exception e){}
        return null;
    }

   /* public String hmmm() {
        try {
            rs = st.executeQuery("SELECT * FROM Queue");
            String rezultat = "";
            while (rs.next()) {
                rezultat = rs.getString("Email");
            }
            return rezultat + " Proslo!";
        } catch (Exception ex) {
            return "error";
        }
    }*/
    }



