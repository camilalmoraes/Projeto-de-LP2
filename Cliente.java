import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente{
    
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public Cliente(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){}
    }

    public void sendMessage(){
        try{
            Scanner sc = new Scanner(System.in);
            while(socket.isConnected()){
                String messageToSend = sc.nextLine();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(IOException e){} 
    }

    public void receiveMessage(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                String msgFromGroup;

                while(socket.isConnected()){
                    try{
                        msgFromGroup = bufferedReader.readLine();
                        System.out.printf("\n%s",msgFromGroup);
                    }catch(IOException e){

                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 4444);
        Cliente cliente = new Cliente(socket);
        cliente.receiveMessage();
        cliente.sendMessage();
        socket.close();
    }
}