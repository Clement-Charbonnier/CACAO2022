package abstraction.eq1Producteur1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.bourseCacao.BourseCacao;
import abstraction.eq8Romu.filiere.Filiere;


import abstraction.eq8Romu.general.Journal;

import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;

public abstract class Producteur1Producteur extends Producteur1Stock{
	private LinkedList<Parc> ListeParc;
	private HashMap<Feve, Double> recolte;
	private int mecontentement_global_basse;
	private int mecontentement_global_moyenne;
	private int mecontentement_global_haute;
	private double vente_tot;
	
	public Producteur1Producteur() {
		super();
		ListeParc = new LinkedList<Parc>();
		Parc ghana = new Parc("Ghana", this);
		Parc cote_divoire = new Parc ("Côte d'Ivoire", this);
		Parc nigeria = new Parc("Nigéria", this);
		Parc cameroun = new Parc("Cameroun", this);
		ListeParc.add(ghana);
		ListeParc.add(cote_divoire);
		ListeParc.add(nigeria);
		ListeParc.add(cameroun);
		this.mecontentement_global_basse = 0;
		this.mecontentement_global_moyenne = 0;
		this.mecontentement_global_haute =0;
	}
	 
	public Parc getGhana() { //Écrit par Antoine
		return ListeParc.get(0);
	}
	
	public Parc getCote_divoire() { //Écrit par Antoine
		return ListeParc.get(1);
	}
	
	public Parc getNigeria() { //Écrit par Antoine
		return ListeParc.get(2); 
	}
	
	public Parc getCameroun() { //Écrit par Antoine
		return ListeParc.get(3);
	}
	
	public Parc getParc(int i) { //Écrit par Antoine
		return ListeParc.get(i);
	}

	public LinkedList<Parc> getListeParc() { //Écrit par Antoine
		return this.ListeParc;
	}
	
	public int getMecontentement_basse() { //Écrit par Antoine
		return this.mecontentement_global_basse;
	}
	
	public int getMecontentement_moyenne() { //Écrit par Antoine
		return this.mecontentement_global_moyenne;
	}
	
	public int getMecontentement_haute() { //Écrit par Antoine
		return this.mecontentement_global_haute;
	}
	
	public double getVente_tot() { //Écrit par Antoine
		return this.vente_tot;
	}
	
	public void setMecontentement_basse(int i) { //Écrit par Antoine
		if (i<0) {
			this.mecontentement_global_basse = 0;
		}
		else {
			this.mecontentement_global_basse = i;
		}
	}
	
	public void setMecontentement_moyenne(int i) { //Écrit par Antoine
		if (i<0) {
			this.mecontentement_global_moyenne = 0;
		}
		else {
			this.mecontentement_global_moyenne = i;
		}
	}
	
	public void setMecontentement_haute(int i) { //Écrit par Antoine
		if (i<0) {
			this.mecontentement_global_haute = 0;
		}
		else {
			this.mecontentement_global_haute = i;
		}
	}
	
	public void setVente_tot(double d) { //Écrit par Antoine
		this.vente_tot =d;
	}
	
