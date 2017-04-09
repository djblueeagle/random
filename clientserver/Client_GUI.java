package clientserver;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.PrintWriter;
import java.net.*;
/**
 *
 * @author DjBlueEagle
 */
public class Client_GUI {
    
    //Global Variable
    private static Client_Chat chatclient;
    public static String userName = "Anonymous";
    
    //Main GUI
    public static JFrame MainWindow = new JFrame();
    private static JButton B_About = new JButton();
    private static JButton B_Connect = new JButton();
    private static JButton B_Disconnect = new JButton();
    private static JButton B_Help = new JButton();
    private static JButton B_Send = new JButton();
    private static JLabel message_label = new JLabel("Message: ");
    private static JLabel conversation_label = new JLabel();
    public static JTextField TF_Message = new JTextField(20);
    public static JTextArea TA_Conversation = new JTextArea();
    private static JScrollPane SP_Conversation = new JScrollPane();
    private static JLabel L_Online = new JLabel();
    public static JList JL_Online = new JList();
    private static JScrollPane SP_Online = new JScrollPane();
    private static JLabel L_LoggedInAs = new JLabel();
    private static JLabel L_LoggedInAsBox = new JLabel();
    
    //Login Window GUI
    public static JFrame LoginWindow = new JFrame();
    public static JTextField TF_UsernameBox = new JTextField(20);
    private static JButton B_Enter = new JButton("Enter");
    private static JLabel L_EnterUsername = new JLabel("Username: ");
    private static JPanel P_Login = new JPanel();
    
    //---------------------------------------------------------------
    
    
    public static void main(String args[]){
        BuildMainWindow();
        Initialize();
    }
    
    
    public static void BuildMainWindow(){
        MainWindow.setTitle(userName+"'s Chat Box");
        MainWindow.setSize(450,500);
        MainWindow.setLocation(220, 180);
        MainWindow.setResizable(false);
        ConfigureMainWindow();
        MainWindow_Action();
        MainWindow.setVisible(true);
    }
    
    public static void Initialize(){
        B_Send.setEnabled(false);
        B_Disconnect.setEnabled(false);
        B_Connect.setEnabled(true);
    }
    
    public static void ConfigureMainWindow(){
        MainWindow.setBackground(new java.awt.Color(255, 255,255));
        MainWindow.setSize(500,320);
        MainWindow.getContentPane().setLayout(null);
        
        B_Send.setBackground(new java.awt.Color(0, 0, 255));
        B_Send.setForeground(new java.awt.Color(255, 255, 255));
        B_Send.setText("Send");
        MainWindow.getContentPane().add(B_Send);
        B_Send.setBounds(250, 40, 81, 25);
        
        B_Disconnect.setBackground(new java.awt.Color(0, 0, 255));
        B_Disconnect.setForeground(new java.awt.Color(255, 255, 255));
        B_Disconnect.setText("Disconnect");
        MainWindow.getContentPane().add(B_Disconnect);
        B_Disconnect.setBounds(10, 40, 110, 25);
        
        B_Connect.setBackground(new java.awt.Color(0, 0, 255));
        B_Connect.setForeground(new java.awt.Color(255, 255, 255));
        B_Connect.setText("Connect");
        B_Connect.setToolTipText("");
        MainWindow.getContentPane().add(B_Connect);
        B_Connect.setBounds(130, 40, 110, 25);
        
        B_Help.setBackground(new java.awt.Color(0, 0, 255));
        B_Help.setForeground(new java.awt.Color(255, 255, 255));
        B_Help.setText("Help");
        MainWindow.getContentPane().add(B_Help);
        B_Help.setBounds(420, 40, 70, 25);
        
        B_About.setBackground(new java.awt.Color(0, 0, 255));
        B_About.setForeground(new java.awt.Color(255, 255, 255));
        B_About.setText("About");
        MainWindow.getContentPane().add(B_About);
        B_About.setBounds(340, 40, 75, 25);
        
        message_label.setText("Message: ");
        MainWindow.getContentPane().add(message_label);
        message_label.setBounds(10, 10, 60, 20);
        
        TF_Message.setForeground(new java.awt.Color(0, 0, 255));
        TF_Message.requestFocus();
        MainWindow.getContentPane().add(TF_Message);
        TF_Message.setBounds(70, 4, 260, 30);
        
        conversation_label.setHorizontalAlignment(SwingConstants.CENTER);
        conversation_label.setText("Conversation");
        MainWindow.getContentPane().add(conversation_label);
        conversation_label.setBounds(100, 70, 140, 16);
        
        TA_Conversation.setColumns(20);
        TA_Conversation.setFont(new java.awt.Font("Tahoma", 0, 12));
        TA_Conversation.setForeground(new java.awt.Color(0, 0, 255));
        TA_Conversation.setLineWrap(true);
        TA_Conversation.setRows(5);
        TA_Conversation.setEditable(false);
        
        SP_Conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_Conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_Conversation.setViewportView(TA_Conversation);
        MainWindow.getContentPane().add(SP_Conversation);
        SP_Conversation.setBounds(10, 90, 330, 180);
        
        L_Online.setHorizontalAlignment(SwingConstants.CENTER);
        L_Online.setText("Currently Online");
        L_Online.setToolTipText("");
        MainWindow.getContentPane().add(L_Online);
        L_Online.setBounds(350, 70, 130, 16);
        
        //String[] TestNames ={"Bob", "Eka", "Budi", "Jean"};
        JL_Online.setForeground(new java.awt.Color(0, 0, 255));
        //JL_Online.setListData(TestNames);
        
        SP_Online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_Online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_Online.setViewportView(JL_Online);
        MainWindow.getContentPane().add(SP_Online);
        SP_Online.setBounds(350, 90, 130, 180);
        
        L_LoggedInAs.setFont(new java.awt.Font("Tahoma", 0, 12));
        L_LoggedInAs.setText("Currently Logged In As");
        MainWindow.getContentPane().add(L_LoggedInAs);
        L_LoggedInAs.setBounds(348, 0, 140, 15);
        
        L_LoggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
        L_LoggedInAsBox.setFont(new java.awt.Font("Tahoma", 0, 12));
        L_LoggedInAsBox.setForeground(new java.awt.Color(255, 0, 0));
        L_LoggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainWindow.getContentPane().add(L_LoggedInAsBox);
        L_LoggedInAsBox.setBounds(340, 17, 150, 20);
    }
    
    public static void MainWindow_Action(){
        B_Send.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_B_Send();
                }
            }
        );
        
        B_Disconnect.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_B_Disconnect();
                }
            }
        );
        
        B_Connect.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuildLoginWindow();
                }
            }
        );
        
        B_Help.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_B_Help();
                }
            }
        );
        
        B_About.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_B_Help();
                }
            }
        );
    }
    
    public static void Login_Action(){
        B_Enter.addActionListener(
                new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                Action_B_Enter();
                }
            }      
        ); 
    }
    
    public static void BuildLoginWindow(){
        LoginWindow.setTitle("What's your name?");
        LoginWindow.setSize(400, 100);
        LoginWindow.setLocation(250, 200);
        LoginWindow.setResizable(false);
        P_Login = new JPanel();
        P_Login.add(L_EnterUsername);
        P_Login.add(TF_UsernameBox);
        P_Login.add(B_Enter);
        LoginWindow.add(P_Login);
        Login_Action();
        LoginWindow.setVisible(true);
    
    }
    
    public static void Action_B_Enter(){
        if(!TF_UsernameBox.getText().equals("")){
            userName = TF_UsernameBox.getText().trim();
            L_LoggedInAsBox.setText(userName);
            Server_multitreaded.CurrentUsers.add(userName);
            MainWindow.setTitle(userName+"'s Chat Box");
            LoginWindow.setVisible(false);
            B_Send.setEnabled(true);
            B_Disconnect.setEnabled(true);
            B_Connect.setEnabled(false);
            Connect();
        }else{
        JOptionPane.showMessageDialog(null, "Please Enter a Name!");
        }
    }
    
    public static void Connect(){
        try{
            final int port = 2000;
            final String HOST = "localhost";
            Socket socket = new Socket(HOST, port);
            System.out.println("You connected to: "+ HOST);
            chatclient = new Client_Chat(socket);
            
            //Send Name to add to online list
            PrintWriter OUT = new PrintWriter(socket.getOutputStream());
            OUT.println(userName);
            OUT.flush();
            
            Thread X = new Thread(chatclient);
            X.start();
        }catch(Exception X){
            System.out.println(X);
            JOptionPane.showMessageDialog(null, "Server not responding.");
            System.exit(0);
        }
    }
    //------------------------------------------------------------------------
    public static void Action_B_Send(){
        if(!TF_Message.getText().equals("")){
            chatclient.Send(TF_Message.getText());
            TF_Message.requestFocus();
        }
    }
    
    public static void Action_B_Disconnect(){
        try{
            chatclient.Disconnect();
        }catch(Exception E){
            E.printStackTrace();
        }
    }
  
    public static void Action_B_Help(){
        JOptionPane.showMessageDialog(null, "Welcome to this chat program");
    }
    
}
