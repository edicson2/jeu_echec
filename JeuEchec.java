import java.util.Scanner;

/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
public class JeuEchec {

    /**
     *
     * @param caractere
     * @return
     */
    static int conversionColonne (char caractere) {

        switch (caractere) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default :
                System.out.println("La valeur de la colonne doit etre une lettre entre a et h.");
                return -1;
        }
    }

    /**
     *
     * @param caractere
     * @return
     */
    static int conversionLigne(char caractere) {
        int ligne;

        switch (caractere) {
            case '1': return 7;
            case '2': return 6;
            case '3': return 5;
            case '4': return 4;
            case '5': return 3;
            case '6': return 2;
            case '7': return 1;
            case '8': return 0;
            default :
                System.out.println("La valeur de la ligne doit etre un nombre entre 1 et 8.");
                return -1;
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public static Positions[] deplacementPossibles ( Piece p, Echiquier echiquier ) {

        Positions[] resultat = new Positions[28];
        int index = 0;

        for (int i = 0; i < resultat.length; i++) {

            for (int j = 0; j < resultat.length; j++) {

                if( p.deplacementValide(i, j) )
                    System.out.print("i : " + i + " - j : " + j + "\n");

                if ( p.deplacementValide(i, j) ) {      // Si c'est vrai, ça veut dire qu'on peut mettre la piece a cette endroit
                    resultat[index] = new Positions();  // On va créer un nouveau objet dans resultat et on va
                    resultat[index].colonne = i;        // garder la position du possible deplacement
                    resultat[index].ligne = j;
                    index++;
                }
            }
        }

        return resultat;
    }

    /**
     *
     * @param caractere
     * @return
     */
    static char changementTour (char caractere) {

        if ( caractere == 'b' ) {
            return 'n';
        } else {
            return  'b';
        }
    }

    /**
     *
     * @param tableau
     * @param affiche
     */
    static void affichage (Echiquier tableau, char affiche) {
        if (affiche == 'A' || affiche == 'a') {
            tableau.afficheAscii();
        } else {
            tableau.afficheUnicode();
        }
    }

    /**
     *
     * @param tableau
     * @param tour
     * @return
     */
    static boolean trouverRoi (Echiquier tableau, char tour) {

        if (tour == 'b') {
            for (int i = 0; i < tableau.getNoirs_captures().length; i++) {

                if (tableau.getNoirs_captures()[i] != null && tableau.getNoirs_captures()[i].representationAscii().charAt(0) == 'r') {
                    System.out.println("Le roi noir a été capturé!");
                    return true;
                }
            }
        } else {
            for (int i = 0; i < tableau.getBlancs_captures().length; i++) {

                if (tableau.getBlancs_captures()[i] != null && tableau.getBlancs_captures()[i].representationAscii().charAt(0) == 'R') {
                    System.out.println("Le roi blanc a été capturé!");
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {

        int colonne_debut, ligne_debut, colonne_destin, ligne_destin;

        char affiche;

        // On va afficher selon le String qui se trouve en args[0]
        if( args[0].equals("ascii") ) {
            affiche = 'A';
        } else {
            affiche = 'U';
        }

        Echiquier tableau = new Echiquier(); // On initialise l'echiquier avec les pieces dans leur position de depart

        String deplacement; // On garde le chaine de caracteres qui est remplir pour l'utilisateur
        char tour = 'b';    // Le tour de joueur qui commence le jeu. Il va être 'b' ou 'n' selon le tour
        boolean finir = false; // La condition pour sortir de la boucle et finir le jeu

        do {

            // On affiche le tour selon le joueur
            if ( tour == 'b' ) {
                System.out.print("Joueur Blanc? ");
            } else {
                System.out.print("Joueur Noir? ");
            }

            Scanner scan = new Scanner(System.in);
            deplacement = scan.nextLine(); // données pour faire le mouvement

            if (deplacement.length() == 0){
                affichage(tableau, affiche); // On affiche l'echiquier si l'utilisateur ne tape rien

            } else if ( deplacement.length() == 2) { // Si l'utilisateur tape deux caractères. ex: c1

                colonne_debut = conversionColonne(deplacement.charAt(0)); // On fait la conversion de la lettre en nombre colonne
                ligne_debut = conversionLigne(deplacement.charAt(1));   // On fait la conversion du nombre entre en l'indice tu tableau_de_jeu

                // Si les données entrées ne sont pas valides on affiche un message d'erreur
                if ( colonne_debut == -1 || ligne_debut == -1 ) {
                    System.out.println("Les valeurs ne sont pas valides!\n" +
                            "Il faut mettre la position initial et la destination de la pièce. Par exemple: h7 h6. Réessayez.");
                    continue; // On recommence la boucle
                }

                // Si dans les coordonnées il n'y a pas de Piece, on affiche un erreur
                if ( tableau.examinePiece(colonne_debut, ligne_debut) == null ) {
                    System.out.println("Il n'y a pas une piece dans cet endroit. Réessayez");
                } else {
                    // Utiliser les cordonnées qui se trouvent a l'interieur du casesPossibles por afficher des "x" et "X"
                    Positions[] casesPossibles = deplacementPossibles(tableau.examinePiece(colonne_debut, ligne_debut), tableau);

                    if (affiche == 'A'){
                        tableau.afficheAscii();
                    } else {
                        tableau.afficheUnicode();
                    }

                }

            } else if (deplacement.length() == 5){ // Si l'utilisateur tape 5 caractères. ex: a2 a4

                colonne_debut = conversionColonne(deplacement.charAt(0));   // On fait la conversion de la lettre en nombre colonne
                ligne_debut = conversionLigne(deplacement.charAt(1));       // On fait la conversion du nombre entre en l'indice tu tableau_de_jeu

                colonne_destin = conversionColonne(deplacement.charAt(3));  // On fait la conversion de la lettre en nombre colonne
                ligne_destin = conversionLigne(deplacement.charAt(4));      // On fait la conversion du nombre entre en l'indice tu tableau_de_jeu

                // Si les données entrées ne sont pas valides on affiche un message d'erreur
                if ( colonne_debut == -1 || ligne_debut == -1 || colonne_destin == -1 || ligne_destin == -1 ) {
                    System.out.println("Les valeurs ne sont pas valides!\n" +
                            "Il faut mettre la position initial et la destination de la pièce. Par exemple: h7 h6. Réessayez.");
                    continue; // On recommence la boucle
                }

                // On essai l'instruction avant d'obtenir un erreur
                try {

                    // On determine si la Piece et le joueur on le même couleur
                    if ( ((tableau.examinePiece(colonne_debut, ligne_debut)).estBlanc() && tour == 'b') ||
                            ( !((tableau.examinePiece(colonne_debut, ligne_debut)).estBlanc()) && tour == 'n') ) {

                        // On verifie si le mouvement est valide
                        if ( (tableau.examinePiece(colonne_debut, ligne_debut)).deplacementValide(colonne_destin, ligne_destin) ) {
                            // On deplace la Piece
                            tableau.examinePiece(colonne_debut, ligne_debut).deplace(colonne_destin, ligne_destin);
                            affichage(tableau, affiche); // On affiche l'echiquier avec les changements

                            finir = trouverRoi(tableau, tour);  // On fini le jeu si on a capture le roi
                            tour = changementTour(tour);    // on appele la fonction pour changer le tour

                        } else  { // On affiche un message si le deplacement de pas valide
                            System.out.println("Ce n'est pas un déplacement valide. Reessayez.\n" +
                                    "Il faut mettre la position initial et la destination de la pièce. Par exemple: b2 b4. Réessayez.");
                        }
                    } else { // On affiche un message si l'utilisateur essai de déplacer un piece qui n'est pas de sa couleur
                        System.out.println("Erreur! Cet n'est pas ta pièce.");
                        System.out.println("C'est le tour du joueur " + (tour == 'b' ? "Blanc" : "Noir"  ) );
                    }
                }

                // On attrape des erreurs si on essai d'acceder un index dehors le tableau ou un case null
                catch( ArrayIndexOutOfBoundsException | NullPointerException exception ) {
                    System.out.println("Les valeurs ne sont pas valides!\n");
                }

            } else { // Si l'utilisateur tape 3 ou plus de 5 caractères on affiche une erreur
                System.out.println("Ce n'est pas un deplacement valide. Reessayez.\n" +
                                    "Il faut mettre la position initial et la destination de la piece. Par exemple: c7 c5. Réessayez.");
            }

        } while (!finir); // condition pour sortir de la boucle

    }

}

class Positions {
    public int colonne;
    public int ligne;
}