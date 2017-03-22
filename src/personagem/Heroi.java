/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import item.Arma;
import item.Armadura;
import item.Item;
import item.Mochila;
import java.io.Serializable;
import java.util.List;
import static main.Main.TAMANHOMAXIMO;

/**
 * Personagem Herói
 * @author Junior
 */
public class Heroi extends Persona implements Serializable {
    //PARAMETROS VARIAVEIS
    private byte pontos;                                                        //PONTOS DE ATRIBUTOS
    private byte peso;                                                          //CARGA DE ITENS ATUAL
    private final Mochila mochila;                                              //ARMAZEM DE ITENS
    
    
    //PARAMETROS DEPENDENTES
    private byte PESOMAX;                                                       //FORCA + RESISTENCIA
    private final int MOEDASMAX = 1000000;                                      //MAXIMO DE MOEDAS          
    
    //CONSTRUTOR
    /**
     * Construtor
     * @param nome Nome do Herói
     * @param forca Força do Herói
     * @param inteligencia Inteligencia do Herói
     * @param agilidade Agilidade do Herói
     * @param resistencia Resistencia do Herói
     */
    public Heroi(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia) {
        super(nome, forca, inteligencia, agilidade, resistencia);               //CONSTRUTOR PAI
        mochila = new Mochila();                                                //LISTA DE ITENS
        ini();                                                                  //INICIALIZA VALORES
    }
    
    //GETTERS AND SETTERS
    
    //INICIALIZACAO DE VALORES
    /**
     * Inicialização do pontos e peso
     */
    private void ini(){
        pontos = 10;                                                            //PONTOS INICIAIS
        peso = 0;                                                               //PESO INICIAL
        geraPesoMax();                                                          //PESO MAXIMO
        setMoedas(0);                                                           //MOEDAS INICIAL
    }
    
    //RETORNA PONTOS
    /**
     * Pega a quantidade de pontos
     * @return byte com a quantidade de pontos
     */
    public byte getPontos(){
        return pontos;
    }
    
    //SETA PONTOS E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Seta quantidade de pontos
     * @param pontos byte com a quantidade de pontos
     */
    public void setPontos(byte pontos){
        if (pontos < TAMANHOMAXIMO)
            this.pontos = pontos;
        else
            this.pontos = TAMANHOMAXIMO;
    }
    
    //RETORNA PESO
    /**
     * Pega peso do inimigo
     * @return byte com o peso do Herói
     */
    public byte getPeso(){
        return peso;
    }
    
    //SETA PESO E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Configura peso do Herói
     * @param peso byte com o peso
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean setPeso(byte peso){
        if (peso <= PESOMAX){
            this.peso = peso;
            return true;
        }
        else
            return false;
    }
    
    //RETORNA PESO MAXIMO
    /**
     * Pega peso máximo
     * @return byte com peso máximo
     */
    public byte getPesoMax(){
        return PESOMAX;
    }
    
    //SETA PESO MAXIMO PARA
    /**
     * Configura peso máximo
     * @param PESOMAX Peso máximo
     */
    private void setPesoMax(byte PESOMAX){
        if (PESOMAX < TAMANHOMAXIMO)
            this.PESOMAX = PESOMAX;
        else
            this.PESOMAX = TAMANHOMAXIMO;
    }
    
    //GERA PESO MAXIMO
    /**
     * Gera peso máximo
     */
    public void geraPesoMax(){
        setPesoMax((byte) (getForca() + getResistencia()));
    }
    
    //RETORNA LISTA DE ITENS
    /**
     * Pega os itens do herói
     * @return lista de itens
     */
    public Mochila getMochila(){
        return mochila;
    }
    
    /**
     * Pega os itens
     * @return lista com os itens
     */
    public List<Item> getItens(){
        return mochila.getItens();
    }
    
    //ADICIONA ITEM A LISTA DE ITENS E RETORNA 0, RETORNA 1 CASO MOCHILA CHEIA, RETORNA 2 CASO PESO EXEDE LIMITE
    /**
     * Adiciona um item a lista de itens
     * @param item item para adicionar
     * @return 1 caso a mochila esteja cheia, 2 caso o peso excede, ou 0 se tudo ocorreu bem
     */
    public byte addMochila(Item item){
        Byte pesoAux = (byte) (peso + item.getPeso());
        if (pesoAux <= PESOMAX){
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
    /**
     * Remove item da mochila
     * @param item item para ser removida
     * @return true se foi removida, false caso contrário
     */
    public boolean subMochila(Item item){
        if(mochila.subMochila(item)){
            setPeso((byte) (peso - item.getPeso()) );
            return true;
        }
        else 
            return false;
    }
    
    //EQUIPA ARMA OU NOVA ARMA E GUARDA NA LISTA DE ITENS ARMA ANTIGA
    /**
     * Equipa arma ou nova arma e guarda na lista
     * @param arma arma para ser equipada
     */
    public void equipaArma(Arma arma){
        mochila.subMochila(arma);
        if (getArma() != null)
            mochila.addMochila(getArma());
        setArma(arma);
    }
    
    //REMOVE ARMA DO PERSONAGEM E GUARDA NA LISTA DE ITENS
    /**
     * Remove arma do personagem e guarda
     */
    public void desequipaArma(){
        if (getArma() != null)
            mochila.addMochila(getArma());
        setArma(null);
    }
    
    //EQUIPA ARMADURA OU NOVA ARMADURA E GUARDA NA LISTA DE ITENS ARMADURA ANTIGA
    /**
     * Equipa armadura
     * @param armadura Armadura para ser equipada 
     */
    public void equipaArmadura(Armadura armadura){
        mochila.subMochila(armadura);
        if (getArmadura() != null)
            mochila.addMochila(getArmadura());
        setArmadura(armadura);
    }
    
    //REMOVE ARMADURA DO PERSONAGEM E GUARDA NA LISTA DE ITENS
    /**
     * Remove armadura e guarda
     */
    public void desequipaArmadura(){
        if (getArmadura() != null)
            mochila.addMochila(getArmadura());
        setArma(null);
    }
    
    /**
     * Adiciona moedas
     * @param moedas quantidade de moedas
     * @return true se foi adicionado, false contrário
     */
    public boolean addMoedas(int moedas){
        if (getMoedas() + moedas < MOEDASMAX){
            if(setPeso((byte) (peso - (this.getMoedas()/100) + (getMoedas() + moedas)/100))){
                setMoedas(getMoedas() + moedas);
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    /**
     * Retira moedas
     * @param moedas quantidade de moedas
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean subMoedas(int moedas){
        if (getMoedas() - moedas >= 0){
            if(setPeso((byte) (peso - (moedas/100)) )){
                setMoedas(getMoedas() - moedas);
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
            
}
