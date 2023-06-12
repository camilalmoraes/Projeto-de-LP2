package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Servidor{
    public void startServer() throws IOException{
        try{
            try (ServerSocket server = new ServerSocket(4444)) {
                while (true){
                    Socket socket = server.accept();
                    Socket socket2 = server.accept();
                    Socket[] sockets = {socket,socket2};
                    System.out.println("Iniciando instância!");
                    ExecutorService exec =  Executors.newFixedThreadPool(10);
                    exec.execute(new Controle(sockets));
                }
            }   
        }catch(IOException e){}
    }

    public static void main(String[] args) throws IOException{
        Servidor server = new Servidor();
        server.startServer();
    }
}