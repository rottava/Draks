package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static main.Main.MAPA;
import static main.Main.SALA;

/**
 * Janela Inicial, dando opção de continuar ou iniciar um novo jogo
 * @author wagner
 */
public class JanelaInicial extends JFrame implements ActionListener {
    private JButton btnNovoJogo;
    private JButton btnCarregarJogo;
    private SalaGUI sala;
    
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
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNovoJogo) {
            System.out.println ("NOVO JOGO");
            this.dispose();
            sala = new SalaGUI();
            sala.initGUI(1);
	} else if (ae.getSource() == btnCarregarJogo) {
            System.out.println ("CARREGAR JOGO");
            JOptionPane.showMessageDialog(null, "Carregar jogo!");
	}
    }
    
    /**
     * Faz a movimentação entre as salas
     * @param destino = sala para qual o usuário deseja se mover
     * @return = true se a movimentação foi bem sucedida ou false caso não foi possível
     */
    public boolean irSala (int destino) {
        SALA = MAPA.getSalas().get(destino-1);
        sala = new SalaGUI();
        sala.initGUI(destino);
        return true;
    }
    
}