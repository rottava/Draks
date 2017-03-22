package gui;

import habilidade.HabilidadeCura;
import habilidade.HabilidadeDano;
import item.Arma;
import item.Armadura;
import item.Chave;
import item.ItemCura;
import item.ItemEnergia;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import static main.Main.CAMINHOINIMIGOS;
import static main.Main.HEROI;
import static main.Main.SALA;
import static main.Main.FLAG;
import mapa.Porta;

/**
 * Criação das salas com seus ítens e inimigos
 * @author wagner
 */
public final class SalaGUI extends JFrame implements ActionListener {
    private int ordem;
    private int inimigos;
    private int itens;
    private String nomeSala;
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;
    private JButton btnPontos;
    private JList inventario;
    
    /**
     * Construtor da interface gráfica da sala
     */
    public SalaGUI () {
        initGUI(SALA.getId());
    }
    
    /**
     * Inicia a janela de Interface Gráfica da Sala
     * @param ordem = Número da imagem a ser exibida com background e ID da sala
     */
    public void initGUI (int ordem) {
        this.ordem = ordem;
        this.nomeSala = SALA.getNome();
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
        titulo.setBounds(10, 40, 350, 50);
        add(titulo);
        
        adicionarInimigos();
        adicionarItens();
        
        btnNorte = new JButton ("Norte");
        btnNorte.setBounds(480,10,80,30); //x, y, largura, altura
        btnNorte.addActionListener(this);
        if (SALA.getNorte().getSala() == 0 || SALA.getNorte().getInimigo() != 0 || SALA.getNorte().getItem() != 0 || SALA.getNorte().getChave() != 0) {
            btnNorte.setVisible(false);
        }
        add(btnNorte);
        
        btnNorte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    JanelaInicial ji = new JanelaInicial();
                    if (ji.irSala (SALA.getNorte().getSala()))
                        sair(); 
                }       
            }
        });
        
        btnOeste = new JButton ("Oeste");
        btnOeste.setBounds(10,300,80,30); //x, y, largura, altura
        btnOeste.addActionListener(this);
        if (SALA.getOeste().getSala() == 0 || SALA.getOeste().getInimigo() != 0 || SALA.getOeste().getItem() != 0 || SALA.getOeste().getChave() != 0) {
            btnOeste.setVisible(false);
        }
        add (btnOeste);
        btnOeste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    JanelaInicial ji = new JanelaInicial();
                    if (ji.irSala (SALA.getOeste().getSala()))
                        sair(); 
                }       
            }
        });
        btnLeste = new JButton ("Leste");
        btnLeste.setBounds(930,300,80,30); //x, y, largura, altura
        btnLeste.addActionListener(this);
        if (SALA.getLeste().getSala() == 0 || SALA.getLeste().getInimigo() != 0 || SALA.getLeste().getItem() != 0 || SALA.getLeste().getChave() != 0) {
            btnLeste.setVisible(false);
        }
        add(btnLeste);
        btnLeste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    JanelaInicial ji = new JanelaInicial();
                    if (ji.irSala (SALA.getLeste().getSala()))
                        sair(); 
                }       
            }
        });
        btnSul = new JButton ("Sul");
        btnSul.setBounds(480,650,80,30); //x, y, largura, altura
        btnSul.addActionListener(this);
        if (SALA.getSul().getSala() == 0 || SALA.getSul().getInimigo() != 0 || SALA.getSul().getItem() != 0 || SALA.getSul().getChave() != 0) {
            btnSul.setVisible(false);
        }
        add(btnSul);
        btnSul.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    JanelaInicial ji = new JanelaInicial();
                    if (ji.irSala (SALA.getSul().getSala()))
                        sair(); 
                }       
            }
        });
        
        btnPontos = new JButton ("Pontos");
        btnPontos.setBounds(930,650,80,30); //x, y, largura, altura
        btnPontos.addActionListener(this);
        add(btnPontos);
        btnPontos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    PontosGUI pontos = new PontosGUI();
                    pontos.setVisible(true);
                }       
            }
        });
        
        /**
         * ALTERAR AQUI PARA ATUALIZAR
         */
        String txtInventario = "Inventário "+HEROI.getItens().size()+"/ "+HEROI.getMochila().getTamanho();
        
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
        if (ordem == 1 && !FLAG) {
            JOptionPane.showMessageDialog(null, "Você está no Castelo do Drácula\n\n"+
                                        "Drácula matou sua esposa e você está aqui para se vingar!\n\n"+
                                        "Ele se preparou e contratou vários capangas\n\n"+
                                        "Você tem apenas uma espada, use os ítens que encontrar pelo mapa "+
                                        "para ajudar em sua vingança!\n\nBoa sorte! Você irá precisa!");
            FLAG = true;
        }
    }
    
    /**
     * Adiciona na tela a imagem dos inimigos
     */
    private void adicionarInimigos() {
        byte id;
        if ((id = SALA.getNorte().getInimigo()) != 0)
            inimigos(id, 480, 10);
        if ((id = SALA.getSul().getInimigo()) != 0)
            inimigos(id, 480, 650);
        if ((id = SALA.getLeste().getInimigo()) != 0)
            inimigos(id, 930, 300);
        if ((id = SALA.getOeste().getInimigo()) != 0)
            inimigos(id, 10, 300);   
    }
    
    private void inimigos(byte id, int posicaoX, int posicaoY){
        JLabel inimigo;
        if(testaItem(id))
            inimigo = new JLabel (new ImageIcon("resources/chefe.png"));
        else
            inimigo = new JLabel (new ImageIcon("resources/inimigo.png"));
        inimigo.setBounds(posicaoX, posicaoY, 100, 100);
        add(inimigo);
        inimigo.setVisible(true);
        inimigo.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(posicaoY == 10){
                    CombateGUI combate = new CombateGUI(SALA.getNorte());
                    sair();
                }
                if(posicaoY == 650){
                    CombateGUI combate = new CombateGUI(SALA.getSul());
                    sair();
                }
                if(posicaoX == 930){
                    CombateGUI combate = new CombateGUI(SALA.getLeste());
                    sair();
                }
                if(posicaoX == 10){
                    CombateGUI combate = new CombateGUI(SALA.getOeste());
                    sair();
                }
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
    
    private void sair(){
        this.dispose();
    }
    
    private void adicionarChaves() {
        byte id;
        if ((id = SALA.getNorte().getChave()) != 0)
            chaves(id, 480, 10);
        if ((id = SALA.getSul().getChave()) != 0)
            chaves(id, 480, 650);
        if ((id = SALA.getLeste().getChave()) != 0)
            chaves(id, 930, 300);
        if ((id = SALA.getOeste().getChave()) != 0)
            chaves(id, 10, 300);   
    }
    
    private void chaves(byte id, int posicaoX, int posicaoY){
        JLabel chave;
        chave = new JLabel (new ImageIcon("resources/cadeado.png"));
        chave.setBounds(posicaoX, posicaoY, 100, 100);
        add(chave);
        chave.setVisible(true);
        chave.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(posicaoY == 10){
                    desbloqueio(SALA.getNorte());
                }
                if(posicaoY == 650){
                    desbloqueio(SALA.getSul());
                }
                if(posicaoX == 930){
                    desbloqueio(SALA.getLeste());
                }
                if(posicaoX == 10){
                    desbloqueio(SALA.getOeste());
                }
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
    
    private void desbloqueio(Porta porta){
        for(byte loop = 0; loop < HEROI.getMochila().getTamanho(); loop++){
            if(HEROI.getItens().get(loop).getClass() == Chave.class){
                if(HEROI.getItens().get(loop).getEfeito() == porta.getChave()){
                    porta.setChave();
                    HEROI.subMochila(HEROI.getItens().get(loop));
                }
                /*else{
                    //VOCÊ NÃO POSSUI A CHAVE CORRETA!  
                }*/
            }
        }
    }
    
    /**
     * Adiciona na tela a imagem dos Itens
     */
    private void adicionarItens() {
        byte id;
        if ((id = SALA.getNorte().getItem()) != 0)
            itens(id, 480, 10);
        if ((id = SALA.getSul().getItem()) != 0)
            itens(id, 480, 650);
        if ((id = SALA.getLeste().getItem()) != 0)
            itens(id, 930, 300);
        if ((id = SALA.getOeste().getItem()) != 0)
            itens(id, 10, 300);
    }
    
    private void itens(byte id, int posicaoX, int posicaoY){
        JLabel item;
        switch (id){
            case 1: 
                item = new JLabel (new ImageIcon("resources/armadura.png"));
                break;
            case 2:
                item = new JLabel (new ImageIcon("resources/arma.png"));
                break;
            case 3:
                item = new JLabel (new ImageIcon("resources/chave.png"));
                break;
            case 4:
                item = new JLabel (new ImageIcon("resources/habilidadecura.png"));
                break;
           case -4:
                item = new JLabel (new ImageIcon("resources/habilidadedano.png"));
                break;
            case 5:
                item = new JLabel (new ImageIcon("resources/itemcura.png"));
                break;
            case 6:
                item = new JLabel (new ImageIcon("resources/itemenergia.png"));
                break;
            case 7:
                item = new JLabel (new ImageIcon("resources/moedas.png"));
                break;
            default:
                item = new JLabel (new ImageIcon("resources/moedas.png"));
                break;
        }
        item.setBounds(posicaoX, posicaoY, 100, 100);
        add(item);
        item.setVisible(true);
        item.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                byte tipo = 0;
                Porta porta = null;
                if(posicaoY == 10){
                    tipo = SALA.getNorte().getTipo();
                    porta = SALA.getNorte();
                }
                if(posicaoY == 650){
                    tipo = SALA.getSul().getTipo();
                    porta = SALA.getSul();
                }
                if(posicaoX == 930){
                    tipo = SALA.getLeste().getTipo();
                    porta = SALA.getLeste();
                }
                if(posicaoX == 10){
                    tipo = SALA.getOeste().getTipo();
                    porta = SALA.getOeste();
                }
                switch (tipo){
                    case 1: 
                        if(HEROI.addMochila(new Armadura(porta.getItem())) == 0)
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    case 2:
                        if(HEROI.addMochila(new Arma(porta.getItem())) == 0)
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    case 3:
                        if(HEROI.addMochila(new Chave(porta.getItem())) == 0)
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    case 4:
                        if(HEROI.addTalentosCura(new HabilidadeCura(porta.getItem())))
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                   case -4:
                       if(HEROI.addTalentosDano(new HabilidadeCura(porta.getItem())))
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    case 5:
                        if(HEROI.addMochila(new ItemCura(porta.getItem())) == 0)
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    case 6:
                        if(HEROI.addMochila(new ItemEnergia(porta.getItem())) == 0)
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    case 7:
                        if(HEROI.addMoedas(porta.getQuantidade()))
                            porta.setItem();
                        /*else
                            //MOCHILA CHEIA;*/
                        break;
                    default:
                        break;
                }
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
    
    /**
     * Itens disponíveis no inventário
     */
    private void carregarInventario() {
        ((DefaultListModel) inventario.getModel()).removeAllElements();
        for(byte loop = 0; loop < HEROI.getItens().size(); loop++){
            ((DefaultListModel) inventario.getModel()).addElement(HEROI.getItens().get(loop).getNome());
        }
    }
    
    @Override
    /**
     * Eventos dos botões de direção
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNorte) {
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getNorte().getSala()))
               this.dispose();           
	} else if (ae.getSource() == btnSul) {
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getSul().getSala()))
               this.dispose();
	} else if (ae.getSource() == btnLeste) {
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getLeste().getSala()))
               this.dispose();
        } else if (ae.getSource() == btnOeste) {
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getOeste().getSala()))
               this.dispose();
        }
    }
    
    //VERIFICA VALIDADE
    /**
     * Verifica a validade do Item
     * @param id ID do item
     * @return true se for válido false caso contrário
     */
    private boolean testaItem(byte id){        
        try (FileReader arq = new FileReader (CAMINHOINIMIGOS)){
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < id) {
                linha = lerArq.readLine();
                loop++;
            }
            if (linha != null){
                String[] parametros;
                parametros = linha.split("/");                                  //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
                byte tipo = (byte) Integer.parseInt(parametros[8]);             //TIPO DO ITEM PORTADO
                return tipo > 0;
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOINIMIGOS);
            e.getMessage();
        }
        return false;
    }
    
}