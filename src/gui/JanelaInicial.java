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

/**
 * Janela Inicial, dando opção de continuar ou iniciar um novo jogo
 * @author wagner
 */
public class JanelaInicial extends JFrame implements ActionListener {
    private JButton btnNovoJogo;
    private JButton btnCarregarJogo;
    private SalaGUI sala1;
    private SalaGUI sala2;
    private SalaGUI sala3;
    private SalaGUI sala4;
    private SalaGUI sala5;
    private SalaGUI sala6;
    private SalaGUI sala7;
    private SalaGUI sala8;
    private SalaGUI sala9;
    private SalaGUI sala10;
    private SalaGUI sala11;
    private SalaGUI sala12;
    private SalaGUI sala13;
    private SalaGUI sala14;
    private SalaGUI sala15;
    private SalaGUI sala16;
    private SalaGUI sala17;
    private SalaGUI sala18;
    private SalaGUI sala19;
    private SalaGUI sala20;
    
    
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
            //JOptionPane.showMessageDialog(null, "Iniciando novo jogo!");
            carregarSalas();
            if (sala1 != null) {
                sala1.initGUI(1);
                this.dispose();
            }
            else {
                carregarSalas();
            }
            
            
	} else if (ae.getSource() == btnCarregarJogo) {
            System.out.println ("CARREGAR JOGO");
            JOptionPane.showMessageDialog(null, "Carregar jogo!");
	}
    }
    
    /**
     * Faz a movimentação entre as salas
     * @param atual = sala atual
     * @param destino = sala para qual o usuário deseja se mover
     * @return = true se a movimentação foi bem sucedida ou false caso não foi possível
     */
    public boolean irSala (int atual, int destino) {
        carregarSalas();
        this.dispose();
        if (atual == 1) {
            if (destino == 2) {
                sala2.initGUI(2);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala1.pegarSaidas());
        }
        if (atual == 2) {
            if (destino == 3) {
                sala3.initGUI(3);
                return true;
            }
            if (destino == 1) {
                sala1.initGUI(1);
                return true;
            }
            if (destino == 4) {
                sala4.initGUI(4);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala2.pegarSaidas());
        }
        if (atual == 3) {
            if (destino == 2) {
                sala2.initGUI(2);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala3.pegarSaidas());
        }
        if (atual == 4) {
            if (destino == 2) {
                sala2.initGUI(2);
                return true;
            }
            if (destino == 6) {
                sala6.initGUI(6);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala4.pegarSaidas());
        }
        if (atual == 5) {
            if (destino == 8) {
                sala8.initGUI(8);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala5.pegarSaidas());
        }
        if (atual == 6) {
            if (destino == 7) {
                sala7.initGUI(7);
                return true;
            }
            if (destino == 4) {
                sala4.initGUI(4);
                return true;
            }
            if (destino == 8) {
                sala8.initGUI(8);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala6.pegarSaidas());
        }
        if (atual == 7) {
            if (destino == 6) {
                sala6.initGUI(6);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala7.pegarSaidas());
        }
        if (atual == 8) {
            if (destino == 6) {
                sala6.initGUI(6);
                return true;
            }
            if (destino == 5) {
                sala5.initGUI(5);
                return true;
            }
            if (destino == 9) {
                sala9.initGUI(9);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala8.pegarSaidas());
        }
        if (atual == 9) {
            if (destino == 8) {
                sala8.initGUI(8);
                return true;
            }
            if (destino == 10) {
                sala10.initGUI(10);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala9.pegarSaidas());
        }
        if (atual == 10) {
            if (destino == 9) {
                sala9.initGUI(9);
                return true;
            }
            if (destino == 11) {
                sala11.initGUI(11);
                return true;
            }
            if (destino == 12) {
                sala12.initGUI(12);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala10.pegarSaidas());
        }
        if (atual == 11) {
            if (destino == 10) {
                sala10.initGUI(10);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala11.pegarSaidas());
        }
        if (atual == 12) {
            if (destino == 10) {
                sala10.initGUI(10);
                return true;
            }
            if (destino == 13) {
                sala13.initGUI(13);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala12.pegarSaidas());
        }
        if (atual == 13) {
            if (destino == 12) {
                sala12.initGUI(12);
                return true;
            }
            if (destino == 14) {
                sala14.initGUI(14);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala13.pegarSaidas());
        }
        if (atual == 14) {
            if (destino == 13) {
                sala13.initGUI(13);
                return true;
            }
            if (destino == 15) {
                sala15.initGUI(15);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala14.pegarSaidas());
        }
        if (atual == 15) {
            if (destino == 14) {
                sala14.initGUI(14);
                return true;
            }
            if (destino == 16) {
                sala16.initGUI(16);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala15.pegarSaidas());
        }
        if (atual == 16) {
            if (destino == 17) {
                sala17.initGUI(17);
                return true;
            }
            if (destino == 15) {
                sala15.initGUI(15);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala16.pegarSaidas());
        }
        if (atual == 17) {
            if (destino == 16) {
                sala16.initGUI(16);
                return true;
            }
            if (destino == 18) {
                sala18.initGUI(18);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala17.pegarSaidas());
        }
        if (atual == 18) {
            if (destino == 17) {
                sala17.initGUI(17);
                return true;
            }
            if (destino == 19) {
                sala19.initGUI(19);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala18.pegarSaidas());
        }
        if (atual == 19) {
            if (destino == 18) {
                sala18.initGUI(18);
                return true;
            }
            if (destino == 20) {
                sala20.initGUI(20);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala19.pegarSaidas());
        }
        if (atual == 20) {
            if (destino == 19) {
                sala19.initGUI(19);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Saídas disponíveis:\n" + sala20.pegarSaidas());
        }
        return false;
    }
    
    /**
     * Carrega as salas fazendo suas devidas configurações
     */
    private void carregarSalas () {
        sala1 = new SalaGUI("Pátio Entrada");
        sala1.setarSaida("norte", 2);
        sala1.setarInimigos(2);
        sala1.setarItens(3);
        sala2 = new SalaGUI("Saguão Principal");
        sala2.setarSaida("oeste", 3);
        sala2.setarSaida("sul", 1);
        sala2.setarSaida("leste", 4);
        sala2.setarInimigos(3);
        sala3 = new SalaGUI("Cozinha");
        sala3.setarSaida("leste", 2);
        sala4 = new SalaGUI("Escada");
        sala4.setarSaida("oeste", 2);
        sala4.setarSaida("norte", 6);
        sala5 = new SalaGUI("Depósito");
        sala5.setarSaida("norte", 8);
        sala6 = new SalaGUI("Sala");
        sala6.setarSaida("oeste", 7);
        sala6.setarSaida("sul", 4);
        sala6.setarSaida("leste", 8);
        sala7 = new SalaGUI("Quarto de Visitas");
        sala7.setarSaida("leste", 6);
        sala8 = new SalaGUI("Biblioteca");
        sala8.setarSaida("oeste", 6);
        sala8.setarSaida("sul", 5);
        sala8.setarSaida("leste", 9);
        sala9 = new SalaGUI("Sala Secreta");
        sala9.setarChefe(1);
        sala9.setarSaida("oeste", 8);
        sala9.setarSaida("norte", 10);
        sala10 = new SalaGUI("Sala Grande");
        sala10.setarSaida("sul", 9);
        sala10.setarSaida("norte", 12);
        sala10.setarSaida("leste", 11);
        sala11 = new SalaGUI("Depósito de Armas");
        sala11.setarSaida("oeste", 10);
        sala12 = new SalaGUI("Acesso ao Corredor");
        sala12.setarSaida("sul", 10);
        sala12.setarSaida("oeste", 13);
        sala13 = new SalaGUI("Corredor");
        sala13.setarSaida("leste", 12);
        sala13.setarSaida("oeste", 14);
        sala14 = new SalaGUI("Corredor");
        sala14.setarSaida("leste", 13);
        sala14.setarSaida("oeste", 15);
        sala15 = new SalaGUI("Corredor");
        sala15.setarSaida("leste", 14);
        sala15.setarSaida("oeste", 16);
        sala16 = new SalaGUI("Corredor");
        sala16.setarSaida("leste", 15);
        sala16.setarSaida("norte", 17);
        sala17 = new SalaGUI("Corredor");
        sala17.setarSaida("sul", 16);
        sala17.setarSaida("norte", 18);
        sala18 = new SalaGUI("Corredor");
        sala18.setarSaida("sul", 17);
        sala18.setarSaida("leste", 19);
        sala19 = new SalaGUI("Acesso ao Quarto do Drácula");
        sala19.setarSaida("oeste", 18);
        sala19.setarSaida("sul", 20);
        sala20 = new SalaGUI("Quarto do Drácula");
        sala20.setarSaida("norte", 19);
    }
    
    /**
     * Início, chama a tela principal
     * @param args 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JanelaInicial jp = new JanelaInicial();
                jp.setVisible(true);
            }
        });
    }
    
}
