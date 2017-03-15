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
    private final Porta norte;                                                  //ID DA PROXIMA SALA/ID DA CHAVE/ID DO ITEM[0~2]/ID DO INIMIGO[0~2]
    private final Porta sul;                                                    //
    private final Porta leste;                                                  //
    private final Porta oeste;                                                  //
                                                                                //  
    //CONSTRUTOR
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
    public byte getId(){
        return id;
    }
    
    //RETORNA NOME
    public String getNome(){
        return nome;
    }
    
    //RETORNA PORTA NORTE
    public Porta getNorte(){
        return norte;
    }
    
    //RETORNA PORTA NORTE
    public Porta getSul(){
        return sul;
    }
    
    //RETORNA PORTA LESTE
    public Porta getLeste(){
        return leste;
    }
    
    //RETORNA PORTA OESTE
    public Porta getOeste(){
        return oeste;
    }

}
