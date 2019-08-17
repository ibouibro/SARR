package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class paiement extends BorderPane {
	
	Label m = new Label("Montant");
	public TextField nom = new TextField();
	public TextField prenom = new TextField();
	public ComboBox matricule = new ComboBox();
	public ComboBox classe = new ComboBox();
	public ComboBox paiemen = new ComboBox();
	public TextField montant = new TextField();
	public Label l = new Label("PAIEMENT MENSUALITE");
	public Button val = new Button("Valider le paiement");
	
	
	GridPane grid = new GridPane();
	public paiement()
	{
	
		initialise();
		
		setTop(makeTop());
		
		setCenter(makeForm());
	
        
        setBottom(makeBottom());    
     

		

    
   
     
	}
	
	public  EventHandler buttonExited(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				b.setStyle("-fx-background-color: #419060;-fx-pref-width:250;-fx-text-fill:white;");
			}
	
		};
		return x;
	}

	public  EventHandler buttonHandler(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				b.setStyle("-fx-background-color: #215060;-fx-pref-width:250;-fx-text-fill:white;");
			}
	
		};
		return x;
	}
	
	
	public GridPane makeForm()
	{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(90, 1500, 80, 200));

		grid.setStyle("-fx-background-color:#FFFEF;-fx-pref-height:100;");
		grid.setPrefHeight(100);

		Text scenetitle = new Text("PAIEMENT MENSUALITE");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		
		grid.add(new Label("Classe de l'élève"), 0, 1);
		grid.add(classe, 1, 1);
		
		grid.add(new Label("Prénom de l'élève"), 0, 2);
		grid.add(prenom, 1, 2);
		
		grid.add(new Label("Nom de l'élève"), 0, 3);
		grid.add(nom, 1, 3);
		
		grid.add(new Label("Matricule de l'élève"), 0, 4);
		grid.add(matricule, 1, 4);
		
		grid.add(new Label("Type Paiement"), 0, 5);
		grid.add(paiemen, 1, 5);

		grid.add(val, 1, 8);
		
		paiemen.getSelectionModel().selectedItemProperty().addListener((option, old, newv) -> 
		
		{
			if(newv=="partiel")
			{
			grid.add(m, 0, 6);
			grid.add(montant, 1, 6);
			}
			else
			{
				grid.getChildren().remove(montant);
				grid.getChildren().remove(m);
				

			}
		});

return grid;

	}
	
	public GridPane makeBottom()
	{
		  GridPane bas = new GridPane();
	       
	       AnchorPane info = new AnchorPane();
	          Label num = new Label("22");
	          num.setStyle("-fx-text-fill:red;-fx-font-weight:40px;-fx-font-size:30px;");
	          Label word = new Label("ne sont pas en règle");
	          word.setStyle("-fx-font-size:15px;");
	          info.setStyle("-fx-pref-width:190px;-fx-pref-height:70px;-fx-border-radius:30px;-fx-background-color:#EFEFEA;");
	          num.setPadding(new Insets(12,30,12,12));
	          word.setPadding(new Insets(25,12,12,65));
	          info.getChildren().add(num);
	          info.getChildren().add(word);
	          bas.add(info, 2, 2);
	          
	          AnchorPane info2 = new AnchorPane();
	          Label num2 = new Label("222");
	          num2.setStyle("-fx-text-fill:red;-fx-font-weight:40px;-fx-font-size:30px;");
	          Label word2 = new Label("paiements partiels");
	          word2.setStyle("-fx-font-size:15px;");
	          info2.setStyle("-fx-pref-width:190px;-fx-pref-height:70px;-fx-border-radius:30px;-fx-background-color:#AFEFEA;");
	          num2.setPadding(new Insets(12,30,12,12));
	          word2.setPadding(new Insets(25,12,12,65));
	          info2.getChildren().add(num2);
	          info2.getChildren().add(word2);
	          bas.add(info2, 3, 2);
	          
	          bas.setStyle("-fx-background-color:#010224;");
	          
	          return bas;
	}
	
	public void initialise()
	{
		val.setStyle("-fx-background-color:#419060;-fx-pref-width:250;-fx-text-fill:white;");
		val.addEventFilter(MouseEvent.MOUSE_ENTERED, buttonHandler(val));
		val.addEventFilter(MouseEvent.MOUSE_EXITED, buttonExited(val));
		paiemen.getItems().addAll("partiel","total");
		nom.setStyle("-fx-pref-width:250;");
		prenom.setStyle("-fx-pref-width:250;");
		matricule.setStyle("-fx-pref-width:250;");
		paiemen.setStyle("-fx-pref-width:250;");
		montant.setStyle("-fx-pref-width:250;");
		classe.setStyle("-fx-pref-width:250;");
		
		
	}
	
	public GridPane makeTop()
	{
		grid.setPrefHeight(90);
	     
	       grid.setStyle("-fx-background-color:#215060;");
	       return grid;
	}

}
