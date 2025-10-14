package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        // 입력
        String s = input();

    }

    /**
     * 덧셈 문자열 입력 함수
     */
    public static String input() {
        System.out.print("덧셈할 문자열을 입력하세요: ");
        return Console.readLine();
    }
}
