/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagem;

import habilidade.Habilidade;
import item.Item;
import java.util.Scanner;
import static main.Main.ARQUIVOINIMIGOS;
import static main.Main.ARQUIVONOMES;
import static main.Main.TAMANHOHABILIDADE;
import static main.Main.TAMANHOMAXIMO;
import static main.Main.aleatorio;

/**
 *
 * @author Junior
 */
public final class Inimigo extends Persona{
    //PARAMETRO DE CONFIGURACAO
    private final byte id;                                                      //-1 = FRACO, -2 = MEDIO, -3 = FORTE, -4 = SUBCHEFE, -5 = CHEFE, -6 = CHEFE FINAL, OTHERS = RND
    private int pontos;                                                         //PONTOS PARA PARAMETROS
    
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
            geraTalentos();
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
    
    //GERADOR DE TALENTOS
    private void geraTalentos(){
        byte idInv = (byte) (id*-1);
        if(TAMANHOHABILIDADE > 0){
            byte quantidade;
            if(TAMANHOHABILIDADE > idInv)
                quantidade = (byte) (aleatorio.nextInt(idInv)+1);
            else
                quantidade = (byte) (aleatorio.nextInt(TAMANHOHABILIDADE-1)+1);
            Habilidade habilidade;
            do{
                habilidade = new Habilidade((byte) aleatorio.nextInt(TAMANHOHABILIDADE));
                if(!verificaHabilidade(habilidade)){
                    addTalentos(habilidade);
                    quantidade--;
                }
            }
            while (quantidade > 0);
        }
    }
    
    //VERIFICA SE HABILIDADE JA EXISTE NA LISTA DE HABILIDADES
    public boolean verificaHabilidade(Habilidade habilidade){
        boolean auxiliar = false;
        for(int contador = 0; contador < getTalentos().getTamanho(); contador++){
            if(getTalentos().getHabilidades().get(contador).getId() == habilidade.getId())
                auxiliar = true;
        }
        return auxiliar;
    }
    
    //LE INIMIGO DO ARQUIVO DE CONFIGURACOES
    private void lerArquivo(){
        Scanner scanner = new Scanner(ARQUIVOINIMIGOS);
        byte loop = 1;
        while((scanner.hasNext()) && (loop < id)){
            String nextLine = scanner.nextLine();
            loop++;
        }
        if (scanner.hasNext()){
            String[] parametros;
            parametros = scanner.nextLine().split("/");                         //NOME/FORCA/INTELIGENCIA/AGILIDADE/RESISTENCIA/ARMA/ARMADURA/HABILIDADE/...
            setNome(parametros[0]);                                             //NOME
            setForca((byte) Integer.parseInt(parametros[1]));                   //FORCA
            setInteligencia((byte) Integer.parseInt(parametros[2]));            //INTELIGENCIA
            setAgilidade((byte) Integer.parseInt(parametros[3]));               //AGILIDADE
            setResistencia((byte) Integer.parseInt(parametros[4]));             //RESISTENCIA
            Item item;
            if(!parametros[5].equals("")){                                      //TESTE SE ARMA VALIDA
                 item = new Item((byte) Integer.parseInt(parametros[5]));       
                if (item.getTipo() == 1);                                       //1 = ARMA
                    setArma(item);                                              //ARMA
            }
            if(!parametros[6].equals("")){                                      //TESTE SE ARMADURA VALIDA
                item = new Item((byte) Integer.parseInt(parametros[6]));
                if (item.getTipo() == 2);                                       //2 = ARMADURA
                    setArmadura(item);                                          //ARMADURA
            }
            for(int contador = 7; parametros[contador] != null; contador++){    //VERIFICA HABILIDADES
                Habilidade habilidade = new Habilidade((byte) Integer.parseInt(parametros[contador]));
                if(!verificaHabilidade(habilidade))                             //VERIFICA SE HABILIDADES NAO ESTAO NA LISTA
                    addTalentos(habilidade);                                    //ADICIONA HABILIDADES
            }
        }
        else
            throw new UnsupportedOperationException("ID de inimigo não encontrado.");
    }
    
}
