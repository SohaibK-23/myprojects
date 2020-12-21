package assignment6;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MyServer {
    public Set<String> listOfUsers = new HashSet<>();    //a set of clients in the network
    public Set<ClientThread> listOfThreads = new HashSet<>();  //a set of client threads
    public HashMap<String, ClientThread> mapOfUsers = new HashMap<>(); //this is a map associating clients with their threads

public static void main(String[] args){
try {
    new MyServer().runChatServer();  //runs the chat server which looks for connections
}
catch(Exception e){
    System.out.println(e);
    }

}

public void runChatServer() throws IOException
{
    ServerSocket ss=new ServerSocket(6666);
 //Keeps looking for connections
    while(true) {
        Socket s = ss.accept();//establishes connection between the server and the client
        System.out.println("Connecting a new user...");

        //This thread is responsible for initiating each client to the server
        ClientThread newClient = new ClientThread(s, this);
        listOfThreads.add(newClient);
        newClient.start();  //runs the thread
    }


}

//This method is supposed to broadcast the message to all the clients in the group chat
void transmit(String message, ClientThread client)
{
    for(ClientThread aClient : listOfThreads)
    {
        if(aClient != client)
        {
            aClient.display(message);
        }
    }
}

//If the user exits the chat, then remove him from the set of users in the chat and also get rid of his thread from the set of threads.

public void removeUser(String username, ClientThread clientThread)
{
    boolean userStatus = listOfUsers.remove(username);
    if(userStatus)
    {
        listOfThreads.remove(clientThread);
        System.out.println("The user "  + username + " has been disconnected");

    }

}

boolean areUsersAvailable()
{
    return !this.listOfUsers.isEmpty();
}


    Set<String> getUserNames() {
        return this.listOfUsers;
    }
}
