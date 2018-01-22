package com.jitendra;

import com.jitendra.util.SumUtil;

public class ParallelStreamIteratorSum {
    public static void main(String[] args) {
        long timeTaken = SumUtil.measureSumPerf(SumUtil::parallelSum, 10_000_000);
        System.out.println(timeTaken);

    }
}
