
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
    private JLabel jLabelNomeHeroi;
    private JLabel jLabelNomeVilao;
    private String nomeHeroi;
    private String nomeVilao;
    private String informacoesHeroi;
    private String informacoesVilao;
    private JTextArea jTextAreaHeroi;
    private JTextArea jTextAreaVilao;
    
    public CombateGUI(String nomeHeroi, String nomeVilao) {
        this.nomeHeroi = nomeHeroi;
        this.nomeVilao = nomeVilao;
        this.informacoesHeroi = "Saúde: "+
                //pegarInformacoesHeroi()+
                "\n\n"+"Energia: "+
                //pegarInformacoesHeroi()+;
                "\n";
        this.informacoesVilao = "Saúde: "+
                //pegarInformacoesVilao()+
                "\n\n"+"Energia: "+
                //pegarInformacoesVilao()+;
                "\n";
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
        
        jLabelNomeHeroi = new JLabel (nomeHeroi);
        jLabelNomeHeroi.setBounds(20, 50, 200, 30);
        add(jLabelNomeHeroi);
        
        jTextAreaHeroi = new JTextArea (informacoesHeroi);
        jTextAreaHeroi.setLineWrap(true);
        jTextAreaHeroi.setEditable(false);
        jTextAreaHeroi.setBounds(20, 90, 300, 100);
        add(jTextAreaHeroi);
        
        jLabelNomeVilao = new JLabel (nomeVilao);
        jLabelNomeVilao.setBounds (400, 50, 200, 30);
        add(jLabelNomeVilao);
        
        jTextAreaVilao = new JTextArea (informacoesVilao);
        jTextAreaVilao.setLineWrap(true);
        jTextAreaVilao.setEditable(false);
        jTextAreaVilao.setBounds(400, 90, 300, 100);
        add(jTextAreaVilao);
        
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
        listaMagia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList listaMagia = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = listaMagia.locationToIndex(evt.getPoint());
                    JOptionPane.showMessageDialog(null, "Dois Cliques indice: "+index);
                }       
            }
        });
        
        listaItem = new JList();
	listaItem.setModel(new DefaultListModel());
	listaItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listaItem.setLayoutOrientation(JList.VERTICAL);
	listaItem.setVisibleRowCount(-1);
	pItem = new JScrollPane(listaItem);
        pItem.setBounds(300, 500, 350, 150);
        pItem.setVisible(false);
	add(pItem);
        listaItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList listaItem = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = listaItem.locationToIndex(evt.getPoint());
                    JOptionPane.showMessageDialog(null, "Dois Cliques indice: "+index);
                }       
            }
        });
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