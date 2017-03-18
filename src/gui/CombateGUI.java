
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author wagner
 */
public class CombateGUI extends JFrame implements ActionListener{
    
    private JButton btnAtacar;
    private JButton btnFugir;
    private JButton btnMagia;
    private JButton btnItem;
    private JList listaMagia;
    private JList listaItem;
    private JScrollPane pMagia;
    private JScrollPane pItem;
    
    public CombateGUI() {
        initGUI();
    }
    
    private void initGUI() {
        //Características da Janela
        setTitle("Combate");
        setSize(720, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        btnAtacar = new JButton ("Atacar");
        btnAtacar.setBounds(30,500,200,50); //x, y, largura, altura
        btnAtacar.addActionListener(this);
        add(btnAtacar);
        
        btnFugir = new JButton ("Fugir");
        btnFugir.setBounds(30, 600, 200, 50);
        btnFugir.addActionListener(this);
        add(btnFugir);
        
        btnMagia = new JButton ("Magia");
        btnMagia.setBounds(300, 400, 100, 50);
        btnMagia.addActionListener(this);
        add(btnMagia);
        
        btnItem = new JButton ("Item");
        btnItem.setBounds(500, 400, 100, 50);
        btnItem.addActionListener(this);
        add(btnItem);
        
        listaMagia = new JList();
	listaMagia.setModel(new DefaultListModel());
	listaMagia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listaMagia.setLayoutOrientation(JList.VERTICAL);
	listaMagia.setVisibleRowCount(-1);
	pMagia = new JScrollPane(listaMagia);
        pMagia.setBounds(300, 500, 350, 150);
        pMagia.setVisible(false);
	add(pMagia);
        
        listaItem = new JList();
	listaItem.setModel(new DefaultListModel());
	listaItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listaItem.setLayoutOrientation(JList.VERTICAL);
	listaItem.setVisibleRowCount(-1);
	pItem = new JScrollPane(listaItem);
        pItem.setBounds(300, 500, 350, 150);
        pItem.setVisible(false);
	add(pItem);
    }
    
    private void carregarItem() {
        ((DefaultListModel) listaItem.getModel()).removeAllElements();
        ((DefaultListModel) listaItem.getModel()).addElement("Espada");
    }
    
    private void carregarMagia() {
        ((DefaultListModel) listaMagia.getModel()).removeAllElements();
        ((DefaultListModel) listaMagia.getModel()).addElement("Fogo");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnMagia) {
             pMagia.setVisible(true);
             pItem.setVisible(false);
             carregarMagia();
	}
        if (ae.getSource() == btnItem) {
             pMagia.setVisible(false);
             pItem.setVisible(true);
             carregarItem();
	}
        if (ae.getSource() == btnFugir) {
            this.dispose();
        }
    }
    
}