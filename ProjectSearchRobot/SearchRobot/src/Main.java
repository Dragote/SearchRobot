
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final String DBUrl = "jdbc:mysql://localhost:4306/Sites";
    private static final String user = "root";
    private static final String password = "mypass";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please, write the date in format: YYYY-MM-DD");
        System.out.print("Date: ");
        String date = in.next();
        String GetQuery = String.format("Select url FROM DB_Sites where date < '%s' or date is NULL ;", date);
        try {
            con = DriverManager.getConnection(DBUrl, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(GetQuery);
            List<Thread> list = new ArrayList<>();
            while (rs.next()) {
                String gettingUrl = rs.getString("url");
                DoingThread thread = new DoingThread(con,gettingUrl);
                thread.start();
                list.add(thread);
            }

            for (Thread thread : list) {
                thread.join();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
            con.close();
            System.out.println("Done!");

            } catch (SQLException se) { }
            try {
                stmt.close();
            } catch (SQLException se) {  }
            try {
                rs.close();
            } catch (SQLException se) {  }
        }

    }


}
