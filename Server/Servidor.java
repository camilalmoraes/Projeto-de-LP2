package Server;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{
    public static void main(String[] args){
        Servidor server = new Servidor();
        server.run();
    }
    public ArrayList<Controle> connections = new ArrayList<>();
    
    @Override

    public void run() {
        Executor exec = Executors.newCachedThreadPool();
        try{
            ServerSocket serverSocket = new ServerSocket(4444);
            while (true) {
                Socket socket = serverSocket.accept();
                Controle controle = new Controle(socket);
                connections.add(controle);
                exec.execute(controle);
            }
        }catch(IOException e){}
    }
    
    public class Controle implements Runnable {
        private Socket socket;
        private BufferedWriter bufferedWriter;
        private BufferedReader bufferedReader;
        
        public Controle(Socket socket) {
            try{
                this.socket = socket;
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
            }catch(IOException e){}
        }
       
        @Override
        public void run(){
            String messagemFromClient;
            while (this.socket.isConnected()){
                try{
                    messagemFromClient = bufferedReader.readLine();
                    sendToAll(messagemFromClient);
                }catch(IOException e){
                    
                }
            }
        }
    
        public void sendToAll(String messageToSend){
            for(Controle controle: connections){
                try{
                    System.out.printf("\n%s",messageToSend);
                    controle.bufferedWriter.write(messageToSend);
                    controle.bufferedWriter.newLine();
                    controle.bufferedWriter.flush();
                }catch(IOException e){
    
                }
            }
        }

        // public Object ByteToObject(byte[] bits) throws IOException, ClassNotFoundException{
        //     ByteArrayInputStream bis = new ByteArrayInputStream(bits);
        //     ObjectInput in = null;
        //     in = new ObjectInputStream(bis);
        //     Object o = in.readObject(); 
            
        //     return o;
        // }
    }
}