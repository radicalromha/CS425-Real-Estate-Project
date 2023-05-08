package src.javaApp;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import src.javaApp.models.agent;
import src.javaApp.models.booking;
import src.javaApp.models.property;
import src.javaApp.models.renter;

public class jdbcAccessor {


    static String url;
    static String user;
    static String pw;

    //method used to connect to database server
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(url, user, pw);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public jdbcAccessor(){
        url = "jdbc:postgresql://localhost/test";
        user = "postgres";
        pw = "TnT1540$1";
    }

    //create object methods
    public static void createRenter(String email,String lastName, String firstName, ArrayList<String> addresses, java.sql.Date mvDate, String loc, BigDecimal budget, ArrayList<String> cards, boolean reward){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO renter VALUES(?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,email);
            stmt.setString(2,lastName);
            stmt.setString(3,firstName);
            java.sql.Array sqlAddresses = conn.createArrayOf("TEXT", addresses.toArray());
            stmt.setArray(4,sqlAddresses);
            stmt.setDate(5,mvDate);
            stmt.setString(6,loc);
            stmt.setBigDecimal(7, budget);
            java.sql.Array sqlCards = conn.createArrayOf("TEXT", cards.toArray());
            stmt.setArray(8,sqlCards);
            stmt.setBoolean(9,reward);
            if(reward){
                stmt.setBigDecimal(10, new BigDecimal(0));
            } else{
                stmt.setNull(10, java.sql.Types.NUMERIC);
            }
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addRenterCards(String email, ArrayList<String> card, ArrayList<String> address){
        Connection conn = connect();
        try{
            for(int i = 0; i < address.size();i++){
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO renter_cards VALUES(?,?,?)");
                stmt.setString(1,email);
                stmt.setString(2,card.get(i));
                stmt.setString(3,address.get(i));
                stmt.executeUpdate();
            }
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void createAgent(String email,String lastName, String firstName, ArrayList<String> addresses, String title, String agencyName, BigDecimal phoneNumber){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO agent VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1,email);
            stmt.setString(2,lastName);
            stmt.setString(3,firstName);
            java.sql.Array sqlAddresses = conn.createArrayOf("TEXT", addresses.toArray());
            stmt.setArray(6,sqlAddresses);
            stmt.setString(4,title);
            stmt.setString(5,agencyName);
            stmt.setBigDecimal(7, phoneNumber);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //get methods
    public static int getPropID(String address){
        Connection conn = connect();
        int id = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT propid FROM property WHERE address = ?");
            stmt.setString(1,address);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt("propid");
            }

        } catch (SQLException e){
            System.out.println("Error At PropID: " + e);
            return -1;
        }
        return id;
    }

    public static String getCardAddress(String card){
    Connection conn = connect();
    try{
        PreparedStatement stmt = conn.prepareStatement("SELECT address FROM renter_cards WHERE credit_card = ?");
        stmt.setString(1,card);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            return rs.getString("address");
        }
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    return "";
}
    public static renter getRenter(String email){
        Connection conn = connect();
        try{
            renter user = new renter();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM renter WHERE email = ?");
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                user.setEmail(email);
                Array addressArray = rs.getArray("addresses");
                Array cardsArray = rs.getArray("credit_cards");
                user.setBudget(new BigDecimal(rs.getLong("budget")));
                user.setAddresses((String[]) addressArray.getArray());
                user.setCards((String[]) cardsArray.getArray());
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setDate(rs.getString("mv_in_date"));
                user.setDesired(rs.getString("desired_location"));
                user.setRewards(rs.getBoolean("getrewards"));
                user.setPoints(rs.getInt("reward_pts"));
            }
            return user;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return new renter();
    }

    public static agent getAgent(String email){
        Connection conn = connect();
        try{
            agent user = new agent();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM agent WHERE email = ?");
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                user.setEmail(email);
                Array addressArray = rs.getArray("addresses");
                user.setAddresses((String[]) addressArray.getArray());
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setTitle(rs.getString("title"));
                user.setAgency(rs.getString("agency_name"));
                user.setPhoneNumber(rs.getBigDecimal("phone_number"));
            }
            return user;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return new agent();
    }

    //modify db methods

    public static void deleteCard(String card, String email){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM renter_cards WHERE credit_card = ? AND email = ?");
            stmt.setString(1,card);
            stmt.setString(2,email);
            stmt.executeQuery();
        } catch (SQLException e){
            System.out.println("Error At Deleting Card: " + e);
        }
    }
    
    public static void modifyReward(String email, String newVal){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE renter SET getrewards = ? WHERE email = ?");
            stmt.setBoolean(1,Boolean.valueOf(newVal));
            stmt.setString(2,email);
            stmt.executeQuery();
        } catch (SQLException e){
            System.out.println("Error At Deleting Card: " + e);
        }
    }
    
    public static void modifyAddress(String email, String[] addresses){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE renter SET addresses = ? WHERE email = ?");
            java.sql.Array sqlAddresses = conn.createArrayOf("TEXT", addresses);
            stmt.setArray(1,sqlAddresses);
            stmt.setString(2,email);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Modifying Address: " + e);
        }
    }

    public static void modifyCard(String email, String[] cards){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE renter SET credit_cards = ? WHERE email = ?");
            java.sql.Array sqlAddresses = conn.createArrayOf("TEXT", cards);
            stmt.setArray(1,sqlAddresses);
            stmt.setString(2,email);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Modifying Card List: " + e);
        }
    }

    public static void modifyCardAddress(String email, String oldCard, String newCard){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE renter SET credit_card = ? WHERE email = ? AND credit_card = ?");
            stmt.setString(1,newCard);
            stmt.setString(2,email);
            stmt.setString(3,oldCard);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Modifying Card Address: " + e);
        }
    }

    public static void addCardAddress(String email, String card, String address){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO renter_cards VALUES(?,?,?)");
            stmt.setString(1,email);
            stmt.setString(2,card);
            stmt.setString(3,address);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Adding Card Address: " + e);
        }
    }

    
    public static void createBooking(int propID, String email, String card){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO booking (email, propid, credit_card) VALUES (?,?,?)");
            stmt.setString(1,email);
            stmt.setInt(2,propID);
            stmt.setString(3,card);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Creating Booking: " + e);
        }
    }
    
    public static void addPoints(String address, String email){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT rent_price FROM property NATURAL JOIN price WHERE address = ?");
            stmt.setString(1,address);
            ResultSet rs = stmt.executeQuery();
            Double price = rs.getDouble("rent_price");
            stmt = conn.prepareStatement("SELECT reward_pts FROM renter WHERE email = ?");
            stmt.setString(1,email);
            rs = stmt.executeQuery();
            Double pts = rs.getDouble("reward_pts");
            pts += price;
            stmt = conn.prepareStatement("UPDATE renter SET reward_pts = ? WHERE email = ?");
            stmt.setDouble(1,pts);
            stmt.setString(2, email);
    } catch (SQLException e){
        System.out.println("Error Adding Points: " + e);
    }
}

    public static void modifyPricing(int propID, String column, BigDecimal value){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("UPDATE price SET ? = ? WHERE propid = ?");
            stmt.setString(1,column);
            switch(column){
            case "sale_price":
            stmt.setBigDecimal(2, value);
            break;
            case "rent_price":
            stmt.setBigDecimal(2,value);
            }
            stmt.setInt(3,propID);
            stmt.executeQuery();
        } catch (SQLException e){
            System.out.println("Error At Modifying Pricing: " + e);
        }

    }

    public static void modifyHousing(int propID, String column, String value, String dataType){
        Connection conn = connect();
        try{
            String query = "UPDATE property SET " + column + " = ? WHERE propid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            switch(dataType){
                case "string":
                    stmt.setString(1,value);
                    break;
                case "boolean":
                    Boolean val = Boolean.parseBoolean(value);
                    stmt.setBoolean(1,val);
                    break;
                case "int":
                    stmt.setInt(1, Integer.parseInt(value));
                    break;
            }
            stmt.setInt(2,propID);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Modifying Properties: " + e);
        }
    }
    
    public static int deleteProperty(String address){
        Connection conn = connect();
        try{
            PreparedStatement query = conn.prepareStatement("DELETE FROM property WHERE address = ?");
            query.setString(1,address);
            return query.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error At Modifying Properties: " + e);
            return -1;
        }
    }

    public static void createProperty(String address, String city, String state, String prop_type, String pets, String furnish, String wheelchair, int area, int rooms, String apartType, String busType, BigDecimal rent, BigDecimal sale){
        Connection conn = connect();
        try{
            Statement getLastID = conn.createStatement();
            ResultSet rs = getLastID.executeQuery("SELECT MAX(propid) FROM property");
            rs.next();
            int propid = rs.getInt("max") + 1;
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO property VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1,propid);
            stmt.setString(2, address);
            stmt.setString(3,city);
            stmt.setString(4,state);
            stmt.setString(5,prop_type);
            stmt.setBoolean(6,Boolean.valueOf(pets));
            stmt.setBoolean(7,Boolean.valueOf(furnish));
            stmt.setBoolean(8,Boolean.valueOf(wheelchair));
            stmt.setInt(9,area);
            stmt.setInt(10,rooms);
            stmt.setString(11,apartType);
            stmt.setString(12,busType);
            stmt.executeUpdate();
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO PRICE (propid,sale_price,rent_price) VALUES (?,?,?)");
            stmt2.setInt(1,propid);
            stmt2.setBigDecimal(2, sale);
            stmt2.setBigDecimal(3, rent);
            stmt2.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error Creating New Property: " + e);
        }
    }
