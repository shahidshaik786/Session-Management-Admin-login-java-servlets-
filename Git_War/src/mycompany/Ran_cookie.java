package mycompany;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;

public class Ran_cookie {
		public static String cookie_value;
		public static String val[];
		public static int i;
		public static int q_result;
		public static String getAlphaNumericString()
	    {
		 int min = 50;
		 int max = 61;
		 int int_random = (int)Math.floor(Math.random()*(max-min+1)+min); 
	     String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz";

	      StringBuilder sb = new StringBuilder(int_random);
		      Random rand = new Random();
	        for (int i = 0; i < int_random; i++) {
	            int index = rand.nextInt(int_random);
	            sb.append(AlphaNumericString.charAt(index));
	        }
	        return sb.toString();
	    }
		public static boolean login() throws ClassNotFoundException, SQLException {
			Connection con = db_con.mydb_con();
			PreparedStatement pst =null;
			cookie_value=getAlphaNumericString();
			try {
			pst = con.prepareStatement("insert into user_sessions values (?,?,current_timestamp,?)");
			pst.setString(1, cookie_value);
			pst.setInt(2, 0);
			pst.setString(3, "Session Started");
    		System.out.println("Login session created successully");
			if(pst.execute() == false) {
			pst.close();
			con.close();
			return true;
			}else {
			pst.close();
			con.close();
			return false;
			}
			}catch(SQLException e){
				pst.close();
				con.close();
				e.getStackTrace();
				return false;
			}finally {
				pst.close();
				con.close();
			}
		}
		public static int login_check(String logincheck) throws ClassNotFoundException, SQLException {

			if(logincheck==null) {
			System.out.println("login check -- login check cookie is null");
				return 0;
			}else {
			Connection con=db_con.mydb_con();
			ResultSet rst = null;
			PreparedStatement pst=null;
			try {
			pst = con.prepareStatement("select count(*) from user_sessions where sess_cookie=? and sess_status=?");
			pst.setString(1, logincheck);
			pst.setInt(2, 0);
			rst = pst.executeQuery();
			ResultSetMetaData rsmd=rst.getMetaData();
			int count=rsmd.getColumnCount();
			val=new String[count];
			while(rst.next()) {
				q_result = rst.getInt(1);
			}
			
    		System.out.println("Login session checking done. see below result");
			System.out.println("Cookie session status result is "+ q_result);
			}catch(SQLException e) {
				e.printStackTrace();
				rst.close();
				pst.close();
				con.close();
			}finally {
				if(rst!=null) {
				rst.close();
				}
				if(pst!=null) {
				pst.close();
				}

				con.close();
				
			}
		
			return q_result;
		}
	}
		
		public static boolean logout(String logincheck) throws ClassNotFoundException, SQLException {
			Connection con = db_con.mydb_con();
			PreparedStatement pst = null;
			if(logincheck==null) {
			System.out.println("login check -- login check cookie is null");
				return false;
			}else {
				try {
			pst = con.prepareStatement(" UPDATE user_sessions set sess_status = ?, logout_time = current_timestamp where sess_cookie=?");
			pst.setInt(1, 1);
			pst.setString(2, logincheck);
			boolean rst = pst.execute();
			System.out.println("Logout Successfull "+ rst);
			pst.close();
			con.close();
			return rst;
				}catch(SQLException e) {
					pst.close();
					con.close();
					pst =null;
					return false;
				}finally {
					pst.close();
					con.close();
				}
		}
	}
}