package db;

import java.sql.*;

public class DB {

    private static Connection conn = null;

    // URL de conexão corrigida e simplificada
    private static final String URL = "jdbc:sqlserver://serverfidelis.database.windows.net:1433;databaseName=Projeto_CD_java;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    // Nome de usuário com o formato completo para Azure SQL Database
    private static final String USUARIO = "ADM@serverfidelis"; // Verifique se este é o seu login completo do servidor
    private static final String SENHA = "[Q;l0nvB58]e"; // Cuidado com hardcoding da senha!

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Carrega o driver JDBC correto para SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (ClassNotFoundException e) {
                // Captura exceções se o driver não for encontrado (JAR ausente ou nome errado)
                throw new DbException("Driver JDBC do SQL Server não encontrado: " + e.getMessage());
            } catch (SQLException e) {
                // Captura outras exceções SQL (problemas de conexão, credenciais, firewall, etc.)
                throw new DbException("Erro de conexão ao banco de dados: " + e.getMessage());
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