package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.filiere.IActeur;

public class Parc {
	private HashMap<Feve, ArrayList<MilleArbre>> cacaoyers;
	private String nom;
	private int nombre_BE_moyenne;
	private int nombre_BE_haute;
	private int nombre_non_BE_basse;
	private int nombre_non_BE_moyenne;
	private int nombre_non_BE_haute;
	private int nombre_PAS_CONTENT_BE_moyenne;
	private int nombre_PAS_CONTENT_BE_haute;
	private int nombre_PAS_CONTENT_nBE_basse;
	private int nombre_PAS_CONTENT_nBE_moyenne;
	private int nombre_PAS_CONTENT_nBE_haute;
	private boolean guerre;
	private int ut_debut_guerre;
	private int ut_fin_guerre;
	private int fin_aleas;
	protected Journal RetourMAJParc;
	protected Journal RetourGuerre;
	protected Journal RetourAléas;
	protected Journal RetourMaladie;
	protected Journal RetourRécolte;

	
	public Parc(String nom, Object producteur) { //Écrit par Antoine
		HashMap<Feve, ArrayList<MilleArbre>> m = new HashMap<Feve ,ArrayList<MilleArbre>>();
		for (Feve f : Feve.values()) {
			m.put(f, new ArrayList<MilleArbre>());
		}
		this.cacaoyers = m;
		this.nom = nom;
		this.nombre_BE_moyenne = 0;
		this.nombre_BE_haute = 0;
		this.nombre_non_BE_basse = 0;
		this.nombre_non_BE_moyenne = 0;
		this.nombre_non_BE_haute = 0;
		this.nombre_PAS_CONTENT_BE_moyenne = 0;
		this.nombre_BE_haute = 0;
		this.nombre_PAS_CONTENT_nBE_basse = 0;
		this.nombre_PAS_CONTENT_nBE_moyenne = 0;
		this.nombre_PAS_CONTENT_nBE_haute = 0;
		this.guerre = false;
		this.ut_debut_guerre = 0;
		this.ut_fin_guerre = 0;
		this.fin_aleas = 0;
		this.RetourMAJParc = new Journal("CAC'AO40 "+nom+"Renouvellement Parc", ((IActeur)producteur));
		this.RetourGuerre = new Journal("CAC'AO40 "+nom+"Guerre", ((IActeur)producteur));
		this.RetourAléas = new Journal("CAC'AO40 "+nom+"Aléas climatiques", ((IActeur)producteur));
		this.RetourMaladie = new Journal("CAC'AO40 "+ nom + "Maladies", ((IActeur)producteur));
		this.RetourRécolte = new Journal("CAC'AO40 "+ nom + "Récolte", ((IActeur)producteur));


	}
	
	public HashMap<Feve, ArrayList<MilleArbre>> getCacaoyers() { //Écrit par Antoine
		return this.cacaoyers;
	}
	
	
	public Journal getRetourMAJParc() { //Écrit par Antoine
		return RetourMAJParc;
	}

	public Journal getRetourGuerre() { //Écrit par Antoine
		return RetourGuerre;
	}
	
	public Journal getRetourAléas() { //Écrit par Antoine
		return RetourAléas;
	}
	
	public Journal getRetourMaladie() { //Écrit par Antoine
		return RetourMaladie;
	}
	
	public Journal getRetourRécolte() { //Écrit par Antoine
		return RetourRécolte;
	}
	public String getNom() { //Écrit par Antoine
		return this.nom;
	}
	
	public int getNombre_BE_moyenne() { //Écrit par Antoine
		return this.nombre_BE_moyenne;
	}
	
	public int getNombre_BE_haute() { //Écrit par Antoine
		return this.nombre_BE_haute;
	}
	
	public int getNombre_non_BE_basse() { //Écrit par Antoine
		return this.nombre_non_BE_basse;
	}
	
	public int getNombre_non_BE_moyenne() { //Écrit par Antoine
		return this.nombre_non_BE_moyenne;
	}
	
	public int getNombre_non_BE_haute() { //Écrit par Antoine
		return this.nombre_non_BE_haute;
	}
	
	public int getNombre_PAS_CONTENT_BE_moyenne() { //Écrit par Antoine
		return this.nombre_PAS_CONTENT_BE_moyenne;
	}
	
	public int getNombre_PAS_CONTENT_BE_haute() { //Écrit par Antoine
		return this.nombre_PAS_CONTENT_BE_haute;
	}
	
	public int getNombre_PAS_CONTENT_nBE_basse() { //Écrit par Antoine
		return this.nombre_PAS_CONTENT_nBE_basse;
	}
	
	public int getNombre_PAS_CONTENT_nBE_moyenne() { //Écrit par Antoine
		return this.nombre_PAS_CONTENT_nBE_moyenne;
	}
	
	public int getNombre_PAS_CONTENT_nBE_haute() { //Écrit par Antoine
		return this.nombre_PAS_CONTENT_nBE_haute;
	}
	
	public boolean getGuerre() { //Écrit par Antoine
		return this.guerre;
	}
	
	public int getUt_debut_guerre() { //Écrit par Antoine
		return this.ut_debut_guerre;
	}
	
	public int getUt_fin_guerre() { //Écrit par Antoine
		return this.ut_fin_guerre;
	}
	
	public int getfin_aleas() { //Écrit par Antoine
		return this.fin_aleas;
	}
	
	public void setNom(String nom) { //Écrit par Antoine
		this.nom = nom;
	}
	
	public void setNombre_BE_moyenne(int i) { //Écrit par Antoine
		this.nombre_BE_moyenne = i;
	}
	
	public void setNombre_BE_haute(int i) { //Écrit par Antoine
		this.nombre_BE_haute = i;
	}
	
	public void setNombre_non_BE_basse(int i) { //Écrit par Antoine
		this.nombre_non_BE_basse = i;
	}
	
	public void setNombre_non_BE_moyenne(int i) { //Écrit par Antoine
		this.nombre_non_BE_moyenne = i;
	}
	
	public void setNombre_non_BE_haute(int i) { //Écrit par Antoine
		this.nombre_non_BE_haute = i;
	}
	
	public void setNombre_PAS_CONTENT_BE_moyenne(int i) { //Écrit par Antoine
		this.nombre_PAS_CONTENT_BE_moyenne = i;
	}
	
	public void setNombre_PAS_CONTENT_BE_haute(int i) { //Écrit par Antoine
		this.nombre_PAS_CONTENT_BE_haute = i;
	}
	
	public void setNombre_PAS_CONTENT_nBE_basse(int i) { //Écrit par Antoine
		this.nombre_PAS_CONTENT_nBE_basse = i;
	}
	
	public void setNombre_PAS_CONTENT_nBE_moyenne(int i) { //Écrit par Antoine
		this.nombre_PAS_CONTENT_nBE_moyenne = i;
	}
	
	public void setNombre_PAS_CONTENT_nBE_haute(int i) { //Écrit par Antoine
		this.nombre_PAS_CONTENT_nBE_haute = i;
	}
	
	public void setGuerre(boolean b) { //Écrit par Antoine
		this.guerre = b;
	}
	
	public void setUt_debut_guerre(int ut) { //Écrit par Antoine
		this.ut_debut_guerre = ut;
	}
	
	public void setUt_fin_guerre(int ut) { //Écrit par Antoine
		this.ut_fin_guerre = ut;
	}
	
	public void setfin_aleas(int i) { //Écrit par Antoine
		this.fin_aleas = i;
	}
	
	public int getNb_arbres_tot() { //Écrit par Antoine
		return this.getNombre_BE_haute()+this.getNombre_BE_moyenne()+this.getNombre_non_BE_basse()+this.getNombre_non_BE_moyenne()+this.getNombre_non_BE_haute();
	}
	public MilleArbre getArbre(Feve f,int i) { //Écrit par Antoine
		return this.getCacaoyers().get(f).get(i);
	}

	
	public ArrayList<MilleArbre> getListeArbre(Feve f) { //Écrit par Antoine
		return this.getCacaoyers().get(f);
	}
	
	public Feve ConversionFeve(int qualite, boolean BE) { //Écrit par Antoine
		if ((qualite==0) && (BE==false)) {
			return Feve.FEVE_BASSE;
		}
		if ((qualite==1) && (BE==false)) {
			return Feve.FEVE_MOYENNE;
		}
		if ((qualite==2) && (BE==false)) {
			return Feve.FEVE_HAUTE;
		}
		if ((qualite==1) && (BE)) {
			return Feve.FEVE_MOYENNE_BIO_EQUITABLE;
		}
		else {
			return Feve.FEVE_HAUTE_BIO_EQUITABLE;
		}
	} 
	
	public void Planter(MilleArbre a) { //Écrit par Antoine
		Feve f = ConversionFeve(a.getQualite(),a.getBioequitable());
		this.getListeArbre(f).add(a);
		MAJCompteur(a,1);
	}
	
	public void Planter(int i,HashMap<Feve, Double> venteChoco,boolean cooperative) { //Écrit par Antoine
		for (int j=0;j<i;j++) {
			MilleArbre nouvel_arbre = this.FuturePlantation(venteChoco, cooperative);
			this.Planter(nouvel_arbre);
			}
	}
	
	public void MAJCompteur(MilleArbre a, int i) { //Écrit par Antoine
		if ((i==1) || (i==-1)) {
			if (a.getBioequitable()) {
				if (a.getQualite()==2) {
					this.setNombre_BE_moyenne(this.getNombre_BE_moyenne()+i);
					if (a.getCooperative()) {
						this.setNombre_PAS_CONTENT_BE_moyenne(this.getNombre_PAS_CONTENT_BE_moyenne()+i);
					}
				}
				if (a.getQualite()==3) {
					this.setNombre_BE_haute(this.getNombre_BE_haute()+i);
					if (a.getCooperative()) {
						this.setNombre_PAS_CONTENT_BE_haute(this.getNombre_PAS_CONTENT_BE_haute()+i);
					}
				}
			}
			else {
				if (a.getQualite()==1) {
					this.setNombre_non_BE_basse(this.getNombre_non_BE_basse()+i);
					if (a.getCooperative()) {
						this.setNombre_PAS_CONTENT_nBE_basse(this.getNombre_PAS_CONTENT_nBE_basse()+i);
					}
				}
				if (a.getQualite()==2) {
					this.setNombre_non_BE_moyenne(this.getNombre_non_BE_moyenne()+i);
					if (a.getCooperative()) {
						this.setNombre_PAS_CONTENT_nBE_moyenne(this.getNombre_PAS_CONTENT_nBE_moyenne()+i);
					}
				}
				if (a.getQualite()==3) {
					this.setNombre_non_BE_haute(this.getNombre_non_BE_haute()+i);
					if (a.getCooperative()) {
						this.setNombre_PAS_CONTENT_nBE_haute(this.getNombre_PAS_CONTENT_nBE_haute()+i);
					}
				}
			}
		}
	}
	

	
	public MilleArbre FuturePlantation(HashMap<Feve, Double> venteChoco,boolean cooperative) { //Écrit par Antoine
		//On regarde l'écart entre la demande et la production et on choisit comme planter en conséquence
		double nb_arbre = this.getNombre_non_BE_basse()+this.getNombre_non_BE_moyenne()+this.getNombre_non_BE_haute()+this.getNombre_BE_moyenne()*0.8+this.getNombre_BE_haute()*0.8; //on pondère avec la production pour le BE (la masse de cacao récoltée dépend du nombre d'arbre)
		LinkedList<Double> ecart_demande = new LinkedList<Double>(Arrays.asList(venteChoco.get(Feve.FEVE_BASSE)-this.getNombre_non_BE_basse()/nb_arbre,venteChoco.get(Feve.FEVE_MOYENNE)-this.getNombre_non_BE_moyenne()/nb_arbre,venteChoco.get(Feve.FEVE_HAUTE)-this.getNombre_non_BE_haute()/nb_arbre,venteChoco.get(Feve.FEVE_MOYENNE_BIO_EQUITABLE)-this.getNombre_BE_moyenne()*0.8/nb_arbre,venteChoco.get(Feve.FEVE_HAUTE_BIO_EQUITABLE)-this.getNombre_BE_haute()*0.8/nb_arbre));
		double max = ecart_demande.get(0);
		int indice = 0;
		for (int i=1;i<ecart_demande.size();i++) {
			if (ecart_demande.get(i)>max) {
				max = ecart_demande.get(i);
				indice = i;
			}
		}
		if (indice==0) {
			return new MilleArbre(1,cooperative,false,Filiere.LA_FILIERE.getEtape());
		}
		if (indice==1) {
			return new MilleArbre(2,cooperative,false,Filiere.LA_FILIERE.getEtape());
		}
		if (indice==2) {
			return new MilleArbre(3,cooperative,false,Filiere.LA_FILIERE.getEtape());
		}
		if (indice==3) {
			return new MilleArbre(2,cooperative,true,Filiere.LA_FILIERE.getEtape());
		}
		else {
			return new MilleArbre(3,cooperative,true,Filiere.LA_FILIERE.getEtape());
		}
	}
	
	public void MAJGuerre() { //Écrit par Antoine
		if (this.getGuerre()==true) {
			if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()) {
				//Si on est après l'ut de fin de guerre, la paix revient
				this.setGuerre(false);
				this.getRetourGuerre().ajouter("Il est temps d'enterrer la hache de guerre, faisons l'amour et non la guerre");
			}
		}
		if (Filiere.LA_FILIERE.getEtape()>=this.getUt_fin_guerre()+Math.ceil((this.getUt_fin_guerre()-this.getUt_debut_guerre())*1.5)) {
			//Si la période de paix est dépassé, on regarde pour redéclarer la guerre 
				double chance_guerre = Math.random();
				if (chance_guerre<=0.15) {
					this.setGuerre(true);
					this.setUt_debut_guerre(Filiere.LA_FILIERE.getEtape());
					double aléa_durée_guerre = Math.random();
					while (aléa_durée_guerre==0) {
						aléa_durée_guerre = Math.random();
					}
					int temps = (int)Math.ceil(aléa_durée_guerre*6);
					this.setUt_fin_guerre(this.getUt_debut_guerre()+temps);
					this.getRetourGuerre().ajouter("Nos ennemis n'achètent pas notre cacao, il est temps de les goumer et ce pendant"+temps+"ut");
			}
				else {
					this.getRetourGuerre().ajouter("La période de paix est fini mais ces cancres de Producteur2 ne méritent pas qu'on leur déclare la guerre pour le moment");
				}
		}
		if (	(Filiere.LA_FILIERE.getEtape()>=this.getUt_debut_guerre()) 
				&& (Filiere.LA_FILIERE.getEtape()<this.getUt_fin_guerre())) {
			this.getRetourGuerre().ajouter("Attends 2 secondes poupée, je finis de massacrer this.getProducteurNul()");
		}
		if ((Filiere.LA_FILIERE.getEtape()<this.getUt_fin_guerre()+Math.ceil((this.getUt_fin_guerre()-this.getUt_debut_guerre())*1.5)) && (this.getGuerre()==false)) {
			this.getRetourGuerre().ajouter("C'est la période de paix frérot, on cherche pas les embrouilles");
		}
	}
	
	public void MAJAleas() { //Écrit par Antoine
		if (Filiere.LA_FILIERE.getEtape()%24==20) {
			//A l'ut 20 de chaque année on regarde combien de temps vont durer les aléas climatiques
			double aléa_durée_aléas = Math.random();
			while (aléa_durée_aléas==0) {
				aléa_durée_aléas = Math.random();
			}
		int temps = (int)Math.ceil((aléa_durée_aléas+1)*2);
		this.setfin_aleas((int)(Filiere.LA_FILIERE.getEtape()+temps));
		this.getRetourAléas().ajouter("Cette année, les aléas climatiques vont durer"+temps+" ut" );
		}
	}

	
	public void MAJParc(int mecontentement_basse,int mecontentement_moyenne, int mecontentement_haute, HashMap<Feve, Double> venteChoco) { //Écrit par Antoine
		int malade5 = 0;
		int plantés = 0;
		int mort_vieillesse = 0;
		for (Feve f : this.getCacaoyers().keySet()) {
			ArrayList<MilleArbre> liste_arbres = this.getListeArbre(f);
			for (int i=0; i<this.getListeArbre(f).size(); i++) {
				MilleArbre arbre_i = liste_arbres.get(i);
				boolean cooperative=arbre_i.getCooperative();
				
				//On met à jour la maladie et le mécontentement de chaque MilleArbre
				arbre_i.MAJMaladie();
				arbre_i.MAJMecontentement(mecontentement_basse,mecontentement_moyenne,mecontentement_haute);
				
				//Quand un arbre meurt de maladie on le remplace en fonction de la demande
				if (arbre_i.getStade_maladie() == 5) { 
					malade5+=1;
					MilleArbre nouvel_arbre = FuturePlantation(venteChoco,cooperative); 
					this.Planter(nouvel_arbre);
					this.MAJCompteur(nouvel_arbre, 1);
					plantés+=1;
					liste_arbres.remove(arbre_i);
					this.MAJCompteur(arbre_i, -1);
				}
				
				//Quand un MilleArbre meurt de vieillesse on l'enlève du parc
				if (arbre_i.Age() == arbre_i.getUt_esperance_vie()) { 
					liste_arbres.remove(arbre_i);
					this.MAJCompteur(arbre_i,-1);
					mort_vieillesse+=1;
				}
				
				//5ans avant qu'un MilleArbre meurt de vieilesse, on plante un nouvel MilleArbre pour répondre à la demande
				if ((arbre_i.getUt_esperance_vie()-arbre_i.Age())==120) { 
					MilleArbre nouvel_arbre = FuturePlantation(venteChoco,cooperative);
					this.Planter(nouvel_arbre);
					this.MAJCompteur(nouvel_arbre,1);
					plantés+=1;
				}
			}
		}
		this.getRetourMAJParc().ajouter(mort_vieillesse+" MilleArbres sont mort de vieillesse, vive les arbres");
		this.getRetourMAJParc().ajouter(plantés+" bébés MilleArbres sont nés, je pense les appeler Groot1, Groot2...");
		this.getRetourMaladie().ajouter(malade5+" MilleArbres sont morts de maladie");
	}
	
	public double Parasites(boolean BE) { //Écrit par Antoine
		double chance_parasite = Math.random();
		if (BE) {
			if (chance_parasite<=0.2) {
				double niveau_parasite = Math.random();
				if (niveau_parasite<=0.7) {
					return 0.9;
				}
				if ((niveau_parasite>0.7) && (niveau_parasite<=0.95)) {
					return 0.5;
				}
				if ((niveau_parasite>0.95) && (niveau_parasite<=1)) {
					return 0.2;
				}
			}
		}
		else {
			if (chance_parasite<=0.04) {
				double niveau_parasite = Math.random();
				if (niveau_parasite<=0.7) {
					return 0.9;
				}
				if ((niveau_parasite>0.7) && (niveau_parasite<=0.95)) {
					return 0.5;
				}
				if ((niveau_parasite>0.95) && (niveau_parasite<=1)) {
					return 0.2;
				}
			}
		}
		return 1.0;
	}

	
	public HashMap<Feve, Double> Recolte() { //Écrit par Antoine
		double BE_moyenne = 0;
		double BE_haute = 0;
		double non_BE_basse = 0;
		double non_BE_moyenne = 0;
		double non_BE_haute = 0;
		int malade1 = 0;
		int malade2 = 0;
		int malade3 = 0;
		int malade4 = 0;
		HashMap<Feve, Double> dicorecolte = new HashMap<Feve, Double>();
		if (Filiere.LA_FILIERE.getEtape()>=this.fin_aleas) {
			this.getRetourAléas().ajouter("OKLM le ciel est dégagé, la saison est bonne il est temps de récolter et d'se faire du blé");
			for (Feve f : this.getCacaoyers().keySet()) {
				ArrayList<MilleArbre> liste_arbres = this.getListeArbre(f);
				for (int i=0; i<liste_arbres.size(); i++) {
					MilleArbre arbre_i = liste_arbres.get(i);
					boolean isBE = arbre_i.getBioequitable();
					int qualite = arbre_i.getQualite();
					int maladie = arbre_i.getStade_maladie();
					if (maladie==1) {
						malade1+=1;
					}
					if (maladie==2) { 
						malade2+=1;
					}
					if (maladie==3) {
						malade3+=1;
					}
					if (maladie==4) {
						malade4+=1;
					}
					double recolte = arbre_i.Recolte();
					if ((isBE) && (qualite==2)) {
						BE_moyenne+=recolte;
					}
					if ((isBE) && (qualite==3)) {
						BE_haute+=recolte;
					}
					if (isBE==false) {
						if (qualite==1) {
							non_BE_basse+=recolte;
						}
						if (qualite==2) {
							non_BE_moyenne+=recolte;
						}
						if (qualite==3) {
							non_BE_haute+=recolte;
						}
					}
				}
			}
		}
		else {
			this.getRetourAléas().ajouter("ON RÉCOLTE PAS C'EST LA TEMPÊTE PLANQUEZ VOUS LES GADJOS");
		}
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade1+" MilleArbres malades au stade 1");
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade2+" MilleArbres malades au stade 2");
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade3+" MilleArbres malades au stade 3");
		this.getRetourMaladie().ajouter("Il y a actuellement "+malade4+" MilleArbres malades au stade 4");
		double parasitesBE = Parasites(true);
		double parasites_non_BE = Parasites(false);
		BE_moyenne = BE_moyenne*parasitesBE;
		BE_haute = BE_haute*parasitesBE;
		non_BE_basse = non_BE_basse*parasites_non_BE;
		non_BE_moyenne = non_BE_moyenne*parasites_non_BE;
		non_BE_haute = non_BE_haute*parasites_non_BE;
		this.getRetourRécolte().ajouter("On a recolté " + non_BE_basse + " kg de Khécao de qualité assez nulle...mais pas BE par contre");
		this.getRetourRécolte().ajouter("On a recolté " + non_BE_moyenne + " kg de Khécao de qualité médiocre...mais pas BE par contre");
		this.getRetourRécolte().ajouter("On a recolté " + non_BE_haute + " kg de Khécao de qualité exceptionnelle...mais pas BE par contre");
		this.getRetourRécolte().ajouter("On a recolté " + BE_moyenne + " kg de Khécao de qualité médiocre...mais BE !");
		this.getRetourRécolte().ajouter("On a recolté " + BE_haute + " kg de Khécao de qualité exceptionnelle...mais BE !");
		dicorecolte.put(Feve.FEVE_BASSE,non_BE_basse);
		dicorecolte.put(Feve.FEVE_MOYENNE,non_BE_moyenne);
		dicorecolte.put(Feve.FEVE_HAUTE,non_BE_haute);
		dicorecolte.put(Feve.FEVE_MOYENNE_BIO_EQUITABLE,BE_moyenne);
		dicorecolte.put(Feve.FEVE_HAUTE_BIO_EQUITABLE,BE_haute);
		return dicorecolte;
	}
}
