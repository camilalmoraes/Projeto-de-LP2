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
    
    public void entrarServidor() throws IOException{
        socket = new Socket("localhost",4444);

        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void receberMensagem() throws ClassNotFoundException, IOException{
        while (socket.isConnected()){
            Mensagem mensagem = (Mensagem)in.readObject();

            System.out.printf("\n%d",mensagem.player);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Cliente cliente = new Cliente();
        cliente.entrarServidor();
        cliente.receberMensagem();
    }
}