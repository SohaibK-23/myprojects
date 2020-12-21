package assignment6;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

//This is the client thread class where the server initiates multiple connections between multiple clients

public class ClientThread extends Thread
{
    private Socket socket;
    private MyServer server;
    private PrintWriter writer;

    public ClientThread(Socket s, MyServer myServer)
    {
        this.socket = s;
        this.server = myServer;

    }


    public void run()
    {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);

            printUsers();
            //reading the username
            String username = reader.readLine();

            //inserting username to the set of usernames in the network
            server.listOfUsers.add(username);

            //inserting their thread and the username in a map
            server.mapOfUsers.put(username, this);

            String serverNotification = username + " has joined the chat";
            server.transmit(serverNotification, this);




            String userMessage = "";

            String indivMessageNotification = "";
            Scanner indiv = new Scanner(System.in);



            while(!userMessage.equals("Exit"))
            {
                userMessage = reader.readLine();
                serverNotification = username + ": " + userMessage;
                //who do you want to send this message to
                System.out.print("Do you want to send this message individually: ");
                indivMessageNotification = indiv.nextLine();
                if(indivMessageNotification.equals("yes"))
                {
                    indivMessage(serverNotification, username); //function is supposed to send the message from one client to another individually
                }
                else
                {
                    server.transmit(serverNotification, this);
                }
            }

            server.removeUser(username, this);
            socket.close();

            serverNotification = username + " has left the chat";
            server.transmit(serverNotification, this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//the function allows the client to send a message to another client individually
    private void indivMessage(String serverNotification, String name)
    {
        String individual = "";
        boolean cond = false;
        Scanner nameOfIndiv = new Scanner(System.in);
        while(!cond) {
            System.out.print("Name the individual whom you want to send this message to: ");
            individual = nameOfIndiv.nextLine();

            if(individual.equals(name))
            {
                System.out.println("Seriously, Stop Joking Around and try again");
            }

            else if (checkIndividual(individual)) {
                    sendMessageIndiv(serverNotification, individual);
                    cond = true;
            }
            else
            {
                System.out.println("This person is not in the database. Please enter another user");
            }

        }
    }

    //sends the message to the client individually by looking at their name and their respective thread
    private void sendMessageIndiv(String notification, String ind) {
        ClientThread client;
        client = server.mapOfUsers.get(ind);
        client.display(notification);

    }

    //checks for whether the person is in the set listOfUsers
    private boolean checkIndividual(String individual) {
        boolean status = false;
        for(String user : server.listOfUsers)
        {
            if(user.equals(individual))
                status = true;
        }

        return status;
    }


    private void printUsers()
    {
        if(server.areUsersAvailable()){
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }

/*
Sends a message to the client
 */
    void display(String message)
    {
        writer.println(message);
    }


}
