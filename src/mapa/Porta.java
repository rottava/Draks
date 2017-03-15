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
public class Porta {
    private boolean estado;                                                     //ESTADO DA PORTA. TRUE = ABERTO, FALSE = FECHADO
    private final byte proximaSala;                                             //ID DA PROXIMA SALA
    private final byte chave;                                                   //ID DO ITEM PARA ABRIR A PORTA, 0 = NENHUM
    private byte item0;                                                         //ID DO ITEM0 NA PORTA, 0 = NENHUM
    private byte item1;                                                         //ID DO ITEM1 NA PORTA, 0 = NENHUM
    private byte item2;                                                         //ID DO ITEM2 NA PORTA, 0 = NENHUM
    private byte inimigo0;                                                      //ID DO INIMIGO0 NA PORTA, 0 = NENHUM
    private byte inimigo1;                                                      //ID DO INIMIGO1 NA PORTA, 0 = NENHUM
    private byte inimigo2;                                                      //ID DO INIMIGO2 NA PORTA, 0 = NENHUM
    
    public Porta(byte proximaSala, byte chave, byte item0, byte item1, byte item2, byte inimigo0, byte inimigo1, byte inimigo2){
        this.proximaSala = proximaSala;
        this.chave = chave;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.inimigo0 = inimigo0;
        this.inimigo1 = inimigo1;
        this.inimigo2 = inimigo2;
        testaPorta();
    }
    
    //GETTERS AND SETTERS
    
    //SETA ESTADO DA PORTA
    public void testaPorta(){
        if(chave == 0 && inimigo0 == 0 && inimigo1 == 0 && inimigo2 == 0        //SE TODOS OS IDS FOR 0
                && item0 == 0 && item1 == 0 && item2 == 0)
            estado = true;                                                      //PORTA ABERTA
        else                                                                    //SENAO
            estado = false;                                                     //PORTA FECHADA
    }
    
    //RETORNA VERDADEIRO SE ABERTA, FALSO SE FECHADA
    public boolean getEstado(){
        return estado;
    }
    
    //RETORNA ID DA PROXIMA SALA
    private byte getSala(){
        return proximaSala;
    }
    
    //RETORNA ID DA CHAVE
    public byte getChave(){
        return chave;
    }
    
    //SETA PARA CHAVE PARA 0
    public void setChave(byte chave){
            chave = 0;
    }
    
    //RETORNA ID DO INIMIGO NA POSICAO NAVEGADOR
    public byte getInimigo(byte navegador){
        switch(navegador){
            case 0: 
                return inimigo0;
            case 1: 
                return inimigo1;
            case 2: 
                return inimigo2;
           default:
                return 0;
        }
    }
    
    //SETA INIMIGOS PARA 0 (NAO EXISTEM)
    public void setInimigo(){
        inimigo0 = 0;
        inimigo1 = 0;
        inimigo2 = 0;
    }
    
    //RETORNA ID DO ITEM NA POSICAO NAVEGADOR
    public byte getItem(byte navegador){
        switch(navegador){
            case 0: 
                return item0;
            case 1: 
                return item1;
            case 2: 
                return item2;
           default:
                return 0;
        }
    }
    
    //SETA ITENS PARA 0 (NAO EXISTE)
    public void setItem(){
        item0 = 0;
        item1 = 0;
        item2 = 0;
    }
            
    
}
