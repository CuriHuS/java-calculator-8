package calculator;

import calculator.view.Input;

public class Application {

    public static String DEFAULT_REGEX = ",|:";

    public static void main(String[] args) {
        try {
            Input input = new Input(); // Input Class 바인딩
            String s = input.readInput();
            int result = calculate(s);

            System.out.println("결과: " + result);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * 문자열 계산 함수
     */
    public static int calculate(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }
        // 구분자, 숫자 문자열 분리
        String delimiter = DEFAULT_REGEX;
        String numbers = s;

        // 커스텀 구분자 처리
        if (s.startsWith("//")) {
            int delimiterEndIndex = s.indexOf("\n");
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("잘못된 형식입니다.");
            }

            String customDelimiter = s.substring(2, delimiterEndIndex);
            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("구분자가 없다");
            }

            // 정규식 특수문자 이스케이프 처리
            delimiter = escapeRegex(customDelimiter);
            numbers = s.substring(delimiterEndIndex + 1);
        }

        return sum(numbers, delimiter);
    }

    /**
     * 정규식 특수문자 이스케이프 처리
     */
    private static String escapeRegex(String s) {
        return s.replaceAll("([\\\\+*?\\[\\]{}()^$.|])", "\\\\$1");
    }

    /**
     * 문자열을 구분자로 분리하여 합산
     */
    private static int sum(String numbers, String delimiter) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String regex = DEFAULT_REGEX + "|" + delimiter;
        String[] tokens = numbers.split(regex);

        int sum = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = parseNumber(token);
                validateNumber(number);
                sum += number;
            }
        }

        return sum;
    }

    /**
     * 문자열을 숫자로 반환하는 함수
     */
    private static int parseNumber(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }

    /**
     * 숫자 유효성 검증(음수 확인)
     */
    private static void validateNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
        }
    }
}
