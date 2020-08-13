package com.example.demo.config;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable
{

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 10 * 60 * 1000;

	@Value("${jwt.key}")
	private String secret;

	@Value("${api.id}")
	private String api_id;
	@Autowired
	private DataSource dataSource;

	// retrieve username from jwt token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
//	private Boolean isTokenExpired(String token)
//	{
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}

	private long createRandomInteger(int aStart, long aEnd, Random aRandom)
	{
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = aEnd - (long) aStart + 1;
		long fraction = (long) (range * aRandom.nextDouble());
		return fraction + (long) aStart;
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) throws SQLException
	{
		Map<String, Object> claims = new HashMap<>();
		boolean value = true;
		Long random=1L;
		while (value == true) {
			random = createRandomInteger(00000000, 99999999, new Random());
			Connection connection = dataSource.getConnection();
			PreparedStatement a = connection.prepareStatement("select value from jwt_transaction where id = ?");

			a.setLong(1, random);
			ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)

			if (Cursor1.next()) {
				// System.out.println (Cursor1.getString (1));
				value = true;
			}
			else {
				value=false;
			
			}
			connection.close();
			if (value == false)
			{
				Connection connection2 = dataSource.getConnection();
				PreparedStatement a2 = connection2.prepareStatement("INSERT INTO public.jwt_transaction\r\n" + 
						"(id, value)\r\n" + 
						"VALUES(?, true);\r\n" + 
						"");

				a2.setLong(1, random);
				a2.executeUpdate();// Evaluate (Connected_Expression1)

				connection2.close();
				
			}

		}
		claims.put("transactionId", random);
		claims.put("timestampInSeconds", System.currentTimeMillis());
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject)
	{

//		System.out.println("expired : " + new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));

		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");

		String token = Jwts.builder().setClaims(claims)
				// .setHeader((Map<String, Object>) header)
				// .setSubject(subject)
				// .setIssuedAt(new Date(System.currentTimeMillis()))
				// .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY *
				// 1000))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes()).setHeader(header).compact();

		token = api_id + token;

		return token;
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails)
	{

		final Claims claims = getAllClaimsFromToken(token);
		String transactionId = claims.get("transactionId").toString();
		String timestampInSeconds = claims.get("timestampInSeconds").toString();
		
		long tId = Long.valueOf(transactionId);
		long tIS = Long.valueOf(timestampInSeconds);
		
//		if ((tIS + JWT_TOKEN_VALIDITY) > System.currentTimeMillis())									// 10 MENIT
//		{
//			System.out.println("TransactionId : " + transactionId);
//			System.out.println("TimestampInSeconds : " + timestampInSeconds + ", " + new Date(tIS));
//			return (true);
//		}
//		else
//		{
//			System.out.println("TransactionId : " + tId + ", Expired.");
//			return (false);
//		}
		return true;
	}

	public String getApiId(String requestTokenHeader)
	{
		return requestTokenHeader;
	}
}