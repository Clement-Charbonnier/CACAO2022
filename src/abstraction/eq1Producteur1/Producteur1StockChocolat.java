package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;


public class Producteur1StockChocolat extends ProducteurActeur1VenteBourse {
	private  HashMap<Chocolat,List<ChocolatProducteur1>> Chocolats;
	private Variable StockBQ ;
	private Variable StockMQ ;
	private Variable StockMQ_BE ;
	
	
	public Producteur1StockChocolat() {
		super();
		
		this.Chocolats = new HashMap<Chocolat, List<ChocolatProducteur1>>();
		Chocolats.put(Chocolat.BQ, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.BQ_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ_BE, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ_BE_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.HQ_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ_BE, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ_BE_O, new ArrayList<ChocolatProducteur1>());
		Chocolats.put(Chocolat.MQ_O, new ArrayList<ChocolatProducteur1>());
		
		this.StockBQ= new Variable(this.getNom()+"StockCHOCO_BQ", "Stock de Chocolat BQ", 
				this, 0, 1000000000, this.getStock(Chocolat.BQ));
		this.StockMQ= new Variable(this.getNom()+"StockCHOCO_MQ", "Stock de Chocolat MQ", 
				this, 0, 1000000000, this.getStock(Chocolat.MQ));
		this.StockMQ_BE= new Variable(this.getNom()+"StockCHOCO_MQ_BE", "Stock de Chocolat MQ_BE", 
				this, 0, 1000000000, this.getStock(Chocolat.MQ_BE));
	}
	
	
	public void next(){
		super.next();
		this.MAJStock();
		
		//Mis à jour Variable
		this.StockBQ.setValeur(this, this.getStock(Chocolat.BQ));
		this.StockMQ.setValeur(this, this.getStock(Chocolat.MQ));
		this.StockMQ_BE.setValeur(this, this.getStock(Chocolat.MQ_BE));
		
		
	}
	
	public void initialiser() {
		super.initialiser();
		this.addLot(Chocolat.BQ, 30000);
		}

	public void addLot(Chocolat c, double quantite) {
		this.getChocolats().get(c).add(new ChocolatProducteur1(quantite, this.getGhana()));

	}

	public double getStock(Chocolat c){
		
		double somme = 0.0 ;
		for(ChocolatProducteur1 Lot : this.getChocolats().get(c)) {
			somme = somme + Lot.getPoids() ;
		}
		return somme ;	
	}
	
	
public double getStockParc(Chocolat c, Parc provenance){
		
		double somme = 0.0 ;
		for(ChocolatProducteur1 Lot : this.getChocolats().get(c)) {
			if (Lot.getProvenance() == provenance) {
				somme = somme + Lot.getPoids() ;
			}
		}
		return somme ;	
	}
	
	public void retirerQuantite(Chocolat c, double quantite) {
		while (quantite>0) {
			double poids = this.getChocolats().get(c).get(0).getPoids() ;
			
			if (quantite >= poids) { //On regarde en fonction de la quantite qui reste dans le lot. Si il y'a plus on enlève le lot
				quantite = quantite - poids ;
				this.getChocolats().get(c).remove(0);
			}
			else {
				this.getChocolats().get(c).get(0).setPoids(poids-quantite); //Si non, on ajuste le poids du lot
				quantite = 0 ;
			}
		}
	}
	
	public void MAJStock() {
		
		for(Chocolat chocolat : this.getChocolats().keySet()) {
			for (int i=0; i< this.getChocolats().get(chocolat).size(); i++) {
				this.getChocolats().get(chocolat).get(i).MajPeremption();
				
				if (this.getChocolats().get(chocolat).get(i).isPerime()) { //On retire du stock si c'est périmée
					this.getChocolats().get(chocolat).remove(i);
				}
			}
		}
	}
	
	
	
	/**
	 * @return the stockMQ
	 */
	public Variable getStockMQ() {
		return StockMQ;
	}


	/**
	 * @return the stockMQ_BE
	 */
	public Variable getStockMQ_BE() {
		return StockMQ_BE;
	}


	/**
	 * @return the chocolats
	 */
	public HashMap<Chocolat, List<ChocolatProducteur1>> getChocolats() {
		return Chocolats;
	}


	/**
	 * @return the stockBQ
	 */
	public Variable getStockBQ() {
		return StockBQ;
	}
	
	
	
}
