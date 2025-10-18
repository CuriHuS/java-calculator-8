package calculator.domain;

import calculator.util.StringParser;

/**
 * 문자열 덧셈 계산기의 비즈니스 로직 수행 클래스
 */
public class Calculator {
    private final StringParser stringParser = new StringParser();

    /**
     * 주어진 문자열을 파싱하여 숫자들의 합을 계산합니다.
     */
    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        Numbers numbers = stringParser.parse(input);
        return numbers.sum();
    }
}