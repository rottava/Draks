/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
 * @author wagner
 */
public class Sala extends JFrame implements ActionListener {
    private String nomeSala;
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;
    private JList inventario;
    private JList acoes;

    public Sala (String nomeSala) {
        this.nomeSala = nomeSala;
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
        
        JLabel background = new JLabel (new ImageIcon("resources/sagao.jpg"));
        background.setBounds(0, 0, 1024, 720);
        
        JLabel titulo = new JLabel(nomeSala.toUpperCase());
        titulo.setFont(new Font("Dialog", Font.BOLD, 20));
        titulo.setForeground(Color.YELLOW);
        titulo.setBounds(430, 20, 350, 50);
        add(titulo);
        
        JPanel movimentos = new JPanel();
	movimentos.setLayout(new BorderLayout());
	movimentos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        movimentos.setBounds(430,580,180, 100);
        btnNorte = new JButton ("Norte");
        btnNorte.addActionListener(this);
        movimentos.add(btnNorte, BorderLayout.NORTH);
        btnSul = new JButton ("Sul");
        btnSul.addActionListener(this);
        movimentos.add(btnSul, BorderLayout.SOUTH);
        btnOeste = new JButton ("Oeste");
        btnOeste.addActionListener(this);
        movimentos.add(btnOeste, BorderLayout.WEST);
        btnLeste = new JButton ("Leste");
        btnLeste.addActionListener(this);
        movimentos.add(btnLeste, BorderLayout.EAST);
        add(movimentos);
        
        JLabel labelItens = new JLabel ("Inventário");
        labelItens.setFont(new Font("Dialog", Font.BOLD, 20));
        labelItens.setForeground(Color.YELLOW);
        labelItens.setBounds(30, 540, 100, 50);
        add(labelItens);
        
        inventario = new JList();
	inventario.setModel(new DefaultListModel());
	inventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	inventario.setLayoutOrientation(JList.VERTICAL);
	inventario.setVisibleRowCount(-1);
	JScrollPane pItens = new JScrollPane(inventario);
        pItens.setBounds(30, 580, 150, 100);
	add(pItens);
        
        JLabel labelAcoes = new JLabel ("Ações");
        labelAcoes.setFont(new Font("Dialog", Font.BOLD, 20));
        labelAcoes.setForeground(Color.YELLOW);
        labelAcoes.setBounds(930, 540, 60, 50);
        add(labelAcoes);
        
        acoes = new JList();
	acoes.setModel(new DefaultListModel());
	acoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	acoes.setLayoutOrientation(JList.VERTICAL);
	acoes.setVisibleRowCount(-1);
	JScrollPane pAcoes = new JScrollPane(acoes);
        pAcoes.setBounds(850, 580, 150, 100);
	add(pAcoes);
        
        add(background);
        
        adicionarAcoes();
    }
    
    private void adicionarAcoes() {
        ((DefaultListModel) acoes.getModel()).removeAllElements();
        ((DefaultListModel) acoes.getModel()).addElement("Mover");
        ((DefaultListModel) acoes.getModel()).addElement("Atacar");
        ((DefaultListModel) acoes.getModel()).addElement("Pegar");
        ((DefaultListModel) acoes.getModel()).addElement("Espiar");
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
