package Message;

import java.io.*;
import java.util.ArrayList;

import Accounts.account;

public class chatRegister implements Serializable {
    private static ArrayList<chat> chats = new ArrayList<chat>();

    public void addChat(chat chat){
        chats.add(chat);
    }

    public chat getChat(account account1, account account2){
        for (chat chat : chats) {
            if (chat.getAccount1().equals(account1) && chat.getAccount2().equals(account2)) {
                return chat;
            }
        }

        return new chat(account1, account2);
    }

    public static void save() throws FileNotFoundException, IOException{
        ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("accountRegister.out"));
        save.writeObject(chats);
        save.close();
    }

    @SuppressWarnings("unchecked")
    public static void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream load = new ObjectInputStream(new FileInputStream("accountRegister.out"));
        chats = (ArrayList<chat>)load.readObject();
        load.close();
    }
}
