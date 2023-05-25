package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controle implements Runnable {
    Socket[] s;
    
    public Controle(Socket ns, Socket ns2) {
        s = new Socket[]{ns,ns2};
    }

    @Override
    public void run(){
        try {
            //Criando comunicador com o Cliente
            DataInputStream[] in = new DataInputStream[2];
            DataOutputStream[] out = new DataOutputStream[2];

            for (int i = 0; i < s.length; i++) { 
                in[i] = new DataInputStream(s[i].getInputStream());
                out[i]= new DataOutputStream(s[i].getOutputStream());            
            }
            
            for (int i = 0; i < s.length; i++){
                out[i].writeInt(i);
            }

            while (true){
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}