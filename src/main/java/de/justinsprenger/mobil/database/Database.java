package de.justinsprenger.mobil.database;
import java.sql.*;

import javax.print.DocFlavor.CHAR_ARRAY;
public class Database {
	private String user;
	private String pass;
	private String address;
	private int port;
	private String database;
	private int id;
	Connection conn;
	/**
	 * 
	 * @param add -url
	 * @param port -portnumber (3306 for mysql)
	 * @param db -database name
	 * @param user -admin user
	 * @param pass -password
	 */
	public Database(String add,int port,String db,String user,String pass){
		this.user = user;
		this.pass = pass;	
		this.address = add;
		this.port = port;
		this.database = db;
	
		
		
	}
	public boolean connect(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from T_Accounts"); 
			
			while(rs.next()){  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4)); 
				id = rs.getRow();
			}
				conn.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public String getDatabase(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from t_Messungen"); 
			while(rs.next()){
				System.out.println(rs.getString("IP")+"  "+rs.getString("RouterIP")+"  "+rs.getFloat("Geschwindigkeit")+"  "+rs.getString("Uhrzeit")+"  "+rs.getString("Datum"));
				id = rs.getRow();
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	public boolean getRow(String[] values){
		boolean t=false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from t_Messungen WHERE IP = '" + values[0] + "' AND RouterIP = '" + values[1]+"'");
			while(rs.next()){
				System.out.println(rs.getString("IP")+"  "+rs.getString("RouterIP")+"  "+rs.getFloat("Geschwindigkeit")+"  "+rs.getString("Uhrzeit")+"  "+rs.getString("Datum"));

			}
			conn.close();
			t = true;
		} catch (SQLException e) {
			t = false;
			System.out.println("error");
		}
		return t;
	}
	public void insertDatabase(String tablename,String[] values){
		String val="'";
		for(int i =0;i<values.length;i++){
			if(i==values.length-1){
				val = val + values[i] + "'";
			}else{
				val = val + values[i] + "','";
			}
		}
		System.out.println(val);
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			/**
			id = id +1;
			val = "'"+id +"',"+ val;
			System.out.println(val);
			**/
			stmt.execute("INSERT INTO "+tablename+" VALUES ("+val+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public void updateDatabase(String tablename,String[] values){

		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			/**
			 id = id +1;
			 val = "'"+id +"',"+ val;
			 System.out.println(val);
			 **/
			stmt.execute("DELETE FROM "+tablename+" WHERE IP = '" + values[0] + "' AND RouterIP = '" + values[1]+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
