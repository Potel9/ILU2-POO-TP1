package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche ;
	
	
	
	private static class Marche{
			private Etal[] etals;

			private Marche(int nbEtals) {
				etals = new Etal[nbEtals];
				for (int i = 0; i < nbEtals; i++) {
					etals[i] = new Etal();
				}
			}

			private void utiliserEtal(int indiceEtal, Gaulois vendeur,
					String produit, int nbProduit) {
				if (indiceEtal >= 0 && indiceEtal < etals.length) {
					etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
				}
			}

			private int trouverEtalLibre() {
			    for (int i = 0; i < etals.length; i++) {
			        if (!etals[i].isEtalOccupe()) {
			            return i;
			        }
			    }
			    return -1;
			}

		private Etal[] trouverEtal(String produit) {
			Etal[] tab = new Etal[etals.length];
			int j = 0 ; 
			
			for (int i = 0 ; i<etals.length ; i++) {
				if(etals[i]!=null && etals[i].contientProduit(produit) ) {
					tab[j]= etals[i];
					j++; 
				}
			}

			return tab ;
		}
		
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0 ; i < etals.length ; i++ ) {
				if(etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null ;
		}
		
		private String afficherMarche() {
			int etalsVide = 0 ;
			StringBuilder chaine = new StringBuilder();
			for(int i = 0; i< etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal() + "\n");
				}
				else {
					etalsVide++ ; 
				}
			}
			
			if (etalsVide > 0 ) {
				chaine.append("Il reste "+ etalsVide +" �tals non utilis�s dans le march�.");
			}
			return chaine.toString(); 
		}
		
		
		
		
		
	}
	public Village(String nom, int nbVillageoisMaximum,int nbrEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbrEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
	    StringBuilder chaine = new StringBuilder();
	    if (chef == null) {
	        throw new VillageSansChefException("Le village ne peut exister sans chef.");
	    }
	    if (nbVillageois < 1) {
	        chaine.append("Il n'y a encore aucun habitant au village du chef "
	                + chef.getNom() + ".\n");
	    } else {
	        chaine.append("Au village du chef " + chef.getNom()
	                + " vivent les légendaires gaulois :\n");
	        for (int i = 0; i < nbVillageois; i++) {
	            chaine.append("- " + villageois[i].getNom() + "\n");
	        }
	    }
	    return chaine.toString();
	}

	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    int etalLibre = marche.trouverEtalLibre();
	    StringBuilder chaine = new StringBuilder() ; 
	    chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " "+ produit + ".\n" );
	    if (etalLibre != -1) {
	        marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
	       chaine.append("Le vendeur " + vendeur.getNom() +" vend des "+ produit + " � l'�tal n�"+ etalLibre);
	    } else {
	       chaine.append("Tous les �tals sont occup�s. Impossible d'installer le vendeur " + vendeur.getNom() + ".");
	    }
	    return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
	    return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
	    Etal etal = rechercherEtal(vendeur);
	    if (etal != null) {
	        return etal.libererEtal();
	    }
	    return vendeur.getNom() + " n'est pas vendeur au marché.";
	}

	public String afficherMarche() {
	    return marche.afficherMarche();
	}

	public String rechercherVendeursProduit(String produit) {
	    Etal[] vendeurs = marche.trouverEtal(produit);
	    StringBuilder chaine = new StringBuilder();
	    if (vendeurs.length == 1) {
	    	if (vendeurs[0] != null) {
	    	chaine.append("Seul le vendeur " +vendeurs[0].getVendeur().getNom()+"vend des "+ produit + "\n");
	    }}
	    if (vendeurs.length > 0 ) {
	        chaine.append("Les vendeurs qui propose des " + produit + " sont :\n");
	        for (Etal etal : vendeurs) {
	        	if (etal != null) {
	            chaine.append("- " + etal.getVendeur().getNom() + "\n");
	        }}
	    } else {
	        chaine.append("Aucun vendeur ne propose le produit '" + produit + "' sur le march�.");
	    }
	    return chaine.toString();
	}

	
	
	
}