package VueControleur;

import Modele.GrilleSimple;
import Modele.PieceForme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;

class VueGrilleV2 extends JPanel implements Observer
{

    private final static int TAILLE = 25;
    private GrilleSimple modele;
    Canvas c;

    public VueGrilleV2(GrilleSimple _modele)
    {

        modele = _modele;
        modele.getPieceCourante();
        setLayout(new BorderLayout());
        Dimension dim = new Dimension(TAILLE*modele.TAILLEX,TAILLE*modele.TAILLEY);
        setBackground(Color.BLACK);
        c = new Canvas()
        {


            public void paint(Graphics g)
            {
                int i,j;
            //Affichage background
                for (i=0; i<TAILLE; i++)
                {
                    for ( j = 0; j <TAILLE; j++)
                    {
                        g.setColor(Color.BLACK);
                        g.fillRect(i * TAILLE, j * (TAILLE+10), TAILLE, TAILLE+10);
                    }
                }

                //Affichage grille
                for ( i = 0; i <modele.TAILLEX; i++)
                {
                    for ( j = 0; j < modele.TAILLEY; j++)
                    {
                        //if (!(i == modele.getPieceCourante().getx() && j == modele.getPieceCourante().gety())) {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);

    //                        g.drawRoundRect(i * modele.TAILLEX, j * modele.TAILLEY, 9, 9, 1, 1);
                        if (modele.getGrille(i,j))
                        {

                            g.setColor(Color.gray);
                            g.fillRect(i * TAILLE, j * TAILLE, TAILLE, TAILLE);
                        }

                    }

                }

                //affichage des pieces
                switch (modele.getPieceCourante().getCodeCouleur())
                {
                    case 1:
                        g.setColor(Color.cyan);
                        break;
                    case 2:
                        g.setColor(Color.magenta);
                        break;
                    case 3:
                        g.setColor(Color.YELLOW);
                        break;
                    case 4:
                        g.setColor(Color.BLUE);
                        break;
                    case 5:
                        g.setColor(Color.pink);
                        break;
                    case 6:
                        g.setColor(Color.WHITE);
                        break;
                    case 7 :
                    g.setColor(Color.GREEN);
                    break;
                }

                //affichage des pieces mémorisées
                for( i=0; i<4; i++)
                {
                    for( j=0; j<4;j++)
                    {
                        if (modele.getPieceCourante().getMatrice(i,j))

                            g.fillRect((modele.getPieceCourante().getx()+i) * TAILLE, (modele.getPieceCourante().gety()+j) * TAILLE, TAILLE, TAILLE);
                    }
                }
                //affichage piece suivante
                for( i=0; i<4; i++)
                {
                    for( j=0; j<4;j++)
                    {
                        if (modele.getNextPiece().getMatrice(i,j)) {
                            g.setColor(Color.pink);
                            g.fillRect((modele.getNextPiece().getx() + i+6) *TAILLE,
                                    (modele.getNextPiece().gety() + j+10) * TAILLE, TAILLE, TAILLE);
                        }
                    }
                }
            }
        };
        c.setPreferredSize(dim);
        add(c, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {

        BufferStrategy bs = c.getBufferStrategy(); // bs + dispose + show : double buffering pour éviter les scintillements
        if(bs == null) {
            c.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        c.paint(g); // appel de la fonction pour dessiner
        g.dispose();
        //Toolkit.getDefaultToolkit().sync(); // forcer la synchronisation
        bs.show();

    }
}
