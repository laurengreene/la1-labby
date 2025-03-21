package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUser {

	@Test
	void testGetUserInfo() {
	    String username = "testUser";
	    String salt = "randomSalt";
	    String securePassword = "securePass123";

	    User user = new User(username, salt, securePassword);

	    assertEquals(username, user.getUsername());
	    assertEquals(salt, user.getSalt());
	    assertEquals(securePassword, user.getSecurePassword());
	}

}
