/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import habilidade.Habilidade;
import java.util.ArrayList;
import java.util.Scanner;
import static main.Main.aleatorio;
import static main.Main.ARQUIVONOMES;
import static main.Main.TAMANHOHABILIDADE;
import static main.Main.aleatorio;

/**
 *
 * @author Junior
 */
public final class Inimigo extends Persona{
    //PARAMETRO DE CONFIGURACAO
    private byte id;                                                //0 = FRACO, 1 = MEDIO, 2 = FORTE, 3 = SUBCHEFE, 4 = CHEFE, 5 = CHEFE FINAL, OTHERS = RND
    private int pontos;                                             //PONTOS PARA PARAMETROS
    
    //CONSTRUTOR POR PASSAGEM DE PARAMETRO
    public Inimigo(String nome, byte forca, byte inteligencia, byte velocidade, byte resistencia) {
        super(nome, forca, inteligencia, velocidade, resistencia);
    }
    
    //CONSTRUTOR ALEATORIO
    public Inimigo(byte id){
        super();
        randomizer(id);
    }
    
    //RANDOMIZER
    private void randomizer(byte id){
        this.id = id;
        geraPontos();
        setNome(geraNome());
        byte forca = 1;
        byte inteligencia = 1;
        byte velocidade = 1;
        byte resistencia = 1;
        do{
            forca += geraParametro(forca);
            inteligencia += geraParametro(inteligencia);
            velocidade += geraParametro(velocidade);
            resistencia += geraParametro(resistencia);
        }
        while(pontos > 0);
        setForca(forca);
        setInteligencia(inteligencia);
        setVelocidade(velocidade);
        setResistencia(resistencia);
        geraVidaMax();
        geraEnergiaMax();
        
        
    }
    
    //GERA NOME APARTIR DE ARQUIVO
    private String geraNome(){
        String resultado = null;
        int contador = 0;
        for(Scanner scanner = new Scanner(ARQUIVONOMES); scanner.hasNext(); contador++){
           String linha = scanner.nextLine();
           if(aleatorio.nextInt(contador) == 0)
              resultado = linha;
        }
        return resultado;
    }
    
    //GERA PARAMETRO NO INTERVALO DE 1 A 255
    private byte geraParametro(byte parametro){
        byte resultado;
        byte auxiliar;
        if( pontos <= (Byte.SIZE - parametro) ){
            auxiliar = (byte) aleatorio.nextInt(pontos);
            resultado = (byte) (auxiliar + parametro);
        }
        else{
            auxiliar = (byte) aleatorio.nextInt(Byte.SIZE - parametro);
            resultado = (byte) (auxiliar + parametro);
        }
        pontos -= auxiliar;
        return resultado;
    }
    
    //GERA PONTOS TOTAL DEPENDENDO DO ID
    private void geraPontos(){
        switch (id){
            case 0:
                pontos = (byte) (aleatorio.nextInt(50)+50);
                break;
            case 1:
                pontos = (byte) (aleatorio.nextInt(50)+100);
                break;
            case 2:
                pontos = (byte) (aleatorio.nextInt(50)+150);
                break;
            case 3:
                pontos = (byte) (aleatorio.nextInt(50)+200);
                break;
            case 4:
                pontos = (byte) (aleatorio.nextInt(50)+250);
                break;
            case 5:
                pontos = (byte) (aleatorio.nextInt(50)+300);
                break;
           default:
                pontos = (byte) (aleatorio.nextInt(Byte.SIZE * 4));
                break;
        }
    }
    
    //CONSTRUTOR RANDOM
    public void geraTalentos(byte id){
        if(TAMANHOHABILIDADE > 0){
            byte quantidade;
            if(TAMANHOHABILIDADE > id)
                quantidade = (byte) (aleatorio.nextInt(id)+1);
            else
                quantidade = (byte) (aleatorio.nextInt(TAMANHOHABILIDADE-1)+1);
            Habilidade habilidade;
            do{
                habilidade = new Habilidade((byte) aleatorio.nextInt(TAMANHOHABILIDADE));
                boolean auxiliar = false;
                for(int contador = 0; contador < getTalentos().getTamanho(); contador++){
                    if(getTalentos().getHabilidades().get(quantidade).getId() == habilidade.getId())
                        auxiliar = true;
                }
                if(!auxiliar){
                    addTalentos(habilidade);
                    quantidade--;
                }
            }
            while (quantidade > 0);
        }
    }
    
}
