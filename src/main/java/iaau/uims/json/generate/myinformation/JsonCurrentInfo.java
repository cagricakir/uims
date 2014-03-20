/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate.myinformation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class JsonCurrentInfo {

    public JsonCurrentInfo() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateCurrentInfoAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT " + "USERS.idnumber, "
                + "CURRENT_INFO.fullname, "
                + "CURRENT_INFO.current_year, "
                + "CURRENT_INFO.current_semester, "
                + "CURRENT_INFO.current_month, "
                + "CURRENT_INFO.current_exam "
                + "FROM USERS "
                + "INNER JOIN CURRENT_INFO "
                + "ON USERS.iduser=CURRENT_INFO.`USERS_iduser` "
                + "WHERE USERS.idnumber=" + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            while (rs.next())
            {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("fullname")));
                row.add(new JsonPrimitive(rs.getString("current_year")));
                row.add(new JsonPrimitive(rs.getString("current_semester")));
                row.add(new JsonPrimitive(rs.getString("current_month")));
                row.add(new JsonPrimitive(rs.getString("current_exam")));
                data.add(row);
            }
            
            jsonResponse.add("jsonCurrentInfo", data);
            System.out.println("JSONArray form: " + data);

            String a = data.toString();
            System.out.println("String form: " + a);
            
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
}
