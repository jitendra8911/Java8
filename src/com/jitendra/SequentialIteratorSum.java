package com.jitendra;

import com.jitendra.util.SumUtil;


public class SequentialIteratorSum {
     public static void main(String[] args) {
        long timeTaken = SumUtil.measureSumPerf(SumUtil::sequentialSum, 10_000_000);
        System.out.println(timeTaken);

    }
}
