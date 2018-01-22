package com.jitendra;

import com.jitendra.util.SumUtil;

public class ParallelStreamRangedSum {
    public static void main(String[] args) {
        long timeTaken = SumUtil.measureSumPerf(SumUtil::parallelRangedSum, 10_000_000);
        System.out.println(timeTaken);
    }
}
