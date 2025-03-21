package music;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class TestUserManager {

    private LibraryModel libModel;
    private UserManager userManager;
    private String username;
    private String password;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        libModel = new LibraryModel();
        userManager = new UserManager(libModel);
        username = "testUser";
        password = "testPassword";
    }

    @Test
    public void testAddUser() {
        userManager.addUser(username, password);
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
        userManager.addUser(username, password);
        assertTrue(userManager.usernameExists(username));
        assertFalse(userManager.usernameExists("noUser"));
    }

    @Test
    public void testCheckPasswordValid() {
        String username = "validUser";
        String password = "correctPassword";

        userManager.addUser(username, password);
        assertTrue(userManager.checkPassword(username, password));
    }

    @Test
    public void testCheckPasswordInvalid() {
        String username = "validUser";
        String password = "correctPassword";
        String incorrectPassword = "wrongPassword";

        userManager.addUser(username, password);
        assertFalse(userManager.checkPassword(username, incorrectPassword));
    }

    @Test
    public void testTurnUserDataIntoFile() {
        userManager.addUser(username, password);
        userManager.turnUserDataIntoFile(username);
        File userFile = new File(username + ".txt");
        assertTrue(userFile.exists());
        String fileContent = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(fileContent.contains("songs:"));
        assertFalse(fileContent.contains("playlists:"));
    }

    @Test
    public void testGetUserData() throws FileNotFoundException {
        userManager.addUser(username, password);
        userManager.turnUserDataIntoFile(username);
        LibraryModel newLibModel = new LibraryModel();
        UserManager newUserManager = new UserManager(newLibModel);
        newUserManager.getUserData(username);
        assertTrue(newLibModel.getLibrarySongs().isEmpty());
        assertTrue(newLibModel.getUserPlaylists().isEmpty());
    }
    
    @Test
    public void testSaltAndHashPassword() {
        userManager.addUser(username, password);
        File userInfoFile = new File("userinfo.txt");
        String userSalt = "";
        String hashedPassword = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(userInfoFile))) {
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
