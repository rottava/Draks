/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static main.Main.CAMINHOARMADURAS;

/**
 * Armadura
 * @author Junior
 */
public class Armadura extends Item{
    
    /**
     * Construtor
     * @param id ID da armadura
     */
    public Armadura(byte id){
        setParam(id);
    }
    
    //CONFIGURA OBJETO ITEM APARTIR DE ARQUIVO
    /**
     * Configura objeto item a partir do arquivo
     * @param id ID da Armadura
     */
    private void setParam(byte id){
        setId(id);
        try {
            FileReader arq = new FileReader (CAMINHOARMADURAS);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < id) {
                linha = lerArq.readLine();
                loop++;
            }
            if (linha != null){
                String[] parametros;                        
                parametros = linha.split("/");                     //DIVIDE A LINHA EM NOME/EFEITO/PESO
                setNome(parametros[0]);                                         //NOME
                setEfeito((byte) Integer.parseInt(parametros[1]));              //EFEITO
                setPeso((byte) Integer.parseInt(parametros[2]));                //PESO
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOARMADURAS);
            e.getMessage();
        }
    }
    
}
