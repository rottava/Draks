/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.io.FileNotFoundException;
import java.util.Scanner;
import static main.Main.HABILIDADESDANO;
import static main.Main.TAMANHOHABILIDADESDANO;

/**
 *
 * @author Junior
 */
public class HabilidadeDano extends Habilidade{
    
    public HabilidadeDano(byte id){
        setParam(id);
    }
    
    //CONFIGURA HABILDIADE APARTIR DE ARQUIVO
    private void setParam(byte id){
        setId(id);
        Scanner scanner;
        byte loop = 1;
        try {
            scanner = new Scanner(HABILIDADESDANO);
            while(loop < TAMANHOHABILIDADESDANO && (loop < id)){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if (loop == id && loop <= TAMANHOHABILIDADESDANO){
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
            throw new UnsupportedOperationException("Arquivo itens.txt não foi encontrado.");
        }
    }
    
}
