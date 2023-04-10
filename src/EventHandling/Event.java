package EventHandling;

import java.io.Serializable;
import java.util.ArrayList;
import Accounts.*;

public class Event implements Serializable {
    private String name;
    private ArrayList<account> accounts = new ArrayList<account>();

    public Event(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addAccount(account account){
        accounts.add(account);
    }

    public ArrayList<account> getAccounts(){
        return accounts;
    }
}
