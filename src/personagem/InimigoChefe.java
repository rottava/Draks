/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import java.io.FileNotFoundException;
import java.util.Scanner;
import static main.Main.INIMIGOS;

/**
 *
 * @author Junior
 */
public final class InimigoChefe extends Inimigo{
    //PARAMETRO DE CONFIGURACAO
    private byte itemId;                                                        //ID DO ITEM PORTADO
    private byte tipo;                                                          //TIPO DO ITEM PORTADO
    
    //CONSTRUTOR POR PASSAGEM DE PARAMETRO
    public InimigoChefe(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia) {
        super(nome, forca, inteligencia, agilidade, resistencia);               //CONSTRUTOR PAI
    }
    
    //CONSTRUTOR
    public InimigoChefe(byte id){
        super(id);                                                              //CONSTRUTOR PAI
        setaItem();
    }
    
    //LE INIMIGO DO ARQUIVO DE CONFIGURACOES
    private void setaItem(){
        try {
            Scanner scanner = new Scanner(INIMIGOS);
            byte loop = 1;
            while((scanner.hasNext()) && (loop < getId())){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if (scanner.hasNext()){
                String[] parametros;
                parametros = scanner.nextLine().split("/");                     //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
                tipo = (byte) Integer.parseInt(parametros[8]);                  //TIPO DO ITEM PORTADO
                if(tipo != 0 && !parametros[9].equals("0"))                     
                    itemId = (byte) Integer.parseInt(parametros[9]);            //ID DO ITEM PORTADO
                else
                    itemId = (byte) Integer.parseInt(parametros[9]);            //SEM ITEM
            }
        } catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("ID de inimigo não encontrado.");
        }
    }
    
}
