import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class DoingThread extends Thread {

    private Connection con;
    private Statement stmt;
    private String gettingUrl;

    public DoingThread(Connection con,String gettingUrl) {
        this.con=con;
        this.gettingUrl = gettingUrl;
    }

    @Override
    public void run() {
        try {
                try {
                    URL url = new URL(gettingUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int status = connection.getResponseCode();
                    String UpdateQuery = String.format("UPDATE DB_Sites SET status = %s, `date`=CURRENT_DATE where url='%s';", status, gettingUrl);
                    stmt = con.createStatement();
                    stmt.executeUpdate(UpdateQuery);
                    System.out.println("Thread: " + Thread.currentThread().getName() + " checked: " + gettingUrl +";");

                } catch (final MalformedURLException e) {
                } catch (final IOException e) {
                    String UpdateQuery = String.format("UPDATE DB_Sites SET status = %s, `date`=CURRENT_DATE where url='%s';", null, gettingUrl);
                    stmt = con.createStatement();
                    stmt.executeUpdate(UpdateQuery);
                    System.out.println("Thread: " + Thread.currentThread().getName() + " checked: " + gettingUrl +";");
                }

            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException se) {  }
        }
    }
}
