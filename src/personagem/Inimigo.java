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
import java.io.FileNotFoundException;
import java.util.Scanner;
import static main.Main.INIMIGOS;
import static main.Main.NOMES;
import static main.Main.TAMANHOHABILIDADESCURA;
import static main.Main.TAMANHOHABILIDADESDANO;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.aleatorio;

/**
 *
 * @author Junior
 */
public class Inimigo extends Persona{
    //PARAMETRO DE CONFIGURACAO
    private final byte id;                                                      //-1 = FRACO, -2 = MEDIO, -3 = FORTE, -4 = SUBCHEFE, -5 = CHEFE, -6 = CHEFE FINAL, OTHERS = RND
    private byte pontos;                                                        //PONTOS PARA PARAMETROS
    
    //CONSTRUTOR POR PASSAGEM DE PARAMETRO
    public Inimigo(String nome, byte forca, byte inteligencia, byte agilidade, byte resistencia) {
        super(nome, forca, inteligencia, agilidade, resistencia);               //CONSTRUTOR PAI
        id = 0;                                                                 //0 = ID NULO
    }
    
    //CONSTRUTOR
    public Inimigo(byte id){
        super();                                                                //CONSTRUTOR PAI
        this.id = id;                                                           //LINHA DO ARQUIVO
        seletor();                                                              //SELETOR DE GERADOR
    }
    
    //SELETOR
    private void seletor(){
        if (id >= 0)                                                            //POSITIVO LE DE ARQUIVO
            lerArquivo();
        else                                                                    //NEGATIVO GERA ALEATORIO
            randomizer();  
    }
    
    //RANDOMIZER
    private void randomizer(){                                                  //GERA INIMIGO ALEATORIO
            setNome(geraNome());
            geraPontos();
            geraAtributos();
            geraVidaMax();
            geraEnergiaMax();
            geraTalentosCura();
            geraTalentosDano();
    }
    
    //GERA NOME APARTIR DE ARQUIVO
    private String geraNome(){
        try {
            String resultado = null;
            int contador = 0;
            for(Scanner scanner = new Scanner(NOMES); scanner.hasNext(); contador++){
                String linha = scanner.nextLine();
                if(aleatorio.nextInt(contador) == 0)
                    resultado = linha;
            }
            return resultado;
        } catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("Arquivo nomes.txt não foi encontrado.");
        }
    }
    
    //GERA PARAMETRO NO INTERVALO DE 1 A 255
    private byte geraParametro(byte parametro){
        byte resultado;
        byte auxiliar;
        if( pontos <= (TAMANHOMAXIMO - parametro) ){
            auxiliar = (byte) aleatorio.nextInt(pontos);
            resultado = (byte) (auxiliar + parametro);
        }
        else{
            auxiliar = (byte) aleatorio.nextInt(TAMANHOMAXIMO - parametro);
            resultado = (byte) (auxiliar + parametro);
        }
        pontos -= auxiliar;
        return resultado;
    }
    
    //GERA ATRIBUTOS NO INTERVALO DE 1 A "PONTOS"
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
    private void geraPontos(){
        switch (id){
            case -1:
                pontos = (byte) (aleatorio.nextInt(50)+50);
                break;
            case -2:
                pontos = (byte) (aleatorio.nextInt(50)+100);
                break;
            case -3:
                pontos = (byte) (aleatorio.nextInt(50)+150);
                break;
            case -4:
                pontos = (byte) (aleatorio.nextInt(50)+200);
                break;
            case -5:
                pontos = (byte) (aleatorio.nextInt(50)+250);
                break;
            case -6:
                pontos = (byte) (aleatorio.nextInt(50)+300);
                break;
           default:
                pontos = (byte) (aleatorio.nextInt(TAMANHOMAXIMO * 4));
                break;
        }
    }
    
    //GERADOR TALENTOS DE CURA
    private void geraTalentosCura(){
        byte idInv = (byte) (id*-1);
        if(TAMANHOHABILIDADESCURA > 0){
            byte quantidade;
            if(TAMANHOHABILIDADESCURA > idInv)
                quantidade = (byte) ((aleatorio.nextInt(idInv) + 2) /2);
            else
                quantidade = (byte) (aleatorio.nextInt((TAMANHOHABILIDADESCURA-1)/2)+1);
            Habilidade habilidade;
            do{
                habilidade = new HabilidadeCura((byte) aleatorio.nextInt(TAMANHOHABILIDADESCURA));
                if(!verificaHabilidadeCura(habilidade)){
                    addTalentosCura(habilidade);
                    quantidade--;
                }
            }
            while (quantidade > 0);
        }
    }
    
    //GERA TALENTOS DE DANO
    private void geraTalentosDano(){
        byte idInv = (byte) (id*-1);
        if(TAMANHOHABILIDADESDANO > 0){
            byte quantidade;
            if(TAMANHOHABILIDADESDANO > idInv)
                quantidade = (byte) ((aleatorio.nextInt(idInv) + 2) /2);
            else
                quantidade = (byte) (aleatorio.nextInt((TAMANHOHABILIDADESDANO-1)/2)+1);
            Habilidade habilidade;
            do{
                habilidade = new HabilidadeDano((byte) aleatorio.nextInt(TAMANHOHABILIDADESDANO));
                if(!verificaHabilidadeDano(habilidade)){
                    addTalentosDano(habilidade);
                    quantidade--;
                }
            }
            while (quantidade > 0);
        }
    }
    
    //VERIFICA SE HABILIDADE JA EXISTE NA LISTA DE HABILIDADES DE CURA
    public boolean verificaHabilidadeCura(Habilidade habilidade){
        boolean auxiliar = false;
        for(int contador = 0; contador < getTalentosCura().getTamanho(); contador++){
            if(getTalentosCura().getHabilidades().get(contador).getId() == habilidade.getId())
                auxiliar = true;
        }
        return auxiliar;
    }
    
    //VERIFICA SE HABILIDADE JA EXISTE NA LISTA DE HABILIDADES DE DANO
    public boolean verificaHabilidadeDano(Habilidade habilidade){
        boolean auxiliar = false;
        for(int contador = 0; contador < getTalentosDano().getTamanho(); contador++){
            if(getTalentosDano().getHabilidades().get(contador).getId() == habilidade.getId())
                auxiliar = true;
        }
        return auxiliar;
    }
    
    //LE INIMIGO DO ARQUIVO DE CONFIGURACOES
    private void lerArquivo(){
        try {
            Scanner scanner = new Scanner(INIMIGOS);
            byte loop = 1;
            while((scanner.hasNext()) && (loop < id)){
                String nextLine = scanner.nextLine();
                loop++;
            }
            if (scanner.hasNext()){
                String[] parametros;
                parametros = scanner.nextLine().split("/");                     //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
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
        } catch (FileNotFoundException ex) {
            throw new UnsupportedOperationException("ID de inimigo não encontrado.");
        }
    }
    
    //RETORNA ID DO INIMIGO
    public byte getId(){
        return id;
    }
    
    //RETORNA PONTOS(){
    public byte getPontos(){
        return pontos;
    }
    
    //SETA PONTOS
    public void setPontos(byte pontos){
        this.pontos = pontos;
    }
    
}
