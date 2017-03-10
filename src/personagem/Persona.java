/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import item.Item;
import static main.Main.aleatorio;

/**
 *
 * @author Junior
 */
public abstract class Persona {
    //PARAMETROS DE SISTEMA
    private String nome;  //NOME DO PERSONAGEM
    
    //PARAMETROS VARIAVEIS
    private byte vida;          //VIDA
    private byte energia;       //ENERGIA
    private Item arma;          //EQUIPAMENTO DE ATAQUE
    private Item armadura;      //EQUIPAMENTO DE DEFESA
    
    //PARAMETROS DEPENDENTES
    private byte vidaMax;       //RESISTENCIA + (FORÇA / 2)    
    private byte energiaMax;    //RESISTENCIA + (INTELIGENCIA / 2)
    
    //PARAMETROS INDEPENDENTES
    private byte forca;         //DEFINE ATAQUE
    private byte inteligencia;  //DEFINE HABILIDADE
    private byte velocidade;    //DEFINE ORDEM E EVASÃO
    private byte resistencia;   //DEFINE DEFESA
    
    //PARAMETROS RANDOMICOS
    private final byte sorte;   //DEFINE %BONUS
    
    //CONSTRUTOR POR PARAMETRO
    Persona(String nome, byte forca, byte inteligencia, byte velocidade, byte resistencia){
        this.nome = nome;                           //PARAMETRO NOME
        this.forca = forca;                         //PARAMETRO FORCA
        this.inteligencia = inteligencia;           //PARAMETRO INTELIGENCIA
        this.velocidade = velocidade;               //PARAMETRO VELOCIDADE
        this.resistencia = resistencia;             //PARAMETRO RESISTENCIA
        sorte = (byte) aleatorio.nextInt(10);       //PARAMETRO SORTE
        geraVidaMax();                              //PARAMETRO VIDA MAXIMA INICIAL
        this.vida = vidaMax;                        //PARAMETRO VIDA
        geraEnergiaMax();                           //PARAMETRO ENERGIA MAXIMA INICIAL
        this.energia = energiaMax;                  //PARAMETRO ENERGIA
    }
    
    //CONSTRUTOR VAZIO
    Persona(){
        sorte = (byte) aleatorio.nextInt(10);       //PARAMETRO SORTE
    }
    
    //GETTERS AND SETTERS
    public String getNome(){
        return nome;
    }
    
    protected void setNome(String nome){
        this.nome = nome;
    }
    
    public byte getVida(){
        return vida;
    }
    
    private boolean setVida(byte vida){
        if (vida != vidaMax){
            if (vida < vidaMax)
                this.vida = vida;
            else
                this.vida = vidaMax;
            return true;
        }
        else 
            return false; 
    }
    
    public byte getVidaMax(){
        return vidaMax;
    }
    
    private void setVidaMax(byte vidaMax){
        if (vidaMax < Byte.SIZE)
            this.vidaMax = vidaMax;
        else 
            this.vidaMax = Byte.SIZE;
    }
    
    public byte getEnergia(){
        return energia;
    }

    private boolean setEnergia(byte energia){
        if (energia != energiaMax){
            if (energia < energiaMax)
                this.energia = energia;
            else
                this.energia = energiaMax;
            return true;
        }
        else
            return false;
    }
    
    public byte getEnergiaMax(){
        return energiaMax;
    }

    private void setEnergiaMax(byte energiaMax){
        if (energiaMax < Byte.SIZE)
            this.energiaMax = energiaMax;
        else
            this.energiaMax = Byte.SIZE;
    }
    
    public Item getArma(){
        return arma;
    }
    
    protected void setArma(Item arma){
        this.arma = arma;
    }
    
    public Item getArmadura(){
        return armadura;
    }
    
    protected void setArmadura(Item armadura){
        this.armadura = armadura;
    }
    
    public byte getForca(){
        return forca;
    }
    
    public void setForca(byte forca){
        if (forca < Byte.SIZE)
            this.forca = forca;
        else
            this.forca = Byte.SIZE;
    }
    
    public byte getInteligencia(){
        return inteligencia;
    }
    
    public void setInteligencia(byte inteligencia){
        if (inteligencia < Byte.SIZE)
            this.inteligencia = inteligencia;
        else
            this.inteligencia = Byte.SIZE;
    }
    
    public byte getVelocidade(){
        return velocidade;
    }
    
    public void setVelocidade(byte velocidade){
        if (velocidade < Byte.SIZE)
            this.velocidade = velocidade;
        else
            this.velocidade = Byte.SIZE;
    }
    
    public byte getResistencia(){
        return resistencia;
    }
    
    public void setResistencia(byte resistencia){
        if (resistencia < Byte.SIZE)
            this.resistencia = resistencia;
        else
            this.resistencia = Byte.SIZE;
    }

    public byte getSorte(){
        return sorte;
    }
    
    protected final void geraVidaMax(){
        setVidaMax((byte) (resistencia + (forca / 2)));
    }
    
    protected final void geraEnergiaMax(){
        setEnergiaMax((byte) (resistencia  + (inteligencia / 2)));
    }
    
    public boolean regenVIDA(byte regen){
        return setVida((byte) (getVida() + regen));
    }
    
    public boolean regenEnergia(byte regen){
        return setEnergia((byte) (getEnergia() + regen));
    }
}
