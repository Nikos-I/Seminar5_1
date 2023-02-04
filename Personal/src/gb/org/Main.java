package gb.org;

import gb.org.FileExecutors.*;
import gb.org.controllers.*;
import gb.org.model.*;
import gb.org.utils.*        ;
import gb.org.views.*;

public class Main {

    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationSQL("users.db"); //Здесь можно менять базу
        Repository repository = new RepositoryFile(fileOperation);
        Validate validate = new Validate();
        UserController controller = new UserController(repository,validate);
        ViewUser view = new ViewUser(controller, validate);
        view.run();
    }
}
