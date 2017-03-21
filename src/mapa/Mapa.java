/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static main.Main.SALAS;
import static main.Main.TAMANHOSALAS;
import static main.Main.CAMINHOSALAS;

/**
 * Mapa
 * @author Junior
 */
public class Mapa {
    private final List<Sala> salas;                                             //LISTA COM TODAS AS SALAS
    
    //CONSTRUTOR
    /**
     * Construtor
     */
    public Mapa(){
        salas = new ArrayList<>();                                              //LISTA DE SALAS
        //lerArquivo();                                                           //LE DO ARQUIVO AS SALAS
        ler();
    }
    
    /**
     * Lê o arquivo e adiciona na lista de salas
     */
    private void ler () {
        byte id = 1;
        try {
            FileReader arq = new FileReader (CAMINHOSALAS);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                String[] parametros;
                parametros = linha.split("/");
                //NOME/SALANORTE/CHAVE/ESTADONORTE/VALORITEM1/VALORITEM2/VALORITEM3/VALORINIMIGO1/VALORINIMIGO2/VALORINIMIGO3
                String nome = parametros[0];                                    //NOME                                                                                     
                Porta norte = new Porta((byte) Integer.parseInt(parametros[1]), //PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[2]), //ID CHAVE
                                        (byte) Integer.parseInt(parametros[3]), //TIPO ITEM
                                        (byte) Integer.parseInt(parametros[4]), //ITEM
                                        Integer.parseInt(parametros[5]),        //QUANTIDADE
                                        (byte) Integer.parseInt(parametros[6])); //INIMIGO                                                                        String nome = parametros[9];                                        //NOME 

                Porta sul = new Porta((byte) Integer.parseInt(parametros[7]),   //PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[8]), //ID CHAVE
                                        (byte) Integer.parseInt(parametros[9]), //TIPO ITEM
                                        (byte) Integer.parseInt(parametros[10]),//ITEM
                                        Integer.parseInt(parametros[11]),       //QUANTIDADE
                                        (byte) Integer.parseInt(parametros[12]));//INIMIGO

                Porta leste = new Porta((byte) Integer.parseInt(parametros[13]),//PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[14]),//ID CHAVE
                                        (byte) Integer.parseInt(parametros[15]),//TIPO ITEM
                                        (byte) Integer.parseInt(parametros[16]),//ITEM
                                        Integer.parseInt(parametros[17]),        //QUANTIDADE
                                        (byte) Integer.parseInt(parametros[18]));//INIMIGO

                Porta oeste = new Porta((byte) Integer.parseInt(parametros[19]),//PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[20]),//ID CHAVE
                                        (byte) Integer.parseInt(parametros[21]), //TIPO ITEM
                                        (byte) Integer.parseInt(parametros[22]),//ITEM
                                        Integer.parseInt(parametros[23]),        //QUANTIDADE 
                        /*Simples, você está tentando acessar uma posição do array que não existe.

Ou seja, você ultrapassou o maior índice que existe nele.
*/
                                        (byte) Integer.parseInt(parametros[24]));//INIMIGO

                Sala sala = new Sala(id, nome, norte, sul, leste, oeste);       //CRIA SALA
                salas.add(sala);                                                //ADICIONA A LISTA DE SALAS
                id++;
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOSALAS);
            e.getMessage();
        }
    }
    
    //RETORNA LISTA DE SALAS
    /**
     * Pega as salas do Mapa
     * @return lista com as salas
     */
    public List<Sala> getSalas(){
        return salas;
    }
 
}
