package com.jitendra.util;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public  class SumUtil {
     public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i <10; i++) {
            long start = System.nanoTime();
            adder.apply(n);
            long end = System.nanoTime();
            long timeTaken = end - start;
            if (timeTaken < fastest) {
                fastest = timeTaken;
            }
        }

        return fastest / 1_000_000;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
         return LongStream.rangeClosed(1, n)
                 .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long sideEffectParallelSum(long n) {
         LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(Accumulator::add);

         System.out.println("total is " + Accumulator.total);
         return Accumulator.total;

    }

    private static class Accumulator {
         private static long total = 0;
         private static void add(long value) {
             total += value;
         }
    }

    public static long sequentialSum(long[] numbers, int start, int end) {
         long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += numbers[i];
        }

         return sum;
    }
}
