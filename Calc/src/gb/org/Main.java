package gb.org;

import gb.org.Loggers.SystemLogs;
import gb.org.views.ViewConsole;

public class Main {

    public static void main(String[] args) {
        SystemLogs logs = new SystemLogs();
        ViewConsole viewConsole = new ViewConsole(logs);
        viewConsole.run();
    }
}
