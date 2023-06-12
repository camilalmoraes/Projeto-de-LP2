package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import common.Mensagem;

public class Controle implements Runnable{
    private Socket[] sockets;
    private ObjectInputStream[] in = new ObjectInputStream[2];
    private ObjectOutputStream[] out = new ObjectOutputStream[2]; 

    public Controle(Socket[] sock) throws IOException{
        this.sockets = sock;

        for(int i = 0;i < 2;i++){
            try{
                out[i] = new ObjectOutputStream(sockets[i].getOutputStream());
                in[i] = new ObjectInputStream(sockets[i].getInputStream());
            }catch(IOException e){
                System.out.printf("%s", e);
            }
        }
    }

    public void sendMessage(Object messageToSend,String titulo,int player) throws IOException{
        Mensagem mensagem = new Mensagem(-1, titulo,messageToSend);
        out[player].writeObject(mensagem);
    }

    public Runnable receberMensagem(int player) throws IOException, ClassNotFoundException{
        while(true){
            Mensagem mensagem = (Mensagem)in[player].readObject();
            protocolo(mensagem);
            return null;
        }
    }

    public void protocolo(Mensagem mensagem) throws IOException{
        switch (mensagem.titulo) {
            case "Adicionar Oponente":
                int player = mensagem.player == 0 ? 1: 0;
                sendMessage(mensagem.conteudo, mensagem.titulo, player);
                break;
            default:
                break;
        }
    }

    @Override
    public void run(){
        try {
            //Enviando ordem para o cliente
            sendMessage(0, "Ordem no Jogo", 0);
            sendMessage(1, "Ordem no Jogo", 1);

            //Criando threads para receber mensagens do cliente
            Thread thread1 = new Thread(receberMensagem(0));
            Thread thread2 = new Thread(receberMensagem(1));
            thread1.start();
            thread2.start();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}