import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Teste implements Runnable {
    Socket s,s2;
    public Teste(Socket ns, Socket ns2) {
        s = ns;
        s2 = ns2;
    }

    @Override
    public void run(){
        try {
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            DataInputStream in2 = new DataInputStream(s2.getInputStream());
            DataOutputStream out2 = new DataOutputStream(s2.getOutputStream());
            
            out.writeInt(1);
            out2.writeInt(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}