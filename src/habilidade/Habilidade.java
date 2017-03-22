/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.io.Serializable;

/**
 *
 * @author Junior
 */
public abstract class Habilidade implements Serializable{
    //PARAMETRO DE CONFIGURACAO
    private byte id;                                                      //LINHA DO ARQUIVO
    
    //PARAMETRO DE DEFINIÇÃO
    private String nome;                                                        //NOME DE HABILIDADE
    private byte efeito;                                                        //EFEITO DE HABILIDADE
    private byte consumo;                                                       //CONSUMO DE ENERGIA POR HABILIDADE
    
    //CONSTRUTOR POR ID
    public Habilidade(){
    }
    
    //GETTERS AND SETTERS
    
    //RETORNA ID
    public byte getId(){
        return id;
    }
    
    //SETA ID PARA
    protected void setId(byte id){
        this.id = id;
    }
    
    //RETORNA NOME
    public String getNome(){
        return nome;
    }
    
    //SETA NOME PARA
    protected void setNome(String nome){
        this.nome = nome;
    }
    
    //RETORNA EFEITO
    public byte getEfeito(){
        return efeito;
    }
    
    //SETA EFEITO PARA
    protected void setEfeito(byte efeito){
        this.efeito = efeito;
    }
    
    //RETORNA CONSUMO
    public byte getConsumo(){
        return consumo;
    }
    
    //SETA CONSUMO PARA
    protected void setConsumo(byte consumo){
        this.consumo = consumo;
    }
    
    
    
}
