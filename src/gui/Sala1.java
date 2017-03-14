/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author wagne
 */
public class Sala1 extends JFrame implements ActionListener {
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;
    private JList itens;
    private JList acoes;

    public Sala1 () {
        initGUI();
    }
    
    private void initGUI () {
        //Características da Janela
        setTitle("Castelo do Draks");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        btnNorte = new JButton ("Norte");
        btnNorte.setBounds(480,10,80,30); //x, y, largura, altura
        btnNorte.addActionListener(this);
        add(btnNorte);
        btnOeste = new JButton ("Oeste");
        btnOeste.setBounds(10,300,80,30); //x, y, largura, altura
        btnOeste.addActionListener(this);
        add (btnOeste);
        btnLeste = new JButton ("Leste");
        btnLeste.setBounds(930,300,80,30); //x, y, largura, altura
        btnLeste.addActionListener(this);
        add(btnLeste);
        btnSul = new JButton ("Sul");
        btnSul.setBounds(480,650,80,30); //x, y, largura, altura
        btnSul.addActionListener(this);
        add(btnSul);
        
        JLabel labelItens = new JLabel ("Itens");
        labelItens.setFont(new Font("Dialog", Font.BOLD, 20));
        labelItens.setBounds(30, 450, 50, 50);
        add(labelItens);
        
        itens = new JList();
	itens.setModel(new DefaultListModel());
	itens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	itens.setLayoutOrientation(JList.VERTICAL);
	itens.setVisibleRowCount(-1);
	JScrollPane pItens = new JScrollPane(itens);
        pItens.setBounds(30, 500, 300, 170);
	add(pItens);
        
        JLabel labelAcoes = new JLabel ("Ações");
        labelAcoes.setFont(new Font("Dialog", Font.BOLD, 20));
        labelAcoes.setBounds(930, 450, 60, 50);
        add(labelAcoes);
        
        acoes = new JList();
	acoes.setModel(new DefaultListModel());
	acoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	acoes.setLayoutOrientation(JList.VERTICAL);
	acoes.setVisibleRowCount(-1);
	JScrollPane pAcoes = new JScrollPane(acoes);
        pAcoes.setBounds(700, 500, 300, 170);
	add(pAcoes);
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
