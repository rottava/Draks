/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import habilidade.Habilidade;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import static main.Main.HEROI;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.ALEATORIO;
import static main.Main.CAMINHOINIMIGOS;
import mapa.Porta;
import personagem.InimigoChefe;
import personagem.Inimigo;
import personagem.Persona;

/**
 * Classe responsável pelo Combate
 * @author Junior
 */
public class Combate {
    private boolean fuga = false;                                               //FLAG DE FUGA
    private Inimigo inimigo;                                                    //MAXIMO DE 3 INIMIGOS POR BATALHA
    
    //TURNO DE BATALHA
    /**
     * Combate entre Herói e Inimigo
     * @param porta Porta que está sendo protegida pelo inimigo
     */
    public void combate(Porta porta){
        setaInimigos(porta);
        while(!fuga && inimigo.getVida() > 0 && HEROI.getVida() > 0){
            byte comandoHeroi, habilidadeHeroi; 
            byte comandoInimigo, habilidadeInimigo;
            byte[] auxiliar = new byte[2];
            //auxiliar = getComandoHeroi();
            comandoHeroi = auxiliar[0];
            habilidadeHeroi = auxiliar[1];
            auxiliar = testaComando(inimigo);
            comandoInimigo = (byte) (auxiliar[0]+2);
            habilidadeInimigo = auxiliar[1];
            if(HEROI.getAgilidade() > inimigo.getAgilidade()){
                ataqueHeroi(comandoHeroi, habilidadeHeroi);
                ataqueInimigo(comandoInimigo, habilidadeInimigo);
            }
            else{
                ataqueInimigo(comandoInimigo, habilidadeInimigo);
                ataqueHeroi(comandoHeroi, habilidadeHeroi);
            }      
        }
        if(HEROI.getVida() > 0 && !fuga)
            HEROI.setPontos((byte)(HEROI.getPontos() + 5));
    }
    
    /**
     * Realiza um ataque do Herói
     * @param comando Tipo 1: Dano Especial; 2: Dano; 3: Regenerar Vida
     * @param habilidade Habilidade do Herói
     */
    private void ataqueHeroi(byte comando, byte habilidade){
        switch (comando){
            case 1:
                causarDanoEspecial(HEROI, inimigo, HEROI.getTalentosDano().getHabilidades().get(habilidade));
                break;
            case 2:
                causarDano(HEROI, inimigo);
                break;
            case 3:
                HEROI.regenVida(HEROI.getTalentosCura().getHabilidades().get(habilidade).getEfeito());
                break;
            default:
                if(calculaFuga(HEROI))
                    fuga = true;
                break;
        }
    }
    
    /**
     * Realiza o ataque do Inimigo
     * @param comando Tipo 1: Dano Especial; 2: Dano; 3: Regenerar Vida
     * @param habilidade Habilidade do Inimigo
     */
    private void ataqueInimigo(byte comando, byte habilidade){
        switch (comando){
            case 1:
                causarDanoEspecial(inimigo, HEROI, inimigo.getTalentosDano().getHabilidades().get(habilidade));
                break;
            case 2:
                causarDano(inimigo, HEROI);
                break;
            case 3:
                inimigo.regenVida(inimigo.getTalentosCura().getHabilidades().get(habilidade).getEfeito());
                break;
            default:
                break;
        }
    }
    
