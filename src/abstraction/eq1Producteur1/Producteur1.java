package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.general.Journal;
import abstraction.eq8Romu.general.Variable;

public class Producteur1 extends Producteur1ContratCadre{

	public Producteur1() {	
		super();
	}
	
	
	//Auteur : Khéo
		public List<Variable> getIndicateurs() {
			List<Variable> res=new ArrayList<Variable>();
			res.add(this.getStockBasse());
			res.add(this.getStockHaut_BE());
			res.add(this.getStockMoyenne());
			res.add(this.getStockMoyenne_BE());
			
			res.add(this.getStockBasse_NA());
			res.add(this.getStockHaut_BE_NA());
			res.add(this.getStockMoyenne_NA());
			res.add(this.getStockMoyenne_BE_NA());
			
			res.add(this.getStockBQ());
			res.add(this.getStockMQ());
			res.add(this.getStockMQ_BE());
			
			return res;
		}
		
		public List<Journal> getJournaux() {
			List<Journal> res=new ArrayList<Journal>();
			res.add(this.getContratCadre());
			/*for (int j=0;j<4;j++) {
				Parc Parc_j = this.getParc(j);
				res.add(Parc_j.getRetourMAJParc());
				res.add(Parc_j.getRetourRécolte());
				res.add(Parc_j.getRetourGuerre());
				res.add(Parc_j.getRetourAléas());
				res.add(Parc_j.getRetourMaladie());
			}*/
			return res;
		}
}
