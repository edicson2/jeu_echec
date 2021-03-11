/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
public class Roi extends Piece {

    /**
     *
     * @param est_blanc
     * @param colonne
     * @param ligne
     * @param echiquier
     */
    public Roi ( boolean est_blanc, int colonne, int ligne, Echiquier echiquier ) {
        super(est_blanc, colonne, ligne, echiquier);
    }

    /**
     *
     * @return
     */
    public String representationAscii () {
        if ( estBlanc() )
            return "R";
        else
            return "r";
    }

    /**
     *
     * @return
     */
    public String representationUnicode () {
        if ( estBlanc() )
            return "♔";
        else
            return "♚";
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
        } else if( super.deplacementValide(colonne, ligne) &&
                (Math.abs(this.getColonne() - colonne) <= 1 && Math.abs(this.getLigne() - ligne) <= 1) ) {
            return true;
        } else
            return false;
    }

}