package com.innova.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends DatabaseInformation{
    //database connection
    private Connection connection;

    //Database connection information
    private  String url=this.getUrl();
    private  String userName=this.getUserName();
    private  String userPassword=this.getUserPassword();

    //Singleton Design Pattern
    private static DatabaseConnection instance;

    //private constructor
    private DatabaseConnection() {
        try {
            Class.forName(this.getForNameData());
            System.out.println("Driver Success...");
            this.connection= DriverManager.getConnection(url,userName,userPassword);
            System.out.println("Connection Success...");
        }catch (Exception e){
            System.out.println("Error connection failed!!!");
            e.printStackTrace();
        }
    }
    //amaçım eğer bağlantı varsa onu kullanmak
    //eğer bağlantı kapatılmış veya bağlantı yoksa instance yapmak
    public static DatabaseConnection getInstance() {
        try {
            if(instance==null)
                instance=new DatabaseConnection();
            else if(instance.connection.isClosed())
                instance=new DatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }

    public DatabaseConnection setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public static void main(String[] args) {
        //DatabaseConnection databaseConnection=new DatabaseConnection();
    }
}
