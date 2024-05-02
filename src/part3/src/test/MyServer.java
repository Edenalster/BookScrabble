package test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    private final int port;
    private final ClientHandler clientH;
    private ServerSocket serverS;
    private boolean runnnig;
    public MyServer(int port, ClientHandler clientH){
        this.port=port;
        this.clientH=clientH;
        runnnig=false;
    }
    public void start(){
        new Thread(()->runServer()).start();
    }
    private void runServer(){
        ServerSocket server =null;
        try {
            server=new ServerSocket(port);
            server.setSoTimeout(1000);
            while (!runnnig){
                try {
                    Socket client= server.accept();
                    try {
                        clientH.handleClient(client.getInputStream(),client.getOutputStream());
                        client.close();
                    }catch (IOException e){

                    }

                }catch (SocketTimeoutException e){

                }
            }
            server.close();
        }catch (Exception e){

        }

    }
    public void close(){
        runnnig=true;
    }


	
}
