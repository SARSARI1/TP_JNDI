import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;

@WebServlet("/checkConnection")
public class CheckConnectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection conn = null;

        try {
            // Lookup the data source
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/testdb");
            conn = ds.getConnection(); // Establish the connection

            // Print the success message
            out.println("Connection to the database successful!");

        } catch (NamingException e) {
            out.println("JNDI configuration error: " + e.getMessage()); // Corrected string concatenation

        } catch (Exception e) {
            out.println("Error connecting to database: " + e.getMessage()); // Corrected string concatenation

        } finally {
            // Close the connection if it is established
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                out.println("Error closing connection: " + e.getMessage()); // Moved this line inside the catch block
            }
        }
    }
}