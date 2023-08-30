package a;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
   
   public int createUser(UserDTO user){
      int ret=0;
      String sql = "insert into User values (?, ?)";
      
      Connection conn=null;
      PreparedStatement pstmt = null;
      
      try {
         conn=getConnection();
         System.out.println("connection ok");
         pstmt = conn.prepareStatement(sql);

            //
         pstmt.setString(1, user.getUserId());
         pstmt.setString(2, user.getPassword());            
         pstmt.executeUpdate();
         
         ret=1;
      }catch (SQLException e){
         ret=-1;
         System.out.println("access error.");
         System.out.println(e.getMessage());
         e.printStackTrace();
      }finally
      {
          closeConnection(null,pstmt,conn);
      } 
      return ret;
   }
   
   public Connection getConnection() {
      
      Connection conn=null;      
      String DBName = "bank";
      String dbURL = "jdbc:mysql://localhost:3306/" + DBName;
      String sslStr="?useSSL=false";

      try {
         
         Class.forName("com.mysql.cj.jdbc.Driver"); 
         System.out.println("JDBC driver load success");

         conn = DriverManager.getConnection(dbURL+sslStr
               , "root","rkdrlans123");          
         System.out.println("DB connection success");
      } catch (ClassNotFoundException e) {
         System.out.println("JDBC driver load fail !!");
      } catch (SQLException e) {
         System.out.println("DB connection fail !!");
      }
      
      return conn;
   }
   public void closeConnection(ResultSet set, PreparedStatement pstmt, Connection connection) {
      if(set!=null)
      {
         try {
         set.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }   
      if(pstmt!=null)
      {
         try {
            pstmt.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
      if(connection!=null)
      {
         try {
            connection.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }
}