package db;

import java.sql.*;

public class DB {

    private static Connection conn = null;

    private static final String URL = "jdbc:mysql://192.168.15.4:3306/project_java";
    private static final String USUARIO = "usuario_remoto";
    private static final String SENHA = "1234567";

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // <- essa linha é ESSENCIAL
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (ClassNotFoundException e) {
                throw new DbException("Driver JDBC não encontrado: " + e.getMessage());
            } catch (SQLException e) {
                throw new DbException("Erro de conexão: " + e.getMessage());
            }
        }
        return conn;
    }


    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st!=null){
            try {
                st.close();
            }catch (SQLException e){
                throw  new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                throw  new DbException(e.getMessage());
            }
        }
    }


}