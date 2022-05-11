package abstraction.eq4Transformateur2; 

import abstraction.eq8Romu.appelsOffres.FiliereTestAO;
import abstraction.eq8Romu.appelsOffres.IVendeurAO;
import abstraction.eq8Romu.bourseCacao.FiliereTestBourse;
import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transformateur2Acteur implements IActeur {
	
	//pas sur de celles ci, mais je les laisse au cas ou...
	private Variable coutStockage;
	protected Variable prixSeuil; // au dela duquel nous n'achetons pas
	private Variable rendementTransfoLongue;
	private Variable prixTransformation; // a renseigner (0? On considere juste le rendement pour le pb des transfolongue, ainsi, un seul parametre à gerer.)
	private Variable prixChocoOriginal;
	private Variable capaciteStockage;
	private Variable capaciteStockageFixe;// stock que l'on souhaite en permanence
	private Variable expirationFeve; //a considerer dans une v1 ?
	private Variable expirationChoco;//a considerer dans une v1?

	private double marge;

	
	// variables pour l'achatAO
	private Stock<Feve> stockfeve;
	private Stock<ChocolatDeMarque> stockchocolat;
	protected double prixInit;// Lorsque l'on est acheteur d'une Appel d'Offre
	protected Journal journal;
	
	
	protected int cryptogramme;
	public static ChocolatDeMarque Test; //Gabriel? Supprimes?
	

	protected double NewCap;//à réinitialiser=cpacité de production au début de chaque tour



	

	
	
	//Nawfel
	public Transformateur2Acteur() { //valeurs des min, max, et init (3 derniers parametres) à changer plus tard.
	
		//pas sur de celles ci, mais je les laisse au cas ou...
		this.coutStockage = new Variable("cout stockage", "<html>Cout de stockage</html>",this, 0.0, 10.0, 3.0);
		this.prixSeuil = new Variable("prix seuil", "<html>Prix Seuil</html>",this, 0.0, 10.0, 3.0);
		this.rendementTransfoLongue=new Variable("rendement transfo longue", "<html>Rendement d'une transformation longue</html>",this, 0.0, 10.0, 3.0);
		this.prixTransformation = new Variable("prix transfo", "<html>Cout d'une transformation longue</html>",this, 0.0, 10.0, 3.0);
		this.prixChocoOriginal=new Variable("cout passage original", "<html>Cout pour passer à la gamme original</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockage=new Variable("capacite stockage", "<html>Capacite max de stockage</html>",this, 0.0, 10.0, 3.0);
		this.capaciteStockageFixe=new Variable("stock theorique desire", "<html>Stock Theorique désiré en permanence</html>",this, 0.0, 10.0, 3.0);
		this.expirationFeve=new Variable("expiration feve", "<html>Duree avant expiration d'une feve</html>",this, 0.0, 10.0, 3.0);
		this.expirationChoco=new Variable("expiration choco", "<html>Duree avant expiration du chocolat</html>",this, 0.0, 10.0, 3.0);
		this.marge = 1.1;

		
		
		
		this.prixInit=100; //arbitraire
		this.journal=new Journal(this.getNom()+" activites", this);
		
		ChocolatDeMarque chocomax=new ChocolatDeMarque(Chocolat.MQ,"Omax");
		HashMap<ChocolatDeMarque,Double> h1=new HashMap<ChocolatDeMarque,Double>();
		h1.put(chocomax, (double) 1000000);
		this.stockchocolat=new Stock(h1,1000000);
		
		
		//this.NewCap=Filiere.LA_FILIERE.getIndicateur("seuilTransformation").getValeur();

	}
	
	













	public void initialiser() {
	}
	
	public String getNom() {
		return "Opti'Cacao";
	}

	public String getDescription() {
		return "Aux petits soins pour vous";
	}

	public Color getColor() {
		return new Color(230, 126, 34);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	public void next() {
		this.journal.ajouter("== ETAPE "+Filiere.LA_FILIERE.getEtape()+" ==");
		if (this.stockchocolat.getQuantiteStock().keySet().size()>0) {
			for (ChocolatDeMarque c : this.stockchocolat.getQuantiteStock().keySet()) {
				this.journal.ajouter("stock de "+c+" : "+this.stockchocolat.getQuantiteStock().get(c));
			}
		}
	}
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filiere = new ArrayList<String>();
		filiere.add("TESTAO");  
		return filiere;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTAO" : return new CopieFiliereTestAO();
	    default : return null;
		}
	}
	
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}
	
	public List<Variable> getParametres() { // A completer avec tous les autres variables d'instances
		List<Variable> p= new ArrayList<Variable>();
//		p.add(this.qualiteHaute);
//		p.add(this.qualiteMoyenne);
//		p.add(this.qualiteBasse);
//		p.add(this.gainQualiteBioEquitable);
//		p.add(this.gainQualiteOriginal); 
//		p.add(this.partDeLaMarqueDansLaQualitePercu);
		return p;
	} 
	

	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		return j;
	}
	public double getCout() {
		return this.coutStockage.getValeur();
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("I'll be back... or not... "+this.getNom());
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}




	public Stock<Feve> getStockfeve() {
		return this.stockfeve;
	}




	public Stock<ChocolatDeMarque> getStockchocolat() {
		return this.stockchocolat;
	}
	public double getMarge() {
		return this.marge;
	}




	public int getCryptogramme() {
		return cryptogramme;
	}




	public Variable getCoutStockage() {
		return coutStockage;
	}




	public Variable getPrixSeuil() {
		return prixSeuil;
	}




	public Variable getRendementTransfoLongue() {
		return rendementTransfoLongue;
	}




	public Variable getPrixTransformation() {
		return prixTransformation;
	}




	public Variable getPrixChocoOriginal() {
		return prixChocoOriginal;
	}




	public Variable getCapaciteStockage() {
		return capaciteStockage;
	}




	public Variable getCapaciteStockageFixe() {
		return capaciteStockageFixe;
	}




	public Variable getExpirationFeve() {
		return expirationFeve;
	}




	public Variable getExpirationChoco() {
		return expirationChoco;
	}

}