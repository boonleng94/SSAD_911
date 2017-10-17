import java.security.MessageDigest;
import java.util.*;

public class HashingDemo {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter password to hash: ");
		String pw = sc.nextLine();
		System.out.println("Original: " + pw);
		pw = hash(pw);
		System.out.println("Hashed: " + pw);
	}
	
	// generate hash for the password using SHA-256
	public static String hash(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
