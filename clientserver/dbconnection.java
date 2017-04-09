package clientserver;
import java.sql.*;

public class dbconnection {
    public Connection cc;
    public Statement ss;
    public ResultSet rr;

public void Class(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        cc=DriverManager.getConnection("jdbc:mysql://localhost/login","root","");
        ss=cc.createStatement();
    }  catch(Exception e){
        System.out.println(e);
    }     
}
}
