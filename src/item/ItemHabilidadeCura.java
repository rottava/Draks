/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.io.FileNotFoundException;
import java.util.Scanner;
import static main.Main.HABILIDADESCURA;
import static main.Main.TAMANHOHABILIDADESCURA;

/**
 * Item Habilidade de Cura
 * @author Junior
 */
public class ItemHabilidadeCura extends Item{
    
    /**
     * Construto
     * @param id ID da Habilidade de Cura 
     */
    public ItemHabilidadeCura(byte id){
        setParam(id);
    }
    
    //CONFIGURA OBJETO ITEM APARTIR DE ARQUIVO
    /**
     * Configura objeto item a partir do arquivo
     * @param id id do ítem
     */
    private void setParam(byte id){
        setId(id);
        Scanner scanner;
        byte loop = 1;
        try {
            scanner = new Scanner(HABILIDADESCURA);
            while((loop < TAMANHOHABILIDADESCURA) && (loop < id)){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if ((loop == id) && (loop <= TAMANHOHABILIDADESCURA)){
                String[] parametros;                        
                parametros = scanner.nextLine().split("/");                     //DIVIDE A LINHA EM NOME/EFEITO/PESO
                setNome("Magia" + parametros[0]);                               //NOME
                setEfeito((byte) 0);                                            //EFEITO
                setPeso((byte) 1);                                              //PESO
            }
            else
                throw new UnsupportedOperationException("ID de habilidade não encontrado.");
        } 
        catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("Arquivo habilidadescura.txt não foi encontrado.");
        }
    }
    
}
