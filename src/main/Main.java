/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
    public static final Heroi HEROI = new Heroi("Cecil",(byte) 1,(byte) 1,(byte) 1 ,(byte)1);//  HEROI 
    public static final Mapa MAPA = new Mapa();                                 //MAPA DO JOGO
    public static Sala SALA;                                                    //SALA ATUAL
    public static Random aleatorio;                                             //NUMERO ALEATORIO
    public static final File ITENSCURA = new File("/config/itenscura.txt");     //ARQUIVO: NOME/EFEITO/PESO
    public static final File ITENSENERGIA = new File("/config/itensenergia.txt");//ARQUIVO: NOME/EFEITO/PESO
    public static final File CHAVES = new File("/config/chaves.txt");           //ARQUIVO: NOME/EFEITO/PESO
    public static final File ARMAS = new File("/config/armas.txt");             //ARQUIVO: NOME/EFEITO/PESO
    public static final File ARMADURAS = new File("/config/armaduras.txt");     //ARQUIVO: NOME/EFEITO/PESO
    public static final File NOMES = new File("/config/nomes.txt");             //ARQUIVO: NOME
    public static final File HABILIDADESCURA = new File("/config/habilidadescura.txt"); //ARQUIVO: NOME/EFEITO/CUSTO
    public static final File HABILIDADESDANO = new File("/config/habilidadesdano.txt"); //ARQUIVO: NOME/EFEITO/CUSTO
    public static final File INIMIGOS = new File("/config/inimigos.txt");       //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final File SALAS = new File("/config/salas.txt");             //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final File TOTEM = new File("/config/totem.txt");             //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final byte TAMANHOITENSCURA = getTamanhoArquivo(ITENSCURA);   //TAMANHO DO ARQUIVO DE ITENS DE CURA (EM LINHAS)
    public static final byte TAMANHOITENSENERGIA = getTamanhoArquivo(ITENSENERGIA);//TAMANHO DO ARQUIVO DE ITENS DE ENERGIA (EM LINHAS)
    public static final byte TAMANHOCHAVES = getTamanhoArquivo(CHAVES);         //TAMANHO DO ARQUIVO DE CHAVES (EM LINHAS)
    public static final byte TAMANHOARMAS = getTamanhoArquivo(ARMAS);           //TAMANHO DO ARQUIVO DE ARMAS (EM LINHAS)
    public static final byte TAMANHOARMADURAS = getTamanhoArquivo(ARMADURAS);   //TAMANHO DO ARQUIVO DE ARMADURAS (EM LINHAS)
    public static final byte TAMANHONOMES = getTamanhoArquivo(NOMES);           //TAMANHO DO ARQUIVO DE NOMES (EM LINHAS)
    public static final byte TAMANHOHABILIDADESCURA = getTamanhoArquivo(HABILIDADESCURA);//TAMANHO DO ARQUIVO DE HABILIDADES DE CURA(EM LINHAS)
    public static final byte TAMANHOHABILIDADESDANO = getTamanhoArquivo(HABILIDADESDANO);//TAMANHO DO ARQUIVO DE HABILIDADES DE DANO(EM LINHAS)
    public static final byte TAMANHOINIMIGOS = getTamanhoArquivo(INIMIGOS);     //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS)
    public static final byte TAMANHOSALAS = getTamanhoArquivo(SALAS);           //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS
    public static final byte TAMANHOTOTEM = getTamanhoArquivo(TOTEM);           //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS
    public static final byte TAMANHOMAXIMO = 127;                               //TAMANHO MAXIMO DE ARQUIVOS
                                 //HEROI
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        aleatorio = new Random();
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
    
    public Item ConversorDeItens(byte tipo, byte id, int quantidade){
        Item item;
        switch (tipo){
            case 1:
                item = new Armadura(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case 2:
                item = new Arma(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case 3:
                item = new Chave(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case 4:
                item = new ItemHabilidadeCura(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case -4:
                item = new ItemHabilidadeDano(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case 5:
                item = new ItemCura(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case 6:
                item = new ItemEnergia(id);
                item.setQuantidade((byte)(quantidade));
                break;
            case 7:
                HEROI.addMoedas(quantidade);
                item = null;
                break;
           default:
               item = null;
               break;
        }
        return item;
    }
    
}
