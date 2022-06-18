package abstraction.eq1Producteur1;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public class ChocolatProducteur1 {
	private int ut_debut; //Plus pratique puisqu'on a pas besoin d'actualiser
	private boolean perime;
	private double poids;
	private Parc provenance;
	
	
//Auteur : Laure ; Modificateur : Khéo
	public ChocolatProducteur1(double poids, Parc provenance) {
		this.ut_debut = Filiere.LA_FILIERE.getEtape();
		this.perime = false;
		this.poids = poids;
		this.provenance = provenance;
	}

	/**
	 * @param age
	 * @param perime
	 */
	public ChocolatProducteur1(int ut_debut, boolean perime,double poids) { 
		this.ut_debut = ut_debut;
		this.perime = perime;
		this.poids = poids;
	}

	public int getAge() {
		return Filiere.LA_FILIERE.getEtape()-this.getUt_debut();
	}
	
	public void setUt_debut(int ut_debut) {
		this.ut_debut=ut_debut;
	}
	
	/**
	 * @return the ut_debut
	 */
	public int getUt_debut() {
		return this.ut_debut;
	}

	/**
	 * @return the provenance
	 */
	public Parc getProvenance() {
		return provenance;
	}

	/**
	 * @return the poids
	 */
	public double getPoids() {
		return this.poids;
	}

	/**
	 * @param poids the poids to set
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}



	/**
	 * @return the perime
	 */
	public boolean isPerime() {
		return this.perime;
	}
	/**
	 * @param perime the perime to set
	 */
	public void setPerime(boolean perime) {
		this.perime = perime;
	}
	
	
	
	//Auteur : Khéo
	//Modifié par : Antoine 
	//La durée de péremption des fèves est de 2 ans
	public void MajPeremption() {  
		if (this.getAge()>Filiere.LA_FILIERE.getIndicateur("dureePeremption").getValeur()) {
			this.setPerime(true);
		}
	}
	

	
}
