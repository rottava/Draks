/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static main.Main.ARQUIVOSALAS;

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
        Scanner scanner = new Scanner(ARQUIVOSALAS);
        byte id = 1;                                                            //LINHA INICIAL = 1
        while(scanner.hasNext()){
            String[] parametros;
            parametros = scanner.nextLine().split("/");                         //DIVIDE O ARQUIVO EM BARRAS
            
            
            
            
            String nome = parametros[0];                                        //NOME                  
                                                                                //NOME/SALANORTE/ESTADONORTE/VALORNORTE1/VALORNORTE2/VALORNORTE3/
            byte[] norte = {(byte) Integer.parseInt(parametros[1]), (byte) Integer.parseInt(parametros[2]), (byte) Integer.parseInt(parametros[3]) ,(byte) Integer.parseInt(parametros[4])};
                                                                                //NOME/SALASUL/ESTADOSUL/VALORSUL1/VALORSUL2/VALORSUL3/
            byte[] sul = {(byte) Integer.parseInt(parametros[5]), (byte) Integer.parseInt(parametros[6]), (byte) Integer.parseInt(parametros[7]) ,(byte) Integer.parseInt(parametros[8])};
                                                                                //NOME/SALALESTE/ESTADOLESTE/VALORLESTE1/VALORLESTE2/VALORLESTE3/
            byte[] leste = {(byte) Integer.parseInt(parametros[9]), (byte) Integer.parseInt(parametros[10]), (byte) Integer.parseInt(parametros[11]) ,(byte) Integer.parseInt(parametros[12])};
                                                                                //NOME/SALAOESTE/ESTADOOESTE/VALOROESTE1/VALOROESTE2/VALOROESTE3
            byte[] oeste = {(byte) Integer.parseInt(parametros[13]), (byte) Integer.parseInt(parametros[14]), (byte) Integer.parseInt(parametros[15]) ,(byte) Integer.parseInt(parametros[16])};
            Sala sala = new Sala(id, nome, norte, sul, leste, oeste);           //CRIA SALA
            salas.add(sala);                                                    //ADICIONA A LISTA DE SALAS
            id++;
        }
    }
 
}
