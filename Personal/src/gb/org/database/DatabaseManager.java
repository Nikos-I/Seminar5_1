package gb.org.database;

import java.io.File;
import java.nio.file.Paths;
import java.sql.*;


public class DatabaseManager {

    private Connection connection;
    private String fileName;

    public DatabaseManager(String fileName) {
        this.fileName = fileName;
        openConnection();
        loadTables();
    }

    private void openConnection() {
        if (!isConnected()) {
            try {
                Class.forName("org.sqlite.JDBC");
                String connectStr = "jdbc:sqlite:" + Paths.get("").toAbsolutePath() + File.separator + fileName;
                connection = DriverManager.getConnection(connectStr);
            }
            catch (SQLException ex) { }
            catch (ClassNotFoundException ex) { }
        }
    }

    private void loadTables() {

        if (isConnected()) {

            update("CREATE TABLE IF NOT EXISTS users(id INT(64), firstName VARCHAR(64), lastName VARCHAR(64), phone VARCHAR(16), PRIMARY KEY (id))");
        }
    }

    public void update(String query) {
        if (isConnected()) {
            try {
                connection.prepareStatement(query).executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet getResult(String query) {
        if (isConnected()) {
            try {
                return connection.prepareStatement(query).executeQuery();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public boolean isConnected() {
        return connection != null;
    }
}
