/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

/**
 *
 * @author Junior
 */
public abstract class Item {
    //PARAMETRO DE CONFIGURACAO
    private byte id;                                                            //ID DO ITEM = LINHA DO ARQUIVO
    
    //PARAMETRO DE DEFINIÇÃO
    private String nome;                                                        //NOME DO ITEM
    private byte efeito;                                                        //EFEITO
    private byte peso;                                                          //PESO POR ITEM
    private final byte QUANTIDADEMAX = 100;                                     //MAXIMO DE ITENS POR SLOT +1
    
    //PARAMETRO VARIAVEL
    private byte quantidade;                                                    //QUANTIDADE
    
    //CONSTRUTOR
    public Item(){
        quantidade = 1;                                                         //QUANTIDADE MINIMA PARA EXISTIR = 1
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA NOME
    public String getNome(){
        return nome;
    }
    
    //SETA NOME PARA
    protected void setNome(String nome){
        this.nome = nome;
    }
    
    //RETORNA ID
    public byte getId(){
        return id;
    }
    
    //SETA ID PARA
    protected void setId(byte id){
        this.id = id;
    }
    
    //RETORNA EFEITO
    public byte getEfeito(){
        return efeito;
    }
    
    //SETA EFEITO PARA
    protected void setEfeito(byte efeito){
        this.efeito = efeito;
    }
    
    //RETORNA QUANTIDADE TOTAL
    public byte getQuantidade(){
        return quantidade;
    }
    
    //RETORNA QUANTIDADE MAXIMA DO ITEM POR OBJETO
    public byte getQuantidadeMax(){
        return QUANTIDADEMAX;
    }
    
    //SETA QUANTIDADE PARA
    public boolean setQuantidade(byte quantidade){
        if(quantidade <= QUANTIDADEMAX && quantidade > 0){
            this.quantidade = quantidade;
            return true;
        }
        else
            return false;  
    }
    
    //RETORNA PESO INDIVIDUAL DO ITEM
    public byte getPeso(){
        return peso;
    }
    
    //SETA PESO ITEM PARA
    protected void setPeso(byte peso){
        this.peso = peso;
    }
    
    //RETORNA PESO TOTAL DO ITEM
    public byte getPesoTotal(){
        float pesoTotal = peso;
        pesoTotal = ((pesoTotal * quantidade));
        return (byte) pesoTotal;
    }
    
    public boolean decrementa(){
        if (quantidade > 1){
            quantidade--;
            return true;
        }
        else
            return false;
    }
    
    public boolean incrementa(){
        if (quantidade < QUANTIDADEMAX){
            quantidade++;
            return true;
        }
        else{
            quantidade = (QUANTIDADEMAX);
            return false;
        }
    }
    
}
