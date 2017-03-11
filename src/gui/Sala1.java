/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author wagne
 */
public class Sala1 extends JFrame implements ActionListener {
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;

    public Sala1 () {
        initGUI();
    }
    
    private void initGUI () {
        //Características da Janela
        setTitle("Castelo do Draks");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        setResizable(false);
        setVisible(true);
        
        JLabel background = new JLabel ("");
        
        background.setLayout(new GridLayout(11,11));
        
        //linha 1
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        btnNorte = new JButton ("Norte");
        btnNorte.addActionListener(this);
        background.add(btnNorte);
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 2
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 3
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 4
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 5
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 6
        btnOeste = new JButton ("Oeste");
        btnOeste.addActionListener(this);
        background.add (btnOeste);
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        btnLeste = new JButton ("Leste");
        btnLeste.addActionListener(this);
        background.add(btnLeste);
        
        //linha 7
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 8
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 9
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 10
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        
        //linha 11
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        btnSul = new JButton ("Sul");
        btnSul.addActionListener(this);
        background.add(btnSul);
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));
        background.add(new JLabel (""));  
        
        add(background);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNorte) {
            System.out.println ("Norte");
            JOptionPane.showMessageDialog(null, "Norte");            
	} else if (ae.getSource() == btnSul) {
            System.out.println ("Sul");
            JOptionPane.showMessageDialog(null, "Sul");
	} else if (ae.getSource() == btnLeste) {
            System.out.println ("Leste");
            JOptionPane.showMessageDialog(null, "Leste");
        } else if (ae.getSource() == btnOeste) {
            System.out.println ("Oeste");
            JOptionPane.showMessageDialog(null, "Oeste");
        }
    }
    
}
