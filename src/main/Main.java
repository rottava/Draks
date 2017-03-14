/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Junior
 */
public class Main {
    public static Random aleatorio;
    public static final String ARQUIVOITENS = "/config/itens.txt";              //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final String ARQUIVONOMES = "/config/nomes.txt";              //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final String ARQUIVOHABILIDADES = "/config/habilidades.txt";  //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final String ARQUIVOINIMIGOS = "/config/inimigos.txt";        //ARQUIVO: NOME/FORCA/INTELIGENCIA/VELOCIDADE/RESISTENCIA/ID ARMA/ID ARMADURA/ID HABILIDADES1/ID HABILIDADE2...
    public static final String ARQUIVOSALAS = "/config/salas.txt";              //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final byte TAMANHOITENS = getTamanhoArquivo(ARQUIVOITENS);    //TAMANHO DO ARQUIVO DE ITENS (EM LINHAS)
    public static final byte TAMANHONOMES = getTamanhoArquivo(ARQUIVONOMES);    //TAMANHO DO ARQUIVO DE NOMES (EM LINHAS)
    public static final byte TAMANHOHABILIDADE = getTamanhoArquivo(ARQUIVOHABILIDADES);//TAMANHO DO ARQUIVO DE HABILIDADES (EM LINHAS)
    public static final byte TAMANHOINIMIGOS = getTamanhoArquivo(ARQUIVOINIMIGOS);//TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS)
    public static final byte TAMANHOSALAS = getTamanhoArquivo(ARQUIVOSALAS);    //TAMANHO DO ARQUIVO DE INIMIGOS (EM LINHAS
    public static final byte TAMANHOMAXIMO = 127;                                  //TAMANHO MAXIMO DE ARQUIVOS
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        aleatorio = new Random();
        
        
       
    }
    
    private static byte getTamanhoArquivo(String arquivo) {
        byte contador = 0;
        try{
            Scanner scanner = new Scanner(new File(arquivo));
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
    
}
