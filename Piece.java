/**
 *
 * @author Edicson Garcia Luis      -   20058544
 * @author Anthony Cisneros Santos   -   20061550
 */
abstract class Piece {

    private boolean est_blanc;
    private boolean est_capture;
    private int colonne;
    private int ligne;
    protected Echiquier echiquier;

    /**
     *
     * @return vrai si la Piece est blanc, false sinon
     */
    public boolean estBlanc () {
        return est_blanc;
    }

    /**
     *
     * @return vrai si la Piece est noir, false sinon
     */
    public boolean estNoir () {
        return !(est_blanc);
    }

    /**
     *
     * @return vrai si la Piece est deja capture
     */
    public boolean estCapture () {
        return est_capture;
    }

    /**
     *
     * @return le nombre de la ligne d'un Piece
     */
    public int getLigne () {
        return ligne;
    }

    /**
     *
     * @return le nombre de la colonne d'un Piece
     */
    public int getColonne () {
        return colonne;
    }

    /**
     *
     * @param est_blanc
     * @param colonne
     * @param ligne
     * @param echiquier
     */
    protected Piece ( boolean est_blanc, int colonne, int ligne, Echiquier echiquier ) {

        this.est_blanc = est_blanc;
        this.colonne = colonne;
        this.ligne = ligne;
        this.echiquier = echiquier;
        est_capture = false;

    }

    /**
     *
     */
    public void meSuisFaitCapture () {
        est_capture = true;
    }

    /**
     *
     * @param nouvelle_colonne
     * @param nouvelle_ligne
     * @return
     */
    public boolean deplacementValide (int nouvelle_colonne, int nouvelle_ligne) {

        if ( this.estCapture() ) {
            return false;
        } else if ( !(echiquier.caseValide(nouvelle_colonne, nouvelle_ligne)) ) {
            return false;
        } else if ( echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne) == null ) {
            return true;
        } else if ( (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne)).estBlanc() != this.estBlanc() ) {
            return true;
        } else {
            return true;
        }

    }

    /**
     *  @param nouvelle_colonne
     *
     */
    public void deplace(int nouvelle_colonne, int nouvelle_ligne) {

        Piece piecePris = echiquier.prendsPiece(colonne, ligne);
        Piece victime = echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne);

        if ( victime == null ) {
            piecePris.colonne = nouvelle_colonne;
            piecePris.ligne = nouvelle_ligne;
            echiquier.posePiece(piecePris);
        } else if ( victime.estBlanc() == piecePris.estBlanc() ) {
            echiquier.posePiece(piecePris);
        } else {
            System.out.println( piecePris.representationUnicode() + " a capturé " + victime.representationUnicode() + " en "
                            + transformationColonne(victime.getColonne()) + (transformationLigne(victime.getLigne()+1)) );
            victime.meSuisFaitCapture();
            victime.colonne = -1;
            victime.ligne = -1;
            echiquier.capturePiece(nouvelle_colonne, nouvelle_ligne);
            piecePris.colonne = nouvelle_colonne;
            piecePris.ligne = nouvelle_ligne;
            echiquier.posePiece(piecePris);
        }

    }

    /**
     *
     * @return
     */
    public String toString () {
        return this.representationAscii() + " - " + this.representationUnicode() +  " - est_blanc: " + this.estBlanc() +
                ", est_capture: " + this.est_capture + ", colonne: " + this.getColonne() + ", ligne: " + this.getLigne() + "\n";
    }

    abstract public String representationAscii ();
    abstract String representationUnicode ();


    /**
     *
     * @param colonne
     * @return
     */
    static char transformationColonne (int colonne) {
        switch (colonne) {
            case 0 : return 'a';
            case 1 : return 'b';
            case 2 : return 'c';
            case 3 : return 'd';
            case 4 : return 'e';
            case 5 : return 'f';
            case 6 : return 'g';
            case 7 : return 'h';
            default: return ' ';
        }
    }

    static int transformationLigne (int ligne) {
        switch (ligne) {
            case 0 : return 8;
            case 1 : return 7;
            case 2 : return 6;
            case 3 : return 5;
            case 4 : return 4;
            case 5 : return 3;
            case 6 : return 2;
            case 7 : return 1;
            default: return -1;
        }
    }

}
