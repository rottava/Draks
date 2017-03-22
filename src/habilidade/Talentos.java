/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Talentos
 * @author Junior
 */
public class Talentos implements Serializable{
    //PARAMETRO VARIAVEL
    private final List<Habilidade> talentos;
    
    //CONSTRUTOR VAZIO
    /**
     * Construtor
     */
    public Talentos(){
        talentos = new ArrayList<>();
    }

    ///GETTERS AND SETTERS
    /**
     * Tamanho da lista de Talentos
     * @return tamanho da Lista de Talentos
     */
    public byte getTamanho(){
        return (byte) talentos.size();
    }
    
    /**
     * Pega as habilidades
     * @return Lista com todas as Habilidades
     */
    public List<Habilidade> getHabilidades(){
        return talentos;
    }
    
    //ADICIONA HABILIDADE EM TALENTOS
    /**
     * Adicionar habilidade em talentos
     * @param habilidade Habilidade à ser adicionada
     * @return false se a habilidade já existir, true se foi adicionada com sucesso
     */
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
