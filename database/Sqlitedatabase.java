package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;

import matrix.Matrix;

public class Sqlitedatabase {
	
	public Connection conn;
	
	public Sqlitedatabase() {
		
		this.conn = null;
		String fileName = "projekt.db";
		String url = "jdbc:sqlite:" + fileName;
		String sql = "CREATE TABLE IF NOT EXISTS matrices (\n"
				+ " id integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n"
				+ " name VARCHAR(30), \n"
				+ " n_rows integer NOT NULL, \n"
				+ " n_columns integer NOT NULL, \n"
				+ " datetime text, \n"
				+ " elements text "
				+ ");";
		try {
			this.conn = DriverManager.getConnection(url);
			Statement stmt = this.conn.createStatement();
			if ( this.conn != null) { stmt.execute(sql);}
			} catch (SQLException e) {
				System.out.println(e.getMessage()); }
	}
	
	public void insert_matrix(Matrix m) {
		
		
		String elements = "";
        for(double[] row : m.elements) {
        	elements += Arrays.toString(row);
        	elements += " ";
        }
        String name ="generic name"; // = m.name;
        
		String sql = "INSERT INTO matrices(name, n_rows, n_columns, datetime, elements) "
				+ "VALUES (?,?,?,datetime('now', 'localtime'), ?);";
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, m.numOfRows);
			pstmt.setInt(3, m.numOfCols);
			pstmt.setString(4, elements);
			pstmt.executeUpdate();
			
		}
		catch(SQLException e){
		
		}
	}
	
	public List<Matrix> selectAll() {
		
		String sql = "SELECT id, name, n_rows, n_columns, datetime, elements "
				+ "FROM matrices ORDER BY datetime;";
		
		List<Matrix> MatrixList = new ArrayList<Matrix>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next()) {
				MatrixList.add(new Matrix(rs.getInt("n_rows"),
						rs.getInt("n_columns"),
						rs.getString("elements"))/*, rs.getString("name")*/);
			}
			
		}
		catch(SQLException e){
		
		}
		
		return MatrixList;
	}
	
	
}
