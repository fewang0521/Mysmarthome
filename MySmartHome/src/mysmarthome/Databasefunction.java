package mysmarthome;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Databasefunction {

	void sql_run(String parameter, int input_values, String status){
		//test today
		String url ="jdbc:mysql://localhost:3306/java_test";
		String id = "root";
		String pass ="123600";
		Statement stmt =null;
		ResultSet rs =null;
		String query_show = "select * from sensor_values";

		//query for insert
		String query_insert ="insert into ";
		String table_name="sensor_values ";
		String values="values (?, ?, ?)";
		String query_insert_total=query_insert+table_name+values;
		//

		//query for update
		String query_update ="update ";
		//String table_name="sensor_values ";
		String set_values="set value=?, status=?";
		String query_update_total=query_update+table_name+set_values+" where parameter = ?";
		//

		Connection conn =null;
		PreparedStatement pstmt= null;
		boolean parameter_exist =false;

		//System.out.println(query_insert_total);

		try{
			conn= (Connection) DriverManager.getConnection(url,id,pass);
			System.out.println("연결 성공");
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(query_show);

			//check whether parameter already exist
			while(rs.next()){
				if (parameter.compareTo(rs.getString(1))==0){
					parameter_exist=true;
					break;
				}
				else{
				}

			}
			if (parameter_exist){
				//update
				pstmt = conn.prepareStatement(query_update_total);
				pstmt.setInt(1, input_values);
				pstmt.setString(2,status);
				pstmt.setString(3, parameter);
				pstmt.executeUpdate();
			}
			else{
				//insert
				pstmt = conn.prepareStatement(query_insert_total);
				pstmt.setString(1, parameter);
				pstmt.setInt(2, input_values);
				pstmt.setString(3, status);
				pstmt.executeUpdate();
			}
			rs = stmt.executeQuery(query_show);
			while(rs.next()){
				System.out.println(rs.getString(1) + " :" + rs.getInt(2) +":"+rs.getString(3));
			}
			rs.close();
			stmt.close();
			pstmt.close();
			conn.close();

		}catch(SQLException ee){
			System.err.println("SQL Error =" + ee.toString());
		}

	}


void dbwrite(String input) throws IOException{
	FileOutputStream output = new FileOutputStream("C:/Apache24/htdocs/light_status.txt");
	String data = input;
	output.write(data.getBytes());
	output.close();
}

}
