package EventHandling;

import java.io.*;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import Accounts.*;

public class EventList implements Serializable {
    private static ArrayList<Event> eventList = new ArrayList<Event>();

    public static void addEvent(Event event){
        eventList.add(event);
    }

    public static void removeEvent(Event event){
        eventList.remove(event);
    }

    public static ArrayList<Event> returnEvents(){
        return eventList;
    }


    public static VBox adminDisplayEvents(){
        VBox vbox = new VBox();
        HBox eventNames = new HBox();
        HBox removeEvents = new HBox();

        vbox.setSpacing(5);
        eventNames.setSpacing(10);
        removeEvents.setSpacing(10);

        for (Event event : eventList) {
            eventNames.getChildren().add(new Button(event.getName()));
            
            Button removeButton = new Button("Remove");
            removeButton.setOnAction(e -> EventList.removeEvent(event));


            removeEvents.getChildren().add(removeButton);
        }
       

        vbox.getChildren().addAll(eventNames, removeEvents);

        return vbox;
    }

    public static VBox displayEvents(account account){
        VBox vbox = new VBox();
        HBox eventNames = new HBox();
        HBox removeEvents = new HBox();

        vbox.setSpacing(5);
        eventNames.setSpacing(10);
        removeEvents.setSpacing(10);

        for (Event event : eventList) {
            eventNames.getChildren().add(new Button(event.getName()));
            
            Button addMeButton = new Button("Mam zaujem!");
            addMeButton.setOnAction(e -> {
                event.addAccount(account);
                account.addEvent(event);
            });

            removeEvents.getChildren().add(addMeButton);
        }
       

        vbox.getChildren().addAll(eventNames, removeEvents);

        return vbox;
    }

    public static void save() throws FileNotFoundException, IOException{
        ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("EventList.out"));
        save.writeObject(eventList);
        save.close();
    }

    @SuppressWarnings("unchecked")
    public static void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream load = new ObjectInputStream(new FileInputStream("EventList.out"));
        eventList = (ArrayList<Event>)load.readObject();
        load.close();
    }
}
