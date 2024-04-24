package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
	    try {
	        if (!etalOccupe) {
	            throw new IllegalStateException("Impossible de libérer un étal qui n'a jamais été occupé par un vendeur.");
	        } else {
	            etalOccupe = false;
	            StringBuilder chaine = new StringBuilder("Le vendeur ");
	            
	            if (vendeur != null) {
	                chaine.append(vendeur.getNom() + " quitte son Etal, ");
	            } 
	            
	            int produitVendu = quantiteDebutMarche - quantite;
	            if (produitVendu > 0) {
	                chaine.append("il a vendu " + produitVendu + " parmi " + produit + ".\n");
	            } else {
	                chaine.append("il n'a malheureusement rien vendu.\n");
	            }
	            return chaine.toString();
	        }
	    } catch (IllegalStateException e) {
	        return e.getMessage();
	    }
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'Etal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'Etal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
	    // Vérifier si l'acheteur est null
	    if (acheteur == null) {
	        throw new NullPointerException("L'acheteur ne peut pas être null.");
	    }
	    
	    // Vérifier si la quantité à acheter est positive
	    if (quantiteAcheter < 1) {
	        throw new IllegalArgumentException("La quantité à acheter doit être positive.");
	    }
	    
	    // Vérifier si l'étal est occupé
	    if (!etalOccupe) {
	        throw new IllegalStateException("L'étal n'est pas occupé. Impossible d'acheter.");
	    }

	    // Le reste de votre code reste inchangé
	    StringBuilder chaine = new StringBuilder();
	    chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
	            + " " + produit + " à " + vendeur.getNom());
	    if (quantite == 0) {
	        chaine.append(", malheureusement il n'y en a plus !");
	        quantiteAcheter = 0;
	    }
	    if (quantiteAcheter > quantite) {
	        chaine.append(", comme il n'y en a plus que " + quantite + ", "
	                + acheteur.getNom() + " vide l'étal de "
	                + vendeur.getNom() + ".\n");
	        quantiteAcheter = quantite;
	        quantite = 0;
	    }
	    if (quantite != 0) {
	        quantite -= quantiteAcheter;
	        chaine.append(". " + acheteur.getNom()
	                + ", est ravi de tout trouver sur l'étal de "
	                + vendeur.getNom() + "\n");
	    }
	    return chaine.toString();
	}
	
	public Gaulois partirVendeur()

	public boolean contientProduit(String produit) {
	    return produit != null && produit.equals(this.produit);
	}

}
