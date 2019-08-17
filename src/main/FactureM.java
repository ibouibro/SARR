package main;


	import java.sql.SQLException;
	import java.util.ArrayList;

	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.ContextMenu;
	import javafx.scene.control.Label;
	import javafx.scene.control.ListView;
	import javafx.scene.control.MenuItem;
	import javafx.scene.control.ScrollBar;
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

	public class FactureM extends BorderPane { 
		
		String TypeFacture;
		ComboBox classe;
		Label lclass = new Label("classe");
		TextField chercher;
		Label lchercher = new Label("chercher");
		ListView mensualites;
		GridPane grid = new GridPane();
		int partiel=0;
		int total=0;
		 ObservableList<Mensuality> alldata = FXCollections.observableArrayList();
		 ObservableList<Mensuality> classdata = FXCollections.observableArrayList();
		
		public void setTableData(TableView table)
		{
			 
		    try {
				alldata = db.getFactures(TypeFacture);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    table.setItems(alldata);
		    for(Mensuality m : alldata)
		    {
		    	if(m.getPaiement().equals("partiel"))
		    	{
		    		partiel= partiel + 1;;
		    		
		    	}
		    	else
		    	{
		    		total= total + 1;
		    	}
		    }
		}
		
		public FactureM(String TypeFacture)
		{
			
		   this.TypeFacture =  TypeFacture;	
	       setTop(makeTop());
	      
	       ScrollPane s = new ScrollPane();
		     s.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		     s.setContent(makeTable());
		    
	       setCenter(s);
	       setBottom(makeBottom());  
	       
	       grid.setStyle("-fx-background-color:#215060;");
	    
	    
		}
		
		TableView<Mensuality> table = new TableView();
		
		public TableView makeTable()
		{
			 
		   
		     TableColumn nom = new TableColumn("nom");
		     nom.setMinWidth(200);
		     nom.setCellValueFactory(
		             new PropertyValueFactory<Student, String>("nom"));
		     
		     TableColumn prenom = new TableColumn("Prénom");
		     prenom.setMinWidth(200);
		     prenom.setCellValueFactory(
		             new PropertyValueFactory<Student, String>("prenom"));


		   
		     TableColumn date = new TableColumn("Date Paiement");
		     date.setMinWidth(200);
		     date.setCellValueFactory(
		             new PropertyValueFactory<Student, String>("date"));
		     
		     TableColumn mois = new TableColumn("Mois");
		     mois.setMinWidth(200);
		     mois.setCellValueFactory(
		             new PropertyValueFactory<Student, String>("mois"));
		     
		     
		     TableColumn paiement = new TableColumn("Type Paiement");
		     paiement.setMinWidth(100);
		     paiement.setCellValueFactory(
		             new PropertyValueFactory<Student, String>("paiement"));

		     table.getColumns().addAll(nom, prenom, paiement,date);
		     if(TypeFacture.equals("mensualité"))
		    	 table.getColumns().add(mois);
		     table.setPrefHeight(500);
		     
		     setTableData(table);
		     
		     ContextMenu cm = new ContextMenu();
		     MenuItem afficher = new MenuItem("Afficher Facture");
		     MenuItem supprimer = new MenuItem("Supprimer");

		     
		     cm.getItems().addAll(afficher,supprimer);
		   table.setContextMenu(cm);
		     EventHandler<MouseEvent> aff = new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent event) {
					
						
					}
			
				};
				
				afficher.addEventHandler(MouseEvent.MOUSE_CLICKED, aff);
		     
				 EventHandler<MouseEvent> supp = new EventHandler<MouseEvent>()
					{

						@Override
						public void handle(MouseEvent event) {
						
							
							
						}
				
					};
					
					supprimer.addEventHandler(MouseEvent.MOUSE_CLICKED, supp);
			     return table;
		}
		
		DatabaseHelper db =DatabaseHelper.getInstance();
		
		public GridPane makeBottom()
		{
			 GridPane bas = new GridPane();
		       AnchorPane info = new AnchorPane();
		          
		          Label num = new Label(String.valueOf(partiel));
		          num.setStyle("-fx-text-fill:red;-fx-font-weight:40px;-fx-font-size:30px;");
		          Label word = new Label("paiements partiels");
		          word.setStyle("-fx-font-size:15px;");
		          info.setStyle("-fx-pref-width:190px;-fx-pref-height:70px;-fx-border-radius:30px;-fx-background-color:#DFEFEA;");
		          num.setPadding(new Insets(12,30,12,12));
		          word.setPadding(new Insets(25,12,12,52));
		          info.getChildren().add(num);
		          info.getChildren().add(word);
		          
	 AnchorPane info1 = new AnchorPane();
		          
		          Label num1 = new Label(String.valueOf(total));
		          num1.setStyle("-fx-text-fill:red;-fx-font-weight:40px;-fx-font-size:30px;");
		          Label word1 = new Label("paiements totaux");
		          word1.setStyle("-fx-font-size:15px;");
		          info1.setStyle("-fx-pref-width:190px;-fx-pref-height:70px;-fx-border-radius:30px;-fx-background-color:#EFEFEA;");
		          num1.setPadding(new Insets(12,30,12,12));
		          word1.setPadding(new Insets(25,12,12,52));
		          info1.getChildren().add(num1);
		          info1.getChildren().add(word1);
		          
		          bas.add(info, 2, 2);
		          bas.add(info1, 3, 2);
		          bas.setStyle("-fx-background-color:#010224;");
		          
		          return bas;
		}
		
		public GridPane makeTop()
		{
			classe = new ComboBox();
			chercher = new TextField("");
			
			
			
			grid.setPadding(new Insets(20, 20, 20, 40));
			    grid.add(lclass,5,2);
			    lclass.setPadding(new Insets(12,12,12,12));
			    grid.add(classe,10 , 2);
			    classe.setPadding(new Insets(0,20,12,12));
			    classe.setPrefWidth(200);
			    classe.setPrefHeight(21);
			    classe.setStyle("-fx-pref-height:21;-fx-font-size:16px;");
			    lclass.setStyle("-fx-text-fill:white;-fx-font-family:arial;-fx-font-size:17px;");
			    try {
					classe.getItems().addAll(db.getClasses());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			    
			    grid.add(lchercher, 20, 2);
			    lchercher.setPadding(new Insets(12,12,12,102));
	            grid.add(chercher, 25, 2);
	            chercher.setPadding(new Insets(12,12,12,12));
	            chercher.setPrefWidth(300);
	            chercher.setPrefHeight(21);
	            lchercher.setStyle("-fx-text-fill:white;-fx-font-family:arial;-fx-font-size:18px;");
	            
	            chercher.textProperty().addListener((option,old,newv) ->
	            		{
	            	            
	            	             String[] tab = String.valueOf(newv).split(" ");
	            	             
	             	            if(tab.length==2)
	             	            {
	             	            	tab[1]= tab[1].toUpperCase();
	             	            	tab[0]= tab[0].substring(0, 1).toUpperCase() + tab[1].substring(1).toLowerCase();
	             	            	
	             	            	for(int i=0;i<alldata.size();i++)
	             	            	{
	             	            		if(alldata.get(i).getNom()+" "+alldata.get(i).getPrenom()==tab[0]+" "+tab[1])
	             	            		{
	             	            			classdata.add(alldata.get(i));
	             	            		}
	             	            	}
	             	            	
	             	            	table.setItems(classdata);
	             	            }
	            	             
	            		});
	            
	            classe.selectionModelProperty().addListener((option,old,newv) ->
        		{
        			 String val = String.valueOf(newv);
        				
     	            	
     	            	table.setItems(db.getFactureByClass(val));
     	            }
        		});
	            
	           
		return grid;
		}
	


}
