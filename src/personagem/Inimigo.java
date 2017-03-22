/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import habilidade.Habilidade;
import habilidade.HabilidadeCura;
import habilidade.HabilidadeDano;
import item.Arma;
import item.Armadura;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static main.Main.CAMINHONOMES;
import static main.Main.TAMANHOHABILIDADESCURA;
import static main.Main.TAMANHOHABILIDADESDANO;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.ALEATORIO;
import static main.Main.CAMINHOINIMIGOS;

/**
 * Inimigo
 * @author Junior
 */
public class Inimigo extends Persona{
    //PARAMETRO DE CONFIGURACAO
    private final byte id;                                                      //-1 = FRACO, -2 = MEDIO, -3 = FORTE, -4 = SUBCHEFE, -5 = CHEFE, -6 = CHEFE FINAL, OTHERS = RND
    private byte pontos;                                                        //PONTOS PARA PARAMETROS
    
    //CONSTRUTOR POR PASSAGEM DE PARAMETRO
    /**
     * Construtor
     * @param nome Nome do Inimigo
     * @param forca Força do Inimigo
     * @param inteligencia Inteligencia do Inimigo
     * @param agilidade Agilidade do Inimigo
     * @param resistencia Resistencia do Inimigo
     */
    public Inimigo(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia) {
        super(nome, forca, inteligencia, agilidade, resistencia);               //CONSTRUTOR PAI
        id = 0;                                                                 //0 = ID NULO
    }
    
    //CONSTRUTOR
    /**
     * Construtor
     * @param id ID do inimigo
     */
    public Inimigo(byte id){
        super();                                                                //CONSTRUTOR PAI
        this.id = id;                                                           //LINHA DO ARQUIVO
        seletor();                                                              //SELETOR DE GERADOR
    }
    
    //SELETOR
    /**
     * Seletor
     */
    private void seletor(){
        if (id >= 0)                                                            //POSITIVO LE DE ARQUIVO
            lerArquivo();
        else                                                                    //NEGATIVO GERA ALEATORIO
            randomizer();  
        geraVidaMax();
        setVida(getVidaMax());
        geraEnergiaMax();
        setEnergia(getVidaMax());
    }
    
    //RANDOMIZER
    /**
     * Randomizer
     */
    private void randomizer(){                                                  //GERA INIMIGO ALEATORIO
            setNome(geraNome());
            geraPontos();
            geraAtributos();
            geraTalentosCura();
            geraTalentosDano();
    }
    
