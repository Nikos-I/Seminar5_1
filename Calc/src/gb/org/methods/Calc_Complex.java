package gb.org.methods;

public class Calc_Complex {

    private int a;
    private int b;

    public Calc_Complex(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }

    public static Calc_Complex add(Calc_Complex num1, Calc_Complex num2) {
        return new Calc_Complex(num1.a + num2.a, num1.b + num2.b);
    }

    public static Calc_Complex sub(Calc_Complex num1, Calc_Complex num2) {
        return new Calc_Complex(num1.a - num2.a, num1.b - num2.b);
    }

    public static Calc_Complex mul(Calc_Complex num1, Calc_Complex num2) {
        int a = num1.a, b = num1.b, c = num2.a, d = num2.b;
        return new Calc_Complex(a * c - b * d, b * c + a * d);
    }

    public static Calc_Complex div(Calc_Complex num1, Calc_Complex num2) {
        int a = num1.a, b = num1.b, c = num2.a, d = num2.b;
        return new Calc_Complex((a * c + b * d) / (c * c + d * d),
                              (b * c - a * d) / (c * c + d * d));
    }


}