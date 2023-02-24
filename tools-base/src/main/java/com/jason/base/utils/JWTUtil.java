package com.jason.base.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.UUID;
import com.jason.base.exception.ServiceException;
import org.jose4j.base64url.Base64;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.ReservedClaimNames;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 *      Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519)。
 *      该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。
 *      JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息。
 *      该token也可直接被用于认证，也可被加密。
 *      <br/>
 *      jwt令牌由三个部分组成
 *      1.header：令牌头部，记录了整个令牌的类型和签名算法。
 *      2.payload：令牌负荷，记录了保存的主体信息，比如你要保存的用户信息就可以放到这里。
 *      3.signature：令牌签名，按照头部固定的签名算法对整个令牌进行签名，该签名的作用是：保证令牌不被伪造和篡改。
 * </pre>
 *
 * @author WongChenHoll
 * @description
 * @date 2023-2-24 星期五 10:11
 **/
public class JWTUtil {

    private static final String PUBLIC_KEY_PATH = "RAS_PUBLIC_KEY.pem";
    private static final String PRIVATE_KEY_PATH = "RAS_PRIVATE_KEY.pem";
    public static final float EXPIRATION_TIME = 60 * 2;
    public static final String VERIFY_CONTEXT = "username";

    private static final PublicKey publicKey;

    static {
        try {
            publicKey = getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ServiceException, JoseException, InvalidJwtException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "张三");
        map.put("ip", "127.0.0.11");
        String token = createToken(map, getPrivateKey());
        System.out.println(token);
        String verifyToken = verifyToken(token);
        System.out.println("张三".equals(verifyToken));
    }

    /**
     * 校验Token
     */
    public static String verifyToken(String token) throws InvalidJwtException {
        JwtConsumer build = new JwtConsumerBuilder()
                .setVerificationKey(publicKey)
                .setExpectedIssuer(ReservedClaimNames.ISSUER)
                .setExpectedAudience(ReservedClaimNames.AUDIENCE)
                .build();
        JwtClaims jwtClaims = build.processToClaims(token);
        return jwtClaims.getClaimValueAsString(VERIFY_CONTEXT);
    }

    /**
     * 创建Token
     */
    public static String createToken(Map<String, Object> map, Key privateKey) throws ServiceException, JoseException {
        JsonWebSignature signature = new JsonWebSignature();
        signature.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        signature.setPayload(claims(map).toJson());
        signature.setKeyIdHeaderValue(UUID.randomUUID().toString());
        signature.setKey(privateKey);
        return signature.getCompactSerialization();
    }

    public static JwtClaims claims(Map<String, Object> map) throws ServiceException {
        JwtClaims claims = new JwtClaims();
        if (CollectionUtil.isEmpty(map)) {
            throw ServiceException.paramsException("JwtClaims内容为空");
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            claims.setClaim(entry.getKey(), entry.getValue());
        }
        claims.setExpirationTimeMinutesInTheFuture(EXPIRATION_TIME); // 设置过期时间，单位：分钟
        claims.setIssuer(ReservedClaimNames.ISSUER);
        claims.setIssuedAtToNow();
        claims.setAudience(ReservedClaimNames.AUDIENCE);
        return claims;
    }

    public static PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return getPrivateKey(PRIVATE_KEY_PATH);
    }

    /**
     * 获取私钥
     *
     * @param privateKeyPath 私钥文件的存放地址
     */
    public static PrivateKey getPrivateKey(String privateKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKey = getFileContext(privateKeyPath);
        String replace = privateKey.replace(RSAUtil.FILE_HEADER_PRIVATE_KEY, "").replace(RSAUtil.FILE_END_PRIVATE_KEY, "");
        byte[] decode = Base64.decode(replace);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.ENCRYPT_TYPE);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥的全部内容
     */
    public static PrivateKey privateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String replace = privateKey.replace(RSAUtil.FILE_HEADER_PRIVATE_KEY, "").replace(RSAUtil.FILE_END_PRIVATE_KEY, "");
        byte[] decode = Base64.decode(replace);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.ENCRYPT_TYPE);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));
    }

    public static PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return getPublicKey(PUBLIC_KEY_PATH);
    }

    /**
     * 获取公钥
     */
    public static PublicKey getPublicKey(String publicKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKey = getFileContext(publicKeyPath);
        String replace = publicKey.replace(RSAUtil.FILE_HEADER_PUBLIC_KEY, "").replace(RSAUtil.FILE_END_PUBLIC_KEY, "");
        byte[] decode = Base64.decode(replace);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.ENCRYPT_TYPE);
        return keyFactory.generatePublic(new X509EncodedKeySpec(decode));
    }

    /**
     * 根据项目中文件路径获取文件中的内容
     */
    private static String getFileContext(String fileClassPath) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileClassPath);
        return IoUtil.read(Files.newInputStream(resource.getFile().toPath()), StandardCharsets.UTF_8);
    }

}