//Search housings methods

    public static ArrayList<booking> getBookings(String email){
        Connection conn = connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT booking.email,address,credit_card FROM (booking JOIN renter " +
            "ON booking.email = renter.email) JOIN property ON booking.propid = property.propid WHERE booking.email = ?");
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            ArrayList<booking> results = new ArrayList<booking>();
            while(rs.next()){
                String address =rs.getString("address");
                String card = rs.getString("credit_card");
                booking bk = new booking(email, address, card);
                results.add(bk);
            }
            return results;
        } catch (SQLException e){
            return new ArrayList<booking>();
        }
    }
    public static ArrayList<property> searchApt(BigDecimal budget, String cityInput,String stateInput, Boolean pets, Boolean furnished, Boolean wheelchair, Integer area, String apartType){
        String mySQL = "SELECT rent_price, sale_price,address, city, state, prop_type, allow_pets, is_furnished, wheelchair_access, sq_foot, num_rooms, apart_type"
        		+ " FROM property NATURAL JOIN price WHERE prop_type = 'a' AND";
        if(!cityInput.isBlank()){
            mySQL += " city = '" + cityInput + "' AND";
        }
        if(!stateInput.isBlank()){
            mySQL += " state = '" + stateInput + "' AND";
        }
        
        if(!apartType.isBlank()){
            mySQL += " apart_type = '" + apartType + "' AND";
        }
        mySQL += " (rent_price <= '" + budget + "' OR sale_price <= '" + budget + "') AND";
        if(pets){
            mySQL += " allow_pets = '" + pets + "' AND";
        }
        if(furnished){
            mySQL += " is_furnished = '" + furnished + "' AND";
        }
        if(wheelchair){
            mySQL += " wheelchair_access = '" + wheelchair + "' AND";
        }
        mySQL += " sq_foot >= '" + area + "'";
        Connection conn = connect();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(mySQL);
            ArrayList<property> results = new ArrayList<property>();
            while(rs.next()){
                Double rsRent = Double.parseDouble(rs.getString("rent_price"));
                Double rsSale = Double.parseDouble(rs.getString("sale_price"));
                String rsAddress = rs.getString("address");
                String rsCity = rs.getString("city");
                String rsState = rs.getString("state");
                String rspropType = rs.getString("prop_type");
                Boolean rsPets = rs.getBoolean("allow_pets");
                Boolean rsFurnish =rs.getBoolean("is_furnished");
                Boolean rsWheel = rs.getBoolean("wheelchair_access");
                int rsArea = Integer.parseInt(rs.getString("sq_foot"));
                int rsRooms = Integer.parseInt(rs.getString("num_rooms"));
                String rsApartType = rs.getString("apart_type");
                property rsProp = new property(rsRent,rsSale, rsAddress, rsCity, rsState, 
                rspropType, rsPets, rsFurnish, rsWheel, rsArea, rsRooms,rsApartType, "NULL");
                results.add(rsProp);
            }
            return results;
        } catch( SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return new ArrayList<property>();
    }

    public static ArrayList<property> searchBus(BigDecimal budget, String cityInput,String stateInput, Boolean pets, Boolean furnished, Boolean wheelchair, Integer area, String busType){
        String mySQL = "SELECT rent_price, sale_price,address, city, state, prop_type, allow_pets, is_furnished, wheelchair_access, sq_foot, num_rooms, bus_type"
        		+ " FROM property NATURAL JOIN price WHERE prop_type = 'b' AND";
        if(!cityInput.isBlank()){
            mySQL += " city = '" + cityInput + "' AND";
        }
        if(!stateInput.isBlank()){
            mySQL += " state = '" + stateInput + "' AND";
        }
        
        if(!busType.isBlank()){
            mySQL += " bus_type = '" + busType + "' AND";
        }
        mySQL += " (rent_price <= '" + budget + "' OR sale_price <= '" + budget + "') AND";
        if(pets){
            mySQL += " allow_pets = '" + pets + "' AND";
        }
        if(furnished){
            mySQL += " is_furnished = '" + furnished + "' AND";
        }
        if(wheelchair){
            mySQL += " wheelchair_access = '" + wheelchair + "' AND";
        }
        mySQL += " sq_foot >= '" + area + "'";
        Connection conn = connect();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(mySQL);
            ArrayList<property> results = new ArrayList<property>();
            while(rs.next()){
                Double rsPrice = Double.parseDouble(rs.getString("rent_price"));
                Double rsSale = Double.parseDouble(rs.getString("sale_price"));
                String rsAddress = rs.getString("address");
                String rsCity = rs.getString("city");
                String rsState = rs.getString("state");
                String rspropType = rs.getString("prop_type");
                Boolean rsPets = rs.getBoolean("allow_pets");
                Boolean rsFurnish =rs.getBoolean("is_furnished");
                Boolean rsWheel = rs.getBoolean("wheelchair_access");
                int rsArea = Integer.parseInt(rs.getString("sq_foot"));
                int rsRooms = Integer.parseInt(rs.getString("num_rooms"));
                String rsBusType = rs.getString("bus_type");
                property rsProp = new property(rsPrice, rsSale, rsAddress, rsCity, rsState, 
                rspropType, rsPets, rsFurnish, rsWheel, rsArea, rsRooms,"NULL", rsBusType);
                results.add(rsProp);
            }
            return results;
        } catch( SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return new ArrayList<property>();
    }
    
    public static ArrayList<property> searchHouse(BigDecimal budget, String cityInput,String stateInput, Boolean pets, Boolean furnished, Boolean wheelchair, Integer area){
        String mySQL = "SELECT rent_price, sale_price,address, city, state, prop_type, allow_pets, is_furnished, wheelchair_access, sq_foot, num_rooms"
        + " FROM property NATURAL JOIN price WHERE prop_type = 'h' AND";
        if(!cityInput.isBlank()){
            mySQL += " city = '" + cityInput + "' AND";
        }
        if(!stateInput.isBlank()){
            mySQL += " state = '" + stateInput + "' AND";
        }
        mySQL += " (rent_price <= '" + budget + "' OR sale_price <= '" + budget + "') AND";
        if(pets){
            mySQL += " allow_pets = '" + pets + "' AND";
        }
        if(furnished){
            mySQL += " is_furnished = '" + furnished + "' AND";
        }
        if(wheelchair){
            mySQL += " wheelchair_access = '" + wheelchair + "' AND";
        }
        mySQL += " sq_foot >= '" + area + "'";
        Connection conn = connect();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(mySQL);
            ArrayList<property> results = new ArrayList<property>();
            while(rs.next()){
                Double rsPrice = Double.parseDouble(rs.getString("rent_price"));
                Double rsSale = Double.parseDouble(rs.getString("sale_price"));
                String rsAddress = rs.getString("address");
                String rsCity = rs.getString("city");
                String rsState = rs.getString("state");
                String rspropType = rs.getString("prop_type");
                Boolean rsPets = rs.getBoolean("allow_pets");
                Boolean rsFurnish =rs.getBoolean("is_furnished");
                Boolean rsWheel = rs.getBoolean("wheelchair_access");
                int rsArea = Integer.parseInt(rs.getString("sq_foot"));
                int rsRooms = Integer.parseInt(rs.getString("num_rooms"));
                property rsProp = new property(rsPrice,rsSale, rsAddress, rsCity, rsState, 
                rspropType, rsPets, rsFurnish, rsWheel, rsArea, rsRooms,"NULL", "NULL");
                results.add(rsProp);
            }
            return results;
        } catch( SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return new ArrayList<property>();
    }

    public static ArrayList<property> searchDuplex(BigDecimal budget, String cityInput,String stateInput, Boolean pets, Boolean furnished, Boolean wheelchair, Integer area){
        String mySQL = "SELECT rent_price, sale_price,address, city, state, prop_type, allow_pets, is_furnished, wheelchair_access, sq_foot, num_rooms"
        		+ " FROM property NATURAL JOIN price WHERE prop_type = 'd' AND";
        if(!cityInput.isBlank()){
            mySQL += " city = '" + cityInput + "' AND";
        }
        if(!stateInput.isBlank()){
            mySQL += " state = '" + stateInput + "' AND";
        }
        mySQL += " (rent_price <= '" + budget + "' OR sale_price <= '" + budget + "') AND";
        if(pets){
            mySQL += " allow_pets = '" + pets + "' AND";
        }
        if(furnished){
            mySQL += " is_furnished = '" + furnished + "' AND";
        }
        if(wheelchair){
            mySQL += " wheelchair_access = '" + wheelchair + "' AND";
        }
        mySQL += " sq_foot >= '" + area + "'";
        Connection conn = connect();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(mySQL);
            ArrayList<property> results = new ArrayList<property>();
            while(rs.next()){
                Double rsPrice = Double.parseDouble(rs.getString("rent_price"));
                Double rsSale = Double.parseDouble(rs.getString("sale_price"));
                String rsAddress = rs.getString("address");
                String rsCity = rs.getString("city");
                String rsState = rs.getString("state");
                String rspropType = rs.getString("prop_type");
                Boolean rsPets = rs.getBoolean("allow_pets");
                Boolean rsFurnish =rs.getBoolean("is_furnished");
                Boolean rsWheel = rs.getBoolean("wheelchair_access");
                int rsArea = Integer.parseInt(rs.getString("sq_foot"));
                int rsRooms = Integer.parseInt(rs.getString("num_rooms"));
                property rsProp = new property(rsPrice,rsSale, rsAddress, rsCity, rsState, 
                rspropType, rsPets, rsFurnish, rsWheel, rsArea, rsRooms,"NULL", "NULL");
                results.add(rsProp);
            }
            return results;
        } catch( SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return new ArrayList<property>();
    }
}   
