package abstraction.eq7Distributeur2.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class Tracker implements ITracker{

	private HashMap<ChocolatDeMarque,List<Double>> ventesQuantiteEtape;
	private HashMap<ChocolatDeMarque,List<Double>> ventesPrixEtape;
	
	private HashMap<ChocolatDeMarque,List<Double>> ventesQuantite;
	private HashMap<ChocolatDeMarque,List<Double>> ventesPrix;
	
	private List<ChocolatDeMarque> chocolats;
	
	private boolean FONCTIONEL;
	
	public Tracker(List<ChocolatDeMarque> listeChocolatsProduits) {
		this.FONCTIONEL= !listeChocolatsProduits.isEmpty();
		
		ventesQuantite = new HashMap<ChocolatDeMarque,List<Double>>();
		ventesPrix = new HashMap<ChocolatDeMarque,List<Double>>();
		
		ventesQuantiteEtape = new HashMap<ChocolatDeMarque,List<Double>>();
		ventesPrixEtape = new HashMap<ChocolatDeMarque,List<Double>>();
		
		chocolats = listeChocolatsProduits;
		
		//Initialisation des hashmaps
		for (ChocolatDeMarque choc : listeChocolatsProduits) {
			ventesQuantite.put(choc, new ArrayList<Double>());
			ventesPrix.put(choc, new ArrayList<Double>());
			
			ventesQuantiteEtape.put(choc, new ArrayList<Double>());
			ventesPrixEtape.put(choc, new ArrayList<Double>());
		}
		
	}

	private List<Double> newListOneElement(double d) {
		ArrayList temp = new ArrayList<Double>();
		temp.add(d);
		return temp;
	}
	
	@Override
	public void resetEtapeHashMaps() {
		for (ChocolatDeMarque choc : this.chocolats) {
			this.getVentesPrixEtapeActuelle(choc).clear();
			this.getVentesQuantiteEtapeActuelle(choc).clear();
		}
	}
	
	@Override
	public void trackVentesQuantite(ChocolatDeMarque choco, Double quantite){
		if (!this.FONCTIONEL) {return ;}
		List<Double> quantites = ventesQuantiteEtape.get(choco);
		quantites.add(quantite);
	}
	
	@Override
	public void trackVentePrix(ChocolatDeMarque choco, Double prix){
		if (!this.FONCTIONEL) {return ;}
		List<Double> prixDeVente = ventesPrixEtape.get(choco);
		prixDeVente.add(prix);
	}

	@Override
	public void endStepSumUp() {
		double sommeQuantite = 0.;
		double sommePrix = 0.;
		
		for (ChocolatDeMarque choco : chocolats) {
			//Somme des quantités
			sommeQuantite = 0.;
			for (double q : this.getVentesQuantiteEtapeActuelle(choco)) {
				sommeQuantite += q;
			}
			this.getVentesQuantite(choco).add(sommeQuantite);
			//Somme des prix
			sommePrix = 0.;
			for (double p : this.getVentesPrixEtapeActuelle(choco)) {
				sommePrix += p;
			}
			/*int nbVentes = this.getVentesPrixEtapeActuelle(choco).size();
			if (nbVentes > 0) {
				sommePrix = sommePrix/nbVentes;
			} else {
				sommePrix = 0.;
			}*/
			this.getVentesPrix(choco).add(sommePrix);
		}
		
		//Reset
		this.resetEtapeHashMaps();
	}

	@Override
	public List<Double> getVentesQuantiteEtapeActuelle(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return this.newListOneElement(10.);}
		return this.ventesQuantiteEtape.get(choco);
	}

	@Override
	public List<Double> getVentesPrixEtapeActuelle(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return this.newListOneElement(10.);}
		return this.ventesPrixEtape.get(choco);
	}

	@Override
	public Double getPreviousVenteQuantite(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return 0.;}
		int lastEtape = this.getVentesQuantite(choco).size()-1;
		if (lastEtape==-1) {
			return 200000.;
		}
		return this.getVentesQuantiteEtape(choco, lastEtape);
	}

	@Override
	public Double getPreviousVentePrix(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return 10.;}
		int lastEtape = this.getVentesPrix(choco).size()-1;
		return this.getVentesPrixEtape(choco, lastEtape);
	}

	@Override
	public List<Double> getVentesQuantite(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return this.newListOneElement(0.);}
		return this.ventesQuantite.get(choco);
	}

	@Override
	public List<Double> getVentesPrix(ChocolatDeMarque choco) {
		if (!this.FONCTIONEL) {return this.newListOneElement(0.);}
		return this.ventesPrix.get(choco);
	}

	@Override
	public Double getVentesQuantiteEtape(ChocolatDeMarque choco,int i) {
		if (!this.FONCTIONEL) {return 0.;}
		return this.getVentesQuantite(choco).get(i);
	}

	@Override
	public Double getVentesPrixEtape(ChocolatDeMarque choco,int i) {
		if (!this.FONCTIONEL) {return 10.;}
		return this.getVentesPrix(choco).get(i);
	}

}