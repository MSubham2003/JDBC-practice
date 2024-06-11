import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/lenden";
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
            Connection connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
            String debitquery = "update accounts set balance = balance - ? where account_no = ?";
            String creditquery = "update accounts set balance = balance + ? where account_no = ?";
            PreparedStatement debitpreparedst = connection.prepareStatement(debitquery);
            PreparedStatement creditpreparedst = connection.prepareStatement(creditquery);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter amount ");
            double amount = sc.nextDouble();
            debitpreparedst.setDouble(1,amount);
            debitpreparedst.setInt(2,39);
            creditpreparedst.setDouble(1,amount);
            creditpreparedst.setDouble(2,17);
            if(ismin(connection, amount)){
                int affectrow1 = debitpreparedst.executeUpdate();
                int affectrow2  = creditpreparedst.executeUpdate();
                connection.commit();
                System.out.println("Transaction successful");
            }
            else{
                connection.rollback();
                System.out.println("Transaction failed");
            }
            debitpreparedst.close();
            creditpreparedst.close();
            sc.close();
            connection.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
    static boolean ismin(Connection connection, double amount){
        try{
            String query = "select balance from accounts where account_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 39);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double cur_bal = resultSet.getDouble("balance");
                if(cur_bal<amount){
                    return false;
                }
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }
}


