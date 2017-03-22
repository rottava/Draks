/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.JanelaInicial.salaGUI;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static main.Main.HEROI;
import static main.Main.SALA;

/**
 *
 * @author Wagner
 */
public class PontosGUI extends JFrame implements ActionListener{
 
    private JLabel jLabelForca;
    private JLabel jLabelPontos;
    private JLabel jLabelAgilidade;
    private JLabel jLabelInteligencia;
    private JLabel jLabelResistencia;
    private JLabel jLabelMoedas;
    private JLabel jLabelPeso;
    private JLabel jLabelArma;
    private JLabel jLabelArmadura;
    private Button btnIncrementarForca;
    private Button btnDecrementarForca;
    private Button btnIncrementarAgilidade;
    private Button btnDecrementarAgilidade;
    private Button btnIncrementarInteligencia;
    private Button btnDecrementarInteligencia;
    private Button btnIncrementarResistencia;
    private Button btnDecrementarResistencia;
    private Button btnOK;
    
    public PontosGUI () {
        initGUI();
    }
    
    private void initGUI() {
        //Características da Janela
        setTitle("Pontos");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        
        jLabelPontos = new JLabel ("Pontos: " + HEROI.getPontos());
        jLabelPontos.setBounds(100, 5, 100, 30);
        add(jLabelPontos);
        
        jLabelForca = new JLabel ("Força: " + HEROI.getForca());
        jLabelForca.setBounds(5, 55, 100, 30);
        add(jLabelForca);
        
        btnIncrementarForca = new Button ("+");
        btnIncrementarForca.setBounds(105, 55, 30, 30);
        add(btnIncrementarForca);
        btnIncrementarForca.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    incrementarForca();
                }       
            }
        });
        
        btnDecrementarForca = new Button ("-");
        btnDecrementarForca.setBounds(145, 55, 30, 30);
        add(btnDecrementarForca);
        btnDecrementarForca.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    decrementarForca();
                }       
            }
        });
        
        jLabelAgilidade = new JLabel ("Agilidade: " + HEROI.getAgilidade());
        jLabelAgilidade.setBounds(5, 95, 100, 30);
        add(jLabelAgilidade);
        
        btnIncrementarAgilidade = new Button ("+");
        btnIncrementarAgilidade.setBounds(105, 95, 30, 30);
        add(btnIncrementarAgilidade);
        btnIncrementarAgilidade.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    incrementarAgilidade();
                }       
            }
        });
        
        btnDecrementarAgilidade = new Button ("-");
        btnDecrementarAgilidade.setBounds(145, 95, 30, 30);
        add(btnDecrementarAgilidade);
        btnDecrementarAgilidade.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    decrementarAgilidade();
                }       
            }
        });
        
        jLabelInteligencia = new JLabel ("Inteligencia: " + HEROI.getInteligencia());
        jLabelInteligencia.setBounds(5, 135, 100, 30);
        add(jLabelInteligencia);
        
        btnIncrementarInteligencia = new Button ("+");
        btnIncrementarInteligencia.setBounds(105, 135, 30, 30);
        add(btnIncrementarInteligencia);
        btnIncrementarInteligencia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    incrementarInteligencia();
                }       
            }
        });
        
        btnDecrementarInteligencia = new Button ("-");
        btnDecrementarInteligencia.setBounds(145, 135, 30, 30);
        add(btnDecrementarInteligencia);
        btnDecrementarInteligencia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    decrementarInteligencia();
                }       
            }
        });
        
        jLabelResistencia = new JLabel ("Resistencia: " + HEROI.getResistencia());
        jLabelResistencia.setBounds(5, 175, 100, 30);
        add(jLabelResistencia);
        
        btnIncrementarResistencia = new Button ("+");
        btnIncrementarResistencia.setBounds(105, 175, 30, 30);
        add(btnIncrementarResistencia);
        btnIncrementarResistencia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    incrementarResistencia();
                }       
            }
        });
        
        btnDecrementarResistencia = new Button ("-");
        btnDecrementarResistencia.setBounds(145, 175, 30, 30);
        add(btnDecrementarResistencia);
        btnDecrementarResistencia.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    decrementarResistencia();
                }       
            }
        });
        
        jLabelMoedas = new JLabel ("Moedas: "+HEROI.getMoedas());
        jLabelMoedas.setBounds(5, 200, 100, 50);
        add(jLabelMoedas);
        
        jLabelPeso = new JLabel ("Peso: "+HEROI.getPeso());
        jLabelPeso.setBounds(5, 230, 100, 50);
        add(jLabelPeso);
        
        jLabelArma = new JLabel ("Arma: ");
        jLabelArma.setBounds (5, 260, 150, 50);
        if (HEROI.getArma() != null) {
            jLabelArma.setText("Arma: " + HEROI.getArma().getNome());
        }
        else {
            jLabelArma.setText("Arma: Não equipado");
        }
        add(jLabelArma);
        
        jLabelArmadura = new JLabel ("Armadura: ");
        jLabelArmadura.setBounds (5, 290, 150, 50);
        if (HEROI.getArmadura() != null) {
            jLabelArmadura.setText ("Armadura: " + HEROI.getArmadura().getNome());
        }
        else {
            jLabelArmadura.setText("Armadura: Não equipado");
        }
        add(jLabelArmadura);
        
        btnOK = new Button ("OK");
        btnOK.setBounds (50, 320, 50, 50);
        add(btnOK);
        btnOK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    recarregar();
                   
                }       
            }
        });
    }
    
    private void incrementarResistencia() {
        if (HEROI.getPontos() > 0) {
            HEROI.setResistencia((byte) (HEROI.getResistencia()+1));
            HEROI.setPontos((byte) (HEROI.getPontos()-1));
            jLabelResistencia.setText("Resistencia: "+HEROI.getResistencia());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void decrementarResistencia() {
        if(HEROI.getResistencia() > 1) {
            HEROI.setResistencia((byte) (HEROI.getResistencia()-1));
            HEROI.setPontos((byte) (HEROI.getPontos()+1));
            jLabelResistencia.setText("Resistencia: "+HEROI.getResistencia());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void incrementarForca() {
        if (HEROI.getPontos() > 0) {
            HEROI.setForca((byte) (HEROI.getForca()+1));
            HEROI.setPontos((byte) (HEROI.getPontos()-1));
            jLabelForca.setText("Força: "+HEROI.getForca());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void decrementarForca() {
        if (HEROI.getForca() > 1) {
            HEROI.setForca((byte) (HEROI.getForca()-1));
            HEROI.setPontos((byte) (HEROI.getPontos()+1));
            jLabelForca.setText("Força: "+HEROI.getForca());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void incrementarAgilidade() {
        if (HEROI.getPontos() > 0) {
            HEROI.setAgilidade((byte) (HEROI.getAgilidade()+1));
            HEROI.setPontos((byte) (HEROI.getPontos()-1));
            jLabelAgilidade.setText("Agilidade: "+HEROI.getAgilidade());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void decrementarAgilidade() {
        if (HEROI.getAgilidade() > 1) {
            HEROI.setAgilidade((byte) (HEROI.getAgilidade()-1));
            HEROI.setPontos((byte) (HEROI.getPontos()+1));
            jLabelAgilidade.setText("Agilidade: "+HEROI.getAgilidade());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void incrementarInteligencia() {
        if (HEROI.getPontos() > 0) {
            HEROI.setInteligencia((byte) (HEROI.getInteligencia()+1));
            HEROI.setPontos((byte) (HEROI.getPontos()-1));
            jLabelInteligencia.setText("Inteligencia: "+HEROI.getInteligencia());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void decrementarInteligencia() {
        if (HEROI.getInteligencia() > 1) {
            HEROI.setInteligencia((byte) (HEROI.getInteligencia()-1));
            HEROI.setPontos((byte) (HEROI.getPontos()+1));
            jLabelInteligencia.setText("Inteligencia: "+HEROI.getInteligencia());
            jLabelPontos.setText("Pontos: "+HEROI.getPontos());
            HEROI.geraVidaMax();
            HEROI.geraEnergiaMax();
        }
    }
    
    private void recarregar(){
        JanelaInicial ji = new JanelaInicial();
        salaGUI.dispose();
        ji.irSala (SALA.getId());
        this.dispose();
    }
    
  
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
