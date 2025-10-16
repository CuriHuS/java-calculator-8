package calculator.utils;

public class Validator {

    private Validator() {
        // 인스턴스 생성 방지
    }

    /**
     * 숫자 유효성 검증 (음수 확인)
     */
    public static void validateNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
        }
    }

    /**
     * 구분자 형식 검증
     */
    public static void validateDelimiterFormat(int delimiterEndIndex) {
        if (delimiterEndIndex == -1) {
            throw new IllegalArgumentException("잘못된 형식입니다.");
        }
    }

    /**
     * 구분자 비어있지 않은지 검증
     */
    public static void validateDelimiterNotEmpty(String delimiter) {
        if (delimiter == null || delimiter.isEmpty()) {
            throw new IllegalArgumentException("구분자가 없습니다.");
        }
    }

    /**
     * 입력 문자열 검증
     */
    public static void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값이 null입니다.");
        }
    }
}