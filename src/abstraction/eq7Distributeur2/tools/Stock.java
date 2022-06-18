package abstraction.eq7Distributeur2.tools;

import java.util.List;
import java.util.HashMap;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.filiere.IActeur;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

/**
 * Classe Stock Biofour -
 * Utilise la classe Variable afin de suivre l'état des stocks
 * @author mgloc
 * 
 */
public class Stock implements IStock{

	private HashMap<ChocolatDeMarque,Variable> reserve;
	private HashMap<ChocolatDeMarque,Variable> seuilRachat;
	private IActeur acteur;
	private boolean FONCTIONEL;
	private double StockInit=10E5;
	
	/**Méthode constructeur, initialise le stock vide
	 * @param acteur
	 */
	public Stock(IActeur acteur,List<ChocolatDeMarque> listeChocolatsProduits) {
		//Si aucun chocolat n'est vendu sur le marché
		if (listeChocolatsProduits.isEmpty()) {
			this.FONCTIONEL= false; 
			System.out.println("Les stocks de Biofour ne sont pas fonctionnel : Aucun chocolat n'est produit sur le marché");
		} else {
			this.FONCTIONEL = true;
		}
		
		//Initialisation des stocks physique
		reserve = new HashMap<ChocolatDeMarque,Variable>();
		//Initialisation du seuil de rachat de stock
		seuilRachat = new HashMap<ChocolatDeMarque,Variable>();
		
		//Initialisation des deux à 0
		for (ChocolatDeMarque choco : listeChocolatsProduits) {
			reserve.put(choco, new Variable("Stock"+choco.toString(),"Valeur du stock pour "+choco.toString(),acteur,0.,Double.POSITIVE_INFINITY,StockInit));
			seuilRachat.put(choco,new Variable("SeuilRachat"+choco.toString(),"Seuil de rachat défini pour "+choco.toString(),acteur,-1.,Double.POSITIVE_INFINITY,1000));
		}
		this.acteur = acteur;
		
	}
	public Stock() {
		this(null,Filiere.LA_FILIERE.getChocolatsProduits());
	}
	
	public IActeur getActeur() {
		return this.acteur;
	}
	
	
	public double getQuantite(ChocolatDeMarque chocolat) {
		if (!this.FONCTIONEL) {return 0.0;}
		
		return reserve.get(chocolat).getValeur();
	}
	
	private void setQuantite(ChocolatDeMarque chocolat,double quantite) {
		if (!this.FONCTIONEL) {return ;}
		reserve.get(chocolat).setValeur(this.acteur, quantite);
	}
	
	public void addProduit(ChocolatDeMarque chocolat, Double quantite) {
		if (!this.FONCTIONEL) {return ;}
		double quantiteActuelle = this.getQuantite(chocolat);
		this.setQuantite(chocolat, quantiteActuelle + quantite);
	}

	public void remove(ChocolatDeMarque chocolat, Double quantite) {
		if (!this.FONCTIONEL) {return ;}
		//This method allow negative stock
		this.addProduit(chocolat, -quantite);
	}
	
	//-----------------------------SUM-------------------------------------------
	public double getQuantiteTotale() {
		double somme = 0;
		for (Variable v : reserve.values()) {
			somme = somme + v.getValeur();
		}
		return somme;
	}
	
	
	//-----------------------------SEUIL-------------------------------------------
	public double getSeuilRachat(ChocolatDeMarque chocolat) {
		if (!this.FONCTIONEL) {return 0.0;}
		return seuilRachat.get(chocolat).getValeur();
	}

	public void setSeuilRachat(ChocolatDeMarque chocolat, Double seuil) {
		if (!this.FONCTIONEL) {return ;}
		seuilRachat.get(chocolat).setValeur(this.acteur, seuil);
	}
	
	
	
}