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
    private final byte id;              //LINHA DO ARQUIVO
    
    //PARAMETRO DE DEFINIÇÃO
    private String nome;                //NOME DE HABILIDADE
    private byte tipo;                  //TIPO DE HABILIDADE
    private byte efeito;                //EFEITO DE HABILIDADE
    private byte consumo;               //CONSUMO DE ENERGIA POR HABILIDADE
    
    //CONSTRUTOR POR ID
    public Habilidade(byte id){
        this.id = id;
        setParam();
    }
    
    //GETTERS AND SETTERS
    public byte getId(){
        return id;
    }
    public String getNome(){
        return nome;
    } 
    
    public byte getTipo(){
        return tipo;
    }
    
    public byte getEfeito(){
        return efeito;
    }
    
    public byte getConsumo(){
        return consumo;
    }
    
    private void setParam(){
        Scanner scanner;
        int n = 0;
        for(scanner = new Scanner(ARQUIVOHABILIDADES); (scanner.hasNext() && n < id); n++){
            String nextLine = scanner.nextLine();
        }
        if (scanner.hasNext()){
            String[] parametros;
            parametros = scanner.nextLine().split("/");
            nome = parametros[0];
            tipo = (byte) Integer.parseInt(parametros[1]);
            efeito = (byte) Integer.parseInt(parametros[2]);
            consumo = (byte) Integer.parseInt(parametros[3]);
        }
        else
            throw new UnsupportedOperationException("ID de habilidade não encontrado.");
    }
    
}
