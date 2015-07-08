package ololo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Даша on 08.07.2015.
 */
public class ForSQL {
    private Connection connection;
    private Statement statement;

    public void createConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reg", "root", "root");
            statement = connection.createStatement();

        } catch (Exception o) {
            System.out.println("What is with your MySQL JDBC Manager?");
            o.printStackTrace();
        }
        return;
    }
    public Integer getLastId(String table_name){
        createConection();
        String select = "Select * From kurs."+table_name;
        ResultSet a=select(select);
        Integer max=0;
        try {
            while (a.next()){
                Integer per=a.getInt("id");
                if (per>max) max=per;
            }
        }catch (Exception o){
            System.out.println("Error" + o.getMessage());
            return null;
        }
        closeConection();
        return max;
    }
    public void querySQL(String a) {

        createConection();
        System.out.println(a);
        try {
            statement.executeUpdate(a);
            statement.close();
        } catch (Exception o) {
            System.out.println("What is with your MySQL JDBC Manager?");
            o.printStackTrace();
        }closeConection();
    }
    public void closeConection() {
        try {
            connection.close();
        } catch (Exception o) {
            System.out.println("What is with your MySQL JDBC Manager?");
            o.printStackTrace();
        }
    }
    public ResultSet select(String a) {
        try {
            ResultSet rs = statement.executeQuery(a);
            return rs;
        } catch (Exception o){
            System.out.println("Error " + o.getMessage());
        }return null;
    }}

