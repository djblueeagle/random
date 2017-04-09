package clientserver;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author DjBlueEagle
 */
public class Client_Chat implements Runnable{
    Socket socket;
    Scanner input;
    Scanner send = new Scanner(System.in);
    PrintWriter out;
    
    //------------------------------------------------------
    public Client_Chat(Socket x){
        this.socket = x;
    }
    
    public void run(){
        try{
            try{
                input = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream());
                out.flush();
                Checkstream();
            }finally{
                socket.close();
            }
        }catch(Exception X){
            System.out.println(X);
        }
    }
    
    public void Disconnect()throws IOException{
        out.println(Client_GUI.userName +" has disconnected.");
        out.flush();
        socket.close();
        JOptionPane.showMessageDialog(null, "You disconnected!");
        System.exit(0);
    }
    
    public void Checkstream (){
        while(true){
            System.out.println("aaa");
            Receive();
        }
    }
    
    public void Receive(){
        System.out.println("ccc");
        System.out.println(input.hasNext());
        if(input.hasNext()){
            System.out.println(input.hasNext());
            System.out.println(input.nextLine());
            System.out.println("ddd");
            String message = input.nextLine();
            if(message.contains("#?!")){
                String TEMP1= message.substring(3);
                       TEMP1=TEMP1.replace("[","");
                       TEMP1=TEMP1.replace("]","");
                       
                String[] CurrentUsers = TEMP1.split(", ");
                System.out.println("bbb");
                Client_GUI.JL_Online.setListData(CurrentUsers);
            }else{
                Client_GUI.TA_Conversation.append(message+ "\n");
            }
        }
    }
    
    public void Send(String x){
        out.println(Client_GUI.userName+ ": "+ x);
        out.flush();
        Client_GUI.TF_Message.setText("");
    }
}
