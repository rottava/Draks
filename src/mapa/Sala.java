/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

/**
 *
 * @author Junior
 */
public class Sala {
    //PARAMETRO DE DEFINICAO
    private final byte id;                                                      //LINHA REFETENTE A SALA NO ARQUIVO
    private final String nome;                                                  //NOME DA SALA
    
    //PARAMETRO VARIAVEL
    private final byte[] norte;                                                 //[0] = ID DA PROXIMA SALA
    private final byte[] sul;                                                   //[1] = ESTADO: 0 = ABERTO, 1 = FECHADO, 2 = ITEM, 3 = INIMIGO
    private final byte[] leste;                                                 //[2] = ID MONSTRO[1] OU ITEM[1] OU -1 ~ -6 PARA INIMIGO ALEATORIO OU 0 PARA VAZIO
    private final byte[] oeste;                                                 //[3] = ID MONSTRO[2] OU ITEM[2] OU -1 ~ -6 PARA INIMIGO ALEATORIO OU 0 PARA VAZIO
                                                                                //[4] = ID MONSTRO[3] OU ITEM[3] OU -1 ~ -6 PARA INIMIGO ALEATORIO OU 0 PARA VAZIO   
    //CONSTRUTOR
    public Sala(byte id, String nome, byte[] norte, byte[] sul, byte[] leste, byte[] oeste){
        this.id = id;                                                           //LINHA DO ARQUIVO
        this.nome = nome;                                                       //NOME
        this.norte = norte;                                                     //NORTE 5 BYTES
        this.sul = sul;                                                         //SUL   5 BYTES
        this.leste = leste;                                                     //LESTE 5 BYTES
        this.oeste = oeste;                                                     //OESTE 5 BYTES
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA ID
    public byte getId(){
        return id;
    }
    
    //RETORNA NOME
    public String getNome(){
        return nome;
    }
    
    //RETORNA VALOR REFERENTE AO NAVEGADOR DO NORTE 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public byte getNorte(byte navegador){
        if (navegador < 5)
            return norte[navegador];
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //SETA VALOR REFERENTE AO NAVEGADOR DO NORTE  0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public void setNorte(byte navegador, byte novoValor){
        if (navegador < 5)
            norte[navegador] = novoValor;
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //RETORNA VALOR REFERENTE AO NAVEGADOR DO SUL 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public byte getSul(byte navegador){
        if (navegador < 5)
            return sul[navegador];
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //SETA VALOR REFERENTE AO NAVEGADOR DO SUL 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public void setSul(byte navegador, byte novoValor){
        if (navegador < 5)
            sul[navegador] = novoValor;
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //RETORNA VALOR REFERENTE AO NAVEGADOR DO LESTE 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public byte getLeste(byte navegador){
        if (navegador < 5)
            return leste[navegador];
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //SETA VALOR REFERENTE AO NAVEGADOR DO LESTE 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public void setLeste(byte navegador, byte novoValor){
        if (navegador < 5)
            leste[navegador] = novoValor;
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //RETORNA VALOR REFERENTE AO NAVEGADOR DO OESTE 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public byte getOeste(byte navegador){
        if (navegador < 5)
            return oeste[navegador];
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }
    
    //SETA VALOR REFERENTE AO NAVEGADOR DO OESTE 0 = ID PROX SALA, 1 = ESTADO, 2 = TIPO1, 3 = TIPO2, 4 = TIPO3
    public void setOeste(byte navegador, byte novoValor){
        if (navegador < 5)
            oeste[navegador] = novoValor;
        else
            throw new UnsupportedOperationException("Navegador de norte excedeu o limite.");
    }

}
