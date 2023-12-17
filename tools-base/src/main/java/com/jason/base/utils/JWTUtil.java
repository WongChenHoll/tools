package com.jason.base.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *      Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519)。
 *      该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。
 *      JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息。
 *      该token也可直接被用于认证，也可被加密。
 *      <br/>
 *
 *     jwt令牌由三个部分组成
 *      1.header：令牌头部，记录了整个令牌的类型和签名算法。
 *      2.payload：令牌负荷，记录了保存的主体信息，比如你要保存的用户信息就可以放到这里。
 *      3.signature：令牌签名，按照头部固定的签名算法对整个令牌进行签名，该签名的作用是：保证令牌不被伪造和篡改。
 *      <br/>
 *
 *      标准中注册的声明（建议但不强制使用）
 *      <ls>iss: jwt签发者<ls/>
 *      <ls>sub: jwt所面向的用户<ls/>
 *      <ls>aud: 接收jwt的一方<ls/>
 *      <ls>exp: jwt的过期时间，这个过期时间必须要大于签发时间<ls/>
 *      <ls>nbf: 定义在什么时间之前，该jwt都是不可用的.<ls/>
 *      <ls>nbf: 定义在什么时间之前，该jwt都是不可用的.<ls/>
 *      <ls>iat: jwt的签发时间<ls/>
 *      <ls>jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。<ls/>
 *      <br/>
 *      此方法使用：io.jsonwebtoken.jjwt
 * </pre>
 *
 * @author WongChenHoll
 * @description
 * @date 2023-2-24 星期五 10:11
 **/
public class JWTUtil {

    private static final String SECRET_KEY = "47E7BF5238DF"; // 任何字符串
    public static final int EXPIRATION_TIME = 30; // 过期时间：30（分钟）
    public static final int ALG_HMAC = 1; // 算法类型：HMAC
    public static final int ALG_RSA = 2;  // 算法类型：RSA


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "张三");
        map.put("ip", "127.0.0.11");

        String jwtHmac1 = createTokenHmac(map);
        System.out.println(jwtHmac1);
        System.out.println(parseToken(jwtHmac1, ALG_HMAC));

        String jwtHmac = createTokenHmac("1234");
        System.out.println(jwtHmac);
        System.out.println(parseToken(jwtHmac, ALG_HMAC).getSubject());

        String tokenRsa = createTokenRsa("112233445566");
        System.out.println(tokenRsa);
        System.out.println(parseToken(tokenRsa, ALG_RSA).getSubject());
    }


    public static String createTokenHmac(Map<String, Object> map) {
        return createTokenHmac(map, UUID.fastUUID().toString(true), EXPIRATION_TIME);
    }

    /**
     * 创建token。<br/>
     * 被加密内容为Map类型。
     * 使用SHA-256的HMAC的JWA算法。<br/>
     *
     * @param map            需要加密的内容
     * @param id             ID
     * @param expirationTime 过期时间，单位：分钟
     * @return 加密后的密文
     */
    public static String createTokenHmac(Map<String, Object> map, String id, int expirationTime) {
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date()) // 设置荷载时间
                .setExpiration(DateUtil.offset(new Date(), DateField.MINUTE, expirationTime).toJdkDate()) // 设置过期时间，1小时
                .addClaims(map)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String createTokenHmac(String subject) {
        return createTokenHmac(subject, UUID.randomUUID().toString(true), EXPIRATION_TIME);
    }

    /**
     * 创建token。<br/>
     * 被加密内容为字符串。
     * 使用SHA-256的HMAC的JWA算法。<br/>
     *
     * @param subject        需要加密的内容
     * @param id             ID
     * @param expirationTime 过期时间，单位：分钟
     * @return 加密后的密文
     */
    public static String createTokenHmac(String subject, String id, int expirationTime) {
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date()) // 设置荷载时间
                .setSubject(subject)
                .setExpiration(DateUtil.offset(new Date(), DateField.MINUTE, expirationTime).toJdkDate()) // 设置过期时间，1小时
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static String createTokenRsa(String subject) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return createTokenRsa(subject, UUID.randomUUID().toString(true), EXPIRATION_TIME);
    }

    /**
     * 基于RSA算法加密生成token
     *
     * @param subject        加密内容
     * @param id             ID
     * @param expirationTime 过期时间，单位：分钟
     * @return 密文
     * @throws IOException,NoSuchAlgorithmException,InvalidKeySpecException 获取密钥异常
     */
    public static String createTokenRsa(String subject, String id, int expirationTime) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(DateUtil.offset(new Date(), DateField.MINUTE, expirationTime))
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    public static Claims parseToken(String token, int algType) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (algType == 2) {
            return Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .parseClaimsJws(token).getBody();
        } else {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody();
        }
    }

    public static PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return getPrivateKey("RAS_PRIVATE_KEY.pem");
    }

    public static PrivateKey getPrivateKey(String privateKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKey = getFileContext(privateKeyPath);
        String replace = privateKey.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        byte[] decode = Base64.decode(replace);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));
    }

    public static PrivateKey privateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String replace = privateKey.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        byte[] decode = Base64.decode(replace);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));
    }

    public static PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return getPublicKey("RAS_PUBLIC_KEY.pem");
    }

    public static PublicKey getPublicKey(String publicKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKey = getFileContext(publicKeyPath);
        String replace = publicKey.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        byte[] decode = Base64.decode(replace);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(decode));
    }

    private static String getFileContext(String fileClassPath) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileClassPath);
        return IoUtil.read(Files.newInputStream(resource.getFile().toPath()), StandardCharsets.UTF_8);
    }

}
