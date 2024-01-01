package VueControleur;

import Modele.GrilleSimple;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;



public class VueGrilleV1 extends JPanel implements Observer {
    JPanel[][] tab;
    GrilleSimple modele;

    public VueGrilleV1(GrilleSimple _modele) {
        modele = _modele;
        setLayout(new GridLayout(modele.TAILLEY, modele.TAILLEY));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        //setBorder(blackline);
        tab = new JPanel[modele.TAILLEY][modele.TAILLEY];

        for(int j = 0; j<modele.TAILLEY;j++){
            for (int i = 0; i < modele.TAILLEY; i++) {
                JPanel caseG = new JPanel();
                tab[i][j] = caseG;
                caseG.setBorder(blackline);
                add(caseG);
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        for(int i = 0; i<modele.TAILLEY;i++){
            for (int j = 0; j < modele.TAILLEY; j++) {

                tab[i][j].setBackground(Color.white);

            }
        }
        tab[modele.getPieceCourante().getx()][modele.getPieceCourante().gety()].setBackground(Color.BLUE);

    }

}
