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
    private final byte id;                  //ID DO ITEM = LINHA DO ARQUIVO
    
    //PARAMETRO DE DEFINIÇÃO
    private String nome;                    //NOME DO ITEM
    private byte tipo;                      //TIPO DO ITEM 0 = CONSUMIVEL, 1 = ARMA, 2 = ARMADURA
    private byte pesoItem;                  //PESO POR ITEM
    private final byte quantidadeMax = 100; //MAXIMO DE ITENS POR SLOT +1
    
    //PARAMETRO VARIAVEL
    private byte quantidade;                //QUANTIDADE
    private byte efeito;                    //QUANTIDADE EFEITO (VIDA, ENERGIA, ATAQUE, DEFESA)
    
    //CONSTRUTOR
    Item(byte id){
        this.id = id;
        setParam();
        quantidade = 1;
    }
    
    //GETTERS AND SETTERS
    public String getNome(){
        return nome;
    }
    
    public byte getId(){
        return id;
    }
    
    public byte getQuantidade(){
        return quantidade;
    }
    
    public boolean setQuantidade(byte quantidade){
        if (quantidade < quantidadeMax){
            this.quantidade = quantidade;
            return true;
        }
        else
            return false;
    }
    
    public byte getQuantidadeMax(){
        return quantidadeMax;
    }
    
    public byte getPesoItem(){
        return pesoItem;
    }
    
    public byte getPesoTotal(){
        return (byte) (pesoItem * quantidade);
    }
    
    private void setParam(){
        Scanner scanner;
        int n = 0;
        for(scanner = new Scanner(ARQUIVOITENS); (scanner.hasNext() && n < id); n++){
            String nextLine = scanner.nextLine();
        }
        if (scanner.hasNext()){
            String[] parametros;
            parametros = scanner.nextLine().split("/");
            nome = parametros[0];
            tipo = (byte) Integer.parseInt(parametros[1]);
            efeito = (byte) Integer.parseInt(parametros[2]);
            pesoItem = (byte) Integer.parseInt(parametros[3]);
        }
        else
            throw new UnsupportedOperationException("ID de habilidade não encontrado.");
    }
}
