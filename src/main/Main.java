package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			AnchorPane parent = new AnchorPane();
			VBox box = new VBox();
			MenuBar menuBar = new MenuBar();
			MenuItem  item1 = new MenuItem("nouveau");
			Menu menu = new Menu("File");
			
			menuBar.getMenus().add(menu);
			menu.getItems().add(item1);
			
			menuBar.setStyle("-fx-pref-width:2100px;");
			
			
			box.getChildren().add(menuBar);
			box.getChildren().add(bp);
			parent.getChildren().add(box);
			
			bp.setCenter(new inscription("mensualité"));
			
		bp.setLeft(initialiseMenu());
			Scene scene = new Scene(parent,1100,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	BorderPane bp = new BorderPane();
	
	
	
	public  EventHandler clicked(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(b.getText().equals("Factures Inscription"))
				{
					bp.setCenter(new FactureM("inscription"));
				}
				
				if(b.getText().equals("Paramètres de Classes"))
				{
					bp.setCenter(new parametres());
					
				}
				
				if(b.getText().equals("Factures Mensualité"))
				{
					bp.setCenter(new FactureM("mensualité"));
				}
				if(b.getText().equals("Nouvelle Inscription"))
				{
					bp.setCenter(new inscription("inscription"));
				}
				if(b.getText().equals("Nouveau Paiement"))
				{
					bp.setCenter(new inscription("mensualité"));
				}
				if(b.getText().equals("Compléter Inscription"))
				{
					bp.setCenter(new inscription("ci"));
				}
			    
			}
	
		};
		return x;
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public  EventHandler buttonEvent(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				b.setStyle("-fx-background-color: #419060;-fx-border-color: #419060;-fx-text-fill:white;-fx-pref-width: 250px;-fx-font-family:arial;-fx-font-size:17px;-fx-pref-height: 40px;");
			}
	
		};
		return x;
	}
	
	public EventHandler buttonExited(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				b.setStyle("-fx-background-color: #215060;-fx-border-color: #419060;-fx-text-fill:white;-fx-pref-width: 250px;-fx-font-family:arial;-fx-font-size:17px;-fx-pref-height: 40px;");
			}
	
		};
		return x;
	}
	
	public VBox initialiseMenu()
	{
		VBox men = new VBox();
		men.setStyle("-fx-pref-width:250px;");
		
		Button b = new Button("Nouvelle Inscription");
		Button m = new Button("MENU");
		m.setStyle("-fx-background-color: #244060;-fx-border-color: #419060;-fx-text-fill:white;-fx-pref-width: 250px;-fx-font-family:arial;-fx-font-size:17px;-fx-pref-height: 40px;");
		Button a = new Button("Factures Inscription");
		Button fm = new Button("Factures Mensualité");
		Button c = new Button("Elèves");
		Button d = new Button("Paramètres de Classes");
		Button pay = new Button("Nouveau Paiement");
        Button param = new Button("Paramètres");
        Button ci = new Button("Compléter Inscription");
		
        
	    Button[] buttons = {m,a,fm,b,c,d,pay,param,ci};
	    
		makeButtons(buttons);
		men.getChildren().addAll(buttons);
		
		return men;
		
	}
	
	public void makeButtons(Button[] b)
	{
		for (int i=0; i< b.length; i++)
		{
			b[i].addEventFilter(MouseEvent.MOUSE_CLICKED, clicked(b[i]));
			b[i].addEventFilter(MouseEvent.MOUSE_ENTERED, buttonEvent(b[i]));
			b[i].addEventFilter(MouseEvent.MOUSE_EXITED, buttonExited(b[i]));
			b[i].setStyle("-fx-background-color: #215060;-fx-border-color: #419060;-fx-text-fill:white;-fx-pref-width: 250px;-fx-font-family:Courier New;-fx-font-size:17px;-fx-pref-height: 40px;");
			
		}
	}
}
