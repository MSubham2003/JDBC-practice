import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/jdbc_learn";
    private static final String username="root";
    private static final String password="Subham@2003";

    public static void main(String[] args) {
//        System.out.println("Hello world!");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//
//            Statement statement = connection.createStatement();

//            //Insertion
//            String query = String.format("insert into students(name,age,marks) values('%s',%d,%f)","Avinash",24,89.65);
//            int affected=statement.executeUpdate(query);
//            if(affected>0){
//                System.out.println("data inserted successfully");
//            }
//            else{
//                System.out.println("data not inserted");
//            }
//
            //Updation
//            String query = String.format("update students set age = %d where id = %d",22,3);
//            int affected=statement.executeUpdate(query);
//            if(affected>0){
//                System.out.println("data updated successfully");
//            }
//            else{
//                System.out.println("data not updated");
//            }



//            //Display
//            String query = "select * from students";
//            ResultSet resultSet=statement.executeQuery(query);
//            while(resultSet.next())//iterate in the resultSet table
//            {
//                int id = resultSet.getInt("id");
//                String name =resultSet.getString("name");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("Student id is "+id+", name is "+name+", marks secured "+marks);
//            }


//            //Delete
//            String query = String.format("delete from students where id = %d",5);
//            int affected=statement.executeUpdate(query);
//            if(affected>0){
//                System.out.println("data deleted successfully");
//            }
//            else{
//                System.out.println("data not deleted");
//            }







//            Prepared statement   ? is the placeholder

////                        Insertion
//            Connection conncetion = DriverManager.getConnection(url,username,password);
//            String query = "insert into students(name,age,marks) values(?,?,?)";
//            PreparedStatement preparedStatement = conncetion.prepareStatement(query);
//            preparedStatement.setString(1,"Lokesh");
//            preparedStatement.setDouble(2,22);
//            preparedStatement.setInt(3,90);
//            int affected = preparedStatement.executeUpdate();
//            if(affected>0){
//                System.out.println("Inserted successfully");
//            }
//            else{
//                System.out.println("Error!!!");
//            }





//            //display
//            Connection conncetion = DriverManager.getConnection(url,username,password);
//            String query = "select marks from students where name = ?";
//            PreparedStatement preparedStatement = conncetion.prepareStatement(query);
//            preparedStatement.setString(1,"Lokesh");
//            ResultSet result = preparedStatement.executeQuery();
//            if(result.next()){
//                System.out.println("Lokesh marks = "+result.getDouble("marks"));
//            }
//            else{
//                System.out.println("ERROR!!!");
//            }


//            //update
//            Connection conncetion = DriverManager.getConnection(url,username,password);
//            String query = "update students set age = ? where name = ?";
//            PreparedStatement preparedStatement = conncetion.prepareStatement(query);
//            preparedStatement.setInt(1,20);
//            preparedStatement.setString(2,"Lokesh");
//            int result = preparedStatement.executeUpdate();
//            if(result>0){
//                System.out.println("Updated");
//            }
//            else{
//                System.out.println("ERROR!!!");
//            }



////            Batch Processing using Statement
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//            Scanner obj = new Scanner(System.in);
//            while(true){
//                System.out.println("Enter details ");
//                String name = obj.next();
//                int age = obj.nextInt();
//                double marks = obj.nextDouble();
//                System.out.println("Enter more data ? (0/1)");
//                int res = obj.nextInt();
//                String query = String.format("insert into students(name,age,marks) values('%s',%d,%f)",name,age,marks);
//                statement.addBatch(query);
//                if(res==0)break;
//            }
//            //output affected will be a boolen type array [1,1,0,0,1]
//            int[] affected = statement.executeBatch();
//            for(int res:affected){
//                if(res>0){
//                    System.out.println("data inserted successfully");
//                }
//                else{
//                    System.out.println("data not inserted");
//                }
//            }



//            Batch Processing using PreparedStatement
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = String.format("insert into students(name,age,marks) values(?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner obj = new Scanner(System.in);
            while(true){
                System.out.println("Enter details ");
                String name = obj.next();
                int age = obj.nextInt();
                double marks = obj.nextDouble();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                System.out.println("Enter more data ? (0/1)");
                int res = obj.nextInt();
                preparedStatement.addBatch();
                if(res==0)break;
            }
            //output affected will be a boolen type array [1,1,0,0,1]
            int[] affected = preparedStatement.executeBatch();
            for(int res:affected){
                if(res>0){
                    System.out.println("data inserted successfully");
                }
                else{
                    System.out.println("data not inserted");
                }
            }


        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}