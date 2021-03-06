/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import iaau.uims.jdbc.dao.InformationDiplomaDAO;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.InformationDiploma;
import iaau.uims.json.model.ModelDiplomaInformation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class GetDiplomaInformationRequest extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private Statement statement;

    List<ModelDiplomaInformation> reqList = new ArrayList<ModelDiplomaInformation>();

    public GetDiplomaInformationRequest() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), reqList);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Taking request parameter
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String params = "";
        params = br.readLine();
        
        // Signing taken parameter to equivalent objects
        ModelDiplomaInformation members;
        members = mapper.readValue(params, ModelDiplomaInformation.class);

        // Setting Response type
        response.setContentType("application/json");

        // Adding signed parameter objects into list
        reqList.add(members);
        int listSize = reqList.size() - 1;

        // Informing admin about added signed parameters from list
        String user_idnumber = reqList.get(listSize).getIdnumber();
        String middlename = reqList.get(listSize).getMiddlename();
        String fullname = reqList.get(listSize).getFullname_ru();
        String phone = reqList.get(listSize).getPhone_number();
        String passport = reqList.get(listSize).getPassport_no();
        String address = reqList.get(listSize).getCurrent_address();
        String birthday = reqList.get(listSize).getBirthday();
        String graduation = reqList.get(listSize).getYear_graduation();
        String project_en = reqList.get(listSize).getThesis_project_en();
        String project_ru = reqList.get(listSize).getThesis_project_ru();
        String project_kg = reqList.get(listSize).getThesis_project_kg();


        // Printing POST request coming from client
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Requested parameters in list: " + reqList);
        System.out.println("list size: " + reqList.size());
        
        try {
            setInfoDiploma(middlename,
                    fullname,
                    phone,
                    passport,
                    address,
                    birthday,
                    graduation,
                    project_en,
                    project_ru,
                    project_kg,
                    user_idnumber);
            
            Boolean result;
            result = check(user_idnumber).get("server_response").getAsBoolean();
            System.out.println("server_result: " + result);
            
            if (result == true) {
                mapper.writeValueAsString("accepted");
                System.out.println("User Diploma Information form is accepted");
                System.out.println("-------------------------------------------------------------------------------");
                mapper.writeValue(response.getOutputStream(), result);
            } else if (result == false) {
                mapper.writeValueAsString("rejected");
                System.out.println("User Diploma Information form is rejected");
                System.out.println("-------------------------------------------------------------------------------");
                mapper.writeValue(response.getOutputStream(), result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetDiplomaInformationRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(GetDiplomaInformationRequest.class.getName()).log(Level.SEVERE, null, io);
        }
        
        
    }
    
    private static JsonObject check(String idNumber)
    {
        try {
            
            InformationDiplomaDAO dao = new InformationDiplomaDAO();
//            InformationDiploma model = dao.getInfoDiplom(idNumber,middlename_param,fullname_param,phone_param,passport_param,address_param,birthday_param,graduation_param,project_en_param,project_ru_param,project_kg_param);
            InformationDiploma model = dao.getInfoDiplomaByIDnumber(idNumber);
            
            JsonObject response = new JsonObject();
            Gson gsonResponse = new GsonBuilder().create();
            
            if( model != null )
            {
                response.addProperty("server_response", Boolean.TRUE);
                gsonResponse.toJson(response);
                System.out.println(response);
                return response;
            } else if( model == null ) {
                response.addProperty("server_response", Boolean.FALSE);
                gsonResponse.toJson(response);
                System.out.println(response);
                return response;
            } else {
                System.out.println("No such User with ID number : " + idNumber);
            }      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("nothing checked");
        return null;
    }
    
    
    public ModelDiplomaInformation setInfoDiploma(String middlename_param, 
            String fullname_param,
            String phone_param,
            String passport_param,
            String address_param,
            String birthday_param,
            String graduation_param,
            String project_en_param,
            String project_ru_param,
            String project_kg_param,
            String idNumber_param) throws SQLException
    {
        
        /*
        middlename	fullname_ru	current_address                             passport_no     birthday        phone_number	thesis_project_en                   thesis_project_ru                           thesis_project_kg                   year_of_school_graduation       USERS_idnumber
            -           Чагры Чакыр	Боконбаева улица \ дом 8 \ квартира 76      S 01244996      08 Tem 199      +996700871990	UIMS-Mobile Android Application     UIMS-Мобильных приложений для Android	UIMS-Android мобилдик тузулуш       2006-01-01                      08010101865
        */
        String query = "INSERT INTO "
                + "dbuims.information_diploma "
                + "(middlename, "
                + "fullname_ru, "
                + "current_address, "
                + "passport_no, "
                + "birthday, "
                + "phone_number, "
                + "thesis_project_en, "
                + "thesis_project_ru, "
                + "thesis_project_kg, "
                + "year_of_school_graduation, "
                + "`USERS_idnumber`) "
                + "	VALUES ("
                + "'" + middlename_param + "'" + "," 
                + "'" + fullname_param + "'" + "," 
                + "'" + address_param + "'" + "," 
                + "'" + passport_param + "'" + "," 
                + "'" + birthday_param + "'" + "," 
                + "'" + phone_param + "'" + "," 
                + "'" + project_en_param + "'" + "," 
                + "'" + project_ru_param + "'" + "," 
                + "'" + project_kg_param + "'" + "," 
                + "'" + graduation_param + "'" + "," 
                + "'" + idNumber_param + "'" + ")";
        
        ResultSet rs = null;
        ModelDiplomaInformation field = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(query);

//            if (rs.next()) {
//                field = new ModelDiplomaInformation();
//
//                field.setMiddlename(rs.getString("middlename"));
//                field.setFullname_ru(rs.getString("fullname_ru"));
//                field.setCurrent_address(rs.getString("current_address"));
//                field.setPassport_no(rs.getString("passport_no"));
//                field.setBirthday(rs.getString("birthday"));
//                field.setPhone_number(rs.getString("phone_number"));
//                field.setThesis_project_en(rs.getString("thesis_project_en"));
//                field.setThesis_project_ru(rs.getString("thesis_project_ru"));
//                field.setThesis_project_kg(rs.getString("thesis_project_kg"));
//                field.setYear_graduation(rs.getString("year_of_school_graduation"));
//                field.setIdnumber(rs.getString("USERS_idnumber"));
//            }
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return field;
    }

    
}
