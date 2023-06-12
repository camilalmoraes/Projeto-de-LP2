package Client;

import java.io.Serializable;

public class Elfo extends Personagem implements Serializable{
    public Elfo(){
        tipo = "Elfo";
        vida = 1800;
        ataque = 300;
        armadura = 30;
    }
   
    public void set_arma(String tipo_arma){
        super.set_arma(tipo_arma);
    }
}
