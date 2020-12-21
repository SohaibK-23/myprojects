package assignment6;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
This thread is responsible for reading user's input and sending it to the server
Runs in an infinite loop until the client types "Exit" to quit
 */

public class WriteThread extends Thread
{
    private PrintWriter writer;
    private Socket socket;
    private MyClient client;
    private boolean checkUser = false;
    private Set<String> chatHistory = new HashSet<>();   //set for keeping track of the messages of the client


    public WriteThread(Socket s, MyClient myClient)
    {
        this.socket = s;
        this.client = myClient;

        try{
            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        Console consoleOutput = System.console();
        String name = consoleOutput.readLine("\nEnter your name: ");
        client.setUserName(name);
        /*
        This is supposed to prompt the client to enter in the appropriate password
         */
        while(!checkUser)
        {
            if(userStatus())
            {
                checkUser = true;
                System.out.println("Okay, you are free to chat");
            }
        }

        writer.println(name);


        String text = "";

        /*
        As long as the client doesn't type "Exit", then their messages are sent to the server
         */
        while(!text.equals("Exit"))
        {
            text = consoleOutput.readLine(name + ": ");
            chatHistory.add(text);
                if (text.equals("show chat history"))
                    showChatHistory();
                else {
                    writer.println(text);
                }
        }

        try{
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

/*
The method shows the chat history for the current client
 */
    private void showChatHistory()
    {
        for(String chat : chatHistory)
        {
            if(!chat.equals("show chat history"))
                System.out.println(chat);
        }
    }

    /*
    Requires the user to enter the correct password. In this case, the password is ECEStudent
     */
    private boolean userStatus()
    {
        boolean checkforUser = false;
        String password = "ECEStudent";
        Scanner inputPassword = new Scanner(System.in);
        System.out.println("Please enter your password");

        while(!checkforUser)
        {
            System.out.print("Password: ");
            String userPassword = inputPassword.nextLine();
            if(userPassword.equals(password))
            {
                checkforUser = true;
            }
            else{
                System.out.println("Invalid Password. Please try again");
            }
        }

        return checkforUser;

    }
}
