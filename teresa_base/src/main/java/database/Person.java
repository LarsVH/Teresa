package database;

import javax.persistence.*;

/**
 * @author Jari Van Melckebeke
 */

@Entity
@Table(name = "persons")
public class Person implements java.io.Serializable {
    @Id @GeneratedValue
    @Column(name = "person_id")
    private int personId;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_surname")
    private String personSurname;

    @Column(name = "person_gender")
    private char personGender;

    @Column(name = "person_email")
    private String personEmail;

    @Column(name = "birthday_day")
    private int birthdayDay;

    @Column(name = "birthday_month")
    private int birthdayMonth;

    @Column(name = "birthday_year")
    private int birthdayYear;


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public char getPersonGender() {
        return personGender;
    }

    public void setPersonGender(char personGender) {
        this.personGender = personGender;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public int getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(int birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public int getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(int birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public int getBirthdayYear() {
        return birthdayYear;

    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }
}
