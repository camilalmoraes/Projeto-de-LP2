package Client;
public class Personagem {
    public String tipo;
    public String tipo_arma;
    public int vida;
    public int ataque;
    public int armadura;
    public Arma arma;
    public int stuned;

    public void set_arma(String tipo){
        if(tipo.equals("1") || tipo.equals("Espada")){
            arma = new Espada();
            this.tipo_arma = "Espada"; 
        }
        else if(tipo.equals("2") || tipo.equals("Cajado")){
            arma = new Cajado();
            this.tipo_arma = "Cajado"; 
        }
        else if(tipo.equals("3") || tipo.equals("Arco")){
            arma = new Arco();
            this.tipo_arma = "Arco"; 
        }
    }
}
