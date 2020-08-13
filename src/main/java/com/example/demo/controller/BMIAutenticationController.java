package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.After_Call_WorkModel;
import com.example.demo.model.BMIModel;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bmi")
public class BMIAutenticationController
{
	@Autowired
	private DataSource dataSource;

	public BMIAutenticationController(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	@PostMapping("/jwt")
	public String JwtBMI(@RequestBody BMIModel cfm) throws UnsupportedEncodingException 
	{
		String id = "6052fqw89034tjf9f8q2bfj0a23nf0cr";
		String token = generateToken(cfm);
		return "{\"token\" :\""+id+token+"\"}";
	}
	
	public String generateToken(BMIModel cfm) throws UnsupportedEncodingException {
		String key = "JWT6052IOklsnfg039fgnjDJS8934bhicv9SDFIHaCCS";
		String id = "6052fqw89034tjf9f8q2bfj0a23nf0cr";
		String a = "{\"conversationId\":\""+cfm.conversationId+"\",\"requestTimeStamp\":\""+cfm.requestTimeStamp+"\",\"acctNo\":\""+cfm.acctNo+"\"}";
		String BasicBase64format = Base64.getEncoder().encodeToString(a.getBytes());
		System.out.println(BasicBase64format );
		byte[] hmacSha256 = calcHmacSha256(key.getBytes("UTF-8"), (BasicBase64format+id).getBytes("UTF-8"));
		String base64HmacSha256 = Base64.getEncoder().encodeToString(hmacSha256);
		Map<String, Object> claims = new HashMap<>();
		claims.put("signatureBase64", base64HmacSha256);
		return doGenerateToken(claims);
	}
	
	static public byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
	    byte[] hmacSha256 = null;
	    try {
	      Mac mac = Mac.getInstance("HmacSHA256");
	      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
	      mac.init(secretKeySpec);
	      hmacSha256 = mac.doFinal(message);
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to calculate hmac-sha256", e);
	    }
	    return hmacSha256;
	  }

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims) {
		
		Map<String, Object> header = new HashMap<>();
		header.put("alg", "HS256");
		header.put("typ", "JWT");
		Header header2 = Jwts.header();
	    header2.setType("JWT");
//		System.out.println("expired : " + new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));
		String key = "JWT6052IOklsnfg039fgnjDJS8934bhicv9SDFIHaCCS";
		return Jwts.builder()
				.setHeader((Map<String, Object>) header2)
				.setClaims(claims)
//				.setSubject(subject)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
			
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
		
	}
}
