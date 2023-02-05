package gb.org.views;


import gb.org.Loggers.SystemLogs;
import gb.org.methods.Calc_Complex;


import javax.swing.text.View;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewConsole {
    private Logger logger;
    public ViewConsole(SystemLogs logs) {
        this.logger = logs.getLogger();
    }
    public void run(){
        showHelp();
        while (true) {
            try {
                String command = prompt("Введите команду: ");
                gb.org.views.Commands com = Commands.valueOf(command.toUpperCase());
                logger.log(Level.INFO, String.format("Пользователь ввел команду: %s", command));
                if (com == Commands.EXIT) return;
                switch (com) {
                    case HELP:
                        showHelp();
                        break;
                    case COMPLEX_CALC:
                        complex_calc();
                        break;
                    case CALC:
                        calc();
                        break;
                }
            }
            catch(Exception ex) {
                System.out.println("Произошла ошибка " + ex.toString());
            }
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
    private int promptInt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextInt();
    }
    private void showHelp() {
        System.out.println("Список команд:");
        for(Commands c : Commands.values()) {
            System.out.println(String.format("%s - %s", c.getUseInfo(), c.getInfo()));
        }
    }
    private void complex_calc() {
        String op = prompt("Операция (*,/,+,-): ");
        int a = promptInt("Первое число (A): ");
        int b = promptInt("Первое число (B): ");
        int _a = promptInt("Второе число (A): ");
        int _b = promptInt("Второе число (B): ");
        switch(op) {
            case "*":
                System.out.println(String.format("Ответ: %s", Calc_Complex.mul(new Calc_Complex(a,b), new Calc_Complex(_a,_b))));
                break;
            case "/":
                System.out.println(String.format("Ответ: %s", Calc_Complex.div(new Calc_Complex(a,b), new Calc_Complex(_a,_b))));
                break;
            case "+":
                System.out.println(String.format("Ответ: %s", Calc_Complex.add(new Calc_Complex(a,b), new Calc_Complex(_a,_b))));
                break;
            case "-":
                System.out.println(String.format("Ответ: %s", Calc_Complex.sub(new Calc_Complex(a,b), new Calc_Complex(_a,_b))));
                break;
        }

    }
    private void calc() {
        String op = prompt("Операция (*,/,+,-): ");
        int a = promptInt("Первое число: ");
        int b = promptInt("Второе число: ");
        switch(op) {
            case "*":
                System.out.println(String.format("Ответ: %s", a*b));
                break;
            case "/":
                System.out.println(String.format("Ответ: %s", a/b));
                break;
            case "+":
                System.out.println(String.format("Ответ: %s", a+b));
                break;
            case "-":
                System.out.println(String.format("Ответ: %s", a-b));
                break;
        }
    }
}
