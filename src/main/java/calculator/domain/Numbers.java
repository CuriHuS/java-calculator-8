package calculator.domain;

/**
 * PositiveNumber 객체들의 목록 관리 클래스
 */
public class Numbers {
    private final PositiveNumber[] numbers;

    /**
     * 문자열 토큰 배열 기반 Numbers 객체 생성
     */
    public Numbers(String[] tokens) {
        int count = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                count++;
            }
        }

        // 2. 크기에 맞는 배열을 생성하고 PositiveNumber 객체를 저장
        this.numbers = new PositiveNumber[count];
        int index = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                numbers[index] = new PositiveNumber(token);
                index++;
            }
        }
    }

    /**
     * Numbers 배열 내 모든 PositiveNumber 값의 합계를 계산하여 반환하는 함수
     */
    public int sum() {
        int total = 0;
        for (PositiveNumber number : numbers) {
            total += number.getValue();
        }
        return total;
    }
}