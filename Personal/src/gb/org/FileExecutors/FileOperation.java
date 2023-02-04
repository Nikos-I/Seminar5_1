package gb.org.FileExecutors;

import java.util.List;

public interface FileOperation {
    List<String> readAllLines();

    void saveAllLines(List<String> lines);

    void updateLine(String id, String fieldName, String fieldValue);
    FileType getFileType();
}
