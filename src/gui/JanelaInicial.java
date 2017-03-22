package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static main.Main.HEROI;
import static main.Main.MAPA;
import static main.Main.SALA;
import mapa.Mapa;
import mapa.Sala;
import personagem.Heroi;

/**
 * Janela Inicial, dando opção de continuar ou iniciar um novo jogo
 * @author wagner
 */
public class JanelaInicial extends JFrame implements ActionListener {
    private JButton btnNovoJogo;
    private JButton btnCarregarJogo;
    public static SalaGUI salaGUI;
    
    /**
     * Construtor
     */
    public JanelaInicial() {
        initGUI();
    }
    
    /**
     * Abre a janela carregando seus componentes
     */
    private void initGUI() {
        //Características da Janela
        setTitle("Castelo do Draks");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        setResizable(false);
        
        //Carrega imagem de background
        JLabel background = new JLabel (new ImageIcon("resources/backgroundInicio.jpg"));        
        
        background.setLayout(new GridLayout(4,3));
        background.setVisible(true);
        JPanel painelBotoes = new JPanel();
	painelBotoes.setLayout(new GridLayout(2,0));
        btnNovoJogo = new JButton("Novo Jogo");
        btnCarregarJogo = new JButton("Carregar Jogo");
        btnNovoJogo.addActionListener(this);
        btnCarregarJogo.addActionListener(this);
        painelBotoes.add(btnNovoJogo);
        painelBotoes.add(btnCarregarJogo);
        
        background.add(new JLabel(""));
        JLabel titulo = new JLabel ("Castelo do Draks");
        titulo.setFont(new Font("Dialog", Font.BOLD, 40));
        titulo.setForeground(Color.white);
        background.add(titulo);
        
        background.add(new JLabel(""));
        background.add(new JLabel(""));
        background.add(new JLabel(""));
        background.add(new JLabel(""));
        background.add(new JLabel(""));
        background.add(painelBotoes);
        background.add(new JLabel(""));
        background.add(new JLabel(""));
        background.add(new JLabel(""));
        
        add (background);
        
    }    
    
    @Override
    /**
     * Pega as ações dos botões
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNovoJogo) {
            System.out.println ("NOVO JOGO");
            this.dispose();
            salaGUI = new SalaGUI();
            salaGUI.initGUI(1);
	} else if (ae.getSource() == btnCarregarJogo) {
            carregar();
            this.dispose();
            salaGUI = new SalaGUI();
            salaGUI.initGUI(SALA.getId());
	}
    }
    
    /**
     * Faz a movimentação entre as salaGUIs
     * @param destino = salaGUI para qual o usuário deseja se mover
     * @return = true se a movimentação foi bem sucedida ou false caso não foi possível
     */
    public boolean irSala (int destino) {
        SALA = MAPA.getSalas().get(destino-1);
        salaGUI = new SalaGUI();
        salaGUI.initGUI(destino);
        return true;
    }
    
    /*
    Função carregar() carrega a partir de um arquivo salvo anteriormente
    Essa função é chamada sempre no início do programa caso o usuário desejar continuar
    */
    public static void carregar () {
            //TRY CATCH
        try {
            //CARREGA CARRO DO ARQUIVO MAPA.SER
            FileInputStream entrada = new FileInputStream("mapa.ser");
            ObjectInputStream in = new ObjectInputStream(entrada);
            //carro = (Carro) in.readObject();
            MAPA = (Mapa) in.readObject();
            in.close();
            entrada.close();
            //NAO ACHOU ARQUIVO MAPA.SER
        }catch(ClassNotFoundException c) {
            System.out.println("mapa.ser não encontrado");
            return;
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        }

        try {
            //CARREGA CARRO DO ARQUIVO HEROI.SER
            FileInputStream entrada = new FileInputStream("HEROI.ser");
            ObjectInputStream in = new ObjectInputStream(entrada);
            HEROI = (Heroi) in.readObject();
            in.close();
            entrada.close();
            //NAO ACHOU ARQUIVO HEROI.SER
        }catch(ClassNotFoundException c) {
                System.out.println("heroi.ser não encontrado");
                return;
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        }
        
        try {
            //CARREGA CARRO DO ARQUIVO SALA.SER
            FileInputStream entrada = new FileInputStream("sala.ser");
            ObjectInputStream in = new ObjectInputStream(entrada);
            SALA = (Sala) in.readObject();
            in.close();
            entrada.close();
            //NAO ACHOU ARQUIVO SALA.SER
        }catch(ClassNotFoundException c) {
                System.out.println("sala.ser não encontrado");
                return;
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        } 
    }
    
}