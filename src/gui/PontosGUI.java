/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import static main.Main.HEROI;

/**
 *
 * @author Wagner
 */
public class PontosGUI extends JFrame implements ActionListener{
 
    private JLabel jLabelForca;
    private JLabel jLabelPontos;
    private Button btnIncrementar;
    
    public PontosGUI () {
        initGUI();
    }
    
    private void initGUI() {
        //Características da Janela
        setTitle("Pontos");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        jLabelPontos = new JLabel ("Pontos: " + HEROI.getPontos());
        jLabelPontos.setBounds(100, 5, 100, 30);
        add(jLabelPontos);
        
        jLabelForca = new JLabel ("Força: " + HEROI.getForca());
        jLabelForca.setBounds(5, 55, 100, 30);
        add(jLabelForca);
        
        btnIncrementar = new Button ("+");
        btnIncrementar.setBounds(105, 55, 30, 30);
        add(btnIncrementar);
        btnIncrementar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    incrementar();
                }       
            }
        });
    }
    
    private void incrementar() {
        if (HEROI.getPontos() > 0) {
            HEROI.setForca((byte) (HEROI.getForca()+1));
            HEROI.setPontos((byte) (HEROI.getPontos()-1));
            jLabelForca.setText("Força: "+HEROI.getForca());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
