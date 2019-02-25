package networking;

import java.net.*;
import java.io.*;

public class Server
{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in =  null;
    private DataOutputStream out = null;

    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            connectToClient();

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            readFromClient();

        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public void connectToClient(){
        System.out.println("Waiting for a client ...");

        try {
            socket = server.accept();
        }catch (Exception e){
            System.out.println("Issue in server while connecting to client" + e);
        }

        System.out.println("Client accepted");
    }

    public void readFromClient(){


        String line = "";
        while (!line.equals("Over"))
        {
            try
            {
                line = in.readUTF();
                System.out.println(line);
            }
            catch(IOException e)
            {
                System.out.println("Issuue while reading data from client " + e);
            }
        }
        System.out.println("Closing connection");
        try{
            socket.close();
            in.close();
        }catch(Exception e){
            System.out.println("Issue while closing " + e);
        }

    }

    public void sendToClient(){
        try {
            out = new DataOutputStream(socket.getOutputStream());

        }catch (Exception e){
            System.out.println("Issue while sending dat ato client: " + e);
        }

    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}
