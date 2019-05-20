/* Project: KomectSport
 * 
 * File Created at 2016/11/10
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.komect.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * The type Base 64.
 *
 * @Type com.cmcc.komectsport.tools
 * @User wangyulong
 * @Desc Created by wangyulong on 2016/11/10.
 * @Version
 */
public class Base64 {
    private static final char[] LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
            .toCharArray();

    /**
     * Instantiates a new Base 64.
     */
    public Base64() {
    }

    /**
     * Encode string.
     *
     * @param data the data
     * @return the string
     */
    public static String encode(byte[] data) {
        byte start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);
        int end = len - 3;
        int i = start;
        int n = 0;

        int d;
        while (i <= end) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8 | data[i + 2] & 255;
            buf.append(LEGAL_CHARS[d >> 18 & 63]);
            buf.append(LEGAL_CHARS[d >> 12 & 63]);
            buf.append(LEGAL_CHARS[d >> 6 & 63]);
            buf.append(LEGAL_CHARS[d & 63]);
            i += 3;
            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8;
            buf.append(LEGAL_CHARS[d >> 18 & 63]);
            buf.append(LEGAL_CHARS[d >> 12 & 63]);
            buf.append(LEGAL_CHARS[d >> 6 & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            d = (data[i] & 255) << 16;
            buf.append(LEGAL_CHARS[d >> 18 & 63]);
            buf.append(LEGAL_CHARS[d >> 12 & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

    private static int decode(char c) {
        if (c >= 65 && c <= 90) {
            return c - 65;
        } else if (c >= 97 && c <= 122) {
            return c - 97 + 26;
        } else if (c >= 48 && c <= 57) {
            return c - 48 + 26 + 26;
        } else {
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException("unexpected code: " + c);
            }
        }
    }

    /**
     * Decode byte [ ].
     *
     * @param s the s
     * @return the byte [ ]
     */
    public static byte[] decode(String s) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            decode(s, bos);
        } catch (IOException var5) {
            throw new RuntimeException();
        }

        byte[] decodedBytes = bos.toByteArray();

        try {
            bos.close();
            bos = null;
        } catch (IOException var4) {
            System.err.println("Error while decoding BASE64: " + var4.toString());
        }

        return decodedBytes;
    }

    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;
        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= 32) {
                ++i;
            }

            if (i == len) {
                break;
            }

            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12)
                    + (decode(s.charAt(i + 2)) << 6) + decode(s.charAt(i + 3));
            os.write(tri >> 16 & 255);
            if (s.charAt(i + 2) == 61) {
                break;
            }

            os.write(tri >> 8 & 255);
            if (s.charAt(i + 3) == 61) {
                break;
            }

            os.write(tri & 255);
            i += 4;
        }

    }

}
/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016/11/10 wangyulong creat
 */
