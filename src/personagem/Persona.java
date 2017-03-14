/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import habilidade.Habilidade;
import habilidade.Talentos;
import item.Item;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.aleatorio;

/**
 *
 * @author Junior
 */
public abstract class Persona {
    //PARAMETROS DE SISTEMA
    private String nome;                                                        //NOME DO PERSONAGEM
    
    //PARAMETROS VARIAVEIS
    private byte vida;                                                          //VIDA
    private byte energia;                                                       //ENERGIA
    private Item arma;                                                          //EQUIPAMENTO DE ATAQUE
    private Item armadura;                                                      //EQUIPAMENTO DE DEFESA
    private final Talentos talentos;                                            //TALENTOS DO PERSONAGEM
    
    //PARAMETROS DEPENDENTES
    private byte vidaMax;                                                       //RESISTENCIA + (FORÇA / 2)    
    private byte energiaMax;                                                    //RESISTENCIA + (INTELIGENCIA / 2)
    
    //PARAMETROS INDEPENDENTES
    private byte forca;                                                         //DEFINE ATAQUE
    private byte inteligencia;                                                  //DEFINE HABILIDADE
    private byte agilidade;                                                     //DEFINE ORDEM E EVASÃO
    private byte resistencia;                                                   //DEFINE DEFESA
    
    //PARAMETROS RANDOMICOS
    private final byte sorte;                                                   //DEFINE %BONUS
    
    //CONSTRUTOR POR PARAMETRO
    Persona(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia){
        this.nome = nome;                                                       //PARAMETRO NOME
        this.forca = forca;                                                     //PARAMETRO FORCA
        this.inteligencia = inteligencia;                                       //PARAMETRO INTELIGENCIA
        this.agilidade = agilidade;                                             //PARAMETRO VELOCIDADE
        this.resistencia = resistencia;                                         //PARAMETRO RESISTENCIA
        sorte = (byte) aleatorio.nextInt(10);                                   //PARAMETRO SORTE
        geraVidaMax();                                                          //PARAMETRO VIDA MAXIMA INICIAL
        this.vida = vidaMax;                                                    //PARAMETRO VIDA
        geraEnergiaMax();                                                       //PARAMETRO ENERGIA MAXIMA INICIAL
        this.energia = energiaMax;                                              //PARAMETRO ENERGIA
        this.talentos = new Talentos();                                         //LISTA DE TALENTOS
    }
    
    //CONSTRUTOR VAZIO
    Persona(){
        sorte = (byte) aleatorio.nextInt(10);                                   //PARAMETRO SORTE
        talentos = new Talentos();                                              //LISTA DE TALENTOS
    }
    
    //GETTERS AND SETTERS
    //RETORNA NOME
    public String getNome(){
        return nome;
    }
    
    //SETA NOME PARA 
    protected void setNome(String nome){
        this.nome = nome;
    }
    
    //RETORNA VIDA
    public byte getVida(){
        return vida;
    }
    
    //SETA VIDA E RETORNA VERDADEIRO OU RETORNA FALSO
    private boolean setVida(byte vida){
        if (vida != vidaMax && vida > 0){
            if (vida < vidaMax)
                this.vida = vida;
            else
                this.vida = vidaMax;
            return true;
        }
        else
            return false;
    }
    
    //RETONA VIDA MAXIMA
    public byte getVidaMax(){
        return vidaMax;
    }
    
    //SETA VIDA MAXIMA E RETORNA VERDADEIRO OU RETORNA FALSO
    private void setVidaMax(byte vidaMax){
        if (vidaMax < TAMANHOMAXIMO)
            this.vidaMax = vidaMax;
        else 
            this.vidaMax = TAMANHOMAXIMO;
    }
    
    //RETORNA ENERGIA
    public byte getEnergia(){
        return energia;
    }

    //SETA ENERGIA E RETORNA VERDADEIRO OU RETORNA FALSO
    private boolean setEnergia(byte energia){
        if (energia != energiaMax && energia > 0){
            if (energia < energiaMax)
                this.energia = energia;
            else
                this.energia = energiaMax;
            return true;
        }
        else
            return false;
    }
    
    //RETORNA ENERGIA MAXIMA
    public byte getEnergiaMax(){
        return energiaMax;
    }

    //SETA ENERGIA MAXIMA E RETORNA VERDADEIRO OU RETORNA FALSO
    private void setEnergiaMax(byte energiaMax){
        if (energiaMax < TAMANHOMAXIMO)
            this.energiaMax = energiaMax;
        else
            this.energiaMax = TAMANHOMAXIMO;
    }
    
    //RETONA ARMA EQUIPADA
    public Item getArma(){
        return arma;
    }
    
    //SETA ARMA EQUIPADA PARA
    protected void setArma(Item arma){
        this.arma = arma;
    }
    
    //RETONAR ARMADURA EQUIPADA
    public Item getArmadura(){
        return armadura;
    }
    
    //SETA ARMADURA EQUIPADA PARA
    protected void setArmadura(Item armadura){
        this.armadura = armadura;
    }
    
    //RETORNA FORCA
    public byte getForca(){
        return forca;
    }
    
    //SETA FORÇA PARA
    protected void setForca(byte forca){
        if (forca < TAMANHOMAXIMO)
            this.forca = forca;
        else
            this.forca = TAMANHOMAXIMO;
    }
    
    //RETORNA INTELIGENCIA
    public byte getInteligencia(){
        return inteligencia;
    }
    
    //SETA INTELIGENCIA PARA
    protected void setInteligencia(byte inteligencia){
        if (inteligencia < TAMANHOMAXIMO)
            this.inteligencia = inteligencia;
        else
            this.inteligencia = TAMANHOMAXIMO;
    }
    
    //RETORNA AGILIDADE
    public byte getAgilidade(){
        return agilidade;
    }
    
    //SETA AGILIDADE PARA
    protected void setAgilidade(byte agilidade){
        if (agilidade < TAMANHOMAXIMO)
            this.agilidade = agilidade;
        else
            this.agilidade = TAMANHOMAXIMO;
    }
    
    //RETORNA RESISTENCIA
    public byte getResistencia(){
        return resistencia;
    }
    
    //SETA RESISTENCIA PARA
    protected void setResistencia(byte resistencia){
        if (resistencia < TAMANHOMAXIMO)
            this.resistencia = resistencia;
        else
            this.resistencia = TAMANHOMAXIMO;
    }

    //RETORNA SORTE
    public byte getSorte(){
        return sorte;
    }
    
    //GERA VIDA MAXIMA
    protected final void geraVidaMax(){
        setVidaMax((byte) (resistencia + (forca / 2)));
    }
    
    //GERA ENERGIA MAXIMA
    protected final void geraEnergiaMax(){
        setEnergiaMax((byte) (resistencia  + (inteligencia / 2)));
    }
    
    //REGENERA VIDA
    public boolean regenVIDA(byte regen){
        return setVida((byte) (getVida() + regen));
    }
    
    //REDUZ VIDA
    public boolean reduzVida(byte damage){
        return setVida((byte) (getVida() - damage));
    }
    
    //REGENERA ENERGIA
    public boolean regenEnergia(byte regen){
        return setEnergia((byte) (getEnergia() + regen));
    }
    
    //REDUZ ENERGIA
    public boolean reduzEnergia(byte damage){
        return setVida((byte) (getVida() - damage));
    }
    
    //RETORNA LISTA DE HABILIDADES
    public Talentos getTalentos(){
        return talentos;
    }
    
    //ADICIONA HABILIDADE A LISTA DE HABILIDADES
    public boolean addTalentos(Habilidade habilidade){
        return (talentos.addTalentos(habilidade));
    }
}
