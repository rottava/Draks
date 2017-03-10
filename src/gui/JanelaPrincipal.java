package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author wagner
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
    private JButton NovoJogo;
    private JButton CarregarJogo;
    
    public JanelaPrincipal() {
        initGUI();
    }
    
    public void initGUI() {
        //Características da Janela
        setTitle("Castelo do Draks");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        
        // Construcao dos paineis principais Esquerda/Direita #############################
        JPanel painelEsq = new JPanel();
        JPanel painelDir = new JPanel();
        JPanel painelCentro = new JPanel ();
        add(painelEsq);
        add(painelCentro);
        add(painelDir);
        painelEsq.setBackground(new Color(255, 0, 0));
        painelCentro.setBackground(Color.WHITE);
        painelDir.setBackground(new Color(0, 0, 255));
        painelEsq.setVisible(true);
        painelCentro.setVisible(true);
        painelDir.setVisible(true);
        
        // Construcao do painel central #################################################
        painelCentro.setLayout(new GridLayout(4,0));
        // Painel com os botoes -------------------------------------------------------------
        JPanel painelDosBotoes = new JPanel();
	painelDosBotoes.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
	painelDosBotoes.setLayout(new GridLayout());
        JPanel comandos = new JPanel();
	comandos.setLayout(new GridLayout(2,0));
	comandos.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
	NovoJogo = new JButton("Novo Jogo");
	//NovoJogo.addActionListener(this);
	comandos.add(NovoJogo);
	CarregarJogo = new JButton("Carregar Jogo");
	//CarregarJogo.addActionListener(this);
	comandos.add(CarregarJogo);
	painelDosBotoes.add(comandos);
        
        painelCentro.add(painelDosBotoes).setLocation(0, 1000);
        
    }    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JanelaPrincipal jp = new JanelaPrincipal();
                jp.setVisible(true);
            }
        });
    }
    
}
