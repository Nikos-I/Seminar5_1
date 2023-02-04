package gb.org.model;

import gb.org.FileExecutors.FileOperation;
import gb.org.FileExecutors.FileType;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper;
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
        if(this.fileOperation.getFileType() == FileType.SQLite) {
            this.mapper = new UserMapperSQL();
        }
        else {
            this.mapper = new UserMapperTXT();
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }
    @Override
    public void UpdateUser(User user, Fields field, String param) {
        String fieldName = "";

        if(field == Fields.FIO) {
            user.setLastName(param);
            fieldName = "FIO";
        }
        else if(field == Fields.NAME) {
            user.setFirstName(param);
            fieldName = "NAME";
        }
        else if(field == Fields.TELEPHONE) {
            user.setPhone(param);
            fieldName = "TELEPHONE";
        }
        List<String> lines = new ArrayList<>();
        List<User> users = getAllUsers();
        for (User item: users) {
            if(user.getId().equals(item.getId())) {
                lines.add(mapper.map(user));
            }
            else {
                lines.add(mapper.map(item));
            }
        }
        fileOperation.updateLine(user.getId(), fieldName, param);
    }
    @Override
    public void DeleteUser(User user) {
        List<String> lines = new ArrayList<>();
        List<User> users = getAllUsers();
        for (User item: users) {
            if(!user.getId().equals(item.getId())) {
                lines.add(mapper.map(item));
            }
        }
        System.out.println(lines);
        fileOperation.saveAllLines(lines);
    }
//    private void saveUser(User user) {
//        List<String> lines = new ArrayList<>();
//        List<User> users = getAllUsers();
//        for (User item: users) {
//            if(user.getId().equals(item.getId())) {
//                lines.add(mapper.map(user));
//            }
//            else {
//                lines.add(mapper.map(item));
//            }
//        }
//        fileOperation.updateLine(user.getId(), );
//
//        //        fileOperation.saveAllLines(lines);
//    }
    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return id;
    }
}
