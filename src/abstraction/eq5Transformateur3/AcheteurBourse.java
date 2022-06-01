package abstraction.eq5Transformateur3;

import abstraction.eq8Romu.bourseCacao.IAcheteurBourse;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;

public class AcheteurBourse  extends Transformateur3Acteur implements IAcheteurBourse{

	// Karla 
	public double demande(Feve f, double cours) {
		
		/* on calcule notre besoin en la fève f (en partant du principe que l'on fait que des transformations classiques)
		 * pour honorer nos contrats 
		 * */
		Double besoin = 0.00;
		for (ExemplaireContratCadre contrat : this.contratsEnCoursVente) {
			ChocolatDeMarque choco = (ChocolatDeMarque) contrat.getProduit() ;
			if (choco.getGamme() == f.getGamme() && choco.isBioEquitable() == f.isBioEquitable() ) {
				besoin += contrat.getQuantiteALivrerAuStep();
			}
		}
		
		/* Si notre stock permet de répondre au besoin, on n'achète pas, 
		 * sinon on achète  
		 */
		Double difference = besoin - this.stockFeves.getstock(f) ;
		if (difference > 0.0 ) {
			this.achats.ajouter("demande de" + difference + " kg de feve" + f.getGamme().toString() + "à un cours" + cours );
		}
		
		return difference ;
	}

	// Karla
	public void notificationAchat(Feve f, double quantiteEnKg, double coursEnEuroParKg) {
		this.stockFeves.ajouter(f, quantiteEnKg);
		this.achats.ajouter("Achat a la bourse de "+ quantiteEnKg +"kg  de " + f + " a "+ coursEnEuroParKg+ " euro par Kg");
	}

	// Karla
	public void notificationBlackList(int dureeEnStep) {	
	}

	public void next () {
		super.next();	
	}
	
}
