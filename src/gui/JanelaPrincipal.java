package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author wagner
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
    private JButton btnNovoJogo;
    private JButton btnCarregarJogo;
    
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
        setResizable(false);
        
        JLabel bg = new JLabel (new ImageIcon("resources/backgroundInicio.jpg"));
        add(bg);
        
        
        bg.setLayout(new GridLayout(4,3));
        bg.setVisible(true);
        JPanel painelBotoes = new JPanel();
	painelBotoes.setLayout(new GridLayout(2,0));
        btnNovoJogo = new JButton("Novo Jogo");
        btnCarregarJogo = new JButton("Carregar Jogo");
        btnNovoJogo.addActionListener(this);
        btnCarregarJogo.addActionListener(this);
        painelBotoes.add(btnNovoJogo);
        painelBotoes.add(btnCarregarJogo);
        
        add (bg);
        bg.add(new JLabel(""));
        JLabel titulo = new JLabel ("Castelo do Draks");
        titulo.setFont(new Font("Dialog", Font.BOLD, 40));
        titulo.setForeground(Color.white);
        bg.add(titulo);
        
        bg.add(new JLabel(""));
        bg.add(new JLabel(""));
        bg.add(new JLabel(""));
        bg.add(new JLabel(""));
        bg.add(new JLabel(""));
        bg.add(painelBotoes);
        bg.add(new JLabel(""));
        bg.add(new JLabel(""));
        bg.add(new JLabel(""));
        
    }    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNovoJogo) {
            System.out.println ("NOVO JOGO");
            JOptionPane.showMessageDialog(null, "Iniciar novo jogo!");
	} else if (ae.getSource() == btnCarregarJogo) {
            System.out.println ("CARREGAR JOGO");
            JOptionPane.showMessageDialog(null, "Carregar jogo!");
	}
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
