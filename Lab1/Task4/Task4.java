import java.lang.Math;
import java.util.Optional;
import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        var localization = new Localization(
                "Ларюшкин Сергей 21ВП1 - Web-программирование - Лабораторная работа №1",
                "При a=%d, b=%d, c=%d, n=%d, a^n + b^n = c^n: =>",
                "%1$d^%4$d + %2$d^%4$d ?= %3$d^%4$d <=> %5$d ?= %6$d",
                "Здесь мы видим, что левая часть и правая часть этих при этих a, b, c не сходится при n > 2 => для этих a, b, c нет натуральных решений a^n + b^n = c^n",
                "Кликните <Enter> чтобы продолжить или любой другой символ чтобы выйти из программы",
                "Введите пожалуйста числа a, b, c для формулы a^n + b^n = c^n, каждое число пишите на новой строке",
                "! Только учтите, что если введёте не числа, причём целые, мы сочтём это за команду выхода",
                "Вот, видимо и пришло время прощаться. Пока!"
        );

        header(localization);
        try (var scanner = new Scanner(System.in)) {
            for (var isRunning = true; isRunning; ) {
                var optionalAbc = readABC(localization, scanner);
                if (optionalAbc.isPresent()) {
                    writeEquationResult(localization, optionalAbc.get());
                } else {
                    farewell(localization);
                    isRunning = false;
                }
            }
        }
    }

    private static void header(Localization localization) {
        System.out.println(localization.mainHeader);
    }

    private static void writeEquationResult(Localization localization, ABC abc) {
        for (long n = 2; n <= 5; n++) {
            System.out.printf(localization.variablesPresentation + "%n", abc.a, abc.b, abc.c, n);
            long leftResult = (long)Math.pow(abc.a, n) + (long)Math.pow(abc.b, n);
            long rightResult = (long)Math.pow(abc.c, n);
            System.out.printf(localization.statementPresentation + "%n%n", abc.a, abc.b, abc.c, n, leftResult, rightResult);
        }
        System.out.println(localization.conclusionPresentation);
        System.out.println(localization.goNextPresentation);
    }

    private static Optional<ABC> readABC(Localization localization, Scanner scanner) {
        System.out.println(localization.inputHeaderInfo);
        System.out.println(localization.inputHeaderExitInfo);
        try {
            return Optional.of(new ABC(
                    Integer.parseInt(scanner.nextLine()),
                    Integer.parseInt(scanner.nextLine()),
                    Integer.parseInt(scanner.nextLine())
            ));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static void farewell(Localization localization) {
        System.out.println(localization.farewellMessage);
    }

    record Localization(
            String mainHeader,
            String variablesPresentation,
            String statementPresentation,
            String conclusionPresentation,
            String goNextPresentation,
            String inputHeaderInfo,
            String inputHeaderExitInfo,
            String farewellMessage
    ) {}

    record ABC(int a, int b, int c) {}
}
