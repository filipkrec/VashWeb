package apps;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DBTester {
    public static void main(String[] args) {
            Connection con;
            Statement st;
            ResultSet rs;
            String Url = "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=QueueDB;user=VashWeb;password=Metallica636;";
            try {
                DbConnect db = new DbConnect();
                QueueValues [] QV = db.getValues();
                Date sada = new Date();
                Date novi = new Date(sada.getTime() + TimeUnit.MINUTES.toMillis(1440));
                if(QV[11].getLong() < sada.getTime()) System.out.println("Vise!");
                else System.out.println("Manje");
            } catch (Exception e) {
            }
    }
}