/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

/**
 * Classe abstrata de Item
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
    /**
     * Construtor
     */
    public Item(){
        quantidade = 1;                                                         //QUANTIDADE MINIMA PARA EXISTIR = 1
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA NOME
    /**
     * Pega o nome do Item
     * @return String com o nome
     */
    public String getNome(){
        return nome;
    }
    
    //SETA NOME PARA
    /**
     * Configura nome do Item
     * @param nome String contendo o nome do item
     */
    protected void setNome(String nome){
        this.nome = nome;
    }
    
    //RETORNA ID
    /**
     * Pega a ID do tem
     * @return byte contendo a ID do ítem
     */
    public byte getId(){
        return id;
    }
    
    //SETA ID PARA
    /**
     * Configura ID do ítem
     * @param id ID do ítem
     */
    protected void setId(byte id){
        this.id = id;
    }
    
    //RETORNA EFEITO
    /**
     * Pega o efeito do ítem
     * @return byte com a ID do efeito
     */
    public byte getEfeito(){
        return efeito;
    }
    
    //SETA EFEITO PARA
    /**
     * Configura efeito
     * @param efeito Efeito do ítem
     */
    protected void setEfeito(byte efeito){
        this.efeito = efeito;
    }
    
    //RETORNA QUANTIDADE TOTAL
    /**
     * Pega a quantidade total de item
     * @return byte com a quantidade total de ítem
     */
    public byte getQuantidade(){
        return quantidade;
    }
    
    //RETORNA QUANTIDADE MAXIMA DO ITEM POR OBJETO
    /**
     * Pega a quantidade máximo de ítem
     * @return byte com a quantidade máxima de ítem
     */
    public byte getQuantidadeMax(){
        return QUANTIDADEMAX;
    }
    
    //SETA QUANTIDADE PARA
    /**
     * Configura a quantidade de ítem
     * @param quantidade byte com a quantidade do ítem
     * @return true se a operação foi realizada com sucesso, false caso contrário
     */
    public boolean setQuantidade(byte quantidade){
        if(quantidade <= QUANTIDADEMAX && quantidade > 0){
            this.quantidade = quantidade;
            return true;
        }
        else
            return false;  
    }
    
    //RETORNA PESO INDIVIDUAL DO ITEM
    /**
     * Pega peso individual do ite
     * @return byte com o peso individual do ítem
     */
    public byte getPeso(){
        return peso;
    }
    
    //SETA PESO ITEM PARA
    /**
     * Configura peso do ítem
     * @param peso byte com o peso do ítem
     */
    protected void setPeso(byte peso){
        this.peso = peso;
    }
    
    //RETORNA PESO TOTAL DO ITEM
    /**
     * Pega o peso total do item
     * @return byte com o peso total do item
     */
    public byte getPesoTotal(){
        float pesoTotal = peso;
        pesoTotal = ((pesoTotal * quantidade));
        return (byte) pesoTotal;
    }
    
    /**
     * Decrementa a quantidade do ítem
     * @return true se foi decrementado, false caso contrário
     */
    public boolean decrementa(){
        if (quantidade > 1){
            quantidade--;
            return true;
        }
        else
            return false;
    }
    
    /**
     * Incrementa na quantidade do ítem
     * @return true se foi incrementado, false caso contrário
     */
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
