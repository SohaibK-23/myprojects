package assignment6;

import java.net.*;
//this class is supposed to set information of the client such as their username. Additionally, it also allows the clients to read
//and write messages to the server using two threads ReadThread and writeThread
public class MyClient {
    private String userName;
    private static boolean checkValidityOfUser = false;

    public static void main(String[] args) {
        MyClient client = new MyClient();
        client.execute();
    }

public void execute()
{
    try{
        Socket s=new Socket("localhost",6666);
        System.out.println("Connected to the server");
        new ReadThread(s, this).start();
        new WriteThread(s, this).start();

    } catch(Exception e)
    {
        System.out.println(e);
    }
}

void setUserName(String username)
{
     this.userName = username;
}

String getUserName()
{
    return this.userName;
}

boolean getStatus()
{
    return this.checkValidityOfUser;
}


}
