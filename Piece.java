package Modele;

import outil.Tool;

import static Modele.PieceForme.*;

//import javafx.scene.shape.Shape;

public class Piece implements Runnable {


    private int y = 0;
    private boolean fige;
    private boolean [][] Matrice;
    private int codeCouleur;
    private GrilleSimple grille;
    private int x = 5;


    public Piece(GrilleSimple _grille) {
        grille = _grille;
        Matrice= new boolean[4][4];
        fige=false;
        for(int i=0 ; i<4; i++)
        {
            for (int j=0; j<4; j++)
                Matrice[i][j]=false;

        }

        modele_piece(piece_ale());
    }

    private PieceForme piece_ale()
    {

        PieceForme [] pf= PieceForme.values();
        return pf[ Tool.monRandom(0, 5)];
    }
    private void modele_piece(PieceForme pf )
    {
        int i;
        switch (pf) {
            case tetrominoT:
                for ( i = 0; i < 3; i++) {
                    Matrice[i][1] = true;
                }
                Matrice[1][0] = true;
                codeCouleur=1;
                break;

            case tetrominoI:
                for ( i = 0; i < 4; i++)
                    Matrice[i][0] = true;
                codeCouleur=2;

                break;

            case  tetrominoC:
                for (i = 0; i <2 ; i++)
                {
                    for (int j = 0; j <2 ; j++) {
                        Matrice[i][j] = true;
                        Matrice[i][j] = true;
                    }
                }
                codeCouleur=3;
                break;
            case  tetrominoL:
                for (i = 0; i <3 ; i++)
                {
                    Matrice[0][i]=true;
                }
                Matrice[1][2]=true;
                codeCouleur=4;
                break;
            case  tetrominoJ:
                for (i = 0; i <3 ; i++)
                {
                    Matrice[1][i]=true;
                }
                Matrice[0][2]=true;
                codeCouleur=5;
                break;
            case  tetrominoZ:
                Matrice[0][0]=true;
                Matrice[1][0]=true;
                Matrice[1][1]=true;
                Matrice[2][1]=true;
                codeCouleur=6;
                break;
            case  tetrominoS:
                Matrice[0][1]=true;
                Matrice[1][0]=true;
                Matrice[1][1]=true;
                Matrice[2][0]=true;
                codeCouleur=7;
                break;
        }

    }

    public void run() {

        int Nexty=y;
        Nexty++;
        if (!grille.PositionValide(Matrice, x, Nexty)){
            fige = true;

        }
        else
            y=Nexty;
    }
    public void action(Direction d)
    {
        int NextX=x, NextY=y;

        switch (d){
            case Bas: // Descend 1 case
                NextY+=1;

                if (grille.PositionValide(Matrice,NextX,NextY ))
                {
                    x=NextX;
                    y=NextY;
                }
                break;


            case Gauche: // Decale a gauche
                NextX=NextX-1;
                if (grille.PositionValide(Matrice,NextX,NextY ))

                {
                    x=NextX;
                    y=NextY;
                }
                break;

            case Droite: // Decale a droite
                NextX+=1;
                if (grille.PositionValide(Matrice,NextX,NextY ))

                {
                    x=NextX;
                    y=NextY;
                }
                break;
            case Haut: // Effectue une rotation Trigo
                boolean[][] rotatedMatrice = new boolean[4][4];

                // Effectue la rotation de la Matrice
                for (int i = 0; i < 4; i++) 
                {
                    for (int j = 0; j < 4; j++) 
                    {
                        rotatedMatrice[i][j] = Matrice[j][3 - i];
                    }
                }

                // Vérifie si la nouvelle position est valide
                if (grille.PositionValide(rotatedMatrice, x, y)) 
                {
                    Matrice = rotatedMatrice;
                }

                break;
        }

    }


    public int getCodeCouleur() {
        return codeCouleur;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public boolean isFige() {
        return fige;
    }

    public boolean getMatrice(int i, int j) {
        return Matrice[i][j];
    }
}
