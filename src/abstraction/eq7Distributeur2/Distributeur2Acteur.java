package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq7Distributeur2.examples.FiliereTestCCBiofour;
import abstraction.eq7Distributeur2.tools.IStock;
import abstraction.eq7Distributeur2.tools.Stock;
import abstraction.eq7Distributeur2.tools.Tracker;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;


public class Distributeur2Acteur implements IActeur{

	protected int cryptogramme;
	
	//Stock
	protected IStock stock;
	
	//Tracker
	protected Tracker venteTracker;
	
	//Liste des chocolats sur le marché
	protected List<ChocolatDeMarque> chocolats;
	
	//Journaux
	protected Journal journalContratCadre;
	protected Journal journalStock;
	protected Journal journalVente;
	protected Journal journalEtudeVente;
	protected Journal journalNegociationContratCadre;
	protected Journal journal;
	protected Journal journalPrix;
	
	//Indicateurs
	private List<Variable> listeIndicateur;
	private Variable stock_BQ;
	private Variable stock_BQ_O;
	private Variable stock_MQ;
	private Variable stock_MQ_O;
	private Variable stock_MQ_B;
	private Variable stock_MQ_B_O;
	private Variable stock_HQ;
	private Variable stock_HQ_O;
	private Variable stock_HQ_B;
	private Variable stock_HQ_B_O;
	private Variable stockTotal;
	protected Variable totalVente;

	public Distributeur2Acteur() {
		this.initialiserJournaux();
		this.initialiserIndicateurs();
	}

	public String getNom() {
		return "Biofour";
	}

	public String getDescription() {
		return "Du bon bio la mmh...";
	}

	public Color getColor() {
		return new Color(1,81,8); 
	}
	
	public Color getColorSuccess() {
		return new Color(0,255,0); 
	}
	
	public Color getColorFaillure() {
		return new Color(255,0,0); 
	}
	
	public Color getColorYellow() {
		return new Color(240,230,140); 
	}
	
	public String getColoredName(IActeur v) {
		if (v == this) {return Journal.texteColore(getColor(), getColorYellow(), v.getNom());}
		return Journal.texteColore(v, v.getNom());
	}
	
	public String yellow(String s) {
		return Journal.texteColore(getColorYellow(), Color.BLACK,s);
	}
	
	public String red(String s) {
		return Journal.texteColore(getColorFaillure(), Color.BLACK,s);
	}
	
	public String green(String s) {
		return Journal.texteColore(getColorSuccess(), Color.BLACK,s);
	}


	public void initialiser() {
		//Initialiser stock
		this.chocolats = Filiere.LA_FILIERE.getChocolatsProduits();
		System.out.println("Liste des chocolats en vente sur le marché : "+chocolats);
		this.stock = new Stock(this,this.chocolats);
		this.venteTracker = new Tracker(chocolats);
		this.actualiserIndicateurs();
		this.journalStock.ajouter("Stock initial: "+this.stock.getQuantiteTotale());
		
	}
	
