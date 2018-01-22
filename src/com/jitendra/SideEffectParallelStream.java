package com.jitendra;

import com.jitendra.util.SumUtil;

public class SideEffectParallelStream {
    public static void main(String[] args) {
        long timeTaken = SumUtil.measureSumPerf(SumUtil::sideEffectParallelSum, 10_000_000);
        System.out.println(timeTaken);
    }
}
