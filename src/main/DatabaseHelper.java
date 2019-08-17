package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.IntegerStringConverter;

public class DatabaseHelper {
	
	private DatabaseHelper() {}
	private Connection con;
	private Statement stm;
	private static DatabaseHelper db;
	private PreparedStatement pstm;
	public static DatabaseHelper getInstance()
	{
		if(db==null)
db=new DatabaseHelper();
return db;
	}
	public void closeConnection()
	{
	if(stm!=null)
	{
		stm=null;
	}

	if(con!=null)
		con=null;
	}

	
	public void openConnection() throws ClassNotFoundException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/geschool";
		try {
			con=DriverManager.getConnection(url,"root","");
			stm=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		public int getEleveCount() throws ClassNotFoundException
	{
		
		try {
			openConnection();
			ResultSet rs=stm.executeQuery("select count(*) from eleve ");
			closeConnection();
			while(rs.next())
			{
				
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return 0;
		}
		closeConnection();
		return 0;
	}
	
	
	
	public int getPartielPays(String indication) throws ClassNotFoundException
	{
		
		
		if(indication.equals("inscription"))
			indication= "Octobre";
		try {
			openConnection();
			ResultSet rs=stm.executeQuery("select count(distinct(eleve)) from moiseleve  where (etat= '2' or etat = '0') and mois = (select id from mois where nom = '"+indication+"')");
			closeConnection();
			while(rs.next())
			{
				
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return 0;
		}
		return 0;
	}
	
	public ObservableList<Student> getEleves(String s) throws ClassNotFoundException
	{
		ObservableList<Student> l = FXCollections.observableArrayList();
		try {
			
			openConnection();
			ResultSet rs=stm.executeQuery("select * from eleve e, classe c where e.classe = c.id and ( matricule = " + s + " or concat(prenom + \" \" + nom) = " + s +")");
			closeConnection();
			while(rs.next())
			{
				Student st = new Student(String.valueOf(rs.getInt(1)),rs.getString("nom"),rs.getString("prenom"),"",rs.getString("dateinscription"),rs.getString(9));
				l.add(st);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return l;
		}
		return l;
	}

	public ObservableList<Student> getElevesByClass(String classe) throws ClassNotFoundException
	{
		ObservableList<Student> l = FXCollections.observableArrayList();
		try {
			
			openConnection();
			ResultSet rs=stm.executeQuery("select * from eleve e, classe c where e.classe = c.id and c.nom = '" + classe + "'");
			closeConnection();
			while(rs.next())
			{
				Student st = new Student(String.valueOf(rs.getInt(1)),rs.getString("nom"),rs.getString("prenom"),"",rs.getString("dateinscription"),rs.getString(9));
				l.add(st);
				System.out.println("1");
			}
			
			return l;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return l;
		}
		
	}


	public ObservableList<Student> getAllEleve() throws ClassNotFoundException
	{
		ObservableList<Student> l = FXCollections.observableArrayList();
		try {
			
			openConnection();
			ResultSet rs=stm.executeQuery("select * from eleve e, classe c  where e.classe = c.id ");
			closeConnection();
			while(rs.next())
			{
				Student st = new Student(String.valueOf(rs.getInt(1)),rs.getString("nom"),rs.getString("prenom"),"",rs.getString("dateinscription"),rs.getString(9));
				l.add(st);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return  l;
		}
		return l;
	}


	public ObservableList<Student> getAllElevesByClass(String classe) throws ClassNotFoundException
	{
		ObservableList<Student> l = FXCollections.observableArrayList();
		try {
			
			openConnection();
			ResultSet rs=stm.executeQuery("select * from eleve e, classe c where c.id = e.classe  and  c.nom = " + classe );
			closeConnection();
			while(rs.next())
			{
				Student st = new Student(String.valueOf(rs.getInt(1)),rs.getString("1"),rs.getString("prenom"),"",rs.getString("dateinscription"),rs.getString(9));
				l.add(st);
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return l;
		}
		return l;
	}


	
	public int MensualitesPartielles() throws ClassNotFoundException
	{
		ObservableList<Student> l = FXCollections.observableArrayList();
		try {
			String etat = "2";
			openConnection();
			
	   ResultSet rs=stm.executeQuery("select count(*) from moiseleve where mois!= '10' and etat= '"+etat+"'");

			while(rs.next())
			{
				return rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeConnection();
			return 0;
		}
		return 0;
	}




public void save(String sql,String[] donnees) throws ClassNotFoundException
    {

try {
	openConnection();
	pstm= con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	for(int i=0;i < donnees.length; i++)
	{

	pstm.setObject(i+1,donnees[i]);
	
	}
	 pstm.executeUpdate();
	 closeConnection();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	closeConnection();
	e.printStackTrace();
}

}



public boolean etatExists(String id) throws ClassNotFoundException, SQLException
{
	openConnection();
	ResultSet rs=stm.executeQuery("select count(*) from moiseleve where eleve ='"+id+"'" );
	closeConnection();
	while(rs.next())
	{
	    if(rs.getInt(1)==10)
	    {
		return true;
	    }
	}
	System.out.println("koooo");
	return false;

}



public ObservableList<Mensuality> getFactures(String im) throws ClassNotFoundException
{
	int a = 1;
	if(im.equals("mensualité"))
		a = 0;
	ObservableList<Mensuality> l = FXCollections.observableArrayList();
	try {
		
		openConnection();
		ResultSet rs=stm.executeQuery("select * from facture f, eleve e, mois m where f.eleve = e.id and f.moi=m.id and f.type = '"+ a + "'" );
	
		while(rs.next())
		{
			
			Mensuality st = new Mensuality(rs.getString("e.nom"),rs.getString("e.prenom"),rs.getString("montant"),rs.getString("date"),rs.getString("m.nom"),rs.getString("paiement"),im);
			l.add(st);
		
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		closeConnection();
		return null;
	}
	return l;
}





public ObservableList<classe> getAllClasse() 
{
	ObservableList<classe> ca = FXCollections.observableArrayList();
	try {
	
		try {
			openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stm = con.createStatement();
		ResultSet rs=stm.executeQuery("select inscription, mensualite, limite, nom, mere from classe ");
		closeConnection();
		while(rs.next())
		{
			classe c = new classe();
			c.setInsc(rs.getString(1));
			c.setMens(rs.getString(2));
			c.setLim(rs.getString(3));
	        c.setNom(rs.getString(4));
	        c.setMere(rs.getString(5));
	        ca.add(c);
	        
			
		}
		return ca;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		closeConnection();
		return ca;
		
	}
	
}




public ObservableList<Mensuality> getOneFacture(String mois, String s,int type) throws ClassNotFoundException
{
	ObservableList<Mensuality> l = FXCollections.observableArrayList();
	try {
		
		openConnection();
		ResultSet rs=stm.executeQuery("select * from facture f, mensualite m, eleve e where m.id = f.mensualite and e.id = m.eleve and f.type= "+ type +" and (e.matricule = " + s + " or concat(e.nom + \" \" + e.prenom) = "+ s +")  and m.mois = " + mois );
		closeConnection();
		while(rs.next())
		{
			
			Mensuality st = new Mensuality(rs.getString("nom"),rs.getString("prenom"),rs.getString("montant"),rs.getString(9),rs.getString("mois"),rs.getString("paiement"),"");
			l.add(st);
		}
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		closeConnection();
		return l;
	}
	return l;
}

public String getClassIndex(String nom) throws ClassNotFoundException
{
	try {
		openConnection();
		ResultSet rs=stm.executeQuery("select id from classe where nom = '" + nom +"'" );
	
		closeConnection();
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			
			return String.valueOf(rs.getInt(1));
		}
		
	} catch (SQLException e) {
		closeConnection();
		return "0";
	}
	return "0";
}


public String selectLastEleve() throws ClassNotFoundException
{
	try {
		openConnection();
		ResultSet rs=stm.executeQuery("SELECT * FROM eleve ORDER BY ID DESC LIMIT 1");
		closeConnection();
		String a = "0";
		while(rs.next())
		{
			a=String.valueOf(rs.getInt(1));
					
		}
		System.out.println(a);
		return a;
		
	} catch (SQLException e) {
		closeConnection();
		return "0";
	}
	
}




public void actualiserPartiels(String id,String idmois) throws ClassNotFoundException, SQLException
{
	ResultSet rs=null;
	{
		int val=0;
		openConnection();
	    rs=stm.executeQuery("select montant from facture where moi = '" + idmois +"' and paiement = 'partiel' and eleve = '"+id+"'" );
	    while(rs.next())
	    {
	    	val+= Integer.parseInt(rs.getString(1));
	    }
	    
	    if(idmois=="10")
	    
	    rs=stm.executeQuery("select inscription  from classe c, eleve e where c.id = e.classe and e.id= '" + id +"'" );
	    else
	    	 rs=stm.executeQuery("select mensualite  from classe c, eleve e where c.id = e.classe and e.id= '" + id +"'" );
	    
	    while(rs.next())
	    {
	    	if(Integer.parseInt(rs.getString(1)) == val)
	    	this.save("update moiseleve set etat = '1' where eleve=? and mois = ?", new String[]{id,idmois});
	    	else
	    		this.save("update moiseleve set etat = '2' where eleve=? and mois = ?", new String[]{id,idmois});
	   	 	
	    }
	
	}
	
	
}





public String getMoisIndex(String m) throws ClassNotFoundException
{
	try {
		openConnection();
		ResultSet rs=stm.executeQuery("SELECT id from mois where nom ='"+m+"'");
		closeConnection();
		String a = "0";
		while(rs.next())
		{
			a=String.valueOf(rs.getInt(1));
					
		}
		System.out.println(a);
		return a;
		
	} catch (SQLException e) {
		closeConnection();
		return "0";
	}
	
}


public String getEleveId(String[] np, String classe) throws ClassNotFoundException, SQLException
{
	
	String nom =  np[0].toUpperCase();
	String prenom = null;
	if(np.length > 1)
    prenom = np[1].substring(0, 1).toUpperCase() + np[1].substring(1).toLowerCase();
		openConnection();
		ResultSet rs=stm.executeQuery("SELECT id from eleve where nom in ('" + nom + "', '" + prenom + "') and prenom in  ('" + nom + "', '" + prenom + "') ");
		closeConnection();
		int a=0;
		String re = "";
		while(rs.next())
		{
		
			a++;
			re = String.valueOf(rs.getInt("id"));
					
		}
		
		if(a > 1)
			return "";
		
		return re;
	
	
	
}






public String getRemaining(String type, String month, String classe,String StudentId) throws ClassNotFoundException
{
	Integer val=0;
	IntegerStringConverter isc = new IntegerStringConverter();
	int factureType = 0;
	if(month.equals("Octobre"))
	{
		factureType=1;
	}
		
	try {
		openConnection();
		
		if(StudentId==null)
		{
			ResultSet rs=stm.executeQuery("select inscription from classe where nom ='"+classe+"'");
			while(rs.next())
			{
				
				return rs.getString(1);
						
			}
		}
		
		ResultSet rs=stm.executeQuery("SELECT montant from  facture  where type = '"+ factureType +"' and moi = (select id from mois where nom = '"+month+"') and eleve='"+ StudentId +"'");
		
		
		while(rs.next())
		{
			val+= isc.fromString(rs.getString(1));
					
		}

		
		String topValue=null;
		
		if(factureType==1)
		{
			rs=stm.executeQuery("select inscription from classe where nom ='"+classe+"'");
			while(rs.next())
			{
				
				topValue= rs.getString(1);
						
			}
			
		}
		
		if( factureType==0)
		{
			rs=stm.executeQuery("select mensualite from classe where nom ='"+classe+"'");
			while(rs.next())
			{
				topValue= rs.getString(1);
						
			}
			
		}
		closeConnection();
		
	
	Integer intTopValue = isc.fromString(topValue);
		
	return isc.toString( intTopValue - val);
		
	} catch (SQLException e) {
		closeConnection();
		return "0";
	}
	

}






public List<String> getClasses() throws ClassNotFoundException, SQLException
{
	openConnection();
	ResultSet rs=stm.executeQuery("select * from classe" );
	closeConnection();
List<String> p = new ArrayList();;
int i=0;
	while(rs.next())
	{
		p.add(rs.getString("nom"));
		i++;
	}
	
	p.add("primaire");

	p.add("secondaire");
	
	p.add("3eme cycle");
	p.add("toutes");
	return p;
	}





public List<String> getRemainingMonths(String id) throws ClassNotFoundException, SQLException
{

	openConnection();
	ResultSet rs=stm.executeQuery("select  distinct(m.nom) from moiseleve me, mois m where (me.etat='0' or me.etat = '2') and me.eleve = '"+id+"' and me.mois = m.id " );
	
List<String> p = new ArrayList();


	while(rs.next())
	{
		p.add(rs.getString(1));
	
	}
	
	closeConnection();
	
	return p;
	
	}





public boolean isNotComplet(String eleve,String mois, String type) throws ClassNotFoundException, SQLException
{
	
	openConnection();
	ResultSet rs=null;
	
	rs=stm.executeQuery("select * from moiseleve where eleve = '"+ eleve +"' and mois =(select id from mois where nom = '" + mois +"' ) and etat = '0'" );

	closeConnection();
	
	while(rs.next())
	{

		return true;
	}
	
	return false;
}


}

