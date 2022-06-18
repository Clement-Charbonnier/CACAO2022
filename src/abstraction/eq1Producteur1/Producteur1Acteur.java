package abstraction.eq1Producteur1;



import java.awt.Color;
import java.util.ArrayList;

import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.filiere.IFabricantChocolatDeMarque;
import abstraction.eq8Romu.filiere.IMarqueChocolat;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


public abstract class Producteur1Acteur implements IActeur, IFabricantChocolatDeMarque {
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Integer cryptogramme;
	protected Journal journal;
	
	
	//Auteur : Khéo
	public Producteur1Acteur() {
		super();
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.journal = new Journal(this.getNom()+" activites", this);
		
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "CAC'AO40";
	}

	public String getDescription() {
		return "Nous sommes la filière Cacaco";
	}

	public Color getColor() {
		return new Color(26, 188, 156);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	//Auteur : Khéo
	public void next() {

	}
	

	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTBOURSEEQ1"); 
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTBOURSEEQ1" : return new FiliereTestBourseEq1();
	    default : return null;
		}
	}
	
	public abstract Variable getStockBasse();
	public abstract Variable getStockHaut_BE();
	public abstract Variable getStockMoyenne();
	public abstract Variable getStockMoyenne_BE();
	public abstract Variable getStockBasse_NA();
	public abstract Variable getStockHaut_BE_NA();
	public abstract Variable getStockMoyenne_NA();
	public abstract Variable getStockMoyenne_BE_NA();

	
	//Auteur : Khéo
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.getStockBasse());
		res.add(this.getStockHaut_BE());
		res.add(this.getStockMoyenne());
		res.add(this.getStockMoyenne_BE());
		
		res.add(this.getStockBasse_NA());
		res.add(this.getStockHaut_BE_NA());
		res.add(this.getStockMoyenne_NA());
		res.add(this.getStockMoyenne_BE_NA());
		
		return res;
	}
	
	public abstract Variable getPrixEntretienArbre();
	
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.getPrixEntretienArbre());
	
		return res; 
	}


	
	
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("Le Stonks n'était qu'un rêve "+this.getNom());
		} else {
			System.out.println("Poor "+acteur.getNom()+"... Why so serious ? "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}
	
	/**
	 * @return the nB_INSTANCES
	 */
	public static int getNB_INSTANCES() {
		return NB_INSTANCES;
	}
	
	public List<ChocolatDeMarque> getChocolatsProduits(){
		List<ChocolatDeMarque> cm=new ArrayList<ChocolatDeMarque>();
		cm.add(new ChocolatDeMarque(Chocolat.MQ_O,"CHOCO'riginal"));
		cm.add(new ChocolatDeMarque(Chocolat.MQ,"CHOCO'riginal"));
		cm.add(new ChocolatDeMarque(Chocolat.BQ, "O'ptella"));
		return cm;
	}
	
	
}