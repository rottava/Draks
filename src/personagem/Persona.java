/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import habilidade.Habilidade;
import habilidade.Talentos;
import item.Arma;
import item.Armadura;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.ALEATORIO;

/**
 * Personagem em Geral
 * @author Junior
 */
public abstract class Persona implements Comparable<Persona>{
    //PARAMETROS DE SISTEMA
    private String nome;                                                        //NOME DO PERSONAGEM
    
    //PARAMETROS VARIAVEIS
    private byte vida;                                                          //VIDA
    private byte energia;                                                       //ENERGIA
    private Arma arma;                                                          //EQUIPAMENTO DE ATAQUE
    private Armadura armadura;                                                  //EQUIPAMENTO DE DEFESA
    private final Talentos talentosCura;                                        //TALENTOS DO PERSONAGEM
    private final Talentos talentosDano;                                        //TALENTOS DO PERSONAGEM
    private int moedas;                                                         //MOEDAS DO PERSONAGEM
    
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
    /**
     * Construtor 
     * @param nome Nome do personagem
     * @param forca Força do personagem
     * @param inteligencia Inteligencia do Personagem
     * @param agilidade Agilidade do Personagem
     * @param resistencia  Resistencia do personagem
     */
    Persona(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia){
        this.nome = nome;                                                       //PARAMETRO NOME
        this.forca = forca;                                                     //PARAMETRO FORCA
        this.inteligencia = inteligencia;                                       //PARAMETRO INTELIGENCIA
        this.agilidade = agilidade;                                             //PARAMETRO VELOCIDADE
        this.resistencia = resistencia;                                         //PARAMETRO RESISTENCIA
        sorte = (byte) ALEATORIO.nextInt(10);                                   //PARAMETRO SORTE
        geraVidaMax();                                                          //PARAMETRO VIDA MAXIMA INICIAL
        this.vida = vidaMax;                                                    //PARAMETRO VIDA
        geraEnergiaMax();                                                       //PARAMETRO ENERGIA MAXIMA INICIAL
        this.energia = energiaMax;                                              //PARAMETRO ENERGIA
        this.talentosCura = new Talentos();                                     //LISTA DE TALENTOS
        this.talentosDano = new Talentos();                                     //LISTA DE TALENTOS
    }
    
    //CONSTRUTOR VAZIO
    /**
     * Construtor vazia gera aleatoriamente
     */
    Persona(){
        sorte = (byte) ALEATORIO.nextInt(10);                                   //PARAMETRO SORTE
        talentosCura = new Talentos();                                          //LISTA DE TALENTOS DE CURA
        talentosDano = new Talentos();                                          //LISTA DE TALENTOS DE DANO
    }
    
    //GETTERS AND SETTERS
    //RETORNA NOME
    /**
     * Pega o nome do Personagem
     * @return String com o nome do Personagem
     */
    public String getNome(){
        return nome;
    }
    
    //SETA NOME PARA 
    /**
     * Configura nome
     * @param nome String com o nome do personagem
     */
    protected void setNome(String nome){
        this.nome = nome;
    }
    
    //RETORNA VIDA
    /**
     * Pega a vida do personagem
     * @return byte contendo vida do personagem
     */
    public byte getVida(){
        return vida;
    }
    
    //SETA VIDA E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Seta a vida
     * @param vida
     * @return true se ocorreu com sucesso false caso contrário
     */
    protected boolean setVida(byte vida){
        if (vida <= vidaMax && vida > 0){
            if (vida < vidaMax)
                this.vida = vida;
            else
                this.vida = vidaMax;
            return true;
        }
        else{
            this.vida = 0;
            return false;
        }
    }
    
    //RETONA VIDA MAXIMA
    /**
     * Pega vida máxima do personagem
     * @return byte com o valor da vida máxima
     */
    public byte getVidaMax(){
        return vidaMax;
    }
    
    //SETA VIDA MAXIMA E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Configura vida máxima do personagem
     * @param vidaMax byte com o valor de vida máxima
     */
    private void setVidaMax(byte vidaMax){
        if (vidaMax < TAMANHOMAXIMO)
            this.vidaMax = vidaMax;
        else 
            this.vidaMax = TAMANHOMAXIMO;
    }
    
    //RETORNA ENERGIA
    /**
     * Pega energia do personagem
     * @return byte com a energia
     */
    public byte getEnergia(){
        return energia;
    }

    //SETA ENERGIA E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Configura energia do personagem
     * @param energia byte com a energia
     * @return true se tudo ocorreu bem, false caso contrário
     */
    protected boolean setEnergia(byte energia){
        if (energia <= energiaMax && energia >= 0){
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
    /**
     * Pega energia máxima do personagem
     * @return byte com a energia máxima
     */
    public byte getEnergiaMax(){
        return energiaMax;
    }

    //SETA ENERGIA MAXIMA E RETORNA VERDADEIRO OU RETORNA FALSO
    /**
     * Configura energia máxima
     * @param energiaMax byte com o valor de energia máxima
     */
    private void setEnergiaMax(byte energiaMax){
        if (energiaMax < TAMANHOMAXIMO)
            this.energiaMax = energiaMax;
        else
            this.energiaMax = TAMANHOMAXIMO;
    }
    
    //RETONA ARMA EQUIPADA
    /**
     * Pega a arma equipada pelo personagem
     * @return arma equipada
     */
    public Arma getArma(){
        return arma;
    }
    
    //SETA ARMA EQUIPADA PARA
    /**
     * Configura arma no personagem
     * @param arma Arma do personagem
     */
    protected void setArma(Arma arma){
        this.arma = arma;
    }
    
    //RETONAR ARMADURA EQUIPADA
    /**
     * Pega a armadura do personagem
     * @return Armadura do personagem
     */
    public Armadura getArmadura(){
        return armadura;
    }
    
    //SETA ARMADURA EQUIPADA PARA
    /**
     * Configura armadura do personagem
     * @param armadura Armadura do personagem
     */
    protected void setArmadura(Armadura armadura){
        this.armadura = armadura;
    }
    
    //RETORNA FORCA
    /**
     * pega a força do personagem
     * @return byte com a força do personagem
     */
    public byte getForca(){
        return forca;
    }
    
    //SETA FORÇA PARA
    /**
     * Configura força do personagem
     * @param forca byte com a força do personagem
     */
    public void setForca(byte forca){
        if (forca < TAMANHOMAXIMO)
            this.forca = forca;
        else
            this.forca = TAMANHOMAXIMO;
    }
    
    //RETORNA INTELIGENCIA
    /**
     * Pega a inteligencia do personagem
     * @return byte com a inteligencia do personagem
     */
    public byte getInteligencia(){
        return inteligencia;
    }
    
    //SETA INTELIGENCIA PARA
    /**
     * Configura inteligencia
     * @param inteligencia byte com a inteligencia
     */
    public void setInteligencia(byte inteligencia){
        if (inteligencia < TAMANHOMAXIMO)
            this.inteligencia = inteligencia;
        else
            this.inteligencia = TAMANHOMAXIMO;
    }
    
    //RETORNA AGILIDADE
    /**
     * Pega agilidade
     * @return byte com a agilidade
     */
    public byte getAgilidade(){
        return agilidade;
    }
    
    //SETA AGILIDADE PARA
    /**
     * Configura agilidade do personagem
     * @param agilidade byte com a agilidade do personagem
     */
    public void setAgilidade(byte agilidade){
        if (agilidade < TAMANHOMAXIMO)
            this.agilidade = agilidade;
        else
            this.agilidade = TAMANHOMAXIMO;
    }
    
    //RETORNA RESISTENCIA
    /**
     * Pega a resistencia do personagem
     * @return byte com a resistencia do personagem
     */
    public byte getResistencia(){
        return resistencia;
    }
    
    //SETA RESISTENCIA PARA
    /**
     * Configura resistencia do personagem
     * @param resistencia byte com a resistencia
     */
    public void setResistencia(byte resistencia){
        if (resistencia < TAMANHOMAXIMO)
            this.resistencia = resistencia;
        else
            this.resistencia = TAMANHOMAXIMO;
    }

    //RETORNA SORTE
    /**
     * Pega a sorte do personagem
     * @return byte com a sorte
     */
    public byte getSorte(){
        return sorte;
    }
    
    //GERA VIDA MAXIMA
    /**
     * Gera a vida máxima do personagem
     */
    public final void geraVidaMax(){
        setVidaMax((byte) (resistencia + (forca / 2)));
    }
    
    //GERA ENERGIA MAXIMA
    /**
     * Gera a energia máxima do personagem
     */
    public final void geraEnergiaMax(){
        setEnergiaMax((byte) (resistencia  + (inteligencia / 2)));
    }
    
    //REGENERA VIDA
    /**
     * Regenera vida do personagem
     * @param regen byte com o valor de regeneração
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean regenVida(byte regen){
        return setVida((byte) (getVida() + regen));
    }
    
    //REDUZ VIDA
    /**
     * Reduz a vida do personagem
     * @param damage byte com o dano recebido do personagem
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean reduzVida(byte damage){
        return setVida((byte) (getVida() - damage));
    }
    
    //REGENERA ENERGIA
    /**
     * Regenera energia
     * @param regen valor de regeneração
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean regenEnergia(byte regen){
        return setEnergia((byte) (getEnergia() + regen));
    }
    
    //REDUZ ENERGIA]
    /**
     * Reduz energia
     * @param damage dano recebido para redução
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean reduzEnergia(byte damage){
        return setVida((byte) (getVida() - damage));
    }
    
    //RETORNA LISTA DE HABILIDADES DE CURA
    /**
     * Pega os talentos de cura
     * @return talentos de cura
     */
    public Talentos getTalentosCura(){
        return talentosCura;
    }
    
    //ADICIONA HABILIDADE A LISTA DE HABILIDADES DE CURA
    /**
     * Adiciona habilidade a lista de habilidades de cura
     * @param habilidade habilidade para ser adicionada
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean addTalentosCura(Habilidade habilidade){
        return (talentosCura.addTalentos(habilidade));
    }
    
    //RETORNA LISTA DE HABILIDADES DE DANO
    /**
     * Pega lista de habilidades de dano
     * @return Lista de habilidades de dano
     */
    public Talentos getTalentosDano(){
        return talentosDano;
    }
    
    //ADICIONA HABILIDADE A LISTA DE HABILIDADES DE DANO
    /**
     * Adiciona habilidade a lista de habilidades de dano
     * @param habilidade Habilidade para ser adicionada
     * @return true se tudo ocorreu bem, false caso contrário
     */
    public boolean addTalentosDano(Habilidade habilidade){
        return (talentosDano.addTalentos(habilidade));
    }
    
    //RETORNA MOEDAS
    /**
     * Pega as moedas do personagem
     * @return int com a quantidade de moedas
     */
    public int getMoedas(){
        return moedas;
    }
    
    //SETA MOEDAS PARA MOEDAS
    /**
     * Configura moedas do personagem
     * @param moedas valor de moedas
     */
    protected void setMoedas(int moedas){
        this.moedas = moedas;
    }
    
    //COMPARADOR DE AGILIDADE
    /**
     * Comparação de agilidades
     * @param outroPersona outro personagem para comparação
     * @return -1 se a agilidade é melhor, 1 se é pior, 0 se for igual
     */
    @Override
    public int compareTo(Persona outroPersona) {
         if (this.agilidade > outroPersona.getAgilidade()) {
              return -1;
         }
         if (this.agilidade < outroPersona.getAgilidade()) {
              return 1;
         }
         return 0;
}

}
