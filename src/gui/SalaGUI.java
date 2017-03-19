package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Criação das salas com seus ítens e inimigos
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
    private int itens;
    private int inventarioAtual;
    private int inventarioMaximo;
    private final String nomeSala;
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;
    private JList inventario;
    private JList acoes;
    
    /**
     * Construtor da interface gráfica da sala
     * @param nomeSala = Nome exibido como título em cada sala
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
     * Configura a quantidade de itens que terá na sala, o tipo de ítem é gerado aleatoriamente
     * @param quantidade quantidade de itens que estarão na sala
     */
    public void setarItens (int quantidade) {
        this.itens = quantidade;
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
    /**
     * Pega as saídas disponíveis da sala
     * @return String com as saídas disponíveis
     */
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
        
        adicionarInimigos();
        adicionarChefes();
        adicionarItens();
        
        btnNorte = new JButton ("Norte");
        btnNorte.setBounds(480,10,80,30); //x, y, largura, altura
        btnNorte.addActionListener(this);
        if (saidaNorte == 0) {
            btnNorte.setVisible(false);
        }
        add(btnNorte);
        btnOeste = new JButton ("Oeste");
        btnOeste.setBounds(10,300,80,30); //x, y, largura, altura
        btnOeste.addActionListener(this);
        if (saidaOeste == 0) {
            btnOeste.setVisible(false);
        }
        add (btnOeste);
        btnLeste = new JButton ("Leste");
        btnLeste.setBounds(930,300,80,30); //x, y, largura, altura
        btnLeste.addActionListener(this);
        if (saidaLeste == 0) {
            btnLeste.setVisible(false);
        }
        add(btnLeste);
        btnSul = new JButton ("Sul");
        btnSul.setBounds(480,650,80,30); //x, y, largura, altura
        btnSul.addActionListener(this);
        if (saidaSul == 0) {
            btnSul.setVisible(false);
        }
        add(btnSul);
        
        /**
         * ALTERAR AQUI PARA ATUALIZAR
         */
        inventarioAtual = 1;
        inventarioMaximo = 10;
        String txtInventario = "Inventário "+inventarioAtual+"/"+inventarioMaximo;
        
        JLabel labelItens = new JLabel (txtInventario);
        labelItens.setFont(new Font("Dialog", Font.BOLD, 20));
        labelItens.setForeground(Color.YELLOW);
        labelItens.setBounds(30, 540, 150, 50);
        add(labelItens);
        
        inventario = new JList();
	inventario.setModel(new DefaultListModel());
	inventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	inventario.setLayoutOrientation(JList.VERTICAL);
	inventario.setVisibleRowCount(-1);
	JScrollPane pItens = new JScrollPane(inventario);
        pItens.setBounds(30, 580, 150, 100);
	add(pItens);
        carregarInventario();
        add(background);        
        if (ordem == 1) {
            JOptionPane.showMessageDialog(null, "Você está no Castelo do Drácula\n\n"+
                                        "Drácula matou sua esposa e você está aqui para se vingar!\n\n"+
                                        "Ele se preparou e contratou vários capangas\n\n"+
                                        "Você tem apenas uma espada, use os ítens que encontrar pelo mapa "+
                                        "para ajudar em sua vingança!\n\nBoa sorte! Você irá precisa!");
        }
    }
    
    /**
     * Adiciona na tela a imagem dos inimigos
     */
    private void adicionarInimigos() {
        for (int i = 0; i < inimigos; i++) {
            Random gerador = new Random();
            int posicaoX = gerador.nextInt(850)+30;
            int posicaoY = gerador.nextInt(350)+100;
            JLabel inimigo = new JLabel (new ImageIcon("resources/inimigo.png"));
            inimigo.setBounds(posicaoX, posicaoY, 100, 100);
            add(inimigo);
            inimigo.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    CombateGUI combate = new CombateGUI("Nome Heroi", "Nome Vilao");
                }
                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });   
        }
    }
    
    /**
     * Adiciona na tela a imagem dos chefes
     */
    private void adicionarChefes() {
        for (int i = 0; i < chefe; i++) {
            Random gerador = new Random();
            int posicaoX = gerador.nextInt(650)+50;
            int posicaoY = gerador.nextInt(200)+100;
            JLabel chefe = new JLabel (new ImageIcon("resources/chefe.png"));
            chefe.setBounds(posicaoX, posicaoY, 300, 300);
            add(chefe);
            chefe.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPopupMenu pop = new JPopupMenu ();
                    JMenuItem menuItem = new JMenuItem ("Lutar");
                    pop.add(menuItem);
                    pop.show(chefe, 100, 100);
                    menuItem.addActionListener((ActionEvent e1) -> {
                        JOptionPane.showMessageDialog(null, "Matando...");
                    });
                }
                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });      
        }
    }
    
    private void adicionarItens() {
        for (int i = 0; i < itens; i++) {
            Random gerador = new Random();
            int posicaoX = gerador.nextInt(650)+50;
            int posicaoY = gerador.nextInt(200)+100;
            int itemAleatorio = gerador.nextInt(10);
            if (itemAleatorio == 0 || itemAleatorio == 1 || itemAleatorio == 2 || itemAleatorio == 3 || itemAleatorio == 4 || itemAleatorio == 5) {
                    JLabel ouro = new JLabel (new ImageIcon("resources/ouro.png"));
                    ouro.setBounds(posicaoX, posicaoY, 30, 30);
                    add(ouro);
                    ouro.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JPopupMenu pop = new JPopupMenu ();
                        JMenuItem menuItem = new JMenuItem ("Pegar");
                        pop.add(menuItem);
                        pop.show(ouro, 5, 5);
                        menuItem.addActionListener((ActionEvent e1) -> {
                            JOptionPane.showMessageDialog(null, "Pegando...");
                        });
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                    });
            }
            if (itemAleatorio == 6 || itemAleatorio == 7) {
                JLabel pocaoCura = new JLabel (new ImageIcon("resources/pocaoCura.png"));
                pocaoCura.setBounds(posicaoX, posicaoY, 30, 30);
                add(pocaoCura);
                pocaoCura.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPopupMenu pop = new JPopupMenu ();
                    JMenuItem menuItem = new JMenuItem ("Pegar");
                    pop.add(menuItem);
                    pop.show(pocaoCura, 5, 5);
                    menuItem.addActionListener((ActionEvent e1) -> {
                        JOptionPane.showMessageDialog(null, "Pegando...");
                    });
                }
                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
                });
            }
        }
    }
    
    /**
     * Itens disponíveis no inventário
     */
    private void carregarInventario() {
        ((DefaultListModel) inventario.getModel()).removeAllElements();
        ((DefaultListModel) inventario.getModel()).addElement("Espada");
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