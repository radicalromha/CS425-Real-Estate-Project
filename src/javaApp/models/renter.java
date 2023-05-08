package src.javaApp.models;

import java.math.BigDecimal;

public class renter extends user{
    
    private String moveDate;
    private String desired;
    private BigDecimal budget;
    private String[] cards;
    private boolean rewards;
    private int points;

    public String getDate(){
        return moveDate;
    }

    public String getDesired(){
        return desired;
    }

    public BigDecimal getBudget(){
        return budget;
    }

    public String[] getCards(){
        return cards;
    }

    public boolean getRewards(){
        return rewards;
    }

    public int getPoints(){
        return points;
    }

    //setters
    
    public void setDate(String date){
        moveDate = date;
    }

    public void setDesired(String loc){
        desired = loc;
    }

    public void setBudget(BigDecimal budget){
        this.budget = budget;
    }

    public void setCards(String[] cards){
        this.cards = cards;
    }

    public void setRewards(boolean rewards){
        this.rewards = rewards;
    }

    public void setPoints(int pts){
        this.points = pts;
    }

    public String toString(){
        return "Name: " + getLastName() + ", " + getFirstName() + ", Email: " + getEmail() + ", Budget: " + getBudget() + ",Move Date: " + moveDate + ", Preferred Location: " + desired + ", Rewards?: " + Boolean.toString(rewards) + ", Reward Points: " + points;
    }
    }

