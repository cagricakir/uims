/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model;

import java.util.Date;

/**
 *
 * @author Çağrı Çakır
 */
public class InformationDiploma {
    
    
    private String USERS_idnumber;
    private String middlename;
    private String fullname_ru;
    private String current_address;
    private String passport_no;
    private String birthday;
    private String phone_number;
    private String thesis_project_en;
    private String thesis_project_ru;
    private String thesis_project_kg;
    private String year_of_school_graduation;

    public InformationDiploma() {
    }

    public InformationDiploma(String USERS_idnumber, String middlename, String fullname_ru, String current_address, String passport_no, String birthday, String phone_number, String thesis_project_en, String thesis_project_ru, String thesis_project_kg, String year_of_school_graduation) {
        this.USERS_idnumber = USERS_idnumber;
        this.middlename = middlename;
        this.fullname_ru = fullname_ru;
        this.current_address = current_address;
        this.passport_no = passport_no;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.thesis_project_en = thesis_project_en;
        this.thesis_project_ru = thesis_project_ru;
        this.thesis_project_kg = thesis_project_kg;
        this.year_of_school_graduation = year_of_school_graduation;
    }
   
    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFullname_ru() {
        return fullname_ru;
    }

    public void setFullname_ru(String fullname_ru) {
        this.fullname_ru = fullname_ru;
    }

    public String getCurrent_address() {
        return current_address;
    }

    public void setCurrent_address(String current_address) {
        this.current_address = current_address;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getThesis_project_en() {
        return thesis_project_en;
    }

    public void setThesis_project_en(String thesis_project_en) {
        this.thesis_project_en = thesis_project_en;
    }

    public String getThesis_project_ru() {
        return thesis_project_ru;
    }

    public void setThesis_project_ru(String thesis_project_ru) {
        this.thesis_project_ru = thesis_project_ru;
    }

    public String getThesis_project_kg() {
        return thesis_project_kg;
    }

    public void setThesis_project_kg(String thesis_project_kg) {
        this.thesis_project_kg = thesis_project_kg;
    }

    public String getYear_of_school_graduation() {
        return year_of_school_graduation;
    }

    public void setYear_of_school_graduation(String year_of_school_graduation) {
        this.year_of_school_graduation = year_of_school_graduation;
    }

    @Override
    public String toString() {
        return "InformationDiploma [USERS_idnumber=" + USERS_idnumber + ", middlename=" + middlename + ", fullname_ru=" + fullname_ru + ", current_address=" + current_address + ", passport_no=" + passport_no + ", birthday=" + birthday + ", phone_number=" + phone_number + ", thesis_project_en=" + thesis_project_en + ", thesis_project_ru=" + thesis_project_ru + ", thesis_project_kg=" + thesis_project_kg + ", year_of_school_graduation=" + year_of_school_graduation + "]";
    }

    
    
}
