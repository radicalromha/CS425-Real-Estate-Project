package src.javaApp.models;

import java.math.BigDecimal;

public class agent extends user {

   private String title;
   private String agency;
   private BigDecimal phone;

    
    public String getTitle(){
        return title;
    }

    public String getAgency(){
        return agency;
    }

    public BigDecimal getPhone(){
        return phone;
    }

    //setters

    public void setTitle(String title){
        this.title = title;
    }
    public void setAgency(String agency){
        this.agency = agency;
    }
    public void setPhoneNumber(BigDecimal number){
        this.phone = number;
    }

    public String toString(){
        return "Name: " + getLastName() + ", " + getFirstName() + ", Agency: " + agency + ", Title: " + title + "Contact: " + phone.toString();
    }
}
