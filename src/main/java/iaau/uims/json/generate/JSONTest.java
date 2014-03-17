/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */
package iaau.uims.json.generate;

import iaau.uims.json.generate.user.JsonUser;
import iaau.uims.json.generate.apps.JsonApplicationsForms;
import iaau.uims.json.generate.apps.JsonInformationDiploma;
import iaau.uims.json.generate.apps.JsonRegistration;
import iaau.uims.json.generate.myinformation.JsonAccountingStatusInfo;
import iaau.uims.json.generate.myinformation.JsonCurrentInfo;
import iaau.uims.json.generate.myinformation.JsonGeneralInfo;
import iaau.uims.json.generate.success.JsonSuccessReport;
import iaau.uims.json.generate.transcript.JsonTranscript;
import iaau.uims.json.generate.user.JsonUserRole;
import iaau.uims.json.generate.user.JsonUserRolePermission;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Çağrı Çakır
 */
public class JSONTest 
{

    public static void main(String[] args) throws SQLException 
    {
        actionMenu();        
    }
    
    
/////////////////////////////  TEST MENU ACTION   //////////////////////////////       
    private static void actionMenu() throws SQLException{
        int selection;

        showMenu();

        Scanner input = new Scanner(System.in);
        selection = input.nextInt();
        switch (selection) {
            case 1: {
                getUserById();
                iterateAction();
                break;
            }
            case 2: {
                getUserRoleByIDnumber();
                iterateAction();
                break;
            }
            case 3: {
                getPermissionOfUserByIDnumber();
                iterateAction();
                break;
            }
            case 4: {
                getGeneralInfoByIDnumber();
                iterateAction();
                break;
            }
            case 5: {
                getCurrentInfoByIDnumber();
                iterateAction();
                break;
            }
            case 6: {
                getAccountingStatusByIDnumber();
                iterateAction();
                break;
            }
            case 7: {
                getSuccessReportByIDnumber();
                iterateAction();
                break;
            }
            case 8: {
                getTranscriptByIDnumber();
                iterateAction();
                break;
            }
            case 9: {
                getAppsFormsByIDnumber();
                iterateAction();
                break;
            }
            case 10: {
                getInfoDiplomaByIDnumber();
                iterateAction();
                break;
            }
            case 11: {
                getRegistrationByIDnumber();
                iterateAction();
                break;
            }
            case -1: {
                break;
            }
            default: {
                System.out.println("\tWrong option!\n");
                iterateAction();
            }
        }
    }
    private static void showMenu() {
        System.out.println("||||||||||||| Generate JSON |||||||||||||||| ");
        System.out.println("|1|  User Login ");
        System.out.println("|2| User role");
        System.out.println("|3| User Role permission");
        System.out.println("|4| General Information");
        System.out.println("|5| Current Information");
        System.out.println("|6| Accounting Status Information");
        System.out.println("|7| Success Report");
        System.out.println("|8| Transcript");
        System.out.println("|9| Applications & Forms info");
        System.out.println("|10| Information of Diploma");
        System.out.println("|11| Registration Info");
        System.out.println("|-1| EXIT ");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
    }
    private static void iterateAction() throws SQLException {
        System.out.println("\nContinue(c) or Exit(-1)");
        Scanner def = new Scanner(System.in);
        String default_selection = def.nextLine();
        if ("c".equals(default_selection)) {
            actionMenu();
        } else {
            System.exit(0);
        }
    }
////////////////////////////////////////////////////////////////////////////////
    
/////////////////////////////  DISPLAYING JSON   ///////////////////////////////
    private static void getUserById() throws SQLException {
        JsonUser user = new JsonUser();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        user.GenerateUserAsJson(a);
    }
    private static void getUserRoleByIDnumber() throws SQLException {
        JsonUserRole userrole = new JsonUserRole();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        userrole.GenerateUserRoleAsJson(a);
    }
    private static void getPermissionOfUserByIDnumber() throws SQLException {
        JsonUserRolePermission perm = new JsonUserRolePermission();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        perm.GenerateUserRolePermAsJson(a);
    }
    private static void getGeneralInfoByIDnumber() throws SQLException {
        JsonGeneralInfo general_info = new JsonGeneralInfo();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        general_info.GenerateGeneralInfoAsJson(a);
    }
    private static void getCurrentInfoByIDnumber() throws SQLException {
        JsonCurrentInfo current_info = new JsonCurrentInfo();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        current_info.GenerateCurrentInfoAsJson(a);
    }
    private static void getAccountingStatusByIDnumber() throws SQLException {
        JsonAccountingStatusInfo account = new JsonAccountingStatusInfo();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        account.GenerateAccountingStatusInfoAsJson(a);
    }
    private static void getSuccessReportByIDnumber() throws SQLException {
        JsonSuccessReport success = new JsonSuccessReport();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        success.GenerateSuccessReportAsJson(a);
    }
    private static void getTranscriptByIDnumber() throws SQLException {
        JsonTranscript script = new JsonTranscript();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        script.GenerateTranscriptAsJson(a);
    }
    private static void getAppsFormsByIDnumber() throws SQLException {
        JsonApplicationsForms app = new JsonApplicationsForms();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        app.GenerateAppsFormsAsJson(a);
    }
    private static void getInfoDiplomaByIDnumber() throws SQLException {
        JsonInformationDiploma diplom = new JsonInformationDiploma();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        diplom.GenerateDiplomaInfoAsJson(a);
    }
    private static void getRegistrationByIDnumber() throws SQLException {
        JsonRegistration reg = new JsonRegistration();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        reg.GenerateRegistrationAsJson(a);
    }
////////////////////////////////////////////////////////////////////////////////
}
