/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import item.Item;
import item.Mochila;
import static main.Main.TAMANHOMAXIMO;

/**
 *
 * @author Junior
 */
public class Heroi extends Persona {
    //PARAMETROS VARIAVEIS
    private byte pontos;                                                        //PONTOS DE ATRIBUTOS
    private byte peso;                                                          //CARGA DE ITENS ATUAL
    private final Mochila mochila;                                              //ARMAZEM DE ITENS
    
    
    //PARAMETROS DEPENDENTES
    private byte pesoMax;                                                       //FORCA + RESISTENCIA
    
    //CONSTRUTOR
    public Heroi(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia) {
        super(nome, forca, inteligencia, agilidade, resistencia);               //CONSTRUTOR PAI
        mochila = new Mochila();                                                //LISTA DE ITENS
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA PONTOS
    public byte getPontos(){
        return pontos;
    }
    
    //SETA PONTOS E RETORNA VERDADEIRO OU RETORNA FALSO
    public void setPontos(byte pontos){
        if (pontos < TAMANHOMAXIMO)
            this.pontos = pontos;
        else
            this.pontos = TAMANHOMAXIMO;
    }
    
    //RETORNA PESO
    public byte getPeso(){
        return peso;
    }
    
    //SETA PESO E RETORNA VERDADEIRO OU RETORNA FALSO
    public boolean setPeso(byte peso){
        if (peso <= pesoMax){
            this.peso = peso;
            return true;
        }
        else
            return false;
    }
    
    //RETORNA PESO MAXIMO
    public byte getPesoMax(){
        return pesoMax;
    }
    
    //SETA PESO MAXIMO PARA
    private void setPesoMax(byte pesoMax){
        if (pesoMax < TAMANHOMAXIMO)
            this.pesoMax = pesoMax;
        else
            this.pesoMax = TAMANHOMAXIMO;
    }
    
    //GERA PESO MAXIMO
    public void geraPesoMax(){
        setPesoMax((byte) (getForca() + getResistencia()));
    }
    
    //RETORNA LISTA DE ITENS
    public Mochila getMochila(){
        return mochila;
    }
    
    //ADICIONA ITEM A LISTA DE ITENS E RETORNA 0, RETORNA 1 CASO MOCHILA CHEIA, RETORNA 2 CASO PESO EXEDE LIMITE
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
    
    //REMOVE ITEM DA MOCHILA E RETORNA VERDADEIRO OU RETORNA FALSO SE ITEM NAO EXISTE
    public boolean subMochila(Item item){
        if(mochila.subMochila(item)){
            setPeso((byte) (getArma().getPesoItem() + getArmadura().getPesoItem() + mochila.getPeso()) );
            return true;
        }
        else 
            return false;
    }
    
    //EQUIPA ARMA OU NOVA ARMA E GUARDA NA LISTA DE ITENS ARMA ANTIGA
    public void equipaArma(Item arma){
        mochila.subMochila(arma);
        if (getArma() != null)
            mochila.addMochila(getArma());
        setArma(arma);
    }
    
    //REMOVE ARMA DO PERSONAGEM E GUARDA NA LISTA DE ITENS
    public void desequipaArma(){
        if (getArma() != null)
            mochila.addMochila(getArma());
        setArma(null);
    }
    
    //EQUIPA ARMADURA OU NOVA ARMADURA E GUARDA NA LISTA DE ITENS ARMADURA ANTIGA
    public void equipaArmadura(Item arma){
        mochila.subMochila(arma);
        if (getArmadura() != null)
            mochila.addMochila(getArmadura());
        setArma(arma);
    }
    
    //REMOVE ARMADURA DO PERSONAGEM E GUARDA NA LISTA DE ITENS
    public void desequipaArmadura(){
        if (getArmadura() != null)
            mochila.addMochila(getArmadura());
        setArma(null);
    }
            
}
