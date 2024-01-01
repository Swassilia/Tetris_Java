package Modele;

import java.util.Observable;


public class GrilleSimple extends Observable implements Runnable {

    public final int TAILLEY = 20;
    public final int TAILLEX = 10;
    private  int score=0;

    private boolean [][] grille =new boolean[TAILLEX][TAILLEY];
    private Piece pieceCourante = new Piece(this);
    private  Piece nextPiece=new Piece(this);


    public GrilleSimple() {

        for(int i=0 ; i<TAILLEX; i++)
        {
            for (int j=0; j<TAILLEY; j++)
                grille[i][j]=false;

        }
        new OrdonnanceurSimple(this).start(); // pour changer le temps de pause, garder la référence de l'ordonnanceur

    }

    public void action(Direction d) {
        pieceCourante.action(d);

        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();

    }

    public boolean validationPosition(int _nextX, int _nextY) {
        return (_nextY>=0 && _nextY < TAILLEY&&_nextX>=0 && _nextX < TAILLEX);
    }

    public  boolean PositionValide(boolean[][] matrice,int x, int y) {
        // Vérifier si les coordonnées sont dans les limites de la matrice
        for(int i=0; i<4; i++)
        {
            for (int j=0; j<4; j++)
            {
                if (matrice[i][j]) {
                    int Nextx = x + i;
                    int Nexty = y + j;
                    if (!validationPosition(Nextx, Nexty)||grille[Nextx][Nexty])
                        return false;
                }
            }
        }
        return true;
    }

    public void memorisation()
    {
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4; j++)
            {
                if(pieceCourante.getMatrice(i,j))
                {
                    grille[pieceCourante.getx()+i][pieceCourante.gety()+j]=true;
                }
            }
        }
    }

    public void run() {
            pieceCourante.run();

            if (pieceCourante.isFige()) {
                memorisation();
                score++;
                pieceCourante = nextPiece;
                nextPiece = new Piece(this);
            }
            if (ligne_complete())
            {
                score += 50;

                setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
                notifyObservers();
            }
            if (defaite())
            {

                System.out.println("Bouh t'as perdu");
            }

        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();

    }

    public boolean ligne_complete() 
    {
        for (int j = 0; j < TAILLEY; j++) 
        {
            boolean ligneComplete = true;
            for (int i = 0; i < TAILLEX; i++) 
            {
                if (!grille[i][j]) 
                {

                    ligneComplete = false;
                    break;
                }
            }

            if (ligneComplete) 
            {
            // Supprime la ligne et fait descendre les autres

                supprimerLigne(j);
                return true;
            }
        }
        return false;
    }

    private void supprimerLigne(int ligne) 
    {
        // Decale les autres lignes en bas
        for (int i = ligne; i > 0; i--) 
        {
            for (int j = 0; j < TAILLEX; j++) 
            {
                grille[j][i] = grille[j][i - 1];
            }
        }
    // La première ligne devient vide WOW CEST MAGIQUE
        for (int j = 0; j < TAILLEX; j++) 
        {
            grille[j][0] = false;
        }
    }

    public boolean defaite()
    {
        for (int i = 0; i < TAILLEX; i++)
        {
            if (grille[i][0])
            {

                return true;
            }

        }
        return false;
    }
    public Piece getPieceCourante() {
        return pieceCourante;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    public boolean getGrille(int i, int j)
    {
        return grille[i][j];
    }

    public int getscore()
    {

        return score;
    }


}
