package calculator;

import calculator.domain.Calculator;
import calculator.view.Input;
import calculator.view.Output;

public class Application {

    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();
        Calculator calculator = new Calculator();

        try {
            String inputString = input.readInput();
            int result = calculator.calculate(inputString);
            output.printCalcResult(result);
        } catch (IllegalArgumentException e) {
            throw e;  // 예외 발생 후 애플리케이션 종료
        }
    }

}