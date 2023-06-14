package Client;
import java.util.Scanner;
import java.util.Map;

public class Jogo{
    public int rodada_atual = 1;
    public Jogador jogador,jogador_oponente;
    public Scanner sc = new Scanner(System.in);
    public Boolean endgame = false;

    // public void startGame(){
    //     while(true){
    //         if(golpe.get("Atordoar") > 0){
    //             jogador_oponente.personagem.stuned = golpe.get("Atordoar");
    //         }

    //         if(jogador_oponente.personagem.vida <= 0){
    //             System.out.println("\n"+jogador_atual.nome+" venceu o jogo, Parabéns!!");
    //             break;
    //         }

    //         rodada_atual ++;

    //         if (jogador_oponente.personagem.stuned > 0){
    //             jogador_oponente.personagem.stuned --;
    //             System.out.println("\nJogador estava stunado");
    //         }else{
    //             if (jogador_atual == jogador[0]){
    //                 jogador_atual = jogador[1];
    //                 jogador_oponente = jogador[0];
    //             }else{
    //                 jogador_atual = jogador[0];
    //                 jogador_oponente = jogador[1];
    //             }
    //         }
    //     }
    // }

    public void adicionar_jogador(String nome_jogador,String tipo_personagem,String tipo_arma,boolean oponente){
        if(oponente){
            jogador_oponente = new Jogador(nome_jogador,tipo_personagem);
            jogador_oponente.personagem.set_arma(tipo_arma);
        }else{
            //Atualmente sem inteface gráfica:
            System.out.print("Diga o nome do Jogador: ");
            nome_jogador = sc.nextLine();
            System.out.print("\nDiga o seu personagem - \n1 - Elfo\n2 - Anão\n3 - Troll\n");
            tipo_personagem = sc.nextLine();
            
            System.out.print("\nDiga sua arma - \n1 - Espada\n2 - Cajado\n3 - Arco\n");
            tipo_arma = sc.nextLine();
            
            //Só essa parte ficará, com a interface gráfica:
            jogador = new Jogador(nome_jogador,tipo_personagem);
            jogador.personagem.set_arma(tipo_arma);
            System.out.println("Nome do Jogador: " + jogador.nome + "\nPersonagem do Jogador: " + jogador.personagem.tipo);
        }
    }

    public Map<String,Integer> escolher_golpe(Jogador jogador){
        int comando = 0;
        while (true){
            System.out.print("\n\nEscolha o golpe - \n1 - "+ jogador.personagem.arma.ataques.keySet().toArray()[0] +
            "\n2 - "+ jogador.personagem.arma.ataques.keySet().toArray()[1] +
            "\n3 - "+ jogador.personagem.arma.ataques.keySet().toArray()[2] +
            "\n4 - "+ jogador.personagem.arma.ataques.keySet().toArray()[3] +"\n");          
            comando = sc.nextInt(); 
            if(jogador.personagem.arma.get_cooldown(String.valueOf(jogador.personagem.arma.ataques.keySet().toArray()[(comando-1)]),jogador.personagem.arma.ataques,rodada_atual)){
                System.out.print("\nHabilidade em cooldown, escolha novamente");
            }
            else{
                break;
            }
        }
        return jogador.personagem.arma.ataques.get(jogador.personagem.arma.ataques.keySet().toArray()[(comando-1)]);
    }

    public void calcular_dano(Map<String, Integer> golpe, Jogador jogador_atual,Jogador jogador_oponente){
        if(golpe.get("Dano") > 0){
            double dano = ((jogador_atual.personagem.ataque * 0.33) * golpe.get("Dano")) / jogador_oponente.personagem.armadura;
            jogador_oponente.personagem.vida -= dano;

            System.out.println("\nVida de "+jogador_oponente.nome+": "+jogador_oponente.personagem.vida);
        }

        if (golpe.get("Cura") > 0){
            jogador_atual.personagem.vida += golpe.get("Cura");
            System.out.println("\nCura realizada, vida atual: "+jogador_atual.personagem.vida);
        }
    }
}
