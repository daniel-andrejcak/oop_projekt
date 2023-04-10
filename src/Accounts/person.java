package Accounts;

import java.io.Serializable;

public class person implements Serializable {
    protected String name;

    public void make(String name, String password){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
