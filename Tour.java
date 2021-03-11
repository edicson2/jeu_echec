/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
public class Tour extends Piece {

    /**
     *
     * @param est_blanc
     * @param colonne
     * @param ligne
     * @param echiquier
     */
    public Tour ( boolean est_blanc, int colonne, int ligne, Echiquier echiquier ) {
        super(est_blanc, colonne, ligne, echiquier);
    }

    /**
     *
     * @return
     */
    public String representationAscii () {
        if ( estBlanc() )
            return "T";
        else
            return "t";
    }

    /**
     *
     * @return
     */
    public String representationUnicode () {
        if ( estBlanc() )
            return "♖";
        else
            return "♜";
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
        } else if ( super.deplacementValide(colonne, ligne) && this.lignesEtColonnesLibres(colonne, ligne) && this.horizontalEtVertical(colonne, ligne) ){//
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
    public boolean horizontalEtVertical(int colonne, int ligne) {

        if ( (this.getColonne() == colonne && this.getLigne() != ligne) || (this.getColonne() != colonne && this.getLigne() == ligne) ) {
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

    public boolean lignesEtColonnesLibres(int colonne, int ligne) {

        int distance = 0;

        if (this.getColonne() - colonne == 0) {
            distance = Math.abs(this.getLigne() - ligne);
        } else {
            distance = Math.abs(this.getColonne() - colonne);
        }

        if (this.getColonne() == colonne) {

            if (this.getLigne() - ligne < 0) {
                for (int i = 0; i < distance-1 ; i++) {
                    if (echiquier.examinePiece(this.getColonne(), this.getLigne() + i + 1) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < distance-1 ; i++) {
                    if (echiquier.examinePiece(this.getColonne(), this.getLigne() - i - 1) != null) {
                        return false;
                    }
                }

            }

        } else {

            if (this.getColonne() - colonne < 0) {
                for (int i = 0; i < distance-1 ; i++) {
                    if (echiquier.examinePiece(this.getColonne() + i + 1, getLigne()) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < distance-1 ; i++) {
                    if (echiquier.examinePiece(this.getColonne() - i - 1, this.getLigne()) != null) {
                        return false;
                    }
                }
            }
        }

        return true;

    }
}
