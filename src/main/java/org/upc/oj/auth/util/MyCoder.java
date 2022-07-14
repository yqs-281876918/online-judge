package org.upc.oj.auth.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class MyCoder {
    private Base64.Encoder base64_encoder = Base64.getEncoder();
    private Base64.Decoder base64_decoder = Base64.getDecoder();

    /**
     * 将一个字符串进行DES加密
     *
     * @param source 源数据
     * @param key    关键字
     * @return 加密后的字符串
     * @throws Exception 所有加密过程中可能发生的错误
     */
    public String encryptDES(String source, String key) throws Exception {
        //源数组
        byte[] data = source.getBytes(StandardCharsets.UTF_8);
        //关键字数组
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        //生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        //从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(keyBytes);
        //创建一个密钥工厂，然后用他把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        byte[] arrB = cipher.doFinal(data);
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int b : arrB) {
            int intTmp = b;
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将一个DES字符串进行解密
     *
     * @param source 原数据
     * @param key    关键字
     * @return 解密后的字符串
     * @throws Exception 所有解密过程中可能发生的错误
     */
    public String decryptDES(String source, String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] arrB = source.getBytes(StandardCharsets.UTF_8);
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(keyBytes);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return new String(cipher.doFinal(arrOut),StandardCharsets.UTF_8);
    }

    /**
     * 将一个字符串加密成BASE64字符串
     *
     * @param data 原文
     * @param key  关键字
     * @return BASE64字符串
     * @throws Exception 所有加密过程中可能发生的错误
     */
    public String encryptBASE64(String data, String key) throws Exception {
        String res = encryptDES(data, key);
        return base64_encoder.encodeToString(res.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将一个BASE64字符串解密成原文
     *
     * @param data BASE64字符串
     * @param key  关键字
     * @return 原文
     * @throws Exception 所有解密过程中可能发生的错误
     */
    public String decryptBASE64(String data, String key) throws Exception {
        return decryptDES(new String(base64_decoder.decode(data), StandardCharsets.UTF_8), key);
    }
}
