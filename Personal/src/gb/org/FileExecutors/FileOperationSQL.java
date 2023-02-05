package gb.org.FileExecutors;

import gb.org.database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileOperationSQL implements FileOperation {

    private DatabaseManager databaseManager;

    public FileOperationSQL(String fileName) {
        this.databaseManager = new DatabaseManager(fileName);
    }

    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        ResultSet resultSet = this.databaseManager.getResult("SELECT * FROM users");
        try {
            while (resultSet.next())
                lines.add(String.format("%s;%s;%s;%s", resultSet.getString("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phone")));
        } catch (SQLException ex) {
        }
        return lines;
    }

    public void updateLine(String id, String fieldName, String fieldValue) {
        String execStr = "UPDATE users SET " + fieldName + "='" + fieldValue + "' WHERE id=" + id;
        this.databaseManager.update(String.format(execStr));
    }

    public void saveAllLines(List<String> lines) {
        this.databaseManager.update("Delete from users");
        for (String line : lines) {
            String[] user_info = line.split(";");
            String id = user_info[0];
            String firstName = user_info[1];
            String lastName = user_info[2];
            String phone = user_info[3];
            this.databaseManager.update(String.format("INSERT INTO users (id, firstName, lastName, phone) VALUES(%s,'%s','%s','%s')", id, firstName, lastName, phone));
        }
    }

    @Override
    public FileType getFileType() {
        return FileType.SQLite;
    }
}