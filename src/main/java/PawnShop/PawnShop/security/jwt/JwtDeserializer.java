//package PawnShop.PawnShop.security.jwt;
//
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.io.DeserializationException;
//import io.jsonwebtoken.io.Deserializer;
//import io.jsonwebtoken.io.IOException;
//import io.jsonwebtoken.lang.Assert;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//@Component
//@RequiredArgsConstructor
//public class JwtDeserializer<T> implements Deserializer<T> {
//
//    static final String MALFORMED_ERROR = "Malformed JWT JSON: ";
//    static final String MALFORMED_COMPLEX_ERROR = "Malformed or excessively complex JWT JSON. This could reflect a potential malicious JWT, please investigate the JWT source further. JSON: ";
//
//    @Override
//    public T deserialize(byte[] bytes) throws DeserializationException {
//        try {
//            String token = new String(bytes);
//            Base64.Decoder decoder = Base64.getUrlDecoder();
//
//            String[] chunks = token.split("\\.");
//
//            String header = new String(decoder.decode(chunks[0]));
//            String payload = new String(decoder.decode(chunks[1]));
//
//            return (T) (header + " " + payload);
//        } catch (DeserializationException e) {
//            throw new MalformedJwtException(MALFORMED_ERROR + new String(bytes, StandardCharsets.UTF_8), e);
//        } catch (StackOverflowError e) {
//            throw new IOException(MALFORMED_COMPLEX_ERROR + new String(bytes, StandardCharsets.UTF_8), e);
//        }
//    }
//}
