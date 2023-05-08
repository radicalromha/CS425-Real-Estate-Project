package src.javaApp.models;

import javafx.beans.property.SimpleStringProperty;

public class booking {
    
    private final SimpleStringProperty address;
    private final SimpleStringProperty email;
    private final SimpleStringProperty card;

    public booking(String email, String address, String card){
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.card = new SimpleStringProperty(card);
    }

    public String getEmail(){
        return email.get();
    }

    public String getCard(){
        return card.get();
    }

    public String getAddress(){
        return address.get();
    }

    public void setEmail(String s){
       email.set(s);
    }

    public void setAddress(String s){
        address.set(s);
    }

    public void setCard(String s){
        card.set(s);
    }

    public String toString(){
        return "Address: " + getAddress() + ", Email: " + getEmail() + ", Card: " + getCard();
    }
}
