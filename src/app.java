import java.io.IOException;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;

import Accounts.*;
import EventHandling.*;


public class app extends Application{
    
    private static TextArea output = new TextArea();
    private Button login = new Button("login");
    private Button register = new Button("register");
    private TextField name = new TextField();
    private PasswordField password = new PasswordField();
    private Button logout = new Button("logout");
    private Button addEvent = new Button("Add event");
    private TextField eventName = new TextField();
    private Button exit = new Button("Exit");
    private TextArea eventList = new TextArea();
    private TextArea accountsForEventText = new TextArea();


    private account account;
    

    public VBox loginPage(){
        VBox vbox = new VBox(10);
        
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().add(name);
        vbox.getChildren().add(password);
        vbox.getChildren().add(login);
        vbox.getChildren().add(register);

        vbox.getChildren().add(output);
        output.setEditable(false);
        
        vbox.getChildren().add(exit);

        name.setMaxWidth(100);
        password.setMaxWidth(100);

        output.setPadding(new Insets(10, 10, 10, 10));
        output.setMaxWidth(300);
        output.setMaxHeight(100);

        return vbox;
    }

    public GridPane adminPage(){
        GridPane grid = new GridPane();

        grid.setVgap(20);
        grid.setHgap(20);

        grid.add(addEvent, 1, 0);
        grid.add(logout, 10, 0);

        grid.add(eventName, 3, 0);
        grid.add(EventList.adminDisplayEvents(), 5, 5);

        return grid;
    }
    
    public GridPane mainPage(String name){
        GridPane grid = new GridPane();

        //grid.setGridLinesVisible(true);

        grid.setVgap(20);
        grid.setHgap(20);

        grid.add(output, 0, 0);
        
        output.setEditable(false);
        output.setText("Hello" + " " + name + "\n");
        grid.add(EventList.displayEvents(account), 5, 5);
        grid.add(eventList, 0, 5);

        for (Event event : account.getEvents()) {
            eventList.appendText(event.getName()+"\n");
        }

        eventList.setEditable(false);
        eventList.setMaxSize(80, 80);

        grid.add(displayPeopleInEvent(account), 0, 8);


        
        grid.add(logout, 2, 0, 2, 1);
        //logout.setMinWidth(100);

        return grid;
    }

    public VBox displayPeopleInEvent(account account){
        HBox buttonsBox = new HBox();
        VBox vbox = new VBox();
        MenuBar menubar = new MenuBar();

        for (Event event : account.getEvents()) {            
            Button displayAccounts = new Button(event.getName());
            
            displayAccounts.setOnAction(e -> {

                for (account accountInEvent : event.getAccounts()) {
                    //accountsForEventText.appendText(accountInEvent.getName() + "\n");
                
                    final Menu menu = new Menu(accountInEvent.getName());
                    menubar.getMenus().add(menu);
                }
            });

            buttonsBox.getChildren().add(displayAccounts);
        }
        
        vbox.getChildren().add(buttonsBox);
        vbox.getChildren().add(menubar);

        return vbox;
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("App");

        register.setOnAction(e -> accountRegister.addAccount(name.getText(), password.getText()));

        login.setOnAction(e -> {
            account = accountRegister.getAccount(name.getText(), password.getText());
            try {
                if (account.getName().equals("admin") && account.getPassword().equals("admin")) {
                    stage.setScene(new Scene(adminPage(), 800, 600));
                }else{
                stage.setScene(new Scene(mainPage(account.getName()), 800, 600));
                }
            } 
            catch (Exception NullPointerExeption) {
                output.appendText("Zle prihlasovacie meno alebo heslo\n");
            }
        });

        exit.setOnAction(e -> {
            try {
                accountRegister.save();
                EventList.save();
            } catch (IOException e1) {
                output.appendText("Nepodarilo sa ulozit\n");
            }

            Platform.exit();
        });
        
        addEvent.setOnAction(e -> {
            EventList.addEvent(new Event(eventName.getText()));
            eventName.setText("");
        });

        logout.setOnAction(e -> {
            stage.setScene(new Scene(loginPage(), 800, 600));
            output.setText("");
            eventList.setText("");
            accountsForEventText.setText("");
        });

        
        //prva scena pri spusteni - loginPage
        stage.setScene(new Scene(loginPage(), 800, 600));
        
        stage.show();   
    }

    public static void main(String args[]){
        try {
            accountRegister.load();
            EventList.load();
        } catch (ClassNotFoundException | IOException e1) {
            output.setText("Nepodarilo sa nacitat\n");
        }
        
        launch(args);
    }
}
