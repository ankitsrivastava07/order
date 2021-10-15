package ordermicroservice.jwtutil;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("jwtTockenUtil")
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 20 * 60 * 1;
	private String secret = "CHowlongistheencryptiondecryptionkeyforanassymetricalgorithmsuchasAESIfIuseAESbithowmanycharactersshouldItypeinformykeyWhataboutbitHoHowlongistheencryptiondecrHowlongistheencryptiondecryptionkeyforanassymetricalgorithmsuchasAESIfIuseAESbithowmanycharactersshouldItypeinformykeyWhataboutbityptionkeyforanassymetricalgorithmsuchasAESIfIuseAESbithowmanycharactersshouldItypeinformykeyWhataboutbitwlongistheencryptiondecryptionkeyforanassymetricalgorithmsuchasAESIfIuseAESbithowmanycharactersshouldItypeinformykeyWhataboutbitlosedThisquesClosedyearsagotionisofftopicItisnotcurrentlyacceptinganswersWanttoimprovethisquestionUpdatethequestionsoitsontopicforInformationSecurityStackExchangen";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Long getUserId(String token) {
		return Long.valueOf(getClaimFromToken(token, Claims::getSubject));
	}

	public Date getExpirationDateFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims.get("exp",Date.class);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Long userId) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userId);
	}

	private String doGenerateToken(Map<String, Object> claims, Long userId) {
		return Jwts.builder().setClaims(claims).setSubject(String.valueOf(userId))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, String username) {
		return (username.equals(username) && !isTokenExpired(token));
	}

	public String getTokenIdentity(String accessToken){
		try {
			Base64.Decoder decoder = Base64.getUrlDecoder();
			String[] parts = accessToken.split("\\."); // Splitting header, payload and signature
			String payload = new String(decoder.decode(parts[1]));
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(payload, Map.class);
			return String.valueOf(map.get("userId"));
		}catch (JsonProcessingException exception){
			exception.printStackTrace();
		}
		return null;
	}

}