    //VERIFICA COMANDO
    /**
     * Verifica comando
     * @param inimigo Inimigo para a verificação
     * @return Vetor com os comandos
     */
    private byte[] testaComando(Inimigo inimigo){
        byte[] comando = new byte[2];
        byte auxiliar;
        if((auxiliar = testaHabilidade(inimigo)) > 0){
            if(inimigo.reduzEnergia( (byte) (inimigo.getEnergia() - 
                (inimigo.getTalentosCura().getHabilidades().get(auxiliar).getConsumo())))){
                comando[0] = 1;
                comando[1] = auxiliar;
            }
                else{
                    comando[0] = 0;
                    comando[1] = 0;
                }
            }
            else 
                if((auxiliar = testaHabilidade(inimigo)) < 0){
                    if(inimigo.reduzEnergia( (byte) (inimigo.getEnergia() - 
                        (inimigo.getTalentosDano().getHabilidades().get(-auxiliar).getConsumo())))){
                    comando[0] = 2;
                    comando[1] = (byte) -auxiliar;
                    }
                    else{
                        comando[0] = 0;
                        comando[1] = 0;
                    }
                }
                else {
                    comando[0] = 0;
                    comando[1] = 0;
                }
        return comando;
    }
    
    
    //VERIFICA HABILIDADE
    /**
     * Verifica a Habilidade do Inimigo
     * @param inimigo Inimigo para a verificação da habilidade
     * @return Habilidade do Inimigo
     */
    private byte testaHabilidade(Inimigo inimigo){
        byte comando, habilidade;
            if (inimigo.getTalentosCura().getTamanho() > 0){
                if(inimigo.getTalentosDano().getTamanho() > 0){
                    comando= (byte) ALEATORIO.nextInt(2);
                    switch (comando) {
                        case 1:
                            habilidade = (byte) ALEATORIO.nextInt(inimigo.getTalentosCura().getTamanho());
                            break;
                        case 2:
                            habilidade = (byte) -(ALEATORIO.nextInt(inimigo.getTalentosDano().getTamanho()));
                            break;
                        default:
                            habilidade = (byte) 0;
                            break;
                    }
                }
                else{
                    comando = (byte) ALEATORIO.nextInt(1);
                    switch (comando) {
                        case 1:
                            habilidade = (byte) ALEATORIO.nextInt(inimigo.getTalentosCura().getTamanho());
                            break;
                        default:
                            habilidade = (byte) 0;
                            break;
                    }
                }
            }else
                habilidade = (byte) 0;
        return habilidade;
    }
        