	public void next() {
		this.actualiserIndicateurs();
	}

	
	// Renvoie la liste des filières proposées par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTCCBiofour");
		return(filieres);
	}

	// Renvoie une instance d'une filière d'après son nom
	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTCCBiofour" : return new FiliereTestCCBiofour();
	    default : return null;
		}
	}
	
	//-----------------------------INDICATEURS-------------------------------------------

	public void initialiserIndicateurs() {
		this.stock_BQ = new Variable ("Stock de chocolat BQ", this, 0);
		this.stock_BQ_O = new Variable ("Stock de chocolat BQ Original", this, 0);
		this.stock_MQ = new Variable ("Stock de chocolat MQ", this, 0);
		this.stock_MQ_O = new Variable ("Stock de chocolat MQ Original", this, 0);
		this.stock_MQ_B = new Variable ("Stock de chocolat MQ Bio", this, 0);
		this.stock_MQ_B_O = new Variable ("Stock de chocolat MQ Bioriginal", this, 0);
		this.stock_HQ = new Variable ("Stock de chocolat HQ", this, 0);
		this.stock_HQ_O = new Variable ("Stock de chocolat HQ Original", this, 0);
		this.stock_HQ_B = new Variable ("Stock de chocolat HQ Bio", this, 0);
		this.stock_HQ_B_O = new Variable ("Stock de chocolat HQ Bioriginal", this, 0);
		this.stockTotal = new Variable("Biofour : Stock total",this,0);
		this.totalVente = new Variable("Biofour : Total des ventes", this, 0);
	}
	
	public void actualiserIndicateurs() {
		this.chocolats = Filiere.LA_FILIERE.getChocolatsProduits();
		
		double stock_HQ_B_O = 0;
		double stock_HQ_B = 0;
		double stock_HQ_O = 0;
		double stock_HQ = 0;
		double stock_MQ_B_O = 0;
		double stock_MQ_B = 0;
		double stock_MQ_O = 0;
		double stock_MQ = 0;
		double stock_BQ_O = 0;
		double stock_BQ = 0;
		double stockTot = 0;
		
		for (ChocolatDeMarque choco : chocolats) {
			if(choco.getGamme()==Gamme.HAUTE && choco.isBioEquitable() && choco.isOriginal()) {
				stock_HQ_B_O+=this.stock.getQuantite(choco);
			}
			if(choco.getGamme()==Gamme.HAUTE && choco.isBioEquitable()) {
				stock_HQ_B+=this.stock.getQuantite(choco);
			}
			if(choco.getGamme()==Gamme.HAUTE && choco.isOriginal()) {
				stock_HQ_O+=this.stock.getQuantite(choco);
				this.stock_HQ_O.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.HAUTE) {
				stock_HQ+=this.stock.getQuantite(choco);
				this.stock_HQ.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.MOYENNE && choco.isBioEquitable() && choco.isOriginal()) {
				stock_MQ_B_O+=this.stock.getQuantite(choco);
				this.stock_MQ_B_O.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.MOYENNE && choco.isBioEquitable()) {
				stock_MQ_B+=this.stock.getQuantite(choco);
				this.stock_MQ_B.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.MOYENNE && choco.isOriginal()) {
				stock_MQ_O+=this.stock.getQuantite(choco);
				this.stock_MQ_O.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.MOYENNE) {
				stock_MQ+=this.stock.getQuantite(choco);
				this.stock_MQ.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.BASSE && choco.isOriginal()) {
				stock_BQ_O+=this.stock.getQuantite(choco);
				this.stock_BQ_O.setValeur(this,this.stock.getQuantite(choco));;
			}
			if(choco.getGamme()==Gamme.BASSE) {
				stock_BQ+=this.stock.getQuantite(choco);
				this.stock_BQ.setValeur(this,this.stock.getQuantite(choco));;
			}
		}
		this.stock_HQ_B_O.setValeur(this,stock_HQ_B_O);
		this.stock_HQ_B.setValeur(this,stock_HQ_B);
		this.stock_HQ_O.setValeur(this,stock_HQ_O);
		this.stock_HQ.setValeur(this,stock_HQ);
		this.stock_MQ_B_O.setValeur(this,stock_MQ_B_O);
		this.stock_MQ_B.setValeur(this,stock_MQ_B);
		this.stock_MQ_O.setValeur(this,stock_MQ_O);
		this.stock_MQ.setValeur(this,stock_MQ);
		this.stock_BQ_O.setValeur(this,stock_BQ_O);
		this.stock_BQ.setValeur(this,stock_BQ);
		stockTot=stock_HQ_B_O+stock_HQ_B+stock_HQ_O+stock_HQ+stock_MQ_B_O+stock_MQ_B+stock_MQ_O+stock_MQ+stock_BQ_O+stock_BQ;
		
		
		this.stockTotal.setValeur(this,stockTot);
	}
	
	// Renvoie les indicateurs
	public List<Variable> getIndicateurs() {
		this.listeIndicateur = new LinkedList<Variable>();
		
		this.listeIndicateur.add(stock_BQ);
		this.listeIndicateur.add(stock_BQ_O);
		this.listeIndicateur.add(stock_MQ);
		this.listeIndicateur.add(stock_MQ_O);
		this.listeIndicateur.add(stock_MQ_B);
		this.listeIndicateur.add(stock_MQ_B_O);
		this.listeIndicateur.add(stock_HQ);
		this.listeIndicateur.add(stock_HQ_O);
		this.listeIndicateur.add(stock_HQ_B);
		this.listeIndicateur.add(stock_HQ_B_O);
		this.listeIndicateur.add(stockTotal);
		this.listeIndicateur.add(totalVente);	
		return listeIndicateur;
	}
	
	//-----------------------------PARAMETRES-------------------------------------------

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	//-----------------------------JOURNAUX-------------------------------------------
	
	public void initialiserJournaux() {
		this.journalContratCadre = new Journal(this.getNom()+" Contrat Cadres", this);
		this.journalNegociationContratCadre = new Journal(this.getNom()+" Contrat Cadres (Détail négociation)", this);
		this.journalStock = new Journal(this.getNom()+" Stock", this);
		this.journalVente = new Journal(this.getNom()+" Vente (Client Final)", this);
		this.journalEtudeVente = new Journal(this.getNom()+" Etude des ventes (Marché)", this);
		this.journal = new Journal(this.getNom()+" Activité", this);
		this.journalPrix = new Journal(this.getNom()+" Suivi des prix de ventes",this);
	}
	
	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		j.add(this.journalContratCadre);
		j.add(this.journalNegociationContratCadre);
		j.add(this.journalStock);
		j.add(this.journalEtudeVente);
		j.add(this.journalVente);
		j.add(journalPrix);
		
		return j;
	}

	//-----------------------------MISC-------------------------------------------


	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}

	public void notificationFaillite(IActeur acteur) {
		System.out.println("F#*! you "+acteur.getNom()+". You won't miss Biofour team");
	}

	public void notificationOperationBancaire(double montant) {
	}
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

}