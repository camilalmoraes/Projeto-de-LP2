package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import common.Mensagem;

public class Cliente{
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    Protocolo protocolo;
    Jogo jogo;
    Integer ordem;
    
    public Cliente() throws IOException{
        this.socket = new Socket("localhost",4444);
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.protocolo = new Protocolo();
    }

    public Mensagem receberMensagem() throws ClassNotFoundException, IOException{
        Mensagem mensagem = (Mensagem)in.readObject();
        System.out.println("\nMensagem Recebida!");
        return mensagem;
    }

    public void sendMessage(String titulo,Object messageToSend) throws IOException{
        Mensagem mensagem = new Mensagem(this.ordem,titulo,messageToSend);
        out.writeObject(mensagem);
    }

    public void iniciarJogo() throws IOException, ClassNotFoundException{
        //Thread para receber mensagens do servidor
        Thread thread = new Thread(){
            public void run(){
                while(socket.isConnected()){
                    try {
                        Mensagem mensagem = receberMensagem();
                        protocolo.decidir(mensagem.titulo,mensagem.conteudo);
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        this.jogo = new Jogo();
        this.jogo.adicionar_jogador("","","",false);

        String[] info = new String[]{jogo.jogador.nome,jogo.jogador.personagem.tipo,jogo.jogador.personagem.tipo_arma};
        sendMessage("Adicionar Oponente",info);  

        while(this.jogo.jogador_oponente == null){};

        do{
            
        }while(jogo.endgame == false);
    }

    public class Protocolo{
        public void decidir(String comando,Object conteudo){
            switch (comando) {
            case "Adicionar Oponente":
                    adicionarOponente((String[])conteudo);
                    break;
            case "Ordem no Jogo":
                    ordem = (int)conteudo;
                    break;
            default:
                break;
            }
        }

        public void adicionarOponente(String[] dados){
            jogo.adicionar_jogador(dados[0], dados[1], dados[2], true);
        } 
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Cliente cliente = new Cliente();
        cliente.iniciarJogo();
    }
}