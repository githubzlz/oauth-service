package com.zlz.oauth.common.util;

import com.zlz.oauth.common.enums.OauthBizErrorEnum;
import com.zlz.oauth.common.exception.OauthBizException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 密码处理
 *
 * @author zhulinzhong
 * @date 2022-04-01 20:23:26
 */
public class PassWordUtil {

    private PassWordUtil() {
    }

    private static final Integer TWO = 2;

    private static final String AES = "AES";

    /**
     * 加密方法
     *
     * @param sSrc 明文
     * @param sKey 密钥
     * @return 加密后的16进制字符串
     */
    public static String encrypt(String sSrc, String sKey) {

        try {
            //非空检查
            if (sSrc == null || sKey == null) {
                throw new OauthBizException("加密失败,数据或密码不能为空");
            }

            //密钥生成器初始化
            KeyGenerator kGen = KeyGenerator.getInstance("AES");
            kGen.init(128, new SecureRandom(sKey.getBytes()));
            SecretKeySpec sKeySpec = new SecretKeySpec(kGen.generateKey().getEncoded(), AES);

            //"AES算法/分组密码模式/补码方式"
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));

            //转化为16进制返回
            return bytesToHex(encrypted);
        } catch (Exception e) {
            throw new OauthBizException("加密失败");
        }
    }

    /**
     * 解密方法
     *
     * @param sSrc 密文
     * @param sKey 密钥
     * @return (String)
     */
    public static String decrypt(String sSrc, String sKey) {

        // 判断Key是否正确
        if (sKey == null || sSrc == null) {
            throw new OauthBizException("解密失败,数据或密码不能为空");
        }
        try {
            //获取密钥，用于Cipher的初始化
            KeyGenerator kGen = KeyGenerator.getInstance("AES");
            kGen.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = kGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec sKeySpec = new SecretKeySpec(enCodeFormat, "AES");

            //初始化Cipher
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);

            //转换为字节数组
            byte[] encrypted1 = hexToByteArray(sSrc);
            byte[] original = cipher.doFinal(encrypted1);

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算加密密码
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String pwd(String pwd, String salt) {
        return md5Hex(pwd, salt);
    }

    /**
     * 验证
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static boolean verify(String pwd, String salt, String rel) {
        return rel.equals(md5Hex(pwd, salt));
    }

    /**
     * 获取随机盐值
     *
     * @return
     */
    public static String salt() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     *
     * @param str 要加密的内容
     * @return String 返回加密后的十六进制字符串
     */
    private static String md5Hex(String str, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hexToByteArray(salt));
            byte[] digest = md.digest(str.getBytes());
            return bytesToHex(digest);
        } catch (Exception e) {
            throw new OauthBizException(OauthBizErrorEnum.PWD_ERROR);
        }
    }

    /**
     * 将字节数组转为16进制字符串
     *
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < TWO) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 16进制转化为字节
     *
     * @param inHex
     * @return
     */
    private static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * 16进制字符串转换为字节数组
     *
     * @param inHex
     * @return
     */
    private static byte[] hexToByteArray(String inHex) {
        int hexLen = inHex.length();
        byte[] result;
        if (hexLen % TWO == 1) {
            //奇数
            hexLen++;
            result = new byte[(hexLen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexLen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexLen; i += TWO) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }
}
