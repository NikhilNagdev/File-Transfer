package networking;

import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

        }
        catch(IOException i)
        {
            System.out.println("Issue while connecting to server " + i);
        }
        sendData();

        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public void sendData() {

        input  = new DataInputStream(System.in);
        try {
            out = new DataOutputStream(socket.getOutputStream());
        }catch (Exception e){
            System.out.println("Issue while sending data to server: " + e);
        }
        String line = "";
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
    }

    public void receiveData(){


    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }


}