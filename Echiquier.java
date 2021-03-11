import java.io.*;

/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
public class Echiquier {

    private Piece[][] tableau_de_jeu;
    private Piece[] blancs_captures;
    private Piece[] noirs_captures;


    /**
     *
     * @return
     */
    public Piece[] getBlancs_captures() {
        return blancs_captures;
    }

    /**
     *
     * @return
     */
    public Piece[] getNoirs_captures() {
        return noirs_captures;
    }

   /**
     *
     * @param colonne
     * @param ligne
     * @return
     */
    public boolean caseValide (int colonne, int ligne) {

        if ( (colonne < 0 || colonne > 7) || (ligne < 0 || ligne > 7) ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return
     */
    public Piece examinePiece (int colonne, int ligne) {

        if (tableau_de_jeu[ligne][colonne] == null)
            return null;
        else
            return tableau_de_jeu[ligne][colonne];
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return
     */
    public Piece prendsPiece (int colonne, int ligne) {

        if (tableau_de_jeu[ligne][colonne] == null) {
            return null;
        } else {
            Piece piecePris = tableau_de_jeu[ligne][colonne];
            tableau_de_jeu[ligne][colonne] = null;
            return piecePris;
        }
    }

    /**
     *
     * @param p
     */
    public void posePiece (Piece p) {
        tableau_de_jeu[ p.getLigne() ][ p.getColonne() ] = p;
    }

    /**
     *
     * @param colonne
     * @param ligne
     */
    public void capturePiece (int colonne, int ligne) {


        if (tableau_de_jeu[ligne][colonne].estBlanc()) {

            for (int i = 0; i < blancs_captures.length ; i++) {
                if ( blancs_captures[i] == null ) {
                    blancs_captures[i] = tableau_de_jeu[ligne][colonne];
                    break;
                }
            }

            tableau_de_jeu[ligne][colonne] = null;

        } else if (tableau_de_jeu[ligne][colonne].estNoir()) {

            for (int i = 0; i < noirs_captures.length ; i++) {
                if ( noirs_captures[i] == null ) {
                    noirs_captures[i] = tableau_de_jeu[ligne][colonne];
                    break;
                }
            }

            tableau_de_jeu[ligne][colonne] = null;

        }
    }


    /**
     *
     */
    public void afficheAscii () {

        String point = ". ";

        System.out.print("Les noirs ont capture : ");
        for (int i = 0; i < blancs_captures.length; i++) {
            if (blancs_captures[i] == null) {
                break;
            } else {
                System.out.print(blancs_captures[i].representationAscii() + " ");
            }
        }
        System.out.print("\n");

        System.out.println("   a b c d e f g h   ");
        System.out.println("   ---------------   ");

        for (int i = 0; i < tableau_de_jeu.length ; i++) {

            System.out.print( (tableau_de_jeu.length - i) + "| ");

            for (int j = 0; j < tableau_de_jeu[i].length ; j++) {

                if (tableau_de_jeu[i][j] == null) {
                    System.out.print(point);
                } else {
                    System.out.print(tableau_de_jeu[i][j].representationAscii() + " ");
                }
            }
            System.out.print("|" + (tableau_de_jeu.length - i) + "\n");
        }

        System.out.println("   ---------------   ");
        System.out.println("   a b c d e f g h   ");

        System.out.print("\nLes blancs ont capture : ");
        for (int j = 0; j < noirs_captures.length; j++) {
            if (noirs_captures[j] == null) {
                break;
            } else {
                System.out.print(noirs_captures[j].representationAscii() + " ");
            }
        }
        System.out.print("\n");

    }

    /**
     *
     */
    public void afficheUnicode () {

        PrintStream sysout = null;
        try {
            sysout = new PrintStream(System.out, true, "UTF-8");
        }
        catch(UnsupportedEncodingException e)
        {}

        System.out.print("Les noirs ont capture : ");
        for (int i = 0; i < blancs_captures.length; i++) {
            if (blancs_captures[i] == null) {
                break;
            } else {
                System.out.print(blancs_captures[i].representationUnicode() + " ");
            }
        }
        System.out.print("\n");

        System.out.println("    a   b   c   d   e   f   g   h ");
        System.out.println("  ┌───┬───┬───┬───┬───┬───┬───┬───┐");

        for (int i = 0; i < tableau_de_jeu.length ; i++) {

            System.out.print( (tableau_de_jeu.length - i) + " │ ");

            for (int j = 0; j < tableau_de_jeu[i].length ; j++) {

                if (tableau_de_jeu[i][j] == null) {
                    System.out.print("  │ ");
                } else {
                    System.out.print(tableau_de_jeu[i][j].representationUnicode() + " │ ");
                }
            }
            System.out.print( (tableau_de_jeu.length - i) + "\n" );
            if ( i == tableau_de_jeu.length-1) {
                continue;
            } else {
                System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┤");
            }
        }

        System.out.println("  └───┴───┴───┴───┴───┴───┴───┴───┘");
        System.out.println("    a   b   c   d   e   f   g   h ");

        System.out.print("\nLes blancs ont capture : ");
        for (int j = 0; j < noirs_captures.length; j++) {
            if (noirs_captures[j] == null) {
                break;
            } else {
                System.out.print(noirs_captures[j].representationUnicode() + " ");
            }
        }
        System.out.print("\n");

    }

    /**
     *
     */
    public Echiquier () {
        tableau_de_jeu = new Piece[8][8];
        blancs_captures = new Piece[16];
        noirs_captures = new Piece[16];

        for (int i = 0; i < tableau_de_jeu.length ; i++) {

            for (int j = 0; j < tableau_de_jeu[i].length ; j++) {

                if ( i == 0 ) {
                    switch (j) {
                        case 0 : tableau_de_jeu[i][j] = new Tour(false, j, i, this);
                                break;
                        case 1 : tableau_de_jeu[i][j] = new Cavalier(false, j, i, this);
                                break;
                        case 2 : tableau_de_jeu[i][j] = new Fou(false, j, i, this);
                                break;
                        case 3 : tableau_de_jeu[i][j] = new Dame(false, j, i, this);
                                break;
                        case 4 : tableau_de_jeu[i][j] = new Roi(false, j, i, this);
                                break;
                        case 5 : tableau_de_jeu[i][j] = new Fou(false, j, i, this);
                                break;
                        case 6 : tableau_de_jeu[i][j] = new Cavalier(false, j, i, this);
                                break;
                        case 7 : tableau_de_jeu[i][j] = new Tour(false, j, i, this);
                                break;
                        default:    break;
                    }
                } else if ( i == 1 ) {
                    tableau_de_jeu[i][j] = new Pion(false, j, i, this);
                } else if ( i == 6 ) {
                    tableau_de_jeu[i][j] = new Pion(true, j, i, this);
                } else if ( i == 7 ) {
                    switch (j) {
                        case 0 : tableau_de_jeu[i][j] = new Tour(true, j, i, this);
                                break;
                        case 1 : tableau_de_jeu[i][j] = new Cavalier(true, j, i, this);
                                break;
                        case 2 : tableau_de_jeu[i][j] = new Fou(true, j, i, this);
                                break;
                        case 3 : tableau_de_jeu[i][j] = new Dame(true, j, i, this);
                                break;
                        case 4 : tableau_de_jeu[i][j] = new Roi(true, j, i, this);
                                break;
                        case 5 : tableau_de_jeu[i][j] = new Fou(true, j, i, this);
                                break;
                        case 6 : tableau_de_jeu[i][j] = new Cavalier(true, j, i, this);
                                break;
                        case 7 : tableau_de_jeu[i][j] = new Tour(true, j, i, this);
                                break;
                        default:    break;
                    }
                } else {
                    tableau_de_jeu[i][j] = null;
                }
            }
        }
    }


}
