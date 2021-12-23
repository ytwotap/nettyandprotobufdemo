package com.yt.capter.capter_3_visibility.thread_seal.use_volitatle;

import com.yt.capter.tool.UnThreadSafe;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author: YT
 * @date: 2021/12/23/023
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger integer) {
        if (lastNumber == null || lastNumber.equals(integer)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
