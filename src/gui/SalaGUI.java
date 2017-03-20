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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
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
import static main.Main.HEROI;
import static main.Main.INIMIGOS;
import static main.Main.SALA;

/**
 * Criação das salas com seus ítens e inimigos
 * @author wagner
 */
public class SalaGUI extends JFrame implements ActionListener {
    private int ordem;
    private int inimigos;
    private int chefe;
    private int itens;
    private final String nomeSala;
    private JButton btnNorte;
    private JButton btnSul;
    private JButton btnLeste;
    private JButton btnOeste;
    private JList inventario;
    private boolean flag = false;
    
    /**
     * Construtor da interface gráfica da sala
     */
    public SalaGUI () {
        this.nomeSala = SALA.getNome();
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
        titulo.setBounds(10, 40, 350, 50);
        add(titulo);
        
        adicionarInimigos();
        
        btnNorte = new JButton ("Norte");
        btnNorte.setBounds(480,10,80,30); //x, y, largura, altura
        btnNorte.addActionListener(this);
        if (SALA.getNorte().getSala() == 0 || SALA.getNorte().getInimigo() != 0 || SALA.getNorte().getItem() != 0) {
            btnNorte.setVisible(false);
        }
        add(btnNorte);
        btnOeste = new JButton ("Oeste");
        btnOeste.setBounds(10,300,80,30); //x, y, largura, altura
        btnOeste.addActionListener(this);
        if (SALA.getOeste().getSala() == 0 || SALA.getOeste().getInimigo() != 0 || SALA.getOeste().getItem() != 0) {
            btnOeste.setVisible(false);
        }
        add (btnOeste);
        btnLeste = new JButton ("Leste");
        btnLeste.setBounds(930,300,80,30); //x, y, largura, altura
        btnLeste.addActionListener(this);
        if (SALA.getLeste().getSala() == 0 || SALA.getLeste().getInimigo() != 0 || SALA.getLeste().getItem() != 0) {
            btnLeste.setVisible(false);
        }
        add(btnLeste);
        btnSul = new JButton ("Sul");
        btnSul.setBounds(480,650,80,30); //x, y, largura, altura
        btnSul.addActionListener(this);
        if (SALA.getSul().getSala() == 0 || SALA.getSul().getInimigo() != 0 || SALA.getSul().getItem() != 0) {
            btnSul.setVisible(false);
        }
        add(btnSul);
        
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
        if (ordem == 1 && !flag) {
            JOptionPane.showMessageDialog(null, "Você está no Castelo do Drácula\n\n"+
                                        "Drácula matou sua esposa e você está aqui para se vingar!\n\n"+
                                        "Ele se preparou e contratou vários capangas\n\n"+
                                        "Você tem apenas uma espada, use os ítens que encontrar pelo mapa "+
                                        "para ajudar em sua vingança!\n\nBoa sorte! Você irá precisa!");
            flag = true;
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
        for(byte loop = 0; loop < HEROI.getMochila().getTamanho(); loop++){
            ((DefaultListModel) inventario.getModel()).addElement(HEROI.getItens().get(loop).getNome());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNorte) {
            testaNorte();         
	} else if (ae.getSource() == btnSul) {
            System.out.println ("Sul");
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, SALA.getSul().getSala()))
                this.dispose();
	} else if (ae.getSource() == btnLeste) {
            System.out.println ("Leste");
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, SALA.getLeste().getSala()))
                this.dispose();
        } else if (ae.getSource() == btnOeste) {
            System.out.println ("Oeste");
            JanelaInicial ji = new JanelaInicial();
            if (ji.irSala (ordem, SALA.getOeste().getSala()))
                this.dispose();
        }
    }
    
    private void testaNorte(){
        if(btnNorte.getIcon().toString().equals("resources/inimigo.png")){
            CombateGUI combate = new CombateGUI(SALA.getNorte(), ordem);
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
                        else{
                            System.out.println ("Norte");
                            JanelaInicial ji = new JanelaInicial();
                            if (ji.irSala (ordem, SALA.getNorte().getSala()))
                                this.dispose();  
                        }
                    }
                }
            }
        }
        setNorte();
    }
    
    private void setNorte(){
        if(SALA.getNorte().getInimigo() != 0){
            btnNorte.setVisible(true);
            ImageIcon img = new ImageIcon("resources/inimigo.png");
            btnNorte.setIcon(img);
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
    
    //VERIFICA VALIDADE
    private boolean testaItem(byte id){
        try {
            Scanner scanner = new Scanner(INIMIGOS);
            byte loop = 1;
            while((scanner.hasNext()) && (loop < id)){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if (scanner.hasNext()){
                String[] parametros;
                parametros = scanner.nextLine().split("/");                     //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
                byte tipo = (byte) Integer.parseInt(parametros[8]);             //TIPO DO ITEM PORTADO
                return tipo > 0;
            }
        } catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("ID de inimigo não encontrado.");
        }
        return false;
    }
    
}