package main;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class parametres extends BorderPane {
	
	String aj = "Ajouter";
	String nc = "Nouvelle classe ?";
	public String nomclass = null;
	public Button b = new Button(aj);
	public Button val = new Button("Enregistrer");
	public ComboBox classeg;
	public TextField cmere;
	public TextField nouvelle;
	public TextField insc;
	String mere = null;
	public TextField mens;
	public TextField nom;
	public TextField lim= new TextField();
	public BorderPane bp;
	String sup = "supprimmer";
	Button supprimer = new Button(sup);
 
	
	public void initialise()
	{
		cmere = new TextField();
		
		nouvelle = new TextField(nc);
		classeg = new ComboBox();
		insc = new TextField();
		b.setStyle("-fx-background-color: #215060;-fx-text-fill:white;-fx-pref-width:150;");
		val.setStyle("-fx-background-color: #419060;-fx-pref-width:250;-fx-text-fill:white;-fx-pref-width:250;");
		bp = new BorderPane();
		nom = new TextField();
classeg.getSelectionModel().selectedItemProperty().addListener((option, old, newv) -> 
		
		{
				classe c = getOne(newv.toString());
				
				nom.setText(c.getNom());
				insc.setText(c.getInsc());
				mens.setText(c.getMens());
				lim.setText(c.getLim());
				cmere.setText(c.getMere());
				nomclass = newv.toString();
				
				if(!nomclass.equals("3e") && !nomclass.equals("Tle"))
				{
					cmere.setDisable(true);
				}else
				{
					cmere.setDisable(false);
				}
				
						});


		
		try {
			
			List<String> l = db.getClasses();
			for(int i=0; i< l.size()-4; i++)
			{
				classeg.getItems().add(l.get(i));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		classeg.setStyle("-fx-pref-width:250;");
		mens = new TextField();
		mens.setStyle("-fx-pref-width:250;");
		supprimer.setStyle("-fx-pref-width:150;-fx-background-color:#880504;-fx-text-fill:white;");
		nom.setStyle("-fx-pref-width:250;");
		nouvelle.setStyle("-fx-pref-width:150;");
		insc.setStyle("-fx-pref-width:250;");
	    val.addEventFilter(MouseEvent.MOUSE_ENTERED, buttonEntered(val));
	    val.addEventFilter(MouseEvent.MOUSE_EXITED, buttonExited(val));
	    supprimer.addEventFilter(MouseEvent.MOUSE_EXITED, buttonExited(supprimer));
	    
	    val.addEventFilter(MouseEvent.MOUSE_CLICKED, clicked(val));
	    b.addEventFilter(MouseEvent.MOUSE_CLICKED, clicked(b));
	    supprimer.addEventFilter(MouseEvent.MOUSE_CLICKED, clicked(supprimer));
	
	b.addEventFilter(MouseEvent.MOUSE_ENTERED, buttonEntered(b));
	b.addEventFilter(MouseEvent.MOUSE_EXITED, buttonExited(b));
	supprimer.addEventFilter(MouseEvent.MOUSE_ENTERED, buttonEntered(supprimer));
	
		
	}
	
	
	
	
	DatabaseHelper db = DatabaseHelper.getInstance();
	
	public ObservableList<classe> getClasses()
	{
		return  db.getAllClasse();
	}
	
	ObservableList<classe> classes = db.getAllClasse();


	public classe getOne(String s)
	{
		classe ca = new classe();
		for(classe c : classes)
		{

			String str = c.getNom();
			if(s.equals(str))
			{
	
			  return c;
			}
			
		}
		return ca;
	}
	
	public  EventHandler clicked(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(b.getText().equals("Enregistrer"))
				{
					try {
						String[] donnees = {mens.getText(),insc.getText(),lim.getText(),cmere.getText(),nomclass};
						
						
						
						db.save("update classe set  mensualite = ?, inscription = ? , limite = ? , mere = ? where nom = ?",donnees);
						classes = db.getAllClasse();

						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("information");
						a.setContentText("les changements ont été bien effectués");
						a.showAndWait();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("Erreur");
						a.setContentText("Il y a eu une erreur, veuillez réessayer plutard !");
						a.showAndWait();
					}
					
					
				}
				
				if(b.getText().equals("Ajouter"))
				{
					if(!nouvelle.getText().equals(nc))
					{
					String[] s = {nouvelle.getText()};
					try {
						db.save("insert into classe(nom) values(?)",s);
						classes = getClasses();
						total.setText(String.valueOf(classes.size()));
						classeg.getItems().add(nouvelle.getText());
						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("information");
						a.setContentText("la classe a été bien ajouté");
						a.showAndWait();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
				}
				
				if(b.getText().equals(sup))
				{
					Alert a = new Alert(AlertType.CONFIRMATION);
					a.setTitle("confirmation");
					a.setContentText("souhaitez-vous vraiment supprimer la classe : " + nomclass);
					 a.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {
				            
				        });
				}
			    
			}
	
		};
		return x;
	}
	
	public  EventHandler buttonExited(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(b.getText().equals("Enregistrer"))
				b.setStyle("-fx-background-color: #419060;-fx-pref-width:250;-fx-text-fill:white;");
				if(b.getText().equals("Ajouter"))
			    b.setStyle("-fx-background-color: #215060;-fx-pref-width:150;-fx-text-fill:white;");
				if(b.getText().equals(sup))
				b.setStyle("-fx-pref-width:150;-fx-background-color:#880504;-fx-text-fill:white;");
			}
	
		};
		return x;
	}

	public  EventHandler buttonEntered(Button b)
	{
		EventHandler<MouseEvent> x = new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(b.getText()=="Enregistrer")
				b.setStyle("-fx-background-color: #215060;-fx-pref-width:250;-fx-text-fill:white;");
				if(b.getText()=="Ajouter")
			    b.setStyle("-fx-background-color: #419060;-fx-pref-width:150;-fx-text-fill:white;");
				if(b.getText().equals(sup))
					b.setStyle("-fx-pref-width:150;-fx-background-color:#992504;-fx-text-fill:white;");
			
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
		grid.setPadding(new Insets(90, 1500, 80, 100));

		grid.setStyle("-fx-background-color:#FFFEF;-fx-pref-height:100;");
		grid.setPrefHeight(100);

		Label scenetitle = new Label("DEFINITION DES MONTANTS A PAYER");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
        scenetitle.setStyle("-fx-text-fill:#215065;");
		
		grid.add(new Label("Classe"), 0, 1);
		grid.add(classeg, 1, 1);
		
		grid.add(new Label("Montant à l'inscription"), 0, 2);
		grid.add(insc, 1, 2);
		
		grid.add(new Label("Montant de la mensualité"), 0, 3);
		grid.add(mens, 1, 3);
		
		grid.add(new Label("Effectif limite"), 0, 4);
		grid.add(lim, 1, 4);
		
		grid.add(new Label("Nom"), 0, 5);
		grid.add(nom, 1, 5);
		
		grid.add(supprimer, 0, 7);
		grid.add(val, 1, 7);
		
		grid.add(new Label("Classe mère"), 0, 6);
		grid.add(cmere, 1, 6);
		
		grid.add(nouvelle, 3, 1);
		grid.add(b, 3, 2);
		
	
		
		
return grid;

	}
	
	 Label total = new Label();
		
	
	public GridPane makeBottom()
	{
		  GridPane bas = new GridPane();
		  bas.setStyle("-fx-background-color:#215065;");
		  AnchorPane infot = new AnchorPane();
		  total.setText(String.valueOf(classes.size()));
          total.setStyle("-fx-text-fill:blue;-fx-font-weight:40px;-fx-font-size:30px;");
          Label wordt = new Label("total de classes :");
          wordt.setStyle("-fx-font-size:15px;");
          infot.setStyle("-fx-pref-width:190px;-fx-pref-height:70px;-fx-border-radius:30px;-fx-background-color:ghostwhite;");
          total.setPadding(new Insets(12,30,12,12));
          wordt.setPadding(new Insets(25,12,12,65));
          infot.getChildren().add(wordt);
          infot.getChildren().add(total);
          
          bas.add(infot, 1, 2);
	       
	      
	          return bas;
	}
	
	
	
	public GridPane makeTop()
	{
		GridPane grid = new GridPane();
		grid.setPrefHeight(90);
	     
	       grid.setStyle("-fx-background-color:#215060;");
	       return grid;
	}
	
	public parametres()
	{
		initialise();
		setTop(makeTop());
		setCenter(makeForm());
		setBottom(makeBottom());
	}


}
