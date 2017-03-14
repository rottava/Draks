/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.util.Scanner;
import static main.Main.ARQUIVOHABILIDADES;

/**
 *
 * @author Junior
 */
public class Habilidade {
    //PARAMETRO DE CONFIGURACAO
    private final byte id;                                                      //LINHA DO ARQUIVO
    
    //PARAMETRO DE DEFINIÇÃO
    private String nome;                                                        //NOME DE HABILIDADE
    private byte tipo;                                                          //TIPO DE HABILIDADE: 0 = CURA, 1 = DANO SIMPLES, 2 = DANO EM AREA, 3 = DANO POR TEMPO
    private byte efeito;                                                        //EFEITO DE HABILIDADE
    private byte consumo;                                                       //CONSUMO DE ENERGIA POR HABILIDADE
    
    //CONSTRUTOR POR ID
    public Habilidade(byte id){
        this.id = id;
        setParam();
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA ID
    public byte getId(){
        return id;
    }
    
    //RETORNA NOME
    public String getNome(){
        return nome;
    } 
    
    //RETORNA TIPO
    public byte getTipo(){
        return tipo;
    }
    
    //RETORNA EFEITO
    public byte getEfeito(){
        return efeito;
    }
    
    //RETORNA CONSUMO
    public byte getConsumo(){
        return consumo;
    }
    
    //CONFIGURA HABILIDADE ATRAVEZ DO ARQUIVO
    private void setParam(){
        Scanner scanner = new Scanner(ARQUIVOHABILIDADES);
        byte loop = 1;
        while((scanner.hasNext()) && (loop < id)){
            String nextLine = scanner.nextLine();
            loop++;
        }
        if (scanner.hasNext()){
            String[] parametros;
            parametros = scanner.nextLine().split("/");                         //NOME/TIPO/EFEITO/CONSUMO
            nome = parametros[0];                                               //NOME
            tipo = (byte) Integer.parseInt(parametros[1]);                      //TIPO
            efeito = (byte) Integer.parseInt(parametros[2]);                    //EFEITO
            consumo = (byte) Integer.parseInt(parametros[3]);                   //CONSUMO
        }
        else
            throw new UnsupportedOperationException("ID de habilidade não encontrado.");
    }
    
}
