package clientserver;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author DjBlueEagle
 */
public class Server_multitreaded {
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();
    
    public static void main(String[] args) throws IOException{
        try{
           final int portnum = 2000;
           ServerSocket ss = new ServerSocket(portnum);
            System.out.println("Waiting for clients ....");
            
            //if there is a client /true
            while(true){
                Socket socket = ss.accept();
                ConnectionArray.add(socket);
                
                System.out.println("Client connected from: "+ socket.getLocalAddress().getHostName());
                //method addusername
                AddUserName(socket);
                
                Server_Chat_Return SCR = new Server_Chat_Return(socket);
                Thread thread = new Thread(SCR);
                thread.start();
        }
    }catch(IOException ex){
            System.out.println(ex);
        }
    }
    
//--------------------------------------- Method------------------------------------------------------

    public static void AddUserName(Socket x)throws IOException{
        Scanner scan = new Scanner(x.getInputStream());
        String username = scan.nextLine();
        CurrentUsers.add(username);
        
        for(int i=1; i< Server_multitreaded.ConnectionArray.size(); i++){
            Socket tempsock = (Socket) Server_multitreaded.ConnectionArray.get(i-1);
            PrintWriter pw = new PrintWriter(tempsock.getOutputStream());
            pw.println("#?!"+ CurrentUsers);
            pw.flush();
        }
    }
}
