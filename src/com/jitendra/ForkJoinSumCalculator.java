package com.jitendra;

import com.jitendra.util.SumUtil;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ForkJoinSumCalculator extends RecursiveTask<Long>{
    private long[] numbers;
    private int start;
    private int end;
    private final static int threshold = 10_000;

    private ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length - 1);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    public static void main(String[] args) {
        System.out.println("time taken to compute sum is " + SumUtil.measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");
    }

    public static long forkJoinSum(long n) {
        ForkJoinSumCalculator forkJoinSumCalculatorTask = new ForkJoinSumCalculator(LongStream.rangeClosed(1, n).toArray());
        long total = new ForkJoinPool().invoke(forkJoinSumCalculatorTask);
        System.out.println("sum of first " + n + " numbers is " + total);
        return total;
    }

    @Override
    protected Long compute() {
        int length = end - start + 1;
        if (length <= threshold) {
            return SumUtil.sequentialSum(numbers, start, end);
        } else {

            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
            leftTask.fork();

            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2 + 1, end);
            long rightSum = rightTask.compute();
            long leftSum = leftTask.join();

            return rightSum + leftSum;
        }
    }
}
