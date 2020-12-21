package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/*
This thread is supposed to read server input repeatedly and print it to the console
Runs an infinite loop until the client disconnects from the server
 */

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private MyClient client;

    public ReadThread(Socket s, MyClient myClient) throws IOException {
        this.socket = s;
        this.client = myClient;

        InputStream input = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public void run()
    {
        while(true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);

                if(client.getUserName() != null) {
                     System.out.print(client.getUserName() + ": ");
                }

            } catch (Exception e) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }



    }
}