    //GERA NOME APARTIR DE ARQUIVO
    /**
     * Gera nomes do arquivo
     * @return String com o nome
     */
    private String geraNome(){
        try {
            FileReader arq = new FileReader (CAMINHONOMES);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            String resultado = "";
            int loop = 1;
            while(linha != null){
                if(ALEATORIO.nextInt(loop) == 0)
                    resultado = linha;
                linha = lerArq.readLine();
            }
            return resultado;
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHONOMES);
            e.getMessage();
        }
        return"";
    }
    
    //GERA PARAMETRO NO INTERVALO DE 1 A 255
    /**
     * Gera parametro
     * @param parametro Parametro
     * @return Resultado gerado
     */
    private byte geraParametro(byte parametro){
        byte resultado = 0;
        byte auxiliar = 0;
        if (pontos > 0){
            if( pontos <= (TAMANHOMAXIMO - parametro)){
                auxiliar = (byte) (ALEATORIO.nextInt(pontos)+1);
                resultado = auxiliar;
            }
            else{
                auxiliar = (byte) (ALEATORIO.nextInt(TAMANHOMAXIMO - parametro)+1);
                resultado = auxiliar;
            }
        }
        pontos -= auxiliar;
        return resultado;
    }
    
    //GERA ATRIBUTOS NO INTERVALO DE 1 A "PONTOS"
    /**
     * Gera atributos
     */
    private void geraAtributos(){
        byte forca = 1;
        byte inteligencia = 1;
        byte agilidade = 1;
        byte resistencia = 1;
        do{
            forca += geraParametro(forca);
            inteligencia += geraParametro(inteligencia);
            agilidade += geraParametro(agilidade);
            resistencia += geraParametro(resistencia);
        }
        while(pontos > 0);
        setForca(forca);
        setInteligencia(inteligencia);
        setAgilidade(agilidade);
        setResistencia(resistencia);
    }
    
    //GERA PONTOS TOTAL DEPENDENDO DO ID
    /**
     * Gera os pontos dependendo da ID
     */
    private void geraPontos(){
        switch (id){
            case -1:
                pontos = (byte) (ALEATORIO.nextInt(50)+50);
                break;
            case -2:
                pontos = (byte) (ALEATORIO.nextInt(50)+100);
                break;
            case -3:
                pontos = (byte) (ALEATORIO.nextInt(50)+150);
                break;
            case -4:
                pontos = (byte) (ALEATORIO.nextInt(50)+200);
                break;
            case -5:
                pontos = (byte) (ALEATORIO.nextInt(50)+250);
                break;
            case -6:
                pontos = (byte) (ALEATORIO.nextInt(50)+300);
                break;
           default:
                pontos = (byte) (ALEATORIO.nextInt(TAMANHOMAXIMO * 4));
                break;
        }
    }
    
    //GERADOR TALENTOS DE CURA
    /**
     * Gera talentos de Cura
     */
    private void geraTalentosCura(){
        byte idInv = (byte) (id*-1);
        if(TAMANHOHABILIDADESCURA > 0){
            byte quantidade = (byte) (ALEATORIO.nextInt((TAMANHOHABILIDADESCURA)));
            Habilidade habilidade;
            while(quantidade > 0){
                habilidade = new HabilidadeCura((byte) ALEATORIO.nextInt(TAMANHOHABILIDADESCURA));
                if(!verificaHabilidadeCura(habilidade)){
                    addTalentosCura(habilidade);
                    quantidade--;
                }
            }
        }
    }
    
    //GERA TALENTOS DE DANO
    /**
     * Gera talentos de dano
     */
    private void geraTalentosDano(){
        byte idInv = (byte) (id*-1);
        if(TAMANHOHABILIDADESDANO > 0){
            byte quantidade = (byte) (ALEATORIO.nextInt((TAMANHOHABILIDADESDANO)));
            Habilidade habilidade;
            while(quantidade > 0){
                habilidade = new HabilidadeCura((byte) ALEATORIO.nextInt(TAMANHOHABILIDADESDANO));
                if(!verificaHabilidadeCura(habilidade)){
                    addTalentosCura(habilidade);
                    quantidade--;
                }
            }
        }
    }
    
    //VERIFICA SE HABILIDADE JA EXISTE NA LISTA DE HABILIDADES DE CURA
    /**
     * Verifica se a habilidade ja existe na lista de habilidades
     * @param habilidade habilidade
     * @return true se já existe, false caso contrário
     */
    public boolean verificaHabilidadeCura(Habilidade habilidade){
        boolean auxiliar = false;
        for(int contador = 0; contador < getTalentosCura().getTamanho(); contador++){
            if(getTalentosCura().getHabilidades().get(contador).getId() == habilidade.getId())
                auxiliar = true;
        }
        return auxiliar;
    }
    
    //VERIFICA SE HABILIDADE JA EXISTE NA LISTA DE HABILIDADES DE DANO
    /**
     * Verifica se a habilidade ja existe na lista
     * @param habilidade Habilidade para verificação
     * @return true se já existe, false caso contrário
     */
    public boolean verificaHabilidadeDano(Habilidade habilidade){
        boolean auxiliar = false;
        for(int contador = 0; contador < getTalentosDano().getTamanho(); contador++){
            if(getTalentosDano().getHabilidades().get(contador).getId() == habilidade.getId())
                auxiliar = true;
        }
        return auxiliar;
    }
    
    //LE INIMIGO DO ARQUIVO DE CONFIGURACOES
    /**
     * Le inimigo do arquivo de configurações
     */
    private void lerArquivo(){
        try {
            FileReader arq = new FileReader (CAMINHOINIMIGOS);
            BufferedReader lerArq = new BufferedReader (arq);
            String linha = lerArq.readLine();
            byte loop = 1;
            while (linha != null && loop < id) {
                linha = lerArq.readLine();
                loop++;
            }
            if(linha != null){
                String[] parametros;
                parametros = linha.split("/");                                  //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
                setNome(parametros[0]);                                         //NOME
                setForca((byte) Integer.parseInt(parametros[1]));               //FORCA
                setInteligencia((byte) Integer.parseInt(parametros[2]));        //INTELIGENCIA
                setAgilidade((byte) Integer.parseInt(parametros[3]));           //AGILIDADE
                setResistencia((byte) Integer.parseInt(parametros[4]));         //RESISTENCIA
                if(!parametros[5].equals("0")){                                 //TESTE SE ARMA VALIDA
                    Arma arma = new Arma((byte) Integer.parseInt(parametros[5]));
                    setArma(arma);                                              //ARMA
                }
                if(!parametros[6].equals("0")){                                 //TESTE SE ARMADURA VALIDA
                    Armadura armadura = new Armadura((byte) Integer.parseInt(parametros[6]));
                    setArmadura(armadura);                                      //ARMADURA
                }
                setMoedas(Integer.parseInt(parametros[7]));                     //MOEDAS
                //PARAMETROS 8 E 9 NÃO SAO VALIDOS PARA INIMIGOS COMUNS E POR ISSO DEVEM SER 0
                for(int contador = 10; !parametros[contador].equals("0"); contador++){//VERIFICA HABILIDADES
                    byte auxiliar = (byte) Integer.parseInt(parametros[contador]);
                    if (auxiliar > 0){                                          //POSITIVOS
                        HabilidadeCura habilidade = new HabilidadeCura(auxiliar);
                        if(!verificaHabilidadeCura(habilidade))                 //TESTA SE HABILIDADE JA ESTA NA LISTA
                            addTalentosCura(habilidade);                        //ADICIONA HABILIDADES
                    }
                    else{                                                       //NEGATIVOS
                        HabilidadeDano habilidade = new HabilidadeDano(auxiliar);
                        if(!verificaHabilidadeDano(habilidade))                 //TESTA SE HABILIDADE JA ESTA NA LISTA
                            addTalentosDano(habilidade);                        //ADICIONA HABILIDADES
                    }
                }
            }
            else
                throw new UnsupportedOperationException("ID de inimigo não encontrado.");
            arq.close();
        } catch (IOException e) {
            System.err.printf ("Erro na abertura do arquivo %s!\n", CAMINHOINIMIGOS);
            e.getMessage();
        }
       
    }
    
    //RETORNA ID DO INIMIGO
    /**
     * Pega ID do inimigo
     * @return byte com a ID do inimigo
     */
    public byte getId(){
        return id;
    }
    
    //RETORNA PONTOS(){
    /**
     * Pega os pontos do inimigo
     * @return byte com os pontos do inimigo
     */
    public byte getPontos(){
        return pontos;
    }
    
    //SETA PONTOS
    /**
     * Configura os pontos do Inimigo
     * @param pontos Byte com os pontos do inimigo
     */
    public void setPontos(byte pontos){
        this.pontos = pontos;
    }
    
}
