package kr.co.egov.test;

import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;

public class ParallelSpeedTest {

    /**
     * 제곱 연산 처리 속도 비교
     * */

    // 숫자의 제곱 계산
    public static long performOperation(long value) {
        return value * value;
    }

    // 직렬 처리
    @Test
    public void testSerialProcessing() {
        long startTime = System.currentTimeMillis();

        LongStream.rangeClosed(1, 100_000_000)
            .forEach(ParallelSpeedTest::performOperation);

        long endTime = System.currentTimeMillis();
        System.out.println("직렬 처리 시간: " + (endTime - startTime) + "ms");
    }

    // 병렬 처리
    @Test
    public void testParallelProcessing() {
        long startTime = System.currentTimeMillis();

        LongStream.rangeClosed(1, 100_000_000)
            .parallel()
            .forEach(ParallelSpeedTest::performOperation);

        long endTime = System.currentTimeMillis();
        System.out.println("병렬 스트림 처리 시간: " + (endTime - startTime) + "ms");
    }

    /**
     * 피보나치 수열 연산 처리 속도 비교
     * */

    // 피보나치 수열의 n번째 항을 계산 (재귀적 방법)
    public static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }

    // 피보나치 수열 직렬 연산
    @Test
    public void testSerialProcessing2() {
        long startTime = System.currentTimeMillis();

        LongStream.rangeClosed(1, 50)  // 피보나치 계산은 연산 부하가 높으므로 숫자 범위를 조정할 필요가 있습니다.
            .forEach(n -> fibonacci((int) n));

        long endTime = System.currentTimeMillis();
        System.out.println("직렬 처리 시간: " + (endTime - startTime) + "ms");
    }

    // 피보나치 수열 병렬 연산
    @Test
    public void testParallelProcessing2() {
        long startTime = System.currentTimeMillis();

        LongStream.rangeClosed(1, 50)  // 피보나치 계산은 연산 부하가 높으므로 숫자 범위를 조정할 필요가 있습니다.
            .parallel()
            .forEach(n -> fibonacci((int) n));

        long endTime = System.currentTimeMillis();
        System.out.println("병렬 스트림 처리 시간: " + (endTime - startTime) + "ms");
    }

}
