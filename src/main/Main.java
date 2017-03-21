/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.JanelaInicial;
import item.Arma;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import mapa.Mapa;
import mapa.Sala;
import personagem.Heroi;

/**
 * Classe principal
 * @author Junior
 */
public class Main {
    public static byte TAMANHOITENSCURA;                                        //TAMANHO DO ARQUIVO DE ITENS DE CURA (EM LINHAS)
    public static byte TAMANHOITENSENERGIA;                                     //TAMANHO DO ARQUIVO DE ITENS DE ENERGIA (EM LINHAS)
    public static byte TAMANHOCHAVES;                                           //TAMANHO DO ARQUIVO DE CHAVES (EM LINHAS)
    public static byte TAMANHOARMAS;                                            //TAMANHO DO ARQUIVO DE ARMAS (EM LINHAS)
    public static byte TAMANHOARMADURAS;                                        //TAMANHO DO ARQUIVO DE ARMADURAS (EM LINHAS)
    public static byte TAMANHONOMES;                                            //TAMANHO DO ARQUIVO DE NOMES (EM LINHAS)
    public static byte TAMANHOHABILIDADESCURA;                                  //TAMANHO DO ARQUIVO DE HABILIDADES DE CURA(EM LINHAS)
    public static byte TAMANHOHABILIDADESDANO;                                  //TAMANHO DO ARQUIVO DE HABILIDADES DE DANO(EM LINHAS)
    public static byte TAMANHOINIMIGOS;                                         //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS)
    public static byte TAMANHOSALAS;                                            //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS)
    public static byte TAMANHOMAXIMO = 127;                                     //TAMANHO MAXIMO DE ARQUIVOS
    public static Random ALEATORIO;                                             //NUMERO ALEATORIO
    public static Heroi HEROI;                                                  //HEROI 
    public static Mapa MAPA;                                                    //MAPA DO JOGO
    public static Sala SALA;                                                    //SALA ATUAL
    public static String CAMINHOSALAS = "config/salas.txt";
    public static String CAMINHOITENSCURA = "config/itenscura.txt";
    public static String CAMINHOITENSENERGIA = "config/itenscura.txt";
    public static String CAMINHOCHAVES = "config/chaves.txt";
    public static String CAMINHOARMAS = "config/armas.txt";
    public static String CAMINHOARMADURAS = "config/armaduras.txt";
    public static String CAMINHONOMES = "config/nomes.txt";
    public static String CAMINHOHABILIDADESCURA = "config/habilidadescura.txt";
    public static String CAMINHOHABILIDADESDANO = "config/habilidadesdano.txt";
    public static String CAMINHOINIMIGOS = "config/inimigos.txt";
    public static boolean FLAG = false;
    

    
                                 //HEROI
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        setParam();
        JanelaInicial janela = new JanelaInicial();
        janela.setVisible(true);
    }
    
    /**
     * Configura os parâmetros
     */
    private static void setParam(){
    TAMANHOITENSCURA = tamanho("config/itenscura.txt");
    TAMANHOITENSENERGIA = tamanho("config/itensenergia.txt");
    TAMANHOCHAVES = tamanho("config/chaves.txt");
    TAMANHOARMAS = tamanho("config/armas.txt");
    TAMANHOARMADURAS = tamanho("config/armaduras.txt");
    TAMANHONOMES = tamanho("config/nomes.txt");
    TAMANHOHABILIDADESCURA = tamanho("config/habilidadescura.txt");
    TAMANHOHABILIDADESDANO = tamanho("config/habilidadesdano.txt");
    TAMANHOINIMIGOS = tamanho("config/inimigos.txt");
    TAMANHOSALAS = tamanho("config/salas.txt");
    ALEATORIO = new Random();
    HEROI = new Heroi("Cecil",(byte) 10,(byte) 10,(byte) 10 ,(byte)10);
    MAPA = new Mapa();
    SALA = MAPA.getSalas().get(0);
    HEROI.addMochila(new Arma((byte)1));
    }
    
    /**
     * Pega o tamanho do arquivo
     * @param caminho String com o caminho do arquivo para saber o tamanho
     * @return byte contendo o tamanho em linhas do arquivo
     */
    private static byte tamanho (String caminho) {
        byte tamanho = 0;
        try {
            FileReader arq = new FileReader (caminho);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                tamanho++;
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", caminho);
            e.getMessage();
        }
        return tamanho;
    }    
}
