package calculator.view;

/**
 * 계산 결과 출력 담당 클래스
 */
public class Output {

    private static final String RESULT_PREFIX = "결과 : ";

    /**
     * 계산 결과를 출력합니다.
     */
    public void printCalcResult(int result) {
        System.out.println(RESULT_PREFIX + result);
    }
}