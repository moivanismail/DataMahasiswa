package com.ismail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBFunctions {
    public Connection connect_to_db(String dbname, String username, String password) {
        // Connect to the database
    Connection conn = null;
    try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +dbname, username, password);
        if(conn!=null) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } else {
            System.out.println("Failed to make connection!");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return conn;
    }

    //    create table
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query = "CREATE TABLE "+table_name+" (id VARCHAR PRIMARY KEY, name VARCHAR(255), kelas VARCHAR(50), jurusan VARCHAR(50), pembayaran VARCHAR(50), jumlah INTEGER)";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table "+table_name+" created successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //    function insert data
    public void insertData(Connection conn, String table_name, String IDMahasiswa, String name, String kelas, String jurusan, String pembayaran, String jumlah){
        Statement statement;
        try{
            String query = "INSERT INTO "+table_name+" VALUES ('"+IDMahasiswa+"', '"+name+"', '"+kelas+"', '"+jurusan+"', '"+pembayaran+"', "+jumlah+")";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //    function update data
    public void updateData(Connection conn, String table_name, String name, String kelas, String jurusan, String pembayaran, int jumlah, String id){
        Statement statement;
        try{
            String query = "UPDATE "+table_name+" SET name = '"+name+"', kelas = '"+kelas+"', jurusan = '"+jurusan+"', pembayaran = '"+pembayaran+"', jumlah = "+jumlah+" WHERE id = '"+id+"'";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //    function delete data
    public void deleteData(Connection conn, String table_name, String idMahasiswa){
        Statement statement;
        try{
            String query = "DELETE  FROM "+table_name+" WHERE id = '"+idMahasiswa+"'";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //    function select data
    public ResultSet selectData(Connection conn, String table_name) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + table_name;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Data selected successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
}