	//Écrit par Antoine
	public void initialiser() {
		super.initialiser();
		int nombre_arbre_debut = 600000; //Nombre d'arbres total à répartir sur les parcs
		double pourcentage_nBE_basse = 0.63;
		double pourcentage_nBE_moyenne = 0.27;
		double pourcentage_nBE_haute = 0;
		double pourcentage_BE_moyenne = 0.05;
		// On calcule le nombre d'arbres par parc à répartir en fonction des pourcentages définis au-dessus
		int nombre_arbre_ghana = (int)Math.floor(nombre_arbre_debut*0.62);
		int nombre_arbre_cote_divoire = (int)Math.floor(nombre_arbre_debut*0.23);
		int nombre_arbre_nigeria = (int)Math.floor(nombre_arbre_debut*0.07);
		int nombre_arbre_cameroun = (int)Math.floor(nombre_arbre_debut*0.08);
		LinkedList<Integer> NbArbres = new LinkedList<Integer>();
		NbArbres.add(nombre_arbre_ghana);
		NbArbres.add(nombre_arbre_cote_divoire);
		NbArbres.add(nombre_arbre_nigeria);
		NbArbres.add(nombre_arbre_cameroun);
		int ut_debut = -500;
		int écart_moyenne = 100;
		for (int j=0;j<ListeParc.size();j++) {
			Parc Parc_j = this.getParc(j);
			// On calcule le nombre d'arbres à planter en fonction de leur qualité et de BE ou pas
			int nombre_arbre_nBE_basse = (int)Math.floor((pourcentage_nBE_basse*NbArbres.get(j)));
			int nombre_arbre_nBE_moyenne = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne)*NbArbres.get(j));
			int nombre_arbre_nBE_haute = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne+pourcentage_nBE_haute)*NbArbres.get(j));
			int nombre_arbre_BE_moyenne = (int)Math.floor((pourcentage_nBE_basse+pourcentage_nBE_moyenne+pourcentage_nBE_haute+pourcentage_BE_moyenne)*NbArbres.get(j));
			for (int i=0;i<NbArbres.get(j);i++) {
				int d = (int)Math.random()*écart_moyenne;
				if (i<nombre_arbre_nBE_basse) {
					//On plante des arbres de basse qualité, non BE, pas en coopérative
					Parc_j.Planter(new MilleArbre(1,false,false,ut_debut-d)); 
				}
				if ((i>=nombre_arbre_nBE_basse) && (i<nombre_arbre_nBE_moyenne)) {
					//On plante des arbres de moyenne qualité, non BE, pas en coopérative
					Parc_j.Planter(new MilleArbre(2,false,false,ut_debut-d));
				}
				if ((i>=nombre_arbre_nBE_moyenne) && (i<nombre_arbre_nBE_haute)) {
					//On plante des arbres de haute qualité, non BE, pas en coopérative
					Parc_j.Planter(new MilleArbre(3,false,false,ut_debut-d));
				}
				if ((i>=nombre_arbre_nBE_haute) && (i<nombre_arbre_BE_moyenne)) {
					//On plante des arbres de moyenne qualité, BE, en coopérative
					Parc_j.Planter(new MilleArbre(2,false,true,ut_debut-d));
				}
				if ((i>=nombre_arbre_BE_moyenne)) {
					//On plante des arbres de haute qualité, BE, en coopérative
					Parc_j.Planter(new MilleArbre(3,false,true,ut_debut-d));
				}
			}
		}
	}
	
	public abstract HashMap<Feve, Double> getPrixmoyenFeve();
	
	public abstract HashMap<Parc, Double> getRepartitionGuerre();
	
	public Double getVente_tot_choco(HashMap<Feve, Double> venteChoco) { //Écrit par Antoine
		//Renvoie la masse totale de chocolat vendue
		Double vente_tot = 0.0;
		for (Feve f : venteChoco.keySet()) {
			vente_tot += venteChoco.get(f);
		}
		return vente_tot;
	}
	
	public int MilleArbresAPlanter(Parc p, HashMap<Feve, Double> venteChoco) { //Écrit par Antoine
		//Donne le nombre de MilleArbres à planter par parc en fonction de l'évolution de la demande
		double vente_tot = getVente_tot_choco(venteChoco);
		double augmentation_max = 0.02;
		int nb_arbre = p.getNb_arbres_tot();
		if (this.getVente_tot()<vente_tot) {
			double diff = (vente_tot-this.getVente_tot())/vente_tot;
			this.setVente_tot(vente_tot);
			if (diff<augmentation_max) {
				return (int)Math.floor(nb_arbre*diff);
			}
			else {
				return (int)Math.floor(nb_arbre*augmentation_max);
			}
		}
		else {
			return 0;
		}
	}
	
	public void MAJMecontentement() { 
		//Met à jour le mécontentement lié à la vente
		
		//Récupération prix actuels de la bourse
		BourseCacao bourse = (BourseCacao)(Filiere.LA_FILIERE.getActeur("BourseCacao"));
		double cours_feve_basse = bourse.getCours(Feve.FEVE_BASSE).getValeur();
		double cours_feve_moyenne = bourse.getCours(Feve.FEVE_MOYENNE).getValeur();
		double cours_feve_haute = bourse.getCours(Feve.FEVE_HAUTE).getValeur();
		int parametre_min_max = 5;

		HashMap<Feve, Double> prixmoyen = new HashMap<Feve, Double>();
		

		for (Feve f : this.getFeves().keySet()) {
			prixmoyen.put(f, this.getPrixmoyenFeve().get(f)/(Filiere.LA_FILIERE.getEtape()));
		}
		

		this.setMecontentement_basse((int)Math.floor(10*((prixmoyen.get(Feve.FEVE_BASSE)-cours_feve_basse)/prixmoyen.get(Feve.FEVE_BASSE))));
		this.setMecontentement_moyenne((int)Math.floor(10*((prixmoyen.get(Feve.FEVE_MOYENNE)-cours_feve_moyenne)/prixmoyen.get(Feve.FEVE_MOYENNE))));
		this.setMecontentement_haute((int)Math.floor(10*((prixmoyen.get(Feve.FEVE_HAUTE)-cours_feve_haute)/prixmoyen.get(Feve.FEVE_HAUTE))));
		//System.out.println(cours_feve_basse + "   " + prixmoyen.get(Feve.FEVE_BASSE) + "   " + 10*((prixmoyen.get(Feve.FEVE_BASSE)-cours_feve_basse)/prixmoyen.get(Feve.FEVE_BASSE)));

		
		//Si le prix de la bourse dépasse le maximum/passe sous le minimum on met à jour le mécontentement en conséquence
		if (cours_feve_basse <= bourse.getCours(Feve.FEVE_BASSE).getMin()) {
			this.setMecontentement_basse(this.getMecontentement_basse()+parametre_min_max);
		}
		if (cours_feve_moyenne <= bourse.getCours(Feve.FEVE_MOYENNE).getMin()) {
			this.setMecontentement_moyenne(this.getMecontentement_moyenne()+parametre_min_max);
		}
		if (cours_feve_haute <= bourse.getCours(Feve.FEVE_HAUTE).getMin()) {
			this.setMecontentement_haute(this.getMecontentement_haute()+parametre_min_max);
		}
		if (cours_feve_basse >= bourse.getCours(Feve.FEVE_BASSE).getMax()) {
			this.setMecontentement_basse(this.getMecontentement_basse()-parametre_min_max);
		}
		if (cours_feve_moyenne >= bourse.getCours(Feve.FEVE_MOYENNE).getMax()) {
			this.setMecontentement_moyenne(this.getMecontentement_moyenne()-parametre_min_max);
		}
		if (cours_feve_haute >= bourse.getCours(Feve.FEVE_HAUTE).getMax()) {
			this.setMecontentement_haute(this.getMecontentement_haute()-parametre_min_max);
		}
	}
	
	public HashMap<Feve, Double> getVenteChoco(boolean en_pourcentage) { //Écrit par Antoine
		//renvoie la masse de chocolat vendue, par type de Feve, en pourcentage ou non 
		List<ChocolatDeMarque> chocolats = Filiere.LA_FILIERE.getChocolatsProduits();
		HashMap<Feve, Double> dicoVente = new HashMap<Feve, Double>();
		dicoVente.put(Feve.FEVE_BASSE, 0.0);
		dicoVente.put(Feve.FEVE_MOYENNE, 0.0);
		dicoVente.put(Feve.FEVE_HAUTE, 0.0);
		dicoVente.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE, 0.0);
		dicoVente.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, 0.0);
		for (int i=0; i<chocolats.size();i++) {
			ChocolatDeMarque chocolat_i = chocolats.get(i);
			Gamme gamme = chocolat_i.getGamme();
			boolean BE = chocolat_i.isBioEquitable();
			double vente = Filiere.LA_FILIERE.getVentes(chocolat_i, Filiere.LA_FILIERE.getEtape()-1);
			if ((gamme==Gamme.BASSE) && (BE==false)) {
				dicoVente.replace(Feve.FEVE_BASSE, dicoVente.get(Feve.FEVE_BASSE) + vente);
			}
			if ((gamme==Gamme.MOYENNE) && (BE==false)) {
				dicoVente.replace(Feve.FEVE_MOYENNE, dicoVente.get(Feve.FEVE_MOYENNE) + vente);
			}
			if ((gamme==Gamme.MOYENNE) && (BE)) {
				dicoVente.replace(Feve.FEVE_MOYENNE_BIO_EQUITABLE, dicoVente.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE) + vente);
			}
			if ((gamme==Gamme.HAUTE) && (BE==false)) {
				dicoVente.replace(Feve.FEVE_HAUTE, dicoVente.get(Feve.FEVE_HAUTE) + vente);
			}
			if ((gamme==Gamme.HAUTE) && (BE)) {
				dicoVente.replace(Feve.FEVE_HAUTE_BIO_EQUITABLE, dicoVente.get(Feve.FEVE_HAUTE_BIO_EQUITABLE) + vente);
			}
		}
		
		if (en_pourcentage) {
			double ventes_totales = 0.0;
			for (Feve f : this.getFeves().keySet()) {
				ventes_totales += dicoVente.get(f);
			}
			for (Feve f : this.getFeves().keySet()) {
				dicoVente.replace(f, dicoVente.get(f)/ventes_totales);
			}
			return dicoVente;
		}
		else {
			return dicoVente;
		}
	}

	public void next() { //Écrit par Antoine
		super.next();
		HashMap<Feve, Double> venteChoco_pourcentage = this.getVenteChoco(true);
		HashMap<Feve, Double> venteChoco = this.getVenteChoco(false);
		HashMap<Parc, Double> repartitionGuerre = this.getRepartitionGuerre();
		for (int j=0; j<ListeParc.size();j++) {
			// booleen pour savoir si le parc vend du chocolat durant cet UT (nécessite de ne pas être en guerre ET de vendre)
			boolean vente = false;
			Parc parc_j = this.getParc(j);
			parc_j.MAJAleas();
			
			//on ajoute la récolte de chaque parc au stock (le fait que l'on soit en période d'aléa climatique est géré dans la fonction récolte)
			this.recolte = parc_j.Recolte();
			for (Feve f : this.getFeves().keySet()) {
				if (this.recolte.get(f) > 0) {
					this.addLot(f, this.recolte.get(f), parc_j);
				}
			}
			
			// Si le parc vend(=est présent dans la HashMap) on prend en compte le mécontentement lié à la vente
			if (repartitionGuerre != null) {
				for (Parc p : repartitionGuerre.keySet()) {
					if (parc_j == p) {
						parc_j.MAJParc(this.getMecontentement_basse(),this.getMecontentement_moyenne(),this.getMecontentement_haute(),venteChoco_pourcentage);
						vente = true;
					}
				}
			}
			
			//Si le parc ne vend pas on ne prend pas en compte le mécontentement lié à la vente
			if (vente==false || repartitionGuerre == null) {
				parc_j.MAJParc(0,0,0,venteChoco_pourcentage);
			}
			
			//Chaque année, on regarde comment a évolué la demande et on augmente le nombre d'arbre de chaque parc en conséquence, augmentation totale < 1%
			if (Filiere.LA_FILIERE.getEtape()%24==0) {
				int arbreAPlanter = MilleArbresAPlanter(parc_j, venteChoco);
				parc_j.Planter(arbreAPlanter, venteChoco, false);
			}
			
			parc_j.MAJGuerre();
		}
		
		if (Filiere.LA_FILIERE.getEtape()>0) {
			this.MAJMecontentement();
		}
		
		
		double prixTotal = 0 ;
		//Calcul du Prix Total de Stockage
		for (Feve f : this.getFeves().keySet()) {
			prixTotal = prixTotal + (this.getStock(f, true)*Filiere.LA_FILIERE.getParametre("Prix Stockage").getValeur()) ;
		}
		
		//Calcul Prix Entretien Arbre
		
		for (int j=0;j<4;j++) {
			prixTotal = prixTotal 
					+ (this.getParc(j).getNombre_BE_haute()-this.getParc(j).getNombre_PAS_CONTENT_BE_haute())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.2
					+ (this.getParc(j).getNombre_PAS_CONTENT_BE_haute())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*2
					+ (this.getParc(j).getNombre_non_BE_haute()-this.getParc(j).getNombre_PAS_CONTENT_nBE_haute())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1
					+ (this.getParc(j).getNombre_PAS_CONTENT_BE_haute())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1*2
					+ (this.getParc(j).getNombre_non_BE_moyenne()-this.getParc(j).getNombre_PAS_CONTENT_nBE_moyenne())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()
					+ (this.getParc(j).getNombre_PAS_CONTENT_nBE_moyenne())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*2
					+ (this.getParc(j).getNombre_BE_moyenne()-this.getParc(j).getNombre_PAS_CONTENT_BE_moyenne())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1 
					+ (this.getParc(j).getNombre_PAS_CONTENT_BE_moyenne())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*1.1*2
					+ (this.getParc(j).getNombre_non_BE_basse()-this.getParc(j).getNombre_PAS_CONTENT_nBE_basse())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*0.9
					+ (this.getParc(j).getNombre_PAS_CONTENT_nBE_basse())*Filiere.LA_FILIERE.getParametre("CAC'AO40Prix Entretien Arbre").getValeur()*0.9*2;
		}
		
		
		
		//Retirer l'argent 
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), prixTotal);
	}

	/**
	 * @return the recolte
	 */
	public HashMap<Feve, Double> getRecolte() {
		return recolte;
	}
}
