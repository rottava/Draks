/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import static main.Main.CAMINHOHABILIDADESDANO;
import static main.Main.CAMINHOINIMIGOS;
import static main.Main.TAMANHOHABILIDADESDANO;

/**
 * Habilidade de Dano
 * @author Junior
 */
public class HabilidadeDano extends Habilidade implements Serializable{
    
    /**
     * Construtor da Habilidade de Dano
     * @param id ID da Habilidade
     */
    public HabilidadeDano(byte id){
        setParam(id);
    }
    
    //CONFIGURA HABILDIADE APARTIR DE ARQUIVO
    /**
     * Configura a habilidade a partir do arquivo
     * @param id ID da habilidade
     */
    private void setParam(byte id){
        setId(id);
        try {
            FileReader arq = new FileReader (CAMINHOHABILIDADESDANO);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < id) {
                linha = lerArq.readLine();
                loop++;
            }
            if (linha != null){
                String[] parametros;                        
                parametros = linha.split("/");                                  //DIVIDE A LINHA EM NOME/EFEITO/CONSUMO
                setNome(parametros[0]);                                         //NOME
                setEfeito((byte) Integer.parseInt(parametros[1]));              //EFEITO
                setConsumo((byte) Integer.parseInt(parametros[2]));             //CONSUMO
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOHABILIDADESDANO);
            e.getMessage();
        }
    }
    
}
