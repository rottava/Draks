/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Talentos {
    //PARAMETRO VARIAVEL
    private final List<Habilidade> talentos;
    
    //CONSTRUTOR VAZIO
    public Talentos(){
        talentos = new ArrayList<>();
    }

    ///GETTERS AND SETTERS
    public int getTamanho(){
        return talentos.size();
    }
    
    public List<Habilidade> getHabilidades(){
        return talentos;
    }
    
    //ADICIONA HABILIDADE EM TALENTOS
    public boolean addTalentos(Habilidade habilidade) {
        byte loop = 0;
        while ((talentos.get(loop).getId() != habilidade.getId()) && loop < talentos.size())//PROCURA HABILIDADE NA LISTA
            loop++;
        if (talentos.get(loop).getId() == habilidade.getId())                   //VERIFICA SE HABILIDADE JA EXISTE NA LISTA
            return false;
        else{
            talentos.add(habilidade);                                           //ADICIONA HABILIDADE
            return true;
        }
    }
    
}
