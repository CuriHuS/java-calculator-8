package calculator.domain;


public class Calculator {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";

    /**
     * 문자열을 분석하여 숫자들의 합을 계산한다.
     */
    public int calculate(String input) {
        // 빈 문자열 또는 null 처리
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // 입력 문자열에서 \\n을 실제 개행 문자로 변환
        input = input.replace("\\n", "\n");

        String delimiter = DEFAULT_DELIMITER;
        String numberString = input;

        // 커스텀 구분자 처리
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

            // "\n"이 없는 경우 잘못된 형식
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }

            // 커스텀 구분자 추출
            String customDelimiter = input.substring(
                    CUSTOM_DELIMITER_PREFIX.length(),
                    delimiterEndIndex
            );

            // 구분자가 비어있는 경우
            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }

            // 정규식 특수문자 이스케이프 처리
            delimiter = DEFAULT_DELIMITER + "|" + escapeRegex(customDelimiter);

            // "\n" 이후의 숫자 문자열 추출
            numberString = input.substring(delimiterEndIndex + 1);
        }

        return sumNumbers(numberString, delimiter);
    }

    /**
     * 정규식 특수문자를 이스케이프 처리한다.
     */
    public String escapeRegex(String str) {
        return str.replaceAll("([\\\\+*?\\[\\]{}()^$.|])", "\\\\$1");
    }

    /**
     * 구분자로 분리된 숫자들의 합을 계산한다.
     */
    public int sumNumbers(String numberString, String delimiter) {
        // 숫자 문자열이 비어있는 경우
        if (numberString.isEmpty()) {
            return 0;
        }

        String[] tokens = numberString.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = parseAndValidateNumber(token);
                sum += number;
            }
        }

        return sum;
    }

    /**
     * 문자열을 숫자로 변환하고 유효성을 검증한다.
     */
    public int parseAndValidateNumber(String token) {
        int number;

        try {
            number = Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
        }

        // 음수 검증
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
        }

        return number;
    }
}
