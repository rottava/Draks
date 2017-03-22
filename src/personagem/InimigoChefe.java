/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static main.Main.CAMINHOINIMIGOS;

/**
 * Inimigo Chefe
 * @author Junior
 */
public final class InimigoChefe extends Inimigo{
    //PARAMETRO DE CONFIGURACAO
    private byte itemId;                                                        //ID DO ITEM PORTADO
    private byte tipo;                                                          //TIPO DO ITEM PORTADO
    private byte quantidade;                                                    //QUANTIDADE
    
    //CONSTRUTOR POR PASSAGEM DE PARAMETRO
    /**
     * Construtor
     * @param nome Nome do inimigo
     * @param forca Força do Inimigo
     * @param inteligencia Inteligencia do inimigo
     * @param agilidade Agilidade do inimigo
     * @param resistencia Resistencia do inimigo
     */
    public InimigoChefe(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia) {
        super(nome, forca, inteligencia, agilidade, resistencia);               //CONSTRUTOR PAI
    }
    
    //CONSTRUTOR
    /**
     * Construtor
     * @param id ID do Chefe
     */
    public InimigoChefe(byte id){
        super(id);                                                              //CONSTRUTOR PAI
        setaItem();
    }
    
    //LE INIMIGO DO ARQUIVO DE CONFIGURACOES
    /**
     * Lê do arquivo e faz as configurações
     */
    private void setaItem(){
        try {
            FileReader arq = new FileReader (CAMINHOINIMIGOS);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < getId()) {
                linha = lerArq.readLine();
                loop++;
            }
            if (linha != null){
                String[] parametros;
                parametros = linha.split("/");                                  //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
                tipo = (byte) Integer.parseInt(parametros[8]);                  //TIPO DO ITEM PORTADO
                if(tipo != 0 && !parametros[9].equals("0"))                     
                    itemId = (byte) Integer.parseInt(parametros[9]);            //ID DO ITEM PORTADO
                else
                    itemId = (byte) 0;                                          //SEM ITEM
                quantidade = (byte) Integer.parseInt(parametros[10]);
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOINIMIGOS);
            e.getMessage();
        }
    }
    
    /**
     * Pega o tipo do item
     * @return byte contendo o tipo
     */
    public byte getItemTipo(){
        return tipo;
    }
    
    /**
     * Pega ID do item
     * @return byte com a ID do item
     */
    public byte getItemId(){
        return itemId;
    }
    
}
