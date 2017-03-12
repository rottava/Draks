/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import habilidade.Habilidade;
import habilidade.Talentos;
import item.Item;
import item.Mochila;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Heroi extends Persona {
    //PARAMETROS VARIAVEIS
    private byte pontos;            //PONTOS DE ATRIBUTOS
    private byte peso;              //CARGA DE ITENS ATUAL
    private final Mochila mochila;  //ARMAZEM DE ITENS
    
    
    //PARAMETROS DEPENDENTES
    private byte pesoMax;           //FORCA + RESISTENCIA
    
    //CONSTRUTOR
    public Heroi(String nome, byte forca, byte inteligencia, byte velocidade, byte resistencia) {
        super(nome, forca, inteligencia, velocidade, resistencia);
        mochila = new Mochila();
    }
    
    //GETTERS AND SETTERS
    public byte getPontos(){
        return pontos;
    }
    
    public void setPontos(byte pontos){
        this.pontos = pontos;
    }
    
    public byte getPeso(){
        return peso;
    }
    
    public boolean setPeso(byte peso){
        if (peso <= pesoMax){
            this.peso = peso;
            return true;
        }
        else
            return false;
    }
    
    public byte getPesoMax(){
        return pesoMax;
    }
    
    private void setPesoMax(byte pesoMax){
        if (pesoMax < Byte.SIZE)
            this.pesoMax = pesoMax;
        else
            this.pesoMax = Byte.SIZE;
    }
    
    public void geraPesoMax(){
        setPesoMax((byte) (getForca() + getResistencia()));
    }
    
    public Mochila getMochila(){
        return mochila;
    }
    
    public byte addMochila(Item item){
        Byte pesoAux = (byte) ((getArma().getPesoItem() + getArmadura().getPesoItem() + mochila.getPeso()));
        if (pesoAux <= pesoMax){
            if(mochila.addMochila(item)){
                setPeso(pesoAux);
                return 0;
            }
            else 
                return 1;
        }
        else
            return 2;
    }
    
    public boolean subMochila(Item item){
        if(mochila.subMochila(item)){
            setPeso((byte) (getArma().getPesoItem() + getArmadura().getPesoItem() + mochila.getPeso()) );
            return true;
        }
        else 
            return false;
    }
    
    public void equipaArma(Item arma){
        mochila.subMochila(arma);
        if (getArma() != null)
            mochila.addMochila(getArma());
        setArma(arma);
    }
    
    public void desequipaArma(){
        if (getArma() != null)
            mochila.addMochila(getArma());
        setArma(null);
    }
    
    public void equipaArmadura(Item arma){
        mochila.subMochila(arma);
        if (getArmadura() != null)
            mochila.addMochila(getArmadura());
        setArma(arma);
    }
    
    public void desequipaArmadura(){
        if (getArmadura() != null)
            mochila.addMochila(getArmadura());
        setArma(null);
    }
            

}
