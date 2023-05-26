package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import common.Mensagem;

public class Controle implements Runnable{
    private Socket sockets;
    private ObjectInputStream in;
    private ObjectOutputStream out; 
    public String[] jogadores;

    public Controle(Socket sock) throws IOException{
        this.sockets = sock;
        this.out = new ObjectOutputStream(sockets.getOutputStream());
        this.in = new ObjectInputStream(sockets.getInputStream());
        // for(int i = 0;i < 2;i++){
            // try{
            // }catch(IOException e){
            //     System.out.printf("%s", e);
            // }
        // }
    }

    public void sendMessage(String messageToSend) throws IOException{
        Mensagem mensagem = new Mensagem(-1, messageToSend);
        for(int i = 0; i < 2; i++){
            this.out.writeObject(mensagem);
        }
    }

    @Override
    public void run(){
        // System.out.println("Start");
        try {
            sendMessage("Teste");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}