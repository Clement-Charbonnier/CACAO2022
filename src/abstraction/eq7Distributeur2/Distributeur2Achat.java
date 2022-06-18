package abstraction.eq7Distributeur2;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

import abstraction.eq7Distributeur2.tools.Demande;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.filiere.Filiere;
import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

//Classe rédigée par Edgar et Matteo
public class Distributeur2Achat extends Distributeur2Acteur implements IAcheteurContratCadre{
	public static final int EPS_ECH_OK=2;
	public static final int ECH_MAX=15;
	public static final Double DELTA_QUANTITE=100000.;
	public static final Double PRIX_MAX=20.0;
	public static final Double PRIX_OK=10.0;
	public static final Double EPSILON_PRIX=2.0;
	
	public Demande demande;
	public int nbStepContrat;
	
	protected List<ExemplaireContratCadre> mesContratEnTantQuAcheteur;
	
	public Distributeur2Achat() {
		super();
		this.mesContratEnTantQuAcheteur = new LinkedList<ExemplaireContratCadre>();
		
		//Nombre de step sur lequel on gère nos contrat
		this.nbStepContrat = 10;
	}
	
	public void initialiser() {
		super.initialiser();
		this.demande = new Demande(this.chocolats);
	}
	
	//A chaque étape, on créer un contrat cadre pour acheter un produit dont le stock est inférieur au seuil
	//On réalise alors des contrats avec tous les vendeurs qui le propose afin de voir quel est leur prix
	//On compare ces prixs et on réalise finalement le contrat avec le meilleur vendeur.
	//On réalise une moyenne des achats du client final sur les 6 steps précédents pour determiner la quantité achetée par step
	//On compare notre stock au seuil limite pour determiner la quantité à acheter pour remettre au seuil
	//Pour qu'un produit soit en boolTeteGondole, il doit être bioéquitable
	//On réalise un contrat pour chacun des différents chocolatss produits afin de tous les avoir en stock
	//Finalement, on fait un contrat avec le vendeur le plus offrant
	@Override
	public void next() {
		super.next();
		

		
		int currentEtape = Filiere.LA_FILIERE.getEtape();
		
		//Pour chaque chocolat produit sur le marché on défini la demande
		for (ChocolatDeMarque chocProduit : Filiere.LA_FILIERE.getChocolatsProduits()) {
			double valeur = this.volumeParEtapeMoyenne(chocProduit, currentEtape, this.nbStepContrat);
			this.demande.set(chocProduit, valeur);
		}
		
		//-------------------------------------NEXT CONTRAT------------------------------------------//
		//Initialisation du superviseur de vente
		SuperviseurVentesContratCadre SupVente = ((SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre")));
		
		journalContratCadre.ajouter("================================================================================");
		//Affichage de la liste des vendeurs actifs pour chaque chocolats
		for (ChocolatDeMarque chocProduit : Filiere.LA_FILIERE.getChocolatsProduits()) {
			List<IVendeurContratCadre> vendeurs = SupVente.getVendeurs(chocProduit);
			this.journalContratCadre.ajouter("Liste des vendeurs disponnible pour le produit : "+chocProduit+" "+vendeurs);
		}
		journalContratCadre.ajouter("================================================================================");
		
		
		//Pour chaque chocolat produit sur le marché
		for (ChocolatDeMarque chocProduit : Filiere.LA_FILIERE.getChocolatsProduits()) {
			
			//On pose le chocolat en tête de gondole si il est bio et équitable
			boolean boolTeteGondole = chocProduit.isBioEquitable();
			
			//Si la quantite du chocolat en question est inférieure au seuil auquel on a décidé d'en racheter, alors on va en racheter, et si on doit acheter plus de 1000kg du produit
			if (stock.getQuantite(chocProduit)<=stock.getSeuilRachat(chocProduit) && this.demande.get(chocProduit)>1000) {
				
				//Retourne le volume restant à acheter
				double venteParStep = demande.get(chocProduit);
				
				//On créer un écheancier correspondant à nos besoin
				Echeancier echeancierAchat = new Echeancier(currentEtape+1,this.nbStepContrat,venteParStep);
				
				//On récupère tout les vendeurs actifs
				List<IVendeurContratCadre> vendeurs = SupVente.getVendeurs(chocProduit);
				
				//Pour chaque vendeur
				for (IVendeurContratCadre vendeur : vendeurs) {
					journalContratCadre.ajouter(this.getColoredName(this) + " propose d'initier un CC avec "+ this.getColoredName(vendeur) +" avec le produit: "+chocProduit);
					journalContratCadre.ajouter("Echeancier : "+echeancierAchat.toString());
					ExemplaireContratCadre propositionContratCadre = SupVente.demandeAcheteur(this, vendeur, chocProduit, echeancierAchat, cryptogramme,boolTeteGondole);
					if (propositionContratCadre == null) {
						journalContratCadre.ajouter(red("Le contrat avec "+vendeur.getNom()+" n'a pas abouti"));
						journalContratCadre.ajouter("================================================================================");
					}
				}
			}
		}
		this.venteTracker.endStepSumUp();
	}
	
	public double volumeParEtapeMoyenne(ChocolatDeMarque chocProduit,int currentEtape,int nbEtape) {
		
		double demandeAnnee = 7200000000.0;
		int nbStepParAn = 24;
		int nbChocolats = this.chocolats.size();
		int nbDistributeur = 2;
		
		
		double venteBase = demandeAnnee/(nbChocolats*nbDistributeur*nbStepParAn);
		
		double quantiteVendue = this.venteTracker.getPreviousVenteQuantite(chocProduit);
		double quantiteTotale = 0;
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			quantiteTotale =  quantiteTotale + (this.venteTracker.getPreviousVenteQuantite(choco));
		}
		double venteJudicieuse = (quantiteVendue/quantiteTotale)*venteBase;
		
		this.journalEtudeVente.ajouter("Quantitée determinée judicieuse pour "+chocProduit+" : "+ venteJudicieuse +" kg");
		return venteJudicieuse;	
	}
	

	@Override
	public boolean achete(Object produit) {
		double demandeRestante = this.demande.get((ChocolatDeMarque) produit);
		if (produit instanceof ChocolatDeMarque && stock.getQuantite((ChocolatDeMarque)produit)<=stock.getSeuilRachat((ChocolatDeMarque)produit) && demandeRestante < 10000) {
			return true;
		}else {
			return false;
		}
		//return (produit!=null && (produit instanceof ChocolatDeMarque) && this.chocolats.contains(produit));
	}

	//edgard: On vérifie que l'echeancier nous fournit une quantite > Quantite Min avant de valider le contrat
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat){
		//On récupère le superviseur des ventes et la quantité min echeancier
		double QuantiteMinEcheancier= SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER;
		
		//On récupère le dernier écheancier négocié
		Echeancier lastEcheancier = contrat.getEcheancier();
		
		//On récupère le chocolat concerné
		ChocolatDeMarque chocProduit = (ChocolatDeMarque)contrat.getProduit();
		
		//L'étape actuelle
		int currentEtape = Filiere.LA_FILIERE.getEtape();
		
		//Retourne la demande restante
		double venteParStep = this.demande.get(chocProduit);
		double quantiteTotale = venteParStep*this.nbStepContrat;
		

		//On créer un écheancier correspondant à nos besoin
		Echeancier echeancierAchat = new Echeancier(currentEtape,this.nbStepContrat,venteParStep);
		
		//Si jamais on atteint la fin des négociations
		if (lastEcheancier.getStepFin()>ECH_MAX) {
			if (contrat.getQuantiteTotale() < quantiteTotale) {
				journalNegociationContratCadre.ajouter("Acceptation sur la quantite avec " + this.getColoredName(contrat.getVendeur()) + " pour dépassement du delai de négociation, quantite :"+contrat.getQuantiteTotale());
				return contrat.getEcheancier();
			}
			journalNegociationContratCadre.ajouter("Arrêt du contrat avec " + this.getColoredName(contrat.getVendeur()) + " pour dépassement du delai de négociation");
			return null;
		}
		//Si la quantité totale proposée dans le contrat est plus petite que la quantite minimale
		if (contrat.getQuantiteTotale()<=QuantiteMinEcheancier) {
			journalNegociationContratCadre.ajouter("Arrêt du contrat avec " + this.getColoredName(contrat.getVendeur()) + " pour quantité reproposée en dessous du seuil de quantite minimale : contrat.getQuantiteTotale() : "+ contrat.getQuantiteTotale());
			return null;
		}
		//Si jamais la quantiteTotale du nouveau contrat est plus petite que 1000kg
		if (quantiteTotale<=QuantiteMinEcheancier) {
			journalNegociationContratCadre.ajouter("Arrêt du contrat avec " + this.getColoredName(contrat.getVendeur()) + " pour quantité determinée en dessous du seuil de quantite minimale : quantiteTotale : "+ quantiteTotale);
			return null;
		}
		if (Math.abs(contrat.getQuantiteTotale()-quantiteTotale)<=DELTA_QUANTITE) {
			return contrat.getEcheancier();
		}
		//journalNegociationContratCadre.ajouter("Reproposition d'un contrat avec " + this.getColoredName(contrat.getVendeur()) + " pour une quantité totale de : "+ quantiteTotale + " (quantite contrat = "+contrat.getQuantiteTotale()+")");
		return echeancierAchat;
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		double prix = contrat.getPrix();
		if (prix>PRIX_MAX) {
			journalNegociationContratCadre.ajouter("Arrêt du contrat avec " + contrat.getVendeur().getNom() + " pour prix au dessus du prix maximal : prix : "+ prix);
			return -1;
		}else {
			if(Math.abs(prix-EPSILON_PRIX)==PRIX_OK || prix<=PRIX_OK) {
				this.journalContratCadre.ajouter(this.green("Contrat négocié :")+ " Vendeur "+ this.getColoredName(contrat.getVendeur())+ "; Acheteur "+ this.getColoredName(contrat.getAcheteur()));
				this.journalContratCadre.ajouter("Quantite Totale " + contrat.getQuantiteTotale() + "; Produit : " + contrat.getProduit() + "; Prix : "+contrat.getPrix());
				journalContratCadre.ajouter("================================================================================");
				return prix;
			}else {
				return (prix+PRIX_OK)/2;
			}
		}
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.stock.addProduit((ChocolatDeMarque)produit, quantite);
		this.journalStock.ajouter("+ "+quantite+" kg -"+produit);
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		IVendeurContratCadre v = contrat.getVendeur();
		IAcheteurContratCadre a = contrat.getAcheteur();
		Echeancier currentEtape = contrat.getEcheancier();
		ChocolatDeMarque chocProduit = (ChocolatDeMarque) contrat.getProduit();
		Double q = contrat.getQuantiteTotale();
	}

}