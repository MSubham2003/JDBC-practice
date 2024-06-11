//Boiler plate code 


import java.sql.*;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/jdbc_learn";
    private static final String username="root";
    private static final String password="Subham@2003";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            String query = String.format("insert into student values('%s',%o,%f)","Shivam",20,90.52);
            int affected=statement.executeUpdate(query);
            if(affected>0){
                System.out.println("data inserted successfully");
            }
            else{
                System.out.println("data not inserted");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
}