/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.io.Serializable;

/**
 * Porta
 * @author Junior
 */
public final class Porta implements Serializable {
    private boolean estado;                                                     //ESTADO DA PORTA. TRUE = ABERTO, FALSE = FECHADO
    private final byte proximaSala;                                             //ID DA PROXIMA SALA
    private byte chave;                                                         //ID DO ITEM PARA ABRIR A PORTA, 0 = NENHUM
    private byte tipo;                                                          //TIPO DO ITEM TiposDeItens: 1 = Armadura, 2 = Arma, 3 = Chave, 4 = Habilidades, 5 = Itens de Cura, 6 = Itens de Energia, 7 = Totem
    private byte item;                                                          //ID DO ITEM NA PORTA, 0 = NENHUM
    private int quantidade;                                                     //QUANTIDADE DE ITENS
    private byte inimigo;                                                       //ID DO INIMIGO NA PORTA, 0 = NENHUM
    
    /**
     * Construtor
     * @param proximaSala Próxima Sala que está nessa Porta
     * @param chave Chave para abrir essa Porta
     * @param tipo Tipo do ítem que está nessa Porta
     * @param item Ítem que está nessa Porta
     * @param quantidade Quantidade do ítem
     * @param inimigo Inimigo que está defendendo a Porta
     */
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
    /**
     * Testa se a porta está aberta ou fechada
     */
    public void testaPorta(){
        estado = chave == 0 && inimigo == 0 && item == 0; //PORTA ABERTA
        //SENAO
        //PORTA FECHADA
    }
    
    //RETORNA VERDADEIRO SE ABERTA, FALSO SE FECHADA
    /**
     * Pega o estado da porta
     * @return true se está aberta, false está fechada
     */
    public boolean getEstado(){
        return estado;
    }
    
    //RETORNA ID DA PROXIMA SALA
    /**
     * Pega a sala que está nessa Porta
     * @return ID da Próxima Sala
     */
    public byte getSala(){
        return proximaSala;
    }
    
    //RETORNA ID DA CHAVE
    /**
     * Pega a ID da chave
     * @return byte com a ID da chave
     */
    public byte getChave(){
        return chave;
    }
    
    //SETA PARA CHAVE PARA 0
    /**
     * Configura chave na porta
     */
    public void setChave(){
            chave = (byte) 0;
    }
    
    //RETORNA ID DO INIMIGO
    /**
     * Pega qual inimigo está nessa porta
     * @return id do Inimigo
     */
    public byte getInimigo(){
        return inimigo;
    }
    
    //SETA INIMIGOS PARA 0 (NAO EXISTEM)
    /**
     * Seta inimigos nessa porta
     */
    public void setInimigo(){
        inimigo = 0;
    }
    
    //RETORNA ID DO ITEM
    /**
     * Pega a id do ítem que está nessa porta
     * @return byte com a ID do ítem
     */
    public byte getItem(){
        return item;
    }
    
    //SETA ITENS PARA 0 (NAO EXISTE)
    /**
     * Seta itens na porta (0 não existe)
     */
    public void setItem(){
        item = 0;
    }
    
    //RETORNA TIPO DO ITEM
    /**
     * Pega o tipo de ítem que está nessa porta
     * @return ID do ítem
     */
    public byte getTipo(){
        return tipo;
    }
    
    //SETA TIPO PARA 0 (NAO EXISTE)
    /**
     * Seta Tipo do ítem para 0 (Não Existe)
     */
    public void setTipo(){
        tipo = 0;
    }
    
    //RETORNA QUANTIDADE DO ITEM
    /**
     * Pega quantidade do item
     * @return quantidade do item
     */
    public int getQuantidade(){
        return quantidade;
    }
    
    //SETA QUANTIDADE PARA 0 (NAO EXISTE)
    /**
     * Seta para 0 (Não Existe)
     */
    public void setQuantidade(){
        quantidade = 0;
    }
            
    
}
