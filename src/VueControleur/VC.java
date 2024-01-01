
ackage VueControleur;

import Modele.Direction;
import Modele.GrilleSimple;
import Modele.Direction;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VC extends JFrame implements Observer {

    JTextField jt = new JTextField("");

    GrilleSimple modele;

    Observer vueGrille;
    private Executor ex =  Executors.newSingleThreadExecutor();

    public VC(GrilleSimple _modele) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modele = _modele;

        setSize(400, 550);
        JPanel jp = new JPanel(new BorderLayout());
        jp.setLayout(new BorderLayout());
        vueGrille = new VueGrilleV2(modele); // composant AWT dédié

        jp.add((JPanel)vueGrille,BorderLayout.CENTER);
        jp.add(jt,BorderLayout.NORTH);
        //vueGrille = new VueGrilleV1(modele); // composants swing, saccades
        setContentPane(jp);


//        jb.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ex.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        modele.run();
//                    }
//
//                });
//            }
//        });
        //bouton gauche
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {


