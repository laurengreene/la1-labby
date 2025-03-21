package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class TestUserManager {

    private static LibraryModel libModel;
    private static UserManager userManager;
    private static String username;
    private static String password;

    @BeforeAll
    public static void setUp() throws FileNotFoundException {
        libModel = new LibraryModel();
        userManager = new UserManager(libModel);
        username = "username";
        password = "password";
        userManager.addUser(username, password);
    }

    @Test
    public static void testAddUser() {
        assertTrue(userManager.usernameExists(username));
        
        File userFile = new File(username + ".txt");
        assertTrue(userFile.exists());
        
        File userInfoFile = new File("userinfo.txt");
        boolean containsUser = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(userInfoFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username)) {
                    containsUser = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(containsUser);
    }

    @Test
    public void testUsernameExists() {
        assertTrue(userManager.usernameExists(username));
        assertFalse(userManager.usernameExists("noUser"));
    }

    @Test
    public void testCheckPasswordValid() {
        assertTrue(userManager.checkPassword(username, password));
    }

    @Test
    public void testCheckPasswordInvalid() {
        String wrongPassword = "wrongPassword";
        assertFalse(userManager.checkPassword(username, wrongPassword));
    }

    @Test
    public void testTurnUserDataIntoFile() {
        userManager.turnUserDataIntoFile(username);
        File userFile = new File(username + ".txt");
        assertTrue(userFile.exists());
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(content.contains("songs:"));
        assertFalse(content.contains("playlists:"));
    }

    @Test
    public void testGetUserData() throws FileNotFoundException {
        userManager.turnUserDataIntoFile(username);
        LibraryModel newLibModel = new LibraryModel();
        UserManager newUserManager = new UserManager(newLibModel);
        newUserManager.getUserData(username);
        assertTrue(newLibModel.getLibrarySongs().isEmpty());
        assertTrue(newLibModel.getUserPlaylists().isEmpty());
    }
    
    @Test
    public void testSaltAndHashPassword() {
        File userInfo = new File("userinfo.txt");
        String userSalt = "";
        String hashedPassword = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(userInfo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    userSalt = parts[1];
                    hashedPassword = parts[2];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(userSalt.isEmpty());
        assertFalse(hashedPassword.isEmpty());
    }
    
    
}
