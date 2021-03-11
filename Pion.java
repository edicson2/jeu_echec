/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
public class Pion extends Piece {

    /**
     *
     * @param est_blanc
     * @param colonne
     * @param ligne
     * @param echiquier
     */
    public Pion ( boolean est_blanc, int colonne, int ligne, Echiquier echiquier ) {
        super(est_blanc, colonne, ligne, echiquier);
    }

    /**
     *
     * @return String qui represente le pion selon en ascii la couleur
     */
    public String representationAscii () {
        if ( estBlanc() )
            return "P";
        else
            return "p";
    }

    /**
     *
     * @return String qui represente la piece en Unicode selon la couleur
     */
    public String representationUnicode () {
        if ( estBlanc() )
            return "♙";
        else
            return "♟";
    }


    /**
     * @Override de la methode dans la class Piece
     * @param colonne
     * @param ligne
     * @return boolean qui permet ou pas de déplacer la piece vers la colonne et la ligne reçu comme paramètres
     */
    public boolean deplacementValide (int colonne, int ligne) {

        if (this.getColonne() == colonne && this.getLigne() == ligne) {
            return false;
        } else if (( super.deplacementValide(colonne, ligne) && (doublePas(colonne, ligne) || devant(colonne, ligne)) || capture(colonne, ligne)) ) {

            if ( (this.estBlanc() && echiquier.examinePiece(colonne, this.getLigne()-1) == null ) ||
                    ( !(this.estBlanc()) && echiquier.examinePiece(colonne, this.getLigne()+1) == null) ) {

                return true;
            } else if ( capture(colonne, ligne) ) {

                return true;
            } else {
                return false;
            }
        } else
            return false;
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return boolean qui permet de se déplacer en diagonal pour capturer une autre piece
     */
    public boolean capture (int colonne, int ligne) {
        if ( Math.abs(this.getColonne() - colonne) == 1 && echiquier.examinePiece(colonne, ligne) != null &&
                echiquier.examinePiece(colonne, ligne).estBlanc() != this.estBlanc() ) {
            return true;
        } else
            return false;
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return boolean qui permet d'avancer s'il n'y a pas de pieces en avant
     */
    public boolean devant (int colonne,  int ligne) {

        int avancee = 0;

        if (this.estBlanc()) {
            avancee = 1;
        } else {
            avancee = -1;
        }

        if ( this.getColonne() == colonne && this.getLigne() != ligne && this.getLigne() - ligne == avancee &&
                echiquier.examinePiece(colonne, ligne) == null) {

            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return boolean pour savoir si on se trouve dans la position initial du pion
     */
    public boolean debut (int colonne, int ligne) {
        return (this.getLigne() == 6 && this.estBlanc()) || (this.getLigne() == 1 && !this.estBlanc());
    }

    /**
     *
     * @param colonne
     * @param ligne
     * @return boolean pour se déplacer deux pas à partir de la position initial du Pion
     */
    public boolean doublePas (int colonne, int ligne) {
        int avancee = 0;

        if (this.estBlanc()) {
            avancee = 1;
        } else {
            avancee = -1;
        }

        if ( echiquier.examinePiece(this.getColonne(), this.getLigne() + avancee) != null &&
                this.debut(this.getColonne(), this.getLigne()) && Math.abs(this.getLigne() - ligne) == 2 ) {
            return true;
        } else {
            return false;
        }

    }

}