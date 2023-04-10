package Accounts;

import java.util.ArrayList;

import EventHandling.Event;

public class account extends person {
    private String password;
    private ArrayList<Event> events = new ArrayList<Event>();

    @Override
    public void make(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public account getAccount(){
        return this;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public ArrayList<Event> getEvents(){
        return events;
    }

}
