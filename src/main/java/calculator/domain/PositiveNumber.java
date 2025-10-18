package calculator.domain;

/**
 * 0 이상의 정수를 나타내는 값 객체
 */
public class PositiveNumber {

    private static final int MIN_VALUE = 0;
    private final int value;

    /**
     * 문자열 토큰 기반으로 객체를 생성하는 함수
     */
    public PositiveNumber(String token) {
        int number = parseNumber(token);
        validatePositive(number);
        this.value = number;
    }

    /**
     * 문자열을 정수로 변환하는 함수
     */
    private int parseNumber(String token) {
        try {
            return Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값 포함");
        }
    }

    /**
     * 숫자가 양수인지 검증하는 함수
     */
    private void validatePositive(int number) {
        if (number < MIN_VALUE) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }

    /**
     * PositiveNumber가 가진 정수 값을 반환하는 함수
     */
    public int getValue() {
        return value;
    }
}
