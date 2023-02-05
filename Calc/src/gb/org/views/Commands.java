package gb.org.views;

public enum Commands {
    COMPLEX_CALC("complex_calc", "Считает комплексные числа", 2),
    CALC("calc", "Считает обычные числа", 2),
    HELP("help", "Помощь", 1),
    EXIT("exit", "Выход", 1);
    private String use_info;
    private String info;
    private int arg_length;

    Commands(String use_info, String info, int arg_length) {
        this.use_info = use_info;
        this.info = info;
        this.arg_length = arg_length;
    }

    public String getInfo() {
        return this.info;
    }
    public String getUseInfo() {
        return this.use_info;
    }
    public int getLenArg() {
        return arg_length;
    }

}
