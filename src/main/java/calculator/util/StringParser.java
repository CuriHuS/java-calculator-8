package calculator.util;

import calculator.domain.Numbers;

/**
 * 입력 문자열을 분석하여 숫자 토큰으로 분리하는 역할을 담당하는 클래스
 */
public class StringParser {
    /**
     * 입력 문자열에서 구분자를 식별하고 이를 기준으로 문자열을 분리하여 Numbers 객체 생성
     */
    public Numbers parse(String input) {
        // 입력 문자열에 포함된 "\\n"을 실제 개행 문자로 변경
        input = input.replace("\\n", "\n");

        Delimiter delimiter = Delimiter.from(input);
        String numbersPart = Delimiter.extractNumbersPart(input);

        String[] tokens = splitByDelimiter(numbersPart, delimiter);

        return new Numbers(tokens);
    }

    /**
     * 주어진 구분자를 기준으로 텍스트를 분리하여 문자열 배열을 반환하는 함수
     */
    private String[] splitByDelimiter(String text, Delimiter delimiter) {
        return text.split(delimiter.getValue());
    }
}