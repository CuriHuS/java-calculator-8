package calculator.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력 담당 클래스
 */
public class Input {
    private final static String READ_INPUT_MESSAGE = "덧셈할 문자열을 입력해주세요.";

    /**
     * 사용자에게 메시지를 출력하고 콘솔로부터 한 줄을 입력 받아 반환하는 함수
     */
    public String readInput() {
        System.out.println(READ_INPUT_MESSAGE);
        return Console.readLine();
    }
}
