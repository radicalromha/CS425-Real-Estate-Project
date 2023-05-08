package src.javaApp.models;

import javafx.beans.property.*;


public class property {

    private final SimpleDoubleProperty rent_price;
    private final SimpleDoubleProperty sale_price;
    private final SimpleStringProperty address;
    private final SimpleStringProperty city;
    private final SimpleStringProperty state;
    private final SimpleStringProperty prop_type;
    private final SimpleBooleanProperty allows_pets;
    private final SimpleBooleanProperty is_furnished;
    private final SimpleBooleanProperty wheelchair_accessibility;
    private final SimpleIntegerProperty square_footage;
    private final SimpleIntegerProperty number_of_rooms;
    private final SimpleStringProperty apartment_type;
    private final SimpleStringProperty business_type;
    
    public property(Double rent_price, Double sale_price,String address, String city, String state, String propType, boolean pets, boolean furnished, boolean wheelchair, int area, int rooms, String apart, String business) {
        this.rent_price = new SimpleDoubleProperty(rent_price);
        this.sale_price = new SimpleDoubleProperty(sale_price);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.prop_type = new SimpleStringProperty(propType);
        this.allows_pets = new SimpleBooleanProperty(pets);
        this.is_furnished = new SimpleBooleanProperty(furnished);
        this.wheelchair_accessibility = new SimpleBooleanProperty(wheelchair);
        this.square_footage = new SimpleIntegerProperty(area);
        this.number_of_rooms = new SimpleIntegerProperty(rooms);
        this.apartment_type = new SimpleStringProperty(apart);
        this.business_type = new SimpleStringProperty(business);
    }
    public Double getRentPrice(){
        return rent_price.get();
    }

    public Double getSalePrice(){
        return sale_price.get();
    }
    public String getAddress(){
        return address.get();
    }

    public String getCity(){
        return city.get();
    }
    public String getState(){
        return state.get();
    }
    public String getPropType(){
        return prop_type.get();
    }
    
    public StringProperty getPropTypeProperty(){
        return prop_type;
    }

    public Boolean getPets(){
        return allows_pets.get();
    }

    public BooleanProperty getPetsProperty(){
        return allows_pets;
    }

    public Boolean getFurnish(){
        return is_furnished.get();
    }
    public BooleanProperty getFurnishProperty(){
        return is_furnished;
    }

    public Boolean getWheelchair(){
        return wheelchair_accessibility.get();
    }
    public Integer getArea(){
        return square_footage.get();
    }
    public Integer getRooms(){
        return number_of_rooms.get();
    }
    public String getApartType(){
        return apartment_type.get();
    }
    public String getBusType(){
        return business_type.get();
    }

    //setters
    public void setAddress(String s){
        address.set(s);
    }
    public void setCity(String s){
        city.set(s);
    }
    public void setState(String s){
        state.set(s);
    }
    public void setPropType(String s){
        prop_type.set(s);
    }
    public void setPets(Boolean s){
        allows_pets.set(s);
    }
    public void setFurnish(Boolean s){
        is_furnished.set(s);
    }
    public void setWheelchair(Boolean s){
        wheelchair_accessibility.set(s);
    }
    public void setArea(Integer s){
        square_footage.set(s);
    }
    public void setRooms(Integer s){
        number_of_rooms.set(s);
    }
    public void setApartType(String s){
        apartment_type.set(s);
    }
    public void setBusType(String s){
        business_type.set(s);
    }

    public void setRent(Double s){
        rent_price.set(s);
    }

    public void setSale(Double s){
        sale_price.set(s);
    }
    
    public String toString(){
        return "Rent Price: "+ getRentPrice() + ", Sale Price: " + getSalePrice() + ", Address: " + getAddress() + ", City: " + getCity() + ", State: " + getState() + ",Property Type: " + getPropType() + ", Pets?: " + getPets() + ", Furnished?: " + getFurnish() + ", Wheelchair Accessible?: " + getWheelchair() + ", ApartmentType: " + getApartType() + ", Business Type: " + getBusType();
    }
}
