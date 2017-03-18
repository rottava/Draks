/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import habilidade.Habilidade;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.aleatorio;
import personagem.Heroi;
import personagem.InimigoComum;
import personagem.Persona;

/**
 *
 * @author Junior
 */
public class Combate {
    private boolean fuga = false;                                               //FLAG DE FUGA
    private InimigoComum inimigo1, inimigo2, inimigo3;                               //MAXIMO DE 3 INIMIGOS POR BATALHA
    private Heroi heroi;                                                        //
    
    //TURNO DE BATALHA
    public void combate(Heroi heroi, byte[] sala){
        
    }
    
    //DEFININE INIMIGOS NO COMBATE
    private void setaInimigo(byte[] sala){
        if (sala[2] == 0)
            inimigo1 = null;
        else
            inimigo1 = new InimigoComum(sala[2]);
        if (sala[3] == 0)
            inimigo2 = null;
        else
            inimigo2 = new InimigoComum(sala[2]);
        if (sala[4] == 0)
            inimigo3 = null;
        else
            inimigo3 = new InimigoComum(sala[2]);
    }
    
    //REDUZ VIDA DO ALVO E RETORNA VERDADEIRO OU RETORNA FALSO
    private boolean causarDano(Persona atacante, Persona alvo){
        if (calcularEvasao(alvo) > calcularEvasao(atacante))                    //VERIFICA SE EVADIU ATAQUE
            return false;
        else{
            int ataque = calcularAtaque(atacante);                              //CALCULA ATAQUE
            int defesa = calcularDefesa(alvo);                                  //CALCULA DEFESA
            alvo.reduzVida((byte) (ataque - defesa));                           //REDUZ VIDA EM ATAQUE - DEFESA
            return true;
        }
    }
    
    //CALCULO DE ATAQUE = FORCA / 2 + INTELIGENCIA / 5 + EFEITO ARM) + ATAQUE%SORTE
    private int calcularAtaque(Persona atacante){
        int ataque;
        ataque = (byte) (atacante.getForca() / 2 + atacante.getInteligencia() / 5); //FORCA / 2 + INTELIGENCIA / 5
        ataque += (byte) (atacante.getArma().getEfeito());                      //EFEITO DE ARMADURA;
        ataque += (byte) (ataque * (atacante.getSorte() / 100));                //DEFESA DE SORTE EM %
        return ataque;
    }
    
    //CALCULO DE DEFESA = RESISTENCIA / 2 + AGILIDADE / 5 + EFEITO ARMADURA + DEFESA%SORTE
    private int calcularDefesa(Persona alvo){
        int defesa;
        defesa = (byte) (alvo.getResistencia() / 2 + alvo.getAgilidade() / 5);  //RESISTENCIA / 2 + VELOCIDADE / 5
        defesa += (byte) (alvo.getArmadura().getEfeito());                      //EFEITO DE ARMADURA;
        defesa += (byte) (defesa * (alvo.getSorte() / 100));                    //DEFESA DE SORTE EM %
        return defesa;
    }
    
    //CALCULO DE EVASAO
    private int calcularEvasao(Persona persona){
        int evasao;
        evasao = (persona.getAgilidade() + persona.getInteligencia() / 2);      //AGILIDADE + INTELIGENCIA / 2
        evasao += (byte) (evasao * persona.getSorte() / 100);                   //EVASAO DE SORTE EM %
        evasao += persona.getSorte() + aleatorio.nextInt(persona.getAgilidade());//SORTE + VALOR ALEATORIO ATE AGILIDADE
        return evasao;
    }
    
    //REDUZ VIDA DO ALVO E RETORNA VERDADEIRO OU RETORNA FALSO
    private boolean causarDanoEspecial(Persona atacante, Persona alvo, Habilidade habilidade){
        if ((calcularEvasao(alvo) * 1.2) > calcularEvasao(atacante))            //VERIFICA SE EVADIU ATAQUE
            return false;
        else{
            int ataque = calcularAtaqueEspecial(atacante, habilidade);          //CALCULA ATAQUE
            int defesa = calcularDefesaEspecial(alvo);                          //CALCULA DEFESA
            alvo.reduzVida((byte) (ataque - defesa));                           //REDUZ VIDA EM ATAQUE - DEFESA
            return true;
        }
    }
    
    //CALCULO DE ATAQUE = INTELIGENCIA / 2 + FORCA / 5 + EFEITO HABILIDADE + ATAQUE%SORTE
    private int calcularAtaqueEspecial(Persona atacante, Habilidade habilidade){
        int ataque;
        ataque = (byte) (atacante.getInteligencia() / 2 + atacante.getForca() / 5); //INTELIGENCIA / 2 + FORCA / 5
        ataque += (byte) (habilidade.getEfeito());                              //EFEITO DE HABILIDADE;
        ataque += (byte) (ataque * (atacante.getSorte() / 100));                //EFEITO DE SORTE EM %
        return ataque;
    }
    
    //CALCULO DE DEFESA = RESISTENCIA / 2 + AGILIDADE / 5 + EFEITO ARMADURA + DEFESA%SORTE
    private int calcularDefesaEspecial(Persona alvo){
        int defesa;
        defesa = (byte) (alvo.getResistencia() / 3 + alvo.getAgilidade() / 3);  //RESISTENCIA / 3 + VELOCIDADE / 3
        defesa += (byte) (alvo.getArmadura().getEfeito());                      //EFEITO DE ARMADURA / 10;
        defesa += (byte) (defesa * (alvo.getSorte() / 100));                    //DEFESA DE SORTE EM %
        return defesa;
    }
    
    //CALCULO DE FUGA
    private void calculaFuga(Persona persona){
        int agilidade = persona.getAgilidade() + persona.getSorte();
        fuga = agilidade > aleatorio.nextInt(TAMANHOMAXIMO);
    }
    
}
