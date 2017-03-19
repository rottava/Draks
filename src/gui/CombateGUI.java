
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import habilidade.Habilidade;
import habilidade.HabilidadeCura;
import habilidade.HabilidadeDano;
import item.Arma;
import item.Armadura;
import item.Chave;
import item.ItemCura;
import item.ItemEnergia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import static main.Main.HEROI;
import static main.Main.INIMIGOS;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.aleatorio;
import personagem.Inimigo;
import mapa.Porta;
import personagem.InimigoChefe;
import personagem.Persona;

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
    private JTextArea jTextAreaHeroi;
    private JTextArea jTextAreaVilao;
    private Inimigo inimigo;
    private boolean fuga = false;
    private final byte ordem;
    private final Porta porta;
    
    public CombateGUI(Porta porta, byte ordem) {
        setaInimigo(porta);
        this.porta = porta;
        this.ordem = ordem;
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
        JLabel background = new JLabel (new ImageIcon("resources/"+ordem+".jpg"));
        background.setBounds(0, 0, 1024, 720);
        
        jLabelNomeHeroi = new JLabel (HEROI.getNome());
        jLabelNomeHeroi.setBounds(20, 50, 200, 30);
        add(jLabelNomeHeroi);
        
        jTextAreaHeroi = new JTextArea ("Vida: " + HEROI.getVida() + "/ " + HEROI.getVidaMax() +
                "\n\nEnergia: " + HEROI.getEnergia() + "/ " + HEROI.getEnergiaMax() + "\n");
        jTextAreaHeroi.setLineWrap(true);
        jTextAreaHeroi.setEditable(false);
        jTextAreaHeroi.setBounds(20, 90, 300, 100);
        add(jTextAreaHeroi);
        
        jLabelNomeVilao = new JLabel (inimigo.getNome());
        jLabelNomeVilao.setBounds (400, 50, 200, 30);
        add(jLabelNomeVilao);
        
        jTextAreaVilao = new JTextArea ("Vida: " + inimigo.getVida() + "/ " + inimigo.getVidaMax() +
                "\n\nEnergia: " + inimigo.getEnergia() + "/ " + inimigo.getEnergiaMax() + "\n");
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
        for(int loop = 0; loop < HEROI.getItens().size(); loop++){
            if (HEROI.getItens().get(loop).getClass() == ItemCura.class || 
                    HEROI.getItens().get(loop).getClass() == ItemEnergia.class)
                ((DefaultListModel) listaMagia.getModel()).addElement(HEROI.getItens().get(loop).getNome());     
        }
    }
    
    private void carregarMagia() {
        ((DefaultListModel) listaMagia.getModel()).removeAllElements();
            for(int loop = 0; loop < HEROI.getTalentosCura().getTamanho(); loop++)
                ((DefaultListModel) listaMagia.getModel()).addElement(HEROI.getTalentosCura().getHabilidades().get(loop).getNome());     
            for(int loop = 0; loop < HEROI.getTalentosDano().getTamanho(); loop++)
                ((DefaultListModel) listaMagia.getModel()).addElement(HEROI.getTalentosDano().getHabilidades().get(loop).getNome());     
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
        if (ae.getSource() == btnAtacar) {
            byte[] comando;
            comando = testaComando(inimigo);
            if(HEROI.getAgilidade() > inimigo.getAgilidade()){
                ataqueHeroi((byte)2, (byte) 0);
                if(inimigo.getVida() == 0){
                    recompensa();
                    porta.setInimigo();
                    //POPUP VOCÊ VENCEU
                    //FECHA JANELA
                }
                else{
                    ataqueInimigo(comando[0], comando[1]);
                    if(HEROI.getVida() == 0){
                        //POPUP VOCÊ PERDEU
                        //VOLTA PRA TELA INICIAL
                    }
                }
            }
            else{
                ataqueInimigo(comando[0], comando[1]);
                ataqueHeroi((byte) 2, (byte) 0);
            }
        }
        if (ae.getSource() == btnFugir) {
            this.dispose();
        }
    }
    
    private void ataqueHeroi(byte comando, byte habilidade){
        switch (comando){
            case 1:
                causarDanoEspecial(HEROI, inimigo, HEROI.getTalentosDano().getHabilidades().get(habilidade));
                break;
            case 2:
                causarDano(HEROI, inimigo);
                break;
            case 3:
                HEROI.regenVida(HEROI.getTalentosCura().getHabilidades().get(habilidade).getEfeito());
                break;
            case 4:
                ItemCura itemCura = new ItemCura(habilidade);
                HEROI.regenVida(itemCura.getEfeito());
                HEROI.subMochila(itemCura);
                break;
            case 5:
                ItemEnergia itemEnergia = new ItemEnergia(habilidade);
                HEROI.regenEnergia(itemEnergia.getEfeito());
                HEROI.subMochila(itemEnergia);
                break;
            default:
                if(calculaFuga(HEROI))
                    fuga = true;
                break;
        }
    }
    //
    private void ataqueInimigo(byte comando, byte habilidade){
        switch (comando){
            case 1:
                causarDanoEspecial(inimigo, HEROI, inimigo.getTalentosDano().getHabilidades().get(habilidade));
                break;
            case 2:
                causarDano(inimigo, HEROI);
                break;
            case 3:
                inimigo.regenVida(inimigo.getTalentosCura().getHabilidades().get(habilidade).getEfeito());
                break;
            default:
                break;
        }
    }
    
    //VERIFICA COMANDO
    private byte[] testaComando(Inimigo inimigo){
        byte[] comando = new byte[2];
        byte auxiliar;
        if((auxiliar = testaHabilidade(inimigo)) > 0){
            if(inimigo.reduzEnergia( (byte) (inimigo.getEnergia() - 
                (inimigo.getTalentosCura().getHabilidades().get(auxiliar).getConsumo())))){
                comando[0] = 1;
                comando[1] = auxiliar;
            }
                else{
                    comando[0] = 0;
                    comando[1] = 0;
                }
            }
            else 
                if((auxiliar = testaHabilidade(inimigo)) < 0){
                    if(inimigo.reduzEnergia( (byte) (inimigo.getEnergia() - 
                        (inimigo.getTalentosDano().getHabilidades().get(-auxiliar).getConsumo())))){
                    comando[0] = 2;
                    comando[1] = (byte) -auxiliar;
                    }
                    else{
                        comando[0] = 0;
                        comando[1] = 0;
                    }
                }
                else {
                    comando[0] = 0;
                    comando[1] = 0;
                }
        return comando;
    }
    
    
    //VERIFICA HABILIDADE
    private byte testaHabilidade(Inimigo inimigo){
        byte comando, habilidade;
            if (inimigo.getTalentosCura().getTamanho() > 0){
                if(inimigo.getTalentosDano().getTamanho() > 0){
                    comando= (byte) aleatorio.nextInt(2);
                    switch (comando) {
                        case 1:
                            habilidade = (byte) aleatorio.nextInt(inimigo.getTalentosCura().getTamanho());
                            break;
                        case 2:
                            habilidade = (byte) -(aleatorio.nextInt(inimigo.getTalentosDano().getTamanho()));
                            break;
                        default:
                            habilidade = (byte) 0;
                            break;
                    }
                }
                else{
                    comando = (byte) aleatorio.nextInt(1);
                    switch (comando) {
                        case 1:
                            habilidade = (byte) aleatorio.nextInt(inimigo.getTalentosCura().getTamanho());
                            break;
                        default:
                            habilidade = (byte) 0;
                            break;
                    }
                }
            }else
                habilidade = (byte) 0;
        return habilidade;
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
    
    //DEFININE INIMIGOS NO COMBATE
    private void setaInimigo(Porta porta){
        if (porta.getInimigo() != 0)                                            //INIMIGO E VALIDO
            if(testaItem(porta.getInimigo()))                                   //INIMIGO POSSUI ITEM 
                inimigo = new InimigoChefe(porta.getInimigo());                 //SETA SE ID DO ITEM E VALIDO
            else
                inimigo = new Inimigo(porta.getInimigo());                      //SETA SE ID DO ITEM E VALIDO
        else
            inimigo = null;                                                     //INIMIGO INVALIDO
    }

    //REDUZ VIDA DO ALVO E RETORNA VERDADEIRO OU RETORNA FALSO
    private boolean causarDano(Persona atacante, Persona alvo){
        if (calcularEvasao(alvo) > calcularEvasao(atacante))                    //VERIFICA SE EVADIU ATAQUE
            return false;
        else{
            int ataque = calcularAtaque(atacante);                              //CALCULA ATAQUE
            int defesa = calcularDefesa(alvo);                                  //CALCULA DEFESA
            alvo.reduzVida((byte) (ataque - defesa));                           //REDUZ VIDA EM ATAQUE - DEFESA
            return true;
        }
    }
    
    //CALCULO DE ATAQUE = FORCA / 2 + INTELIGENCIA / 5 + EFEITO ARM) + ATAQUE%SORTE
    private int calcularAtaque(Persona atacante){
        int ataque;
        ataque = (byte) (atacante.getForca() / 2 + atacante.getInteligencia() / 5); //FORCA / 2 + INTELIGENCIA / 5
        ataque += (byte) (atacante.getArma().getEfeito());                      //EFEITO DE ARMADURA;
        ataque += (byte) (ataque * (atacante.getSorte() / 100));                //DEFESA DE SORTE EM %
        return ataque;
    }
    
    //CALCULO DE DEFESA = RESISTENCIA / 2 + AGILIDADE / 5 + EFEITO ARMADURA + DEFESA%SORTE
    private int calcularDefesa(Persona alvo){
        int defesa;
        defesa = (byte) (alvo.getResistencia() / 2 + alvo.getAgilidade() / 5);  //RESISTENCIA / 2 + VELOCIDADE / 5
        defesa += (byte) (alvo.getArmadura().getEfeito());                      //EFEITO DE ARMADURA;
        defesa += (byte) (defesa * (alvo.getSorte() / 100));                    //DEFESA DE SORTE EM %
        return defesa;
    }
    
    //CALCULO DE EVASAO
    private int calcularEvasao(Persona persona){
        int evasao;
        evasao = (persona.getAgilidade() + persona.getInteligencia() / 2);      //AGILIDADE + INTELIGENCIA / 2
        evasao += (byte) (evasao * persona.getSorte() / 100);                   //EVASAO DE SORTE EM %
        evasao += persona.getSorte() + aleatorio.nextInt(persona.getAgilidade());//SORTE + VALOR ALEATORIO ATE AGILIDADE
        return evasao;
    }
    
    //REDUZ VIDA DO ALVO E RETORNA VERDADEIRO OU RETORNA FALSO
    private boolean causarDanoEspecial(Persona atacante, Persona alvo, Habilidade habilidade){
        if ((calcularEvasao(alvo) * 1.2) > calcularEvasao(atacante))            //VERIFICA SE EVADIU ATAQUE
            return false;
        else{
            int ataque = calcularAtaqueEspecial(atacante, habilidade);          //CALCULA ATAQUE
            int defesa = calcularDefesaEspecial(alvo);                          //CALCULA DEFESA
            alvo.reduzVida((byte) (ataque - defesa));                           //REDUZ VIDA EM ATAQUE - DEFESA
            return true;
        }
    }
    
    //CALCULO DE ATAQUE = INTELIGENCIA / 2 + FORCA / 5 + EFEITO HABILIDADE + ATAQUE%SORTE
    private int calcularAtaqueEspecial(Persona atacante, Habilidade habilidade){
        int ataque;
        ataque = (byte) (atacante.getInteligencia() / 2 + atacante.getForca() / 5); //INTELIGENCIA / 2 + FORCA / 5
        ataque += (byte) (habilidade.getEfeito());                              //EFEITO DE HABILIDADE;
        ataque += (byte) (ataque * (atacante.getSorte() / 100));                //EFEITO DE SORTE EM %
        return ataque;
    }
    
    //CALCULO DE DEFESA = RESISTENCIA / 2 + AGILIDADE / 5 + EFEITO ARMADURA + DEFESA%SORTE
    private int calcularDefesaEspecial(Persona alvo){
        int defesa;
        defesa = (byte) (alvo.getResistencia() / 3 + alvo.getAgilidade() / 3);  //RESISTENCIA / 3 + VELOCIDADE / 3
        defesa += (byte) (alvo.getArmadura().getEfeito());                      //EFEITO DE ARMADURA / 10;
        defesa += (byte) (defesa * (alvo.getSorte() / 100));                    //DEFESA DE SORTE EM %
        return defesa;
    }
    
    //CALCULO DE FUGA
    private boolean calculaFuga(Persona persona){
        int agilidade = persona.getAgilidade() + persona.getSorte();
        fuga = agilidade > aleatorio.nextInt(TAMANHOMAXIMO);
        return fuga;
    }
    
    //RECOMPENSA DE BATALHA
    private void recompensa(){
        HEROI.addMoedas(inimigo.getMoedas());
        InimigoChefe inimigoChefe = (InimigoChefe) inimigo;
        if(inimigoChefe.getItemId() != 0){
            switch(inimigoChefe.getItemId()){
                case 1:
                    HEROI.addMochila(new Armadura(inimigoChefe.getItemId()));
                    break;
                case 2:
                    HEROI.addMochila(new Arma(inimigoChefe.getItemId()));
                    break;
                case 3:
                    HEROI.addMochila(new Chave(inimigoChefe.getItemId()));
                    break;
                case 4:
                    HEROI.addTalentosCura(new HabilidadeCura(inimigoChefe.getItemId()));
                    break;
                case -4:
                    HEROI.addTalentosDano(new HabilidadeDano(inimigoChefe.getItemId()));
                    break;
                case 5:
                    HEROI.addMochila(new ItemCura(inimigoChefe.getItemId()));
                    break;
                case 6:
                    HEROI.addMochila(new ItemEnergia(inimigoChefe.getItemId()));
                    break;
               default:
                    break;      
            }
        }
    }
    
}