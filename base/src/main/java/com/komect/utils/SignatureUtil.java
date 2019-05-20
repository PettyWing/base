package com.komect.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 解析App签名信息的辅助类，用于防止二次打包
 * Author by hf
 * Create on 16/10/21
 */
public class SignatureUtil {
    private static final String TAG = "SignatureUtil";
    //    private static final String KEYSTORE_MD5 = "6B:4B:38:47:36:01:44:6A:6A:12:79:31:A2:B8:43:2D";
    //    private static final String KEYSTORE_SHA1 = "6C:D9:1E:A4:E8:7D:A4:3D:D3:03:E4:1E:A5:87:95:0D:51:73:84:66";
    private static final String KEYSTORE_SHA1 = "6CD91EA4E87DA43DD303E41EA587950D51738466";
    private static final String KEYSTORE_SHA256 = "490BD7FFF5540390B9045CAFFE1CE424B97A72130217245FC836BAD64F1AAD5E";

    /**
     * 检查当前应用的签名信息是否和开发原定的签名一致
     *
     * @param context
     * @return
     */
    public static boolean checkSignature(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            if (signatures != null && signatures.length > 0) {
                //                String signatureStr;
                String certHashSha1;
                String certHashSha256;
                for (Signature signature : signatures) {
                    //                    signatureStr = signature.toCharsString().toUpperCase();
                    certHashSha1 = bytesToHexString(getCertHash(signature, "SHA-1")).toUpperCase();
                    certHashSha256 = bytesToHexString(getCertHash(signature, "SHA-256")).toUpperCase();
                    Log.d(TAG,
                            //"md5: " + signatureStr + "\n" +
                            "SHA1: " + certHashSha1 + "\nSHA256: " + certHashSha256);
                    if (KEYSTORE_SHA1.equals(certHashSha1) && KEYSTORE_SHA256.equals(certHashSha256)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从签名里取出SHA1或者SHA256的信息
     *
     * @param signature SHA-1或者SHA-256
     * @param algo
     * @return
     */
    private static byte[] getCertHash(Signature signature, String algo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            return md.digest(signature.toByteArray());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            //            Log.e(TAG, "NoSuchAlgorithmException: " + ex);
        }
        return null;
    }

    /**
     * 将byte数组转换成HEX十六进制的字符串
     *
     * @param bytes
     * @return
     */

    private static String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder ret = new StringBuilder(2 * bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            int b;
            b = 0x0f & (bytes[i] >> 4);
            ret.append("0123456789abcdef".charAt(b));
            b = 0x0f & bytes[i];
            ret.append("0123456789abcdef".charAt(b));
        }
        return ret.toString();
    }

    /*private static String parseLength(String data) {
        int length = data.length() / 2;
        //如果因为包名长度过小，使得length小于15（十六进制F），需要补一位0凑出两位HEX值
        if (length <= 15) {
            return "0" + Integer.toHexString(length);
        } else {
            return Integer.toHexString(length);
        }
    }*/
}
