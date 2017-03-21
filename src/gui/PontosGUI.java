/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Button btnIncrementar;
    
    public PontosGUI () {
        initGUI();
    }
    
    private void initGUI() {
        //Características da Janela
        setTitle("Pontos");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        jLabelForca = new JLabel ("Força: "+HEROI.getForca());
        jLabelForca.setBounds(5, 5, 100, 30);
        add(jLabelForca);
        
        btnIncrementar = new Button ("+");
        btnIncrementar.setBounds(105, 5, 50, 50);
        add(btnIncrementar);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
