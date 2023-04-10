package Accounts;

import java.io.*;
import java.util.*;

public class accountRegister extends account{
    private static ArrayList<account> accounts = new ArrayList<account>();

    public static void addAccount(String name, String password){
        account account = new account();

        account.make(name, password);
        /*account.setName(name);
        account.setPassword(password);*/

        accounts.add(account);
    }


    public static account getAccount(String name, String password){
        for (account accountToReturn : accounts) {
            if (accountToReturn.getName().equals(name) && accountToReturn.getPassword().equals(password)) {
                return accountToReturn;
            };
        }

        return null;
    }

    public static void save() throws FileNotFoundException, IOException{
        ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("accountRegister.out"));
        save.writeObject(accounts);
        save.close();
    }

    @SuppressWarnings("unchecked")
    public static void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream load = new ObjectInputStream(new FileInputStream("accountRegister.out"));
        accounts = (ArrayList<account>)load.readObject();
        load.close();
    }
}
