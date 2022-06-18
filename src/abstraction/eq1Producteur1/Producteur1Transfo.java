package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Chocolat;

public class Producteur1Transfo extends Producteur1StockChocolat {
	
	
	/**
	 * 
	 */
	public Producteur1Transfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Auteur : Khéo
	public void nextTransformation(Feve f, boolean original) {
		double quantiteChoco = this.getRecolte().get(f)*0.05; //On transforme 5%% de notre quantité produite sur l'ut
		double prixTotal = quantiteChoco*Filiere.LA_FILIERE.getIndicateur("coutTransformation").getValeur();
		if (prixTotal>0) {
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
		}
		this.retirerQuantite(f, quantiteChoco);
		this.addLot(Chocolat.get(f.getGamme(), f.isBioEquitable(), original), quantiteChoco);
	}
	
	public void next() {
		super.next();
		this.nextTransformation(Feve.FEVE_BASSE, false);
		this.nextTransformation(Feve.FEVE_MOYENNE, false);
		this.nextTransformation(Feve.FEVE_MOYENNE_BIO_EQUITABLE, false);
	}
}
