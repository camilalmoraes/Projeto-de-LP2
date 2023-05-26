package common;

import java.io.Serializable;

public class Mensagem implements Serializable{
    public Object mensagem;
    public int player;
    
    public Mensagem(int player,Object mensagem){
        this.player = player;
        this.mensagem = mensagem;
    }
}
