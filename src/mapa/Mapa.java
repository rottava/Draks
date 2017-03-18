/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static main.Main.SALAS;
import static main.Main.TAMANHOSALAS;

/**
 *
 * @author Junior
 */
public class Mapa {
    private final List<Sala> salas;                                             //LISTA COM TODAS AS SALAS
    
    //CONSTRUTOR
    public Mapa(){
        salas = new ArrayList<>();                                              //LISTA DE SALAS
        lerArquivo();                                                           //LE DO ARQUIVO AS SALAS
    }
    
    //LE ARQUIVO E ADICIONA A LISTA DE SALAS
    private void lerArquivo(){
        byte id = 1;                                                            //LINHA INICIAL = 1
        try {
            Scanner scanner = new Scanner(SALAS);
            while(id <= TAMANHOSALAS){
                String[] parametros;
                parametros = scanner.nextLine().split("/");                     //DIVIDE O ARQUIVO EM BARRAS
                //NOME/SALANORTE/CHAVE/ESTADONORTE/VALORITEM1/VALORITEM2/VALORITEM3/VALORINIMIGO1/VALORINIMIGO2/VALORINIMIGO3
                String nome = parametros[0];                                    //NOME                                                                                     
                Porta norte = new Porta((byte) Integer.parseInt(parametros[1]), //PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[2]), //ID CHAVE
                                        (byte) Integer.parseInt(parametros[3]), //ITEM
                                        (byte) Integer.parseInt(parametros[4])); //INIMIGO                                                                        String nome = parametros[9];                                        //NOME 

                Porta sul = new Porta((byte) Integer.parseInt(parametros[5]),   //PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[6]), //ID CHAVE
                                        (byte) Integer.parseInt(parametros[7]), //ITEM
                                        (byte) Integer.parseInt(parametros[8])); //INIMIGO

                Porta leste = new Porta((byte) Integer.parseInt(parametros[9]), //PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[10]),//ID CHAVE
                                        (byte) Integer.parseInt(parametros[11]),//ITEM
                                        (byte) Integer.parseInt(parametros[12]));//INIMIGO

                Porta oeste = new Porta((byte) Integer.parseInt(parametros[13]),//PROXIMA SALA
                                        (byte) Integer.parseInt(parametros[14]),//ID CHAVE
                                        (byte) Integer.parseInt(parametros[15]),//ITEM
                                        (byte) Integer.parseInt(parametros[16]));//INIMIGO

                Sala sala = new Sala(id, nome, norte, sul, leste, oeste);       //CRIA SALA
                salas.add(sala);                                                //ADICIONA A LISTA DE SALAS
                id++;
            }
        }
        catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("Arquivo itens.txt não foi encontrado.");
        }
    }
    
    //RETORNA LISTA DE SALAS
    public List<Sala> getSalas(){
        return salas;
    }
 
}
