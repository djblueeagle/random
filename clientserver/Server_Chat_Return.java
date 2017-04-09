package clientserver;
import java.net.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author DjBlueEagle
 */
public class Server_Chat_Return implements Runnable{
    //global variable
    Socket socket;
    private Scanner scan;
    private PrintWriter pw;
    String message = "";

    //Contructor
    public Server_Chat_Return(Socket x) {
        this.socket = x;
    }
    
    //checkconnection method
    public void CheckConnection() throws IOException{
        if(!socket.isConnected()){
            for(int i= 1; i<=Server_multitreaded.ConnectionArray.size(); i++){
                if(Server_multitreaded.ConnectionArray.get(i)== socket){
                    Server_multitreaded.ConnectionArray.remove(i);
                }
        }
            for(int i= 1; i<=Server_multitreaded.ConnectionArray.size(); i++){
                Socket tempsock = (Socket) Server_multitreaded.ConnectionArray.get(i-1);
                PrintWriter pw = new PrintWriter(tempsock.getOutputStream());
                //client side
                pw.println(tempsock.getLocalAddress().getHostName() + " Disconnected!");
                pw.flush();
                //server side
                System.out.println(tempsock.getLocalAddress().getHostName()+ "Disconnected!");
            }
        }
    }
//--------------------------------------------------------------------------------------

    public void run(){
        try{
            try{
                scan = new Scanner(socket.getInputStream());
                pw = new PrintWriter(socket.getOutputStream());
                while(true){
                    CheckConnection();
                    if(!scan.hasNext()){
                        return;
                    }
                    message = scan.nextLine();
                    System.out.println("Client said: "+ message);
                    
                    for(int i=1; i<Server_multitreaded.ConnectionArray.size(); i++){
                        Socket tempsock = (Socket) Server_multitreaded.ConnectionArray.get(i-1);
                        PrintWriter pw = new PrintWriter(tempsock.getOutputStream());
                        pw.println(message);
                        pw.flush();
                        System.out.println("Sent to: "+ tempsock.getLocalAddress().getHostName());
                    } //close for loop
                }//close while loop
            }finally{
                socket.close();
            }
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}
