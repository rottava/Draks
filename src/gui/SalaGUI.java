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
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author wagner
 */
public class SalaGUI extends JFrame implements ActionListener {
    private int saidaNorte;
    private int saidaSul;
    private int saidaLeste;
    private int saidaOeste;
    private int ordem;
    private int inimigos;
    private int chefe;
    private String nomeSala;
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;
    private JList inventario;
    private JList acoes;
    
    /**
     * Construtor da interface gráfica da sala
     * @param nomeSala = Nome exibido como título em cada sala
     * @param ordem = Número da imagem a ser exibida como background
     */
    public SalaGUI (String nomeSala) {
        this.nomeSala = nomeSala;
        this.saidaLeste = 0;
        this.saidaNorte = 0;
        this.saidaOeste = 0;
        this.saidaSul = 0;
    }
    
    /**
     * Configura a quantidade de inimigos que terão na sala
     * São inimigos fracos ou médios
     * Deve ser chamada antes da initGUI
     * @param inimigos = quantidade de inimigos na Sala
     */
    public void setarInimigos (int inimigos) {
        this.inimigos = inimigos;
    }
    
    /**
     * Configura a quantidade de chefes que terão na sala
     * Chefes são inimigos mais fortes, mais difíceis de serem derrotados
     * Deve ser chamada antes da initGUI
     * @param chefe = quantidade de chefes na Sala
     */
    public void setarChefe (int chefe) {
        this.chefe = chefe;
    }
    
    /**
     * Seta as saídas da sala
     * @param direcao = Norte, Sul, Leste, Oeste
     * @param sala = código da sala na direção
     */
    public void setarSaida (String direcao, int sala) {
        if (direcao.toLowerCase().contains("norte")) {
            this.saidaNorte = sala;
        }
        if (direcao.toLowerCase().contains("sul")) {
            this.saidaSul = sala;
        }
        if (direcao.toLowerCase().contains("leste")) {
            this.saidaLeste = sala;
        }
        if (direcao.toLowerCase().contains("oeste")) {
            this.saidaOeste = sala;
        }
    }
    
    public String pegarSaidas () {
        String saidas = "";
        if (saidaNorte != 0) {
            saidas += "Norte\n";
        }
        if (saidaSul != 0) {
            saidas += "Sul\n";
        }
        if (saidaLeste != 0) {
            saidas += "Leste\n";
        }
        if (saidaOeste != 0) {
            saidas += "Oeste\n";
        }        
        return saidas;
    }
    
    /**
     * Inicia a janela de Interface Gráfica da Sala
     * @param ordem = Número da imagem a ser exibida com background
     */
    public void initGUI (int ordem) {
        this.ordem = ordem;
        //Características da Janela
        setTitle("Castelo do Draks");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        JLabel background = new JLabel (new ImageIcon("resources/"+ordem+".jpg"));
        background.setBounds(0, 0, 1024, 720);
        
        JLabel titulo = new JLabel(nomeSala.toUpperCase());
        titulo.setFont(new Font("Dialog", Font.BOLD, 20));
        titulo.setForeground(Color.YELLOW);
        titulo.setBounds(430, 40, 350, 50);
        add(titulo);        
        
        for (int i = 0; i < inimigos; i++) {
            Random gerador = new Random();
            int posicaoX = gerador.nextInt(850)+30;
            int posicaoY = gerador.nextInt(400)+100;
            JButton inimigo = new JButton ("Inimigo "+i);
            inimigo.setBounds(posicaoX, posicaoY, 100, 100);
            add(inimigo);
            inimigo.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JPopupMenu pop = new JPopupMenu ();
                    JMenuItem menuItem = new JMenuItem ("Lutar");
                    pop.add(menuItem);
                    pop.show(inimigo, 100, 100);
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Matando...");
                        }
                    });
                }
            });      
        }
        
        for (int i = 0; i < chefe; i++) {
            Random gerador = new Random();
            int posicaoX = gerador.nextInt(850)+30;
            int posicaoY = gerador.nextInt(400)+100;
            JButton chefe = new JButton ("Chefe "+i);
            chefe.setBounds(posicaoX, posicaoY, 100, 100);
            add(chefe);
            chefe.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JPopupMenu pop = new JPopupMenu ();
                    JMenuItem menuItem = new JMenuItem ("Lutar");
                    pop.add(menuItem);
                    pop.show(chefe, 100, 100);
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Matando...");
                        }
                    });
                }
            });      
        }
        
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
    
    /**
     * Ações disponíveis na lista
     */
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
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, saidaNorte))
                this.dispose();          
	} else if (ae.getSource() == btnSul) {
            System.out.println ("Sul");
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, saidaSul))
                this.dispose();
	} else if (ae.getSource() == btnLeste) {
            System.out.println ("Leste");
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, saidaLeste))
                this.dispose();
        } else if (ae.getSource() == btnOeste) {
            System.out.println ("Oeste");
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, saidaOeste))
                this.dispose();
        }
    }
    
}
