package com.example.demo.sm;


import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.util.Arrays;

/**
 * @Author: swh
 * @Date: 2020 03 2020/3/21 15:16
 * @Version: 1.0
 */
public class SM3 {
    public static void main(String[] args) {
        byte[] message = "abc".getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(message,0,message.length);
        byte[] hash = new byte[sm3.getDigestSize()];
        sm3.doFinal(hash,0);
        String hexString = ByteUtils.toHexString(hash);
        System.out.println(hexString);
    }
}
