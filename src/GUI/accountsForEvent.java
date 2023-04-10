package GUI;

import Accounts.*;
import EventHandling.*;
import javafx.scene.control.*;

public class accountsForEvent{

    public static TextArea getAccount(Event event){
        TextArea text = new TextArea();

        for (account account : event.getAccounts()) {
            text.appendText(account.getName() + "\n");
        }
    
        return text;
    }
}
