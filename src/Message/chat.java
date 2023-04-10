package Message;

import java.util.ArrayList;

import Accounts.*;

public class chat {
    private account account1;
    private account account2;

    public chat(account account1, account account2){
        this.account1 = account1;
        this.account2 = account2;
    }

    public account getAccount1() {
        return account1;
    }

    public account getAccount2() {
        return account2;
    }

    private ArrayList<message> chat= new ArrayList<message>();

    public void addMessage(message message){
        chat.add(message);
    }

    public ArrayList<message> getChat(){
        return chat;
    }
}
