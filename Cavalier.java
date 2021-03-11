/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
public class Cavalier extends Piece {

    /**
     *
     * @param est_blanc
     * @param colonne
     * @param ligne
     * @param echiquier
     */
    public Cavalier ( boolean est_blanc, int colonne, int ligne, Echiquier echiquier ) {
        super(est_blanc, colonne, ligne, echiquier);
    }

    /**
     *
     * @return
     */
    public String representationAscii () {
        if ( estBlanc() )
            return "C";
        else
            return "c";
    }

    /**
     *
     * @return
     */
    public String representationUnicode () {
        if ( estBlanc() )
            return "♘";
        else
            return "♞";
    }

    /**
     * @Override de la methode dans la class Piece
     * @param colonne
     * @param ligne
     * @return
     */
    public boolean deplacementValide (int colonne, int ligne) {

        if (this.getColonne() == colonne && this.getLigne() == ligne) {
            return false;
        } else if ( super.deplacementValide(colonne, ligne) && mouvementCavalier(colonne, ligne) ) {
            return true;
        } else
            return false;
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return
     */
    public boolean mouvementCavalier (int colonne, int ligne) {

        if ( Math.pow((this.getColonne() - colonne), 2) + Math.pow((this.getLigne() - ligne), 2) == 5) {
            return true;
        } else
            return false;
    }

}
