package util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    public String getHash(String string) {
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(string.getBytes(StandardCharsets.UTF_8), 0, string.length());
            return DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
