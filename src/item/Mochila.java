/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.util.ArrayList;
import java.util.List;

/**
 * Mochila
 * @author Junior
 */
public class Mochila {
    //PARAMETRO DE DEFINICAO
    private final List<Item> mochila;                                           //MOCHILA
    
    //PARAMETRO VARIAVEL
    private byte tamanho;                                                       //TAMANHO
    private byte peso;                                                          //PESO
    
    //PARAMETRO INDEPENDENTE
    private final byte TAMANHOINICIAL = 10;                                     //TAMANHO INICIAL
    private final byte TAMANHOFINAL = 100;                                      //TAMANHO MAXIMO
    
    //CONSTRUTOR
    /**
     * Construtor
     */
    public Mochila(){
        mochila = new ArrayList<>();                                            //LISTA DE ITENS
        tamanho = TAMANHOINICIAL;                                               //TAMANHO INICIAL DA LISTA
        peso = 0;                                                               //PESO
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA LISTA DE ITENS
    /**
     * Pega os itens da Mochila
     * @return lista com todos os ítens da Mochila
     */
    public List<Item> getItens(){
        return mochila;
    }
    
    //RETORNA INDEX DO ITEM
    /**
     * Pega Index do ítem
     * @param id ID do ítem
     * @return retorna index do ítem
     */
    public byte getItemIndex(byte id){
        byte index = 0;
        while(mochila.get(index).getId() != id && index <= mochila.size())
            index++;
        return index;
    }
    
    /**
     * Pega a ID do efeito na Mochila
     * @param id ID do efeito
     * @return ID do efeito na Mochila
     */
    public byte getEfeitoId(byte id){
        return mochila.get(getItemIndex(id)).getEfeito();
    }
    
    //RETORNA TAMANHO
    /**
     * Pega o tamanho da Mochila
     * @return byte com o tamanho da mochila
     */
    public byte getTamanho(){
        return tamanho;
    }
    
    //SETA TAMANHO ENQUANTO TAMANHO MENO QUE TAMANHOFINAL
    /**
     * Configura o tamanho da Mochila
     * @param tamanho byte com o novo tamanho da Mochila
     */
    public void setTamanho(byte tamanho){
        if (tamanho < TAMANHOFINAL)
            this.tamanho = tamanho;
        else
            this.tamanho = TAMANHOFINAL;
    }
    
    //RETORNA PESO
    /**
     * Pega o peso atual da Mochila
     * @return byte com o peso atual da Mochila
     */
    public byte getPeso(){
        return peso;
    }
    
    //ADICIONA ITEM NA LISTA DE ITENS
    /**
     * Adiciona itens na Mochila
     * @param item id do ítem
     * @return true se foi adicionado com sucesso, false caso contrário
     */
    public boolean addMochila(Item item){
        byte loop = 0;
        if (mochila.size() > 0){
            while ((mochila.get(loop).getId() != item.getId()) && loop < mochila.size())   //VERIFICA LISTA PROCURANDO O ITEM
                loop++;
            if (mochila.get(loop).getId() == item.getId()){                         //SE ITEM JA EXISTE NA LISTA DE ITENS
                if ((mochila.get(loop).getQuantidadeMax() - mochila.get(loop).getQuantidade()) >= item.getQuantidade()){ //SE NUMERO DE ITENS NÃO ULTRAPASSA LIMITE DE ITENS POR OBJETO
                    peso = (byte) (peso + item.getPesoTotal());
                    mochila.get(loop).setQuantidade((byte) (mochila.get(loop).getQuantidade() + item.getQuantidade()));
                    return true;                                                    
                }
                else{                                                               //NUMERO DE ITENS ULTRAPASSA LIMITE DE ITENS POR OBJETO
                    if(mochila.size() < tamanho){                                   //SE NOVO NUMERO DE ITENS NAO ULTRAPASSA LIMITE DE ITENS NA LISTA
                        peso = (byte) (peso + item.getPesoTotal());
                        item.setQuantidade((byte) (mochila.get(loop).getQuantidade() + item.getQuantidade() - mochila.get(loop).getQuantidadeMax()));
                        mochila.get(loop).setQuantidade(mochila.get(loop).getQuantidadeMax());
                        mochila.add(item);                                          //ADICIONA NOVO OBJETO NA LISTA
                        return true;
                    }
                    else
                        return false;
                }
            }
            else {                                                                  //SE ITEM NAO EXISTE NA LISTA DE ITENS
                if (mochila.size() < tamanho){                                      //SE O NOVO PESO NAO ULTRAPASSA LIMITE MAXIMO DE ITENS
                        peso = (byte) (peso + item.getPesoTotal());
                        mochila.add(item);
                        return true;
                    }
                else
                    return false;
            }
        }
        else {                                                                  //SE ITEM NAO EXISTE NA LISTA DE ITENS                                   //SE O NOVO PESO NAO ULTRAPASSA LIMITE MAXIMO DE ITENS
            peso = (byte) (peso + item.getPesoTotal());
            mochila.add(item);
            return true;       
        }
    }
    
    //REMOVE ITEM DA LISTA DE ITENS
    /**
     * Remove um ítem da mochila
     * @param item ID do ítem
     * @return true se a operação foi bem realizada com sucesso, false caso contrário
     */
    public boolean subMochila(Item item){
        byte loop = 0;
        byte ultimo = 0;
        if(mochila.size() > 0){
            while (loop < tamanho){
                if (mochila.get(loop).getId() == item.getId())                      //VERIFICA LISTA PROCURANDO ITEM
                    ultimo = loop;
                loop++;
            } 
            if (mochila.get(ultimo).getId() == item.getId()){                       //SE ITEM EXISTE NA LISTA
                if ((mochila.get(ultimo).getQuantidade() - item.getQuantidade()) > 0){// SE NOVO NUMERO DE ITENS E MAIOR QUE 0
                    peso = (byte) (peso - (item.getPesoTotal()));
                    mochila.get(ultimo).setQuantidade((byte) (mochila.get(ultimo).getQuantidade() - item.getQuantidade()));
                    return true;
                }
                else {                                                              //SE NOVO NUMERO DE ITENS E 0
                    if ((mochila.get(loop).getQuantidade() - item.getQuantidade()) == 0){
                        peso = (byte) (peso - (item.getPesoTotal()));
                        mochila.remove(loop);
                        return true;
                    }
                    else
                        return false;
                }
            }
            else
                return false;
        }
        else
            return false;
    }
    
}
