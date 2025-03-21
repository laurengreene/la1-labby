package music;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class TestUserManager {

    private UserManager userManager;
    private LibraryModel libModel;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        libModel = new LibraryModel();  // Assuming LibraryModel constructor initializes everything.
        userManager = new UserManager(libModel);
    }

    @Test
    void testAddUser() {
        String username = "testUser";
        String password = "securePassword";

        userManager.addUser(username, password);

        assertTrue(userManager.usernameExists(username));
        assertTrue(userManager.checkPassword(username, password));
    }

    @Test
    void testUsernameExists() {
        String username = "existingUser";
        String password = "somePassword";
        
        userManager.addUser(username, password);
        
        assertTrue(userManager.usernameExists(username));
        assertFalse(userManager.usernameExists("nonExistentUser"));
    }

    @Test
    void testCheckPassword() {
        String username = "userWithPassword";
        String password = "password123";

        userManager.addUser(username, password);

        assertTrue(userManager.checkPassword(username, password));
        assertFalse(userManager.checkPassword(username, "wrongPassword"));
    }

    @Test
    void testTurnUserDataIntoFile() throws FileNotFoundException {
        String username = "testFileUser";
        String password = "testPassword";
        
        userManager.addUser(username, password);
        
        // After adding user, we generate a file with their data.
        userManager.turnUserDataIntoFile(username);

        // Verify file creation and content
        File userFile = new File(username + ".txt");
        assertTrue(userFile.exists());
        
        Scanner scn = new Scanner(userFile);
        String content = scn.useDelimiter("\\A").next();  // Read all content
        scn.close();
        
        // We expect the content to contain username (sanity check)
        assertTrue(content.contains(username));
    }

    @Test
    void testGetUserData() throws FileNotFoundException {
        String username = "testGetDataUser";
        String password = "testPassword";

        userManager.addUser(username, password);
        userManager.turnUserDataIntoFile(username);

        // Simulate user login
        userManager.getUserData(username);

        // Check if the user data is properly loaded
        File userFile = new File(username + ".txt");
        assertTrue(userFile.exists());

        // Ensure we have some songs or playlists data (basic check)
        Scanner scn = new Scanner(userFile);
        String content = scn.useDelimiter("\\A").next();  // Read all content
        scn.close();
        
        // Check if it contains expected sections like "songs" or "playlists"
        assertTrue(content.contains("songs:"));
    }
}
