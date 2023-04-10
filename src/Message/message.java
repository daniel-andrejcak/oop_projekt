package Message;

import java.io.Serializable;

import Accounts.*;

public class message implements Serializable{
    private String message;
    private account account;

    public message(String message, account account){
        this.message = message;
        this.account = account;
    }

    public String getMessage(){
        return this.message;
    }

    public account getAccount() {
        return this.account;
    }

}
