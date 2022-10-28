package me.color;

import java.sql.*;

public class DataBase {


    Connection con;

    {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mdi","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void database(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){ System.out.println(e);}
    }


    public void insertQuery(String query) throws SQLException {
        if(!con.isClosed()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

        }
    }

    public boolean exist(String query) throws SQLException {
        if(!con.isClosed()){
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            if(rs.next())
                return true;

        }

        return false;
    }

    public void executeQuery(String query) throws SQLException {
        if(!con.isClosed()){
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

        }
    }


}
