/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.io.FileNotFoundException;
import java.util.Scanner;
import static main.Main.ARMAS;
import static main.Main.TAMANHOARMAS;

/**
 * Arma
 * @author Junior
 */
public class Arma extends Item{
    
    /**
     * Construtor Arma
     * @param id ID da Arma
     */
    public Arma(byte id){
        setParam(id);
    }
    
    //CONFIGURA OBJETO ITEM APARTIR DE ARQUIVO
    /**
     * Configura objeto item a partir do arquivo
     * @param id ID da arma
     */
    private void setParam(byte id){
        setId(id);
        Scanner scanner;
        byte loop = 1;
        try {
            scanner = new Scanner(ARMAS);
            while(loop < TAMANHOARMAS && (loop < id)){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if (loop == id && loop <= TAMANHOARMAS){
                String[] parametros;                        
                parametros = scanner.nextLine().split("/");                     //DIVIDE A LINHA EM NOME/EFEITO/PESO
                setNome(parametros[0]);                                         //NOME
                setEfeito((byte) Integer.parseInt(parametros[1]));              //EFEITO
                setPeso((byte) Integer.parseInt(parametros[2]));                //PESO
            }
            else
                throw new UnsupportedOperationException("ID de habilidade não encontrado.");
        } 
        catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("Arquivo itens.txt não foi encontrado.");
        }
    }
    
}
