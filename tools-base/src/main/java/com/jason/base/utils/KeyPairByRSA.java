package com.jason.base.utils;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author WongChenHoll
 * @description RSA加密、解密、签名、验签
 * @date 2023-2-21 星期二 13:26
 **/
public class KeyPairByRSA {

    // 加密类型
    private static final String ENCRYPT_TYPE = "RSA";
    // 签名类型
    private static final String SING_TYPE = "SHA256WITHRSA";
    // 编码格式
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    // 业务数据
    private static final String DATA_CONTENT = "username=张三,age=25,address=上海市";

    public static void main(String[] args) throws Exception {
        // 1.生成公私钥
        KeyPair keyPair = createKeys();
        // 因为生成的密钥对包含不可见的字符，所以经过Base64编码，使其变成可见字符串
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        System.out.println("Base64编码的公钥：" + publicKey);
        System.out.println("Base64编码的私钥：" + privateKey + "\n");

        // 2.加密
        System.out.println("加密前的业务数据：" + DATA_CONTENT);
        String encryptData = encrypt(DATA_CONTENT, publicKey);
        System.out.println("加密后的数据：" + encryptData);

        // 3.解密
        String decryptData = decrypt(encryptData, privateKey);
        System.out.println("解密后的业务数据：" + decryptData + "\n");

        // 4.签名
        String sign = sign(DATA_CONTENT, privateKey);
        System.out.println("签名后的数据：" + sign);

        // 5.验签
        boolean verifySign = verifySign(sign, DATA_CONTENT, publicKey);
        System.out.println("验签结果：" + verifySign);

    }

    /**
     * 生成密钥对
     *
     * @return 密钥对
     * @throws NoSuchAlgorithmException 密钥算法不可用
     */
    public static KeyPair createKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPT_TYPE);
        keyPairGenerator.initialize(1024); // 定义密钥的长度为：2048
        return keyPairGenerator.genKeyPair();
    }

    /**
     * 根据公钥字符串用Base64解码生成公钥对象
     *
     * @param publicKey Base64编码后的公钥字符串
     * @return 公钥对象
     * @throws Exception 异常
     */
    public static PublicKey decoderPublicKey(String publicKey) throws Exception {
        byte[] decode = Base64.getDecoder().decode(publicKey.getBytes());
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_TYPE);
        return keyFactory.generatePublic(new X509EncodedKeySpec(decode));
    }

    public static PrivateKey decoderPrivateKey(String privateKey) throws Exception {
        byte[] decode = Base64.getDecoder().decode(privateKey.getBytes()); // 将私钥解码
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_TYPE);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));

    }

    /**
     * 加密
     *
     * @param data      业务数据
     * @param publicKey Base64编码后的公钥
     * @return 经过Base64编码的加密后的数据
     * @throws Exception 异常
     */
    public static String encrypt(String data, String publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, decoderPublicKey(publicKey));
        byte[] bytes = cipher.doFinal(data.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解密
     *
     * @param encryptData 经过Base64编码的加密后的数据
     * @param privateKey  Base64编码后的私钥
     * @return 业务数据
     * @throws Exception 异常
     */
    public static String decrypt(String encryptData, String privateKey) throws Exception {
        byte[] decode = Base64.getDecoder().decode(encryptData);// 将加密后的数据通过Base64解码
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, decoderPrivateKey(privateKey));
        return new String(cipher.doFinal(decode));
    }

    /**
     * 签名
     *
     * @param data       业务数据
     * @param privateKey Base64编码后的私钥
     * @return 签名后的数据
     * @throws Exception 异常
     */
    public static String sign(String data, String privateKey) throws Exception {
        Signature signature = Signature.getInstance(SING_TYPE);
        signature.initSign(decoderPrivateKey(privateKey));
        signature.update(data.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 验签
     *
     * @param sign      签名字符串
     * @param data      业务数据
     * @param publicKey 公钥
     * @return 验签结果
     * @throws Exception 异常
     */
    public static boolean verifySign(String sign, String data, String publicKey) throws Exception {
        Signature signature = Signature.getInstance(SING_TYPE);
        signature.initVerify(decoderPublicKey(publicKey));
        signature.update(data.getBytes(UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign));
    }
}


