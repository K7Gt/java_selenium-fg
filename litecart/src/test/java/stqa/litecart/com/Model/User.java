package stqa.litecart.com.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    private String taxId;
    private String companyName;
    private String firstName;
    private String lastName;
    private String addres1;
    private String addres2;
    private String postCode;
    private String city;
    private String country;
    private String state;
    private String email;
    private String phone;
    private boolean newsLetterSubscritption;
    private String desiredPassword;


    public User(String taxId, String companyName, String firstName, String lastName, String addres1, String addres2, String postCode, String city, String country, String state, String email, String phone, boolean newsLetterSubscritption, String desiredPassword) {
        this.taxId = taxId;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addres1 = addres1;
        this.addres2 = addres2;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.state = state;
        this.email = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()).replaceAll("[- :]","") +email;
        this.phone = phone;
        this.newsLetterSubscritption = newsLetterSubscritption;
        this.desiredPassword = desiredPassword;
    }

    //   new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()).replaceAll("[- :]","");


    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddres1() {
        return addres1;
    }

    public void setAddres1(String addres1) {
        this.addres1 = addres1;
    }

    public String getAddres2() {
        return addres2;
    }

    public void setAddres2(String addres2) {
        this.addres2 = addres2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isNewsLetterSubscritption() {
        return newsLetterSubscritption;
    }

    public void setNewsLetterSubscritption(boolean newsLetterSubscritption) {
        this.newsLetterSubscritption = newsLetterSubscritption;
    }

    public String getDesiredPassword() {
        return desiredPassword;
    }

    public void setDesiredPassword(String desiredPassword) {
        this.desiredPassword = desiredPassword;
    }
}