    //VERIFICA VALIDADE
    /**
     * Verifica a validade de um item
     * @param id ID do item
     * @return true se válido ou false caso contrário
     */
    private boolean testaItem(byte id){
        try {
            FileReader arq = new FileReader (CAMINHOINIMIGOS);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < id) {
                linha = lerArq.readLine();
                loop++;
            }
            if (linha != null){
                String[] parametros;
                linha = lerArq.readLine();
                parametros = linha.split("/");                     //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
                byte tipo = (byte) Integer.parseInt(parametros[8]);             //TIPO DO ITEM PORTADO
                return tipo > 0;
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOINIMIGOS);
            e.getMessage();
        }
        return false;
    }
    
    //DEFININE INIMIGOS NO COMBATE
    /**
     * Define a posição do Inimigo
     * @param porta posição do Inimigo no Mapa
     */
    private void setaInimigos(Porta porta){
        if (porta.getInimigo() != 0)                                            //INIMIGO E VALIDO
            if(testaItem(porta.getInimigo()))                                   //INIMIGO POSSUI ITEM 
                inimigo = new InimigoChefe(porta.getInimigo());                 //SETA SE ID DO ITEM E VALIDO
            else
                inimigo = new Inimigo(porta.getInimigo());                      //SETA SE ID DO ITEM E VALIDO
        else
            inimigo = null;                                                     //INIMIGO INVALIDO
    }

    //REDUZ VIDA DO ALVO E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Realiza o ataque
     * @param atacante Personagem que fará o ataque
     * @param alvo Alvo que será atacado
     * @return Se o ataque foi realizado retorna true, false caso contrário
     */
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
    /**
     * Faz o Cálculo de Ataque
     * @param atacante Personagem que está realizando o ataque
     * @return Retorna o ataque que o personagem realizará
     */
    private int calcularAtaque(Persona atacante){
        int ataque;
        ataque = (byte) (atacante.getForca() / 2 + atacante.getInteligencia() / 5); //FORCA / 2 + INTELIGENCIA / 5
        ataque += (byte) (atacante.getArma().getEfeito());                      //EFEITO DE ARMADURA;
        ataque += (byte) (ataque * (atacante.getSorte() / 100));                //DEFESA DE SORTE EM %
        return ataque;
    }
    
    //CALCULO DE DEFESA = RESISTENCIA / 2 + AGILIDADE / 5 + EFEITO ARMADURA + DEFESA%SORTE
    /**
     * Faz o cálculo da defesa
     * @param alvo Personagem que está sendo atacado
     * @return Retorna a defesa do personagem atacado
     */
    private int calcularDefesa(Persona alvo){
        int defesa;
        defesa = (byte) (alvo.getResistencia() / 2 + alvo.getAgilidade() / 5);  //RESISTENCIA / 2 + VELOCIDADE / 5
        defesa += (byte) (alvo.getArmadura().getEfeito());                      //EFEITO DE ARMADURA;
        defesa += (byte) (defesa * (alvo.getSorte() / 100));                    //DEFESA DE SORTE EM %
        return defesa;
    }
    
    //CALCULO DE EVASAO
    /**
     * Cálculo da evasão de um personagem
     * @param persona Personagem para o cálculo da evasão
     * @return Evasão do Personagem
     */
    private int calcularEvasao(Persona persona){
        int evasao;
        evasao = (persona.getAgilidade() + persona.getInteligencia() / 2);      //AGILIDADE + INTELIGENCIA / 2
        evasao += (byte) (evasao * persona.getSorte() / 100);                   //EVASAO DE SORTE EM %
        evasao += persona.getSorte() + ALEATORIO.nextInt(persona.getAgilidade());//SORTE + VALOR ALEATORIO ATE AGILIDADE
        return evasao;
    }
    
    //REDUZ VIDA DO ALVO E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Reduz vida do alvo
     * @param atacante Personagem que está realizando o ataque
     * @param alvo Personagem que está sendo atacado
     * @param habilidade Habilidade utilizada no ataque
     * @return true se o ataque ocorreu ou false caso contrário
     */
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
    /**
     * Calculo do Ataque especial
     * @param atacante Personagem que está realizando o ataque
     * @param habilidade Habilidade utilizada pelo personagem
     * @return valor do ataque
     */
    private int calcularAtaqueEspecial(Persona atacante, Habilidade habilidade){
        int ataque;
        ataque = (byte) (atacante.getInteligencia() / 2 + atacante.getForca() / 5); //INTELIGENCIA / 2 + FORCA / 5
        ataque += (byte) (habilidade.getEfeito());                              //EFEITO DE HABILIDADE;
        ataque += (byte) (ataque * (atacante.getSorte() / 100));                //EFEITO DE SORTE EM %
        return ataque;
    }
    
    //CALCULO DE DEFESA = RESISTENCIA / 2 + AGILIDADE / 5 + EFEITO ARMADURA + DEFESA%SORTE
    /**
     * Calculo de Defesa Especial
     * @param alvo Personagem que está sendo atacado
     * @return defesa do personagem
     */
    private int calcularDefesaEspecial(Persona alvo){
        int defesa;
        defesa = (byte) (alvo.getResistencia() / 3 + alvo.getAgilidade() / 3);  //RESISTENCIA / 3 + VELOCIDADE / 3
        defesa += (byte) (alvo.getArmadura().getEfeito());                      //EFEITO DE ARMADURA / 10;
        defesa += (byte) (defesa * (alvo.getSorte() / 100));                    //DEFESA DE SORTE EM %
        return defesa;
    }
    
    //CALCULO DE FUGA
    /**
     * Cálculo da fuga dos personagens
     * @param persona Personagem para o cálculo
     * @return valor da Fuga
     */
    private boolean calculaFuga(Persona persona){
        int agilidade = persona.getAgilidade() + persona.getSorte();
        fuga = agilidade > ALEATORIO.nextInt(TAMANHOMAXIMO);
        return fuga;
    }
}
