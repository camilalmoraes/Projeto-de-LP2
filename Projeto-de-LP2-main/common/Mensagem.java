package common;

import java.io.Serializable;

public class Mensagem implements Serializable{
    public Object conteudo;
    public int player;
    public String titulo;
    
    public Mensagem(int player,String titulo,Object conteudo){
        this.player = player;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }
}
