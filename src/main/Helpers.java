package main;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.StringUtils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public  class Helpers {
	
	private static Helpers helpers = null;
	
	
	public static Helpers getInstance()
	{
		if(helpers == null)
			helpers = new Helpers();
		return helpers;
	}
	

	
	DatabaseHelper db = DatabaseHelper.getInstance();
	
	
	public void EnregistrerFactureInscription(String prenom,String paie,TextField montant,String idmois,String id)
	{
		try {
			
			
			 id =  db.selectLastEleve();
				
				if(db.etatExists(id)==false)  // vérification existence de l'élève
				{
					String[] mois = {"1","2","3","4","5","6","7","10","11","12"};
					for(int i=0;i<mois.length;i++ )
					db.save("insert into moiseleve(mois,eleve,etat) values(?,?,?)", new String[]{mois[i],id,"0"} );
				}
			
				if(paie.equals("partiel"))
				{
					
					
				db.save("insert into facture(montant,paiement,eleve,type,moi) values(?,?,?,?,?)",new String[]{montant.getText(),"partiel",id,"1","10" });
				Platform.runLater(new ActualisePays(id,"10"));
					
				}
				else
				{
				
					 
					db.save("insert into facture(montant,paiement,eleve,type,moi) values(?,?,?,?,?)",new String[]{montant.getText(),"total",id,"1","10" });
					db.save("update moiseleve set etat = '1' where eleve = ? and mois = ? ", new String[] {id,db.getMoisIndex(idmois)}); 
					
				
				}
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("information");
			a.setContentText("l'enregistrement a réussi");
			a.showAndWait(); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void EnregistrerFactureMensualite(String prenom,String paie,TextField montant,String idmois,String id)
	{

		try {
		 if(!prenom.equals(""))
		 {
		if(paie.equals("partiel"))
		{
			
			
		db.save("insert into facture(montant,paiement,eleve,type,moi) values(?,?,?,?,?)",new String[] {montant.getText(),"partiel",id,"0",db.getMoisIndex(idmois)} );
		Platform.runLater(new ActualisePays(id,db.getMoisIndex(idmois)));
		}
		else
		{
			
			db.save("insert into facture(montant,paiement,eleve,type,moi) values(?,?,?,?,?)", new String[] {montant.getText(),"total",id,"0",db.getMoisIndex(idmois)});
			db.save("update moiseleve set etat = '1' where eleve = ? and mois = ? ", new String[] {id,db.getMoisIndex(idmois)}); 
			
			
			
			
		} 
		 Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("information");
			a.setContentText("l'enregistrement a réussi");
			a.showAndWait(); 
		 }
		
			
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void EnregistrerInscription(String nom, String prenom, String clas, String genr,String paie,TextField montant,String idmois)
	{
		 Date d = new Date();
		 String id="";
			Format form = new SimpleDateFormat("dd/MM/yyyy");
			String s = form.format(d);
	      String classi;
		
			try {
				
	         prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
	         nom = nom.toUpperCase();
				
				classi = db.getClassIndex(clas);
				
				db.save("insert into eleve(nom,prenom,classe,dateinscription,genre) values(?,?,?,?,?)",new String[]{nom, prenom, classi,s,genr});
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


	}
	
	
	public class ActualisePays implements Runnable

	{
		
		String eleve;
		String mois;
		
		

		public ActualisePays(String eleve, String mois) {
			super();
			this.eleve = eleve;
			this.mois = mois;
		}



		@Override
		public void run() {
			try {
				db.actualiserPartiels(eleve, mois);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}



}
