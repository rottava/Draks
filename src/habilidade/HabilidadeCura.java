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
import static main.Main.CAMINHOHABILIDADESCURA;
import static main.Main.CAMINHOINIMIGOS;
import static main.Main.TAMANHOHABILIDADESCURA;

/**
 * Habilidade de Cura
 * @author Junior
 */
public class HabilidadeCura extends Habilidade implements Serializable{
    
    /**
     * Construtor da Habilidade de Cura
     * @param id ID da Habilidade
     */
    public HabilidadeCura(byte id){
        setParam(id);
    }
    
    //CONFIGURA HABILDIADE APARTIR DE ARQUIVO
    /**
     * Configura Habilidade a partir de arquivo
     * @param id ID da Habilidade
     */
    private void setParam(byte id){
        setId(id);
        Scanner scanner;
        byte loop = 1;
        try {
            FileReader arq = new FileReader (CAMINHOHABILIDADESCURA);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            while (loop < TAMANHOHABILIDADESCURA && (loop < id)) {
                linha = lerArq.readLine();
                loop++;
            }
            if (loop == id && loop <= TAMANHOHABILIDADESCURA){
                String[] parametros;                        
                parametros = linha.split("/");                     //DIVIDE A LINHA EM NOME/EFEITO/CONSUMO
                setNome(parametros[0]);                                         //NOME
                setEfeito((byte) Integer.parseInt(parametros[1]));              //EFEITO
                setConsumo((byte) Integer.parseInt(parametros[2]));             //CONSUMO
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOHABILIDADESCURA);
            e.getMessage();
        }
    }
    
}
