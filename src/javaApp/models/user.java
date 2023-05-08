package src.javaApp.models;

public class user {
    private String email;
    private String lastName;
    private String firstName;
    private String[] addresses;

    public String getEmail(){
        return email;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String[] getAddresses(){
        return addresses;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public  void setLastName(String ln){
        lastName = ln;
    }
    public void setFirstName(String fn){
        firstName = fn;
    }
    public void setAddresses(String[] addresses){
        this.addresses = addresses;
    }
}
