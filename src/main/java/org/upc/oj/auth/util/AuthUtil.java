package org.upc.oj.auth.util;

import com.alibaba.fastjson.JSON;
import org.upc.oj.auth.OnlineJudgeToken;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthUtil {
    private static String  SECURE_KEY="7.11-7.29 in summer2022";
    private static Base64.Encoder base64_encoder = Base64.getEncoder();
    private static Base64.Decoder base64_decoder = Base64.getDecoder();
    public static void main(String[] args) {
        OnlineJudgeToken ojt = new OnlineJudgeToken();
        ojt.setUserName("yqs");
        ojt.setIdentity("admin");
        String token = geneToken(ojt);
        System.out.println(token);
        System.out.println(parseToken(token));
    }
    private static byte[] encryptDES(byte[] data,byte[] key) throws Exception{
        //生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        //从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
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
        return sb.toString().getBytes();
    }
    private static byte[] decryptDES(byte[] arrB, byte[] key) throws Exception {
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            try {
                arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
            } catch (Exception ignored) {
            }
        }
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(arrOut);
    }
    private static String decryptBASE64(String data, String key) throws Exception {
        byte[] desByte = decryptDES(base64_decoder.decode(data),key.getBytes(StandardCharsets.UTF_8));
        return new String(desByte,StandardCharsets.UTF_8);
    }
    private static String encryptBASE64(String data,String key) throws Exception {
        byte[] res=encryptDES(data.getBytes(StandardCharsets.UTF_8),key.getBytes(StandardCharsets.UTF_8));
        return base64_encoder.encodeToString(res);
    }
    public static String geneToken(OnlineJudgeToken obj){
        try {
            return encryptBASE64(JSON.toJSONString(obj),SECURE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
    public static OnlineJudgeToken parseToken(String token){
        try {
            String jsonStr=decryptBASE64(token,SECURE_KEY);
            return JSON.parseObject(jsonStr,OnlineJudgeToken.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
