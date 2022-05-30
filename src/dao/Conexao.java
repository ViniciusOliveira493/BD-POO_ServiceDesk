package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private String server = "localhost:3306";
	private String login = "root";
	private String senha = "123456";
	private String bd = "bdServiceDesk";
	private final String DB_ATUAL = "MariaDb";
	
	public Connection getConexao() {
		if(DB_ATUAL.equals("MariaDb")) {
			return getConexaoMDB();
		}else {
			return getConexaoSqlServer();
		}
	}
	
	//ainda não está funcionando
	public Connection getConexaoSqlServer() {
		String path = "jdbc:sqlserver://"+server+";"
        + "databaseName="+bd+";"
        + "user="+login+";"
        + "password="+senha+";"+
        "encrypt=true;trustServerCertificate=true";
       
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(path);
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Connection getConexaoMDB() {
		String path="jdbc:mariadb://"+server+"/"+bd;		
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(path,login,senha);
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public void close(Connection cn) {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
