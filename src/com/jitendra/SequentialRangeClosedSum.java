package com.jitendra;

import com.jitendra.util.SumUtil;

public class SequentialRangeClosedSum {
    public static void main(String[] args) {
        long timeTaken = SumUtil.measureSumPerf(SumUtil::rangedSum, 10_000_000);
        System.out.println(timeTaken);
    }
}
