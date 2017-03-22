/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.io.Serializable;

/**
 * Sala
 * @author Junior
 */
public class Sala implements Serializable {
    //PARAMETRO DE DEFINICAO
    private final byte id;                                                      //LINHA REFETENTE A SALA NO ARQUIVO
    private final String nome;                                                  //NOME DA SALA
    
    //PARAMETRO VARIAVEL
    private final Porta norte;                                                  //ID DA PROXIMA SALA/ID DA CHAVE/ID DO ITEM[0~2]/ID DO INIMIGO[0~2]
    private final Porta sul;                                                    //
    private final Porta leste;                                                  //
    private final Porta oeste;                                                  //
                                                                                //  
    //CONSTRUTOR
    /**
     * Construtor da Sala
     * @param id ID da Sala
     * @param nome Nome da Sala
     * @param norte Porta que está ao norte
     * @param sul Porta que está ao sul
     * @param leste Porta que está ao leste
     * @param oeste Porta que está ao oeste
     */
    public Sala(byte id, String nome, Porta norte, Porta sul, Porta leste, Porta oeste){
        this.id = id;                                                           //LINHA DO ARQUIVO
        this.nome = nome;                                                       //NOME
        this.norte = norte;                                                     //NORTE 8 BYTES
        this.sul = sul;                                                         //SUL   8 BYTES
        this.leste = leste;                                                     //LESTE 8 BYTES
        this.oeste = oeste;                                                     //OESTE 8 BYTES
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA ID
    /**
     * Pega id da sala
     * @return byte com a id da sala
     */
    public byte getId(){
        return id;
    }
    
    //RETORNA NOME
    /**
     * Pega nome da Sala
     * @return String com o nome da Sala
     */
    public String getNome(){
        return nome;
    }
    
    //RETORNA PORTA NORTE
    /**
     * Pega a saída que está ao norte
     * @return Porta norte
     */
    public Porta getNorte(){
        return norte;
    }
    
    //RETORNA PORTA NORTE
    /**
     * Pega a saída que está ao sul
     * @return Porta sul
     */
    public Porta getSul(){
        return sul;
    }
    
    //RETORNA PORTA LESTE
    /**
     * Pega a saída que está ao leste
     * @return Porta leste
     */
    public Porta getLeste(){
        return leste;
    }
    
    //RETORNA PORTA OESTE
    /**
     * Pega a saída que está ao oeste
     * @return Porta oeste
     */
    public Porta getOeste(){
        return oeste;
    }

}
