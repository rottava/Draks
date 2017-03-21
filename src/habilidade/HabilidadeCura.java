/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.io.FileNotFoundException;
import java.util.Scanner;
import static main.Main.HABILIDADESCURA;
import static main.Main.TAMANHOHABILIDADESCURA;

/**
 * Habilidade de Cura
 * @author Junior
 */
public class HabilidadeCura extends Habilidade{
    
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
            scanner = new Scanner(HABILIDADESCURA);
            while(loop < TAMANHOHABILIDADESCURA && (loop < id)){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if (loop == id && loop <= TAMANHOHABILIDADESCURA){
                String[] parametros;                        
                parametros = scanner.nextLine().split("/");                     //DIVIDE A LINHA EM NOME/EFEITO/CONSUMO
                setNome(parametros[0]);                                         //NOME
                setEfeito((byte) Integer.parseInt(parametros[1]));              //EFEITO
                setConsumo((byte) Integer.parseInt(parametros[2]));             //CONSUMO
            }
            else
                throw new UnsupportedOperationException("ID de habilidade não encontrado.");
        } 
        catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("Arquivo HabilidadeCura.txt não foi encontrado.");
        }
    }
    
}
