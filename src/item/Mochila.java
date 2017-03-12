/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Mochila {
    //PARAMETRO DE DEFINICAO
    private final List<Item> mochila;           //MOCHILA
    
    //PARAMETRO VARIAVEL
    private byte tamanho;                       //TAMANHO
    private byte peso;                          //PESO
    
    //PARAMETRO INDEPENDENTE
    private final byte tamanhoIni = 10;         //TAMANHO INICIAL
    private final byte tamanhoMax = 100;        //TAMANHO MAXIMO
    
    //CONSTRUTOR
    public Mochila(){
        mochila = new ArrayList<>();
        tamanho = tamanhoIni;
        peso = 0;
    }
    
    //GETTERS AND SETTERS
    public List<Item> getMochila(){
        return mochila;
    }
    
    public byte getTamanho(){
        return tamanho;
    }
    
    public void setTamanho(byte tamanho){
        if (tamanho < tamanhoMax)
            this.tamanho = tamanho;
        else
            this.tamanho = tamanhoMax;
    }
    
    public byte getPeso(){
        return peso;
    }
    
    //ADICIONA ITEM NA MOCHILA
    public boolean addMochila(Item item){
        byte loop = 0;
        while ((mochila.get(loop).getId() != item.getId()) && loop < tamanho)
            loop++;
        if (mochila.get(loop).getId() == item.getId()){
            if ((mochila.get(loop).getQuantidadeMax() - mochila.get(loop).getQuantidade()) >= item.getQuantidade()){
                peso = (byte) (peso + item.getPesoTotal());
                mochila.get(loop).setQuantidade((byte) (mochila.get(loop).getQuantidade() + item.getQuantidade()));
                return true;
            }
            else{
                if(mochila.size() < tamanho){
                    peso = (byte) (peso + item.getPesoTotal());
                    item.setQuantidade((byte) (mochila.get(loop).getQuantidade() + item.getQuantidade() - mochila.get(loop).getQuantidadeMax()));
                    mochila.get(loop).setQuantidade(mochila.get(loop).getQuantidadeMax());
                    mochila.add(item);
                    return true;
                }
                else
                    return false;
            }
        }
        else {
            if (mochila.size() < tamanho){
                    peso = (byte) (peso + item.getPesoTotal());
                    mochila.add(item);
                    return true;
                }
                else
                    return false;
        }
    }
    
    //REMOVE ITEM DA MOCHILA
    public boolean subMochila(Item item){
        byte loop = 0;
        byte ultimo = 0;
        while (loop < tamanho){
            if (mochila.get(loop).getId() == item.getId())
                ultimo = loop;
            loop++;
        } 
        if (mochila.get(ultimo).getId() == item.getId()){
            if ((mochila.get(ultimo).getQuantidade() - item.getQuantidade()) > 0){
                peso = (byte) (peso - (item.getPesoTotal()));
                mochila.get(ultimo).setQuantidade((byte) (mochila.get(ultimo).getQuantidade() - item.getQuantidade()));
                return true;
            }
            else {
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
    
}
