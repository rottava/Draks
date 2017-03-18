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
public final class Porta {
    private boolean estado;                                                     //ESTADO DA PORTA. TRUE = ABERTO, FALSE = FECHADO
    private final byte proximaSala;                                             //ID DA PROXIMA SALA
    private final byte chave;                                                   //ID DO ITEM PARA ABRIR A PORTA, 0 = NENHUM
    private byte tipo;                                                          //TIPO DO ITEM TiposDeItens: 1 = Armadura, 2 = Arma, 3 = Chave, 4 = Habilidades, 5 = Itens de Cura, 6 = Itens de Energia, 7 = Totem
    private byte item;                                                          //ID DO ITEM NA PORTA, 0 = NENHUM
    private int quantidade;                                                     //QUANTIDADE DE ITENS
    private byte inimigo;                                                       //ID DO INIMIGO NA PORTA, 0 = NENHUM
    
    public Porta(byte proximaSala, byte chave, byte tipo, byte item, int quantidade, byte inimigo){
        this.proximaSala = proximaSala;
        this.chave = chave;
        this.tipo = tipo;
        this.item = item;
        this.quantidade = quantidade;
        this.inimigo = inimigo;
        testaPorta();
    }
    
    //GETTERS AND SETTERS
    
    //SETA ESTADO DA PORTA
    public void testaPorta(){
        estado = chave == 0 && inimigo == 0 && item == 0; //PORTA ABERTA
        //SENAO
        //PORTA FECHADA
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
    
    //RETORNA ID DO INIMIGO
    public byte getInimigo(){
        return inimigo;
    }
    
    //SETA INIMIGOS PARA 0 (NAO EXISTEM)
    public void setInimigo(){
        inimigo = 0;
    }
    
    //RETORNA ID DO ITEM NA POSICAO NAVEGADOR
    public byte getItem(byte navegador){
        return item;
    }
    
    //SETA ITENS PARA 0 (NAO EXISTE)
    public void setItem(){
        item = 0;
    }
            
    
}
