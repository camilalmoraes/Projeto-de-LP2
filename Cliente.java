import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente{
    public static int vez;
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", 4444);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
                
        out.writeUTF("Entrar na Fila");
        vez = in.readInt();
        System.out.printf("Você entrou no jogo, como jogador %d",vez+1);
     
        
        // new Jogo(in,out,vez);
        
        in.close();
        out.close();
        s.close();
    }
}