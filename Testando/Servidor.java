import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        Executor exec = Executors.newCachedThreadPool();
        try {
            ServerSocket s = new ServerSocket(4444);
            while (true) {
                Socket ns = s.accept();
                Socket ns2 = s.accept();
                exec.execute(new Teste(ns,ns2));
            }
        }catch (IOException e){

        }
    }
}