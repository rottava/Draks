/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.JanelaInicial;
import item.Arma;
import item.Armadura;
import item.Chave;
import item.Item;
import item.ItemCura;
import item.ItemEnergia;
import item.ItemHabilidadeCura;
import item.ItemHabilidadeDano;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import mapa.Mapa;
import mapa.Sala;
import personagem.Heroi;

/**
 *
 * @author Junior
 */
public class Main {
    public static Heroi HEROI;//  HEROI 
    public static Mapa MAPA;                                 //MAPA DO JOGO
    public static Sala SALA;                                                   //SALA ATUAL
    public static Random aleatorio;                                             //NUMERO ALEATORIO
    public static File ITENSCURA;     //ARQUIVO: NOME/EFEITO/PESO
    public static File ITENSENERGIA;//ARQUIVO: NOME/EFEITO/PESO
    public static File CHAVES;           //ARQUIVO: NOME/EFEITO/PESO
    public static File ARMAS;            //ARQUIVO: NOME/EFEITO/PESO
    public static File ARMADURAS;    //ARQUIVO: NOME/EFEITO/PESO
    public static File NOMES;             //ARQUIVO: NOME
    public static File HABILIDADESCURA; //ARQUIVO: NOME/EFEITO/CUSTO
    public static File HABILIDADESDANO; //ARQUIVO: NOME/EFEITO/CUSTO
    public static File INIMIGOS;       //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static File SALAS;             //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static File TOTEM;             //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static byte TAMANHOITENSCURA;   //TAMANHO DO ARQUIVO DE ITENS DE CURA (EM LINHAS)
    public static byte TAMANHOITENSENERGIA;//TAMANHO DO ARQUIVO DE ITENS DE ENERGIA (EM LINHAS)
    public static byte TAMANHOCHAVES;        //TAMANHO DO ARQUIVO DE CHAVES (EM LINHAS)
    public static byte TAMANHOARMAS;           //TAMANHO DO ARQUIVO DE ARMAS (EM LINHAS)
    public static byte TAMANHOARMADURAS;   //TAMANHO DO ARQUIVO DE ARMADURAS (EM LINHAS)
    public static byte TAMANHONOMES;          //TAMANHO DO ARQUIVO DE NOMES (EM LINHAS)
    public static byte TAMANHOHABILIDADESCURA;//TAMANHO DO ARQUIVO DE HABILIDADES DE CURA(EM LINHAS)
    public static byte TAMANHOHABILIDADESDANO;//TAMANHO DO ARQUIVO DE HABILIDADES DE DANO(EM LINHAS)
    public static byte TAMANHOINIMIGOS;     //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS)
    public static byte TAMANHOSALAS;           //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS
    public static byte TAMANHOTOTEM;           //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS
    public static byte TAMANHOMAXIMO;                               //TAMANHO MAXIMO DE ARQUIVOS
                                 //HEROI
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        aleatorio = new Random();
        HEROI = new Heroi("Cecil",(byte) 1,(byte) 1,(byte) 1 ,(byte)1);
        MAPA = new Mapa();
        SALA = MAPA.getSalas().get(0);
        ITENSCURA = new File("/config/itenscura.txt");
        ITENSENERGIA = new File("/config/itensenergia.txt");
        CHAVES = new File("/config/chaves.txt");
        ARMAS = new File("/config/armas.txt");
        ARMADURAS = new File("/config/armaduras.txt");
        NOMES = new File("/config/nomes.txt"); 
        HABILIDADESCURA = new File("/config/habilidadescura.txt");
        HABILIDADESDANO = new File("/config/habilidadesdano.txt");
        INIMIGOS = new File("/config/inimigos.txt");
        SALAS = new File("/config/salas.txt");
        TOTEM = new File("/config/totem.txt");
        TAMANHOITENSCURA = getTamanhoArquivo(ITENSCURA);
        TAMANHOITENSENERGIA = getTamanhoArquivo(ITENSENERGIA);
        TAMANHOCHAVES = getTamanhoArquivo(CHAVES);
        TAMANHOARMAS = getTamanhoArquivo(ARMAS);
        TAMANHOARMADURAS = getTamanhoArquivo(ARMADURAS);
        TAMANHONOMES = getTamanhoArquivo(NOMES);
        TAMANHOHABILIDADESCURA = getTamanhoArquivo(HABILIDADESCURA);
        TAMANHOHABILIDADESDANO = getTamanhoArquivo(HABILIDADESDANO);
        TAMANHOINIMIGOS = getTamanhoArquivo(INIMIGOS);
        TAMANHOSALAS = getTamanhoArquivo(SALAS); 
        TAMANHOTOTEM = getTamanhoArquivo(TOTEM);
        TAMANHOMAXIMO = 127;
        
        
        
        JanelaInicial janela = new JanelaInicial();
        Sistema();
    }
    
    private static byte getTamanhoArquivo(File arquivo) {
        byte contador = 0;
        try{
            Scanner scanner = new Scanner(arquivo);
            while(scanner.hasNext()){
                String linha = scanner.nextLine();
                if (contador == TAMANHOMAXIMO)
                    throw new UnsupportedOperationException("Arquivo em " + arquivo + " é muito grande!");
                contador++;
            }
        }
        catch(FileNotFoundException fe) {
            throw new UnsupportedOperationException("Arquivo em " + arquivo + " não foi encontrado.");
        }
        return contador;
    }

    private static void Sistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
