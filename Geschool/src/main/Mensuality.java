package main;

public class Mensuality {

	public Mensuality(String nom, String prenom, String montant, String date, String mois, String paiement,String type) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.montant = montant;
		Date = date;
		this.mois = mois;
		this.paiement = paiement;
		this.type = type;
	}

	
    private String type;
	private String nom;
	private String prenom;
	private String montant;
	private String Date;
	private String mois;
	private String paiement;
	public Mensuality() {}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getPaiement() {
		return paiement;
	}

	public void setPaiement(String paiement) {
		this.paiement = paiement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
