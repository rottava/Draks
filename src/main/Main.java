/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
import personagem.Heroi;
import personagem.Inimigo;

/**
 *
 * @author Junior
 */
public class Main {
    public static Random aleatorio;
    public static final String ARQUIVOITENS = "/config/itens.txt";              //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final String ARQUIVONOMES = "/config/nomes.txt";              //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final String ARQUIVOHABILIDADES = "/config/habilidades.txt";  //ARQUIVO: NOME/TIPO/EFEITO/PESO
    public static final byte TAMANHOITENS = getTamanhoArquivo(ARQUIVOITENS);    //TAMANHO DO ARQUIVO DE ITENS (EM LINHAS)
    public static final byte TAMANHONOMES = getTamanhoArquivo(ARQUIVONOMES);    //TAMANHO DO ARQUIVO DE NOMES (EM LINHAS)
    public static final byte TAMANHOHABILIDADE = getTamanhoArquivo(ARQUIVOHABILIDADES);//TAMANHO DO ARQUIVO DE HABILIDADES (EM LINHAS)
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
                if (contador == Byte.SIZE)
                    throw new UnsupportedOperationException("Arquivo %s muito grande!");
                contador++;
            }
        }
        catch(FileNotFoundException fe) {
            throw new UnsupportedOperationException("Arquivo não encontrado.");
        }
        return contador;
    }
    
}
