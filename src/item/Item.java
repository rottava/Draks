/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.util.Scanner;
import static main.Main.ARQUIVOITENS;

/**
 *
 * @author Junior
 */
public class Item {
    //PARAMETRO DE CONFIGURACAO
    private final byte id;                                                      //ID DO ITEM = LINHA DO ARQUIVO
    
    //PARAMETRO DE DEFINIÇÃO
    private String nome;                                                        //NOME DO ITEM
    private byte tipo;                                                          //TIPO DO ITEM 0 = CONSUMIVEL, 1 = ARMA, 2 = ARMADURA
    private byte efeito;                                                        //QUANTIDADE EFEITO (VIDA, ENERGIA, ATAQUE, DEFESA)
    private byte pesoItem;                                                      //PESO POR ITEM
    private final byte quantidadeMax = 100;                                     //MAXIMO DE ITENS POR SLOT +1
    
    //PARAMETRO VARIAVEL
    private byte quantidade;                                                    //QUANTIDADE
    
    //CONSTRUTOR
    public Item(byte id){
        this.id = id;                                                           //LINHA DO ARQUIVO
        setParam();                                                             //CONFIGURA PARAMETROS
        quantidade = 1;                                                         //QUANTIDADE MINIMA PARA EXISTIR = 1
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA NOME
    public String getNome(){
        return nome;
    }
    
    //RETORNA ID
    public byte getId(){
        return id;
    }
    
    //RETORNA TIPO DE ITEM
    public byte getTipo(){
        return tipo;
    }
    
    //RETORNA QUANTIDADE TOTAL
    public byte getQuantidade(){
        return quantidade;
    }
    
    //SETA QUANTIDADE E RETORNA VERDADEIRO OU RETORNA FALSO
    public boolean setQuantidade(byte quantidade){
        if (quantidade < quantidadeMax){
            this.quantidade = quantidade;
            return true;
        }
        else
            return false;
    }
    
    //RETORNA QUANTIDADE MAXIMA DO ITEM POR OBJETO
    public byte getQuantidadeMax(){
        return quantidadeMax;
    }
    
    //RETORNA PESO INDIVIDUAL DO ITEM
    public byte getPesoItem(){
        return pesoItem;
    }
    
    //RETORNA PESO TOTAL DO ITEM
    public byte getPesoTotal(){
        return (byte) (pesoItem * quantidade);
    }
    
    //CONFIGURA OBJETO ITEM APARTIR DE ARQUIVO
    private void setParam(){
        Scanner scanner = new Scanner(ARQUIVOITENS);
        byte loop = 1;
        while((scanner.hasNext()) && (loop < id)){
            String nextLine = scanner.nextLine();
            loop++;
        }
        if (scanner.hasNext()){
            String[] parametros;                        
            parametros = scanner.nextLine().split("/");                         //DIVIDE A LINHA EM NOME/TIPO/EFEITO/PESO
            nome = parametros[0];                                               //NOME
            tipo = (byte) Integer.parseInt(parametros[1]);                      //TIPO
            efeito = (byte) Integer.parseInt(parametros[2]);                    //EFEITO
            pesoItem = (byte) Integer.parseInt(parametros[3]);                  //PESO
        }
        else
            throw new UnsupportedOperationException("ID de habilidade não encontrado.");
    }
}
