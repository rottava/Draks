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
        if (SALA.getNorte().getSala() == 0 || SALA.getNorte().getInimigo() != 0 || SALA.getNorte().getItem() != 0) {
            //btnNorte.setVisible(false);
            setNorte();
        }
        add(btnNorte);
        
        btnNorte.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    testaNorte();
                }       
            }
        });
        
        btnOeste = new JButton ("Oeste");
        btnOeste.setBounds(10,300,80,30); //x, y, largura, altura
        btnOeste.addActionListener(this);
        if (SALA.getOeste().getSala() == 0 || SALA.getOeste().getInimigo() != 0 || SALA.getOeste().getItem() != 0) {
            btnOeste.setVisible(false);
        }
        add (btnOeste);
        btnOeste.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    testaOeste();
                }       
            }
        });
        btnLeste = new JButton ("Leste");
        btnLeste.setBounds(930,300,80,30); //x, y, largura, altura
        btnLeste.addActionListener(this);
        if (SALA.getLeste().getSala() == 0 || SALA.getLeste().getInimigo() != 0 || SALA.getLeste().getItem() != 0) {
            btnLeste.setVisible(false);
        }
        add(btnLeste);
        btnLeste.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    testaLeste();
                }       
            }
        });
        btnSul = new JButton ("Sul");
        btnSul.setBounds(480,650,80,30); //x, y, largura, altura
        btnSul.addActionListener(this);
        if (SALA.getSul().getSala() == 0 || SALA.getSul().getInimigo() != 0 || SALA.getSul().getItem() != 0) {
            btnSul.setVisible(false);
        }
        add(btnSul);
        btnSul.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    testaSul();
                }       
            }
        });
        
        btnPontos = new JButton ("Pontos");
        btnPontos.setBounds(930,650,80,30); //x, y, largura, altura
        btnPontos.addActionListener(this);
        add(btnPontos);
        btnPontos.addMouseListener(new MouseAdapter() {
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
        for (int i = 0; i < inimigos; i++) {
            JLabel inimigo;
            int posicaoX = 0;
            int posicaoY = 0;
            byte  id = 0;
            if (SALA.getNorte().getInimigo() != 0) {
                id = SALA.getNorte().getInimigo();
                posicaoX = 480;
                posicaoY = 10;
            }
            if (SALA.getSul().getInimigo() != 0) {
                id = SALA.getSul().getInimigo();
                posicaoX = 480;
                posicaoY = 650;
            }
            if (SALA.getLeste().getInimigo() != 0) {
                id = SALA.getLeste().getInimigo();
                posicaoX = 930;
                posicaoY = 300;
            }
            if (SALA.getOeste().getInimigo() != 0) {
                id = SALA.getOeste().getInimigo();
                posicaoX = 10;
                posicaoY = 300;
            }
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
     * Adiciona na tela a imagem dos Itens
     */
    private void adicionarItens() {
        for (int i = 0; i < itens; i++) {
            JLabel item;
            int posicaoX = 0;
            int posicaoY = 0;
            int id = 0;
            if (SALA.getNorte().getSala() != 0) {
                id = SALA.getNorte().getItem();
                posicaoX = 480;
                posicaoY = 10;
            }
            if (SALA.getSul().getSala() != 0) {
                id = SALA.getSul().getItem();
                posicaoX = 480;
                posicaoY = 650;
            }
            if (SALA.getLeste().getSala() != 0) {
                id = SALA.getLeste().getItem();
                posicaoX = 930;
                posicaoY = 300;
            }
            if (SALA.getOeste().getSala() != 0) {
                id = SALA.getOeste().getItem();
                posicaoX = 10;
                posicaoY = 300;
            }
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
            item.setBounds(posicaoX, posicaoY, 30, 30);
            add(item);
            item.addMouseListener(new MouseListener(){
            
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPopupMenu pop = new JPopupMenu ();
                    JMenuItem menuItem = new JMenuItem ("Pegar");
                    pop.add(menuItem);
                    pop.show(item, 5, 5);
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
            System.out.println("BOTAO NORTE");
            testaNorte();         
	} else if (ae.getSource() == btnSul) {
            testaSul();
	} else if (ae.getSource() == btnLeste) {
            testaLeste();
        } else if (ae.getSource() == btnOeste) {
            testaOeste();
        }
    }
    
    /**
     * Verifica o que tem na posição Norte
     */
    private void testaNorte(){
        if(btnNorte.getIcon() != null){
            if(btnNorte.getIcon().toString().equals("resources/inimigo.png")){
                CombateGUI combate = new CombateGUI(SALA.getNorte());
                this.dispose();
            }
            else{
                if(btnNorte.getIcon().toString().equals("resources/item.png")){
                    switch (SALA.getNorte().getTipo()){
                        case 1:
                            Armadura armadura = new Armadura(SALA.getNorte().getItem());
                            armadura.setQuantidade((byte) SALA.getNorte().getQuantidade());
                            HEROI.addMochila(armadura);
                            break;
                        case 2:
                            Arma arma = new Arma(SALA.getNorte().getItem());
                            arma.setQuantidade((byte) SALA.getNorte().getQuantidade());
                            HEROI.addMochila(arma);
                            break;
                        case 3:
                            Chave chave = new Chave(SALA.getNorte().getItem());
                            chave.setQuantidade((byte) SALA.getNorte().getQuantidade());
                            HEROI.addMochila(chave);
                            break;
                        case 4:
                            HabilidadeCura cura = new HabilidadeCura(SALA.getNorte().getItem());
                            HEROI.addTalentosCura(cura);
                            break;
                        case -4:
                            HabilidadeDano dano = new HabilidadeDano(SALA.getNorte().getItem());
                            HEROI.addTalentosDano(dano);
                            break;
                        case 5:
                            ItemCura itemCura = new ItemCura(SALA.getNorte().getItem());
                            itemCura.setQuantidade((byte) SALA.getNorte().getQuantidade());
                            HEROI.addMochila(itemCura);
                            break;
                        case 6:
                            ItemEnergia itemEnergia = new ItemEnergia(SALA.getNorte().getItem());
                            itemEnergia.setQuantidade((byte) SALA.getNorte().getQuantidade());
                            HEROI.addMochila(itemEnergia);
                        case 7:
                            HEROI.addMoedas(SALA.getNorte().getQuantidade());
                            break;
                       default:
                           break;
                    }
                    SALA.getNorte().setItem();
                }
                else{
                    if(btnNorte.getIcon().toString().equals("resources/cadeado.png")){
                        for(byte loop = 0; loop < HEROI.getMochila().getTamanho(); loop++){
                            if(HEROI.getItens().get(loop).getClass() == Chave.class){
                                if(HEROI.getItens().get(loop).getEfeito() == SALA.getNorte().getChave()){
                                    SALA.getNorte().setChave();
                                    HEROI.subMochila(HEROI.getItens().get(loop));
                                }
                                /*else{
                                  //VOCÊ NÃO POSSUI A CHAVE CORRETA!  
                                }*/
                            }
                        }
                    }

                }
            }
        }
        else{
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getNorte().getSala()))
               this.dispose();  
        }
        setNorte();
    }
    
    /**
     * Seta o que tem na posição Norte
     */
    private void setNorte(){
        if(SALA.getNorte().getInimigo() != 0){
            btnNorte.setVisible(true);
            ImageIcon img = new ImageIcon("resources/inimigo.png");
            btnNorte.setIcon(img);
            btnNorte.setVisible(true);
        }
        else{
            if(SALA.getNorte().getItem() != 0){
                btnNorte.setVisible(true);
                ImageIcon img = new ImageIcon("resources/item.png");
            btnNorte.setIcon(img);
            }
            else{
                if(SALA.getNorte().getChave() != 0){
                    btnNorte.setVisible(true);
                    ImageIcon img = new ImageIcon("resources/cadeado.png");
            btnNorte.setIcon(img);
                }
                else{
                    if(SALA.getNorte().getSala() != 0){
                        btnNorte.setVisible(true);
                        ImageIcon img = new ImageIcon("resources/norte.png");
            btnNorte.setIcon(img);
                    }
                    else{
                        btnNorte.setVisible(false);
                    }
                }
            }
        }
    }
    
    /**
     * Verifica o que tem na posição Sul
     */
    private void testaSul(){
        if(btnSul.getIcon() != null){
            if(btnSul.getIcon().toString().equals("resources/inimigo.png")){
                CombateGUI combate = new CombateGUI(SALA.getSul());
            }
            else{
                if(btnSul.getIcon().toString().equals("resources/item.png")){
                    switch (SALA.getSul().getTipo()){
                        case 1:
                            Armadura armadura = new Armadura(SALA.getSul().getItem());
                            armadura.setQuantidade((byte) SALA.getSul().getQuantidade());
                            HEROI.addMochila(armadura);
                            break;
                        case 2:
                            Arma arma = new Arma(SALA.getSul().getItem());
                            arma.setQuantidade((byte) SALA.getSul().getQuantidade());
                            HEROI.addMochila(arma);
                            break;
                        case 3:
                            Chave chave = new Chave(SALA.getSul().getItem());
                            chave.setQuantidade((byte) SALA.getSul().getQuantidade());
                            HEROI.addMochila(chave);
                            break;
                        case 4:
                            HabilidadeCura cura = new HabilidadeCura(SALA.getSul().getItem());
                            HEROI.addTalentosCura(cura);
                            break;
                        case -4:
                            HabilidadeDano dano = new HabilidadeDano(SALA.getSul().getItem());
                            HEROI.addTalentosDano(dano);
                            break;
                        case 5:
                            ItemCura itemCura = new ItemCura(SALA.getSul().getItem());
                            itemCura.setQuantidade((byte) SALA.getSul().getQuantidade());
                            HEROI.addMochila(itemCura);
                            break;
                        case 6:
                            ItemEnergia itemEnergia = new ItemEnergia(SALA.getSul().getItem());
                            itemEnergia.setQuantidade((byte) SALA.getSul().getQuantidade());
                            HEROI.addMochila(itemEnergia);
                        case 7:
                            HEROI.addMoedas(SALA.getSul().getQuantidade());
                            break;
                       default:
                           break;
                    }
                    SALA.getSul().setItem();
                }
                else{
                    if(btnSul.getIcon().toString().equals("resources/cadeado.png")){
                        for(byte loop = 0; loop < HEROI.getMochila().getTamanho(); loop++){
                            if(HEROI.getItens().get(loop).getClass() == Chave.class){
                                if(HEROI.getItens().get(loop).getEfeito() == SALA.getSul().getChave()){
                                    SALA.getSul().setChave();
                                    HEROI.subMochila(HEROI.getItens().get(loop));
                                }
                                /*else{
                                  //VOCÊ NÃO POSSUI A CHAVE CORRETA!  
                                }*/
                            }
                        }
                    }

                }
            }
        }
        else{
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getSul().getSala()))
               this.dispose();  
        }
        setSul();
    }
    
    /**
     * Seta o que tem na posição Sul
     */
    private void setSul(){
        if(SALA.getSul().getInimigo() != 0){
            btnSul.setVisible(true);
            ImageIcon img = new ImageIcon("resources/inimigo.png");
            btnSul.setIcon(img);
        }
        else{
            if(SALA.getSul().getItem() != 0){
                btnSul.setVisible(true);
                ImageIcon img = new ImageIcon("resources/item.png");
            btnSul.setIcon(img);
            }
            else{
                if(SALA.getSul().getChave() != 0){
                    btnSul.setVisible(true);
                    ImageIcon img = new ImageIcon("resources/cadeado.png");
            btnSul.setIcon(img);
                }
                else{
                    if(SALA.getSul().getSala() != 0){
                        btnSul.setVisible(true);
                        ImageIcon img = new ImageIcon("resources/Sul.png");
            btnSul.setIcon(img);
                    }
                    else{
                        btnSul.setVisible(false);
                    }
                }
            }
        }
    }
    
    /**
     * Verifica o que tem na posição Leste
     */
    private void testaLeste(){
        if(btnLeste.getIcon() != null){
            if(btnLeste.getIcon().toString().equals("resources/inimigo.png")){
                CombateGUI combate = new CombateGUI(SALA.getLeste());
            }
            else{
                if(btnLeste.getIcon().toString().equals("resources/item.png")){
                    switch (SALA.getLeste().getTipo()){
                        case 1:
                            Armadura armadura = new Armadura(SALA.getLeste().getItem());
                            armadura.setQuantidade((byte) SALA.getLeste().getQuantidade());
                            HEROI.addMochila(armadura);
                            break;
                        case 2:
                            Arma arma = new Arma(SALA.getLeste().getItem());
                            arma.setQuantidade((byte) SALA.getLeste().getQuantidade());
                            HEROI.addMochila(arma);
                            break;
                        case 3:
                            Chave chave = new Chave(SALA.getLeste().getItem());
                            chave.setQuantidade((byte) SALA.getLeste().getQuantidade());
                            HEROI.addMochila(chave);
                            break;
                        case 4:
                            HabilidadeCura cura = new HabilidadeCura(SALA.getLeste().getItem());
                            HEROI.addTalentosCura(cura);
                            break;
                        case -4:
                            HabilidadeDano dano = new HabilidadeDano(SALA.getLeste().getItem());
                            HEROI.addTalentosDano(dano);
                            break;
                        case 5:
                            ItemCura itemCura = new ItemCura(SALA.getLeste().getItem());
                            itemCura.setQuantidade((byte) SALA.getLeste().getQuantidade());
                            HEROI.addMochila(itemCura);
                            break;
                        case 6:
                            ItemEnergia itemEnergia = new ItemEnergia(SALA.getLeste().getItem());
                            itemEnergia.setQuantidade((byte) SALA.getLeste().getQuantidade());
                            HEROI.addMochila(itemEnergia);
                        case 7:
                            HEROI.addMoedas(SALA.getLeste().getQuantidade());
                            break;
                       default:
                           break;
                    }
                    SALA.getLeste().setItem();
                }
                else{
                    if(btnLeste.getIcon().toString().equals("resources/cadeado.png")){
                        for(byte loop = 0; loop < HEROI.getMochila().getTamanho(); loop++){
                            if(HEROI.getItens().get(loop).getClass() == Chave.class){
                                if(HEROI.getItens().get(loop).getEfeito() == SALA.getLeste().getChave()){
                                    SALA.getLeste().setChave();
                                    HEROI.subMochila(HEROI.getItens().get(loop));
                                }
                                /*else{
                                  //VOCÊ NÃO POSSUI A CHAVE CORRETA!  
                                }*/
                            }
                        }
                    }

                }
            }
        }
        else{
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getLeste().getSala()))
               this.dispose();  
        }
        setLeste();
    }
    
    /**
     * Seta o que tem na posição Leste
     */
    private void setLeste(){
        if(SALA.getLeste().getInimigo() != 0){
            btnLeste.setVisible(true);
            ImageIcon img = new ImageIcon("resources/inimigo.png");
            btnLeste.setIcon(img);
        }
        else{
            if(SALA.getLeste().getItem() != 0){
                btnLeste.setVisible(true);
                ImageIcon img = new ImageIcon("resources/item.png");
            btnLeste.setIcon(img);
            }
            else{
                if(SALA.getLeste().getChave() != 0){
                    btnLeste.setVisible(true);
                    ImageIcon img = new ImageIcon("resources/cadeado.png");
            btnLeste.setIcon(img);
                }
                else{
                    if(SALA.getLeste().getSala() != 0){
                        btnLeste.setVisible(true);
                        ImageIcon img = new ImageIcon("resources/Leste.png");
            btnLeste.setIcon(img);
                    }
                    else{
                        btnLeste.setVisible(false);
                    }
                }
            }
        }
    }
    
    /**
     * Verifica o que tem na posição Oeste
     */
    private void testaOeste(){
        if(btnOeste.getIcon() != null){
            if(btnOeste.getIcon().toString().equals("resources/inimigo.png")){
                CombateGUI combate = new CombateGUI(SALA.getOeste());
            }
            else{
                if(btnOeste.getIcon().toString().equals("resources/item.png")){
                    switch (SALA.getOeste().getTipo()){
                        case 1:
                            Armadura armadura = new Armadura(SALA.getOeste().getItem());
                            armadura.setQuantidade((byte) SALA.getOeste().getQuantidade());
                            HEROI.addMochila(armadura);
                            break;
                        case 2:
                            Arma arma = new Arma(SALA.getOeste().getItem());
                            arma.setQuantidade((byte) SALA.getOeste().getQuantidade());
                            HEROI.addMochila(arma);
                            break;
                        case 3:
                            Chave chave = new Chave(SALA.getOeste().getItem());
                            chave.setQuantidade((byte) SALA.getOeste().getQuantidade());
                            HEROI.addMochila(chave);
                            break;
                        case 4:
                            HabilidadeCura cura = new HabilidadeCura(SALA.getOeste().getItem());
                            HEROI.addTalentosCura(cura);
                            break;
                        case -4:
                            HabilidadeDano dano = new HabilidadeDano(SALA.getOeste().getItem());
                            HEROI.addTalentosDano(dano);
                            break;
                        case 5:
                            ItemCura itemCura = new ItemCura(SALA.getOeste().getItem());
                            itemCura.setQuantidade((byte) SALA.getOeste().getQuantidade());
                            HEROI.addMochila(itemCura);
                            break;
                        case 6:
                            ItemEnergia itemEnergia = new ItemEnergia(SALA.getOeste().getItem());
                            itemEnergia.setQuantidade((byte) SALA.getOeste().getQuantidade());
                            HEROI.addMochila(itemEnergia);
                        case 7:
                            HEROI.addMoedas(SALA.getOeste().getQuantidade());
                            break;
                       default:
                           break;
                    }
                    SALA.getOeste().setItem();
                }
                else{
                    if(btnOeste.getIcon().toString().equals("resources/cadeado.png")){
                        for(byte loop = 0; loop < HEROI.getMochila().getTamanho(); loop++){
                            if(HEROI.getItens().get(loop).getClass() == Chave.class){
                                if(HEROI.getItens().get(loop).getEfeito() == SALA.getOeste().getChave()){
                                    SALA.getOeste().setChave();
                                    HEROI.subMochila(HEROI.getItens().get(loop));
                                }
                                /*else{
                                  //VOCÊ NÃO POSSUI A CHAVE CORRETA!  
                                }*/
                            }
                        }
                    }

                }
            }
        }
        else{
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (SALA.getOeste().getSala()))
               this.dispose();  
        }
        setOeste();
    }
    
    /**
     * Seta o que tem na posição Oeste
     */
    private void setOeste(){
        if(SALA.getOeste().getInimigo() != 0){
            btnOeste.setVisible(true);
            ImageIcon img = new ImageIcon("resources/inimigo.png");
            btnOeste.setIcon(img);
        }
        else{
            if(SALA.getOeste().getItem() != 0){
                btnOeste.setVisible(true);
                ImageIcon img = new ImageIcon("resources/item.png");
            btnOeste.setIcon(img);
            }
            else{
                if(SALA.getOeste().getChave() != 0){
                    btnOeste.setVisible(true);
                    ImageIcon img = new ImageIcon("resources/cadeado.png");
            btnOeste.setIcon(img);
                }
                else{
                    if(SALA.getOeste().getSala() != 0){
                        btnOeste.setVisible(true);
                        ImageIcon img = new ImageIcon("resources/Oeste.png");
            btnOeste.setIcon(img);
                    }
                    else{
                        btnOeste.setVisible(false);
                    }
                }
            }
        }
    }
    
    //VERIFICA VALIDADE
    /**
     * Verifica a validade do Item
     * @param id ID do item
     * @return true se for válido false caso contrário
     */
    private boolean testaItem(byte id){        
        try {
            FileReader arq = new FileReader (CAMINHOINIMIGOS);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < id) {
                linha = lerArq.readLine();
                loop++;
            }
            if (linha != null){
                String[] parametros;
                linha = lerArq.readLine();
                parametros = linha.split("/");                     //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
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