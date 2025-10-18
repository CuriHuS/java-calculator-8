package calculator.util;

/**
 * 문자열에서 사용될 구분자를 식별 담당 클래스 기본 구분자 및 커스텀 구분자 로직을 모두 처리
 */
public class Delimiter {
    private static final String DEFAULT_DELIMITER_REGEX = ",|:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private static final int CUSTOM_DELIMITER_START_INDEX = CUSTOM_DELIMITER_PREFIX.length();

    private final String value;

    private Delimiter(String value) {
        this.value = value;
    }

    /**
     * 입력 문자열 분석하여 적절한 Delimiter 객체 생성
     */
    public static Delimiter from(String input) {
        if (isCustom(input)) {
            String customDelimiter = extractCustomDelimiter(input);
            return new Delimiter(DEFAULT_DELIMITER_REGEX + "|" + escapeRegex(customDelimiter));
        }
        return new Delimiter(DEFAULT_DELIMITER_REGEX);
    }

    /**
     * 정규식에서 특별한 의미를 갖는 문자를 일반 문자로 처리되도록 하는 함수
     */
    private static String escapeRegex(String str) {
        // 정규식에서 특별한 의미를 갖는 문자들을 이스케이프 처리
        String[] specialChars = {"\\", "+", "*", "?", "[", "]", "{", "}", "(", ")", "^", "$", ".", "|"};
        for (String specialChar : specialChars) {
            if (str.contains(specialChar)) {
                str = str.replace(specialChar, "\\" + specialChar);
            }
        }
        return str;
    }

    /**
     * 커스텀 구분자 포함 여부 확인
     */
    public static boolean isCustom(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    /**
     * 입력 문자열에서 커스텀 구분자 추출 함수
     */
    private static String extractCustomDelimiter(String input) {
        int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        if (delimiterEndIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }
        String customDelimiter = input.substring(CUSTOM_DELIMITER_START_INDEX, delimiterEndIndex);
        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
        }
        return customDelimiter;
    }

    /**
     * 입력 문자열에서 숫자 부분만 추출하는 함수
     */
    public static String extractNumbersPart(String input) {
        if (isCustom(input)) {
            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            return input.substring(delimiterEndIndex + 1);
        }
        return input;
    }

    /**
     * 현재 Delimiter 객체가 가진 정규식 구분자 값 반환 함수
     */
    public String getValue() {
        return value;
    }
}