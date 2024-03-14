import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {
	private Library library = new Library();
	private Users users = Users.getInstance();
	private ArrayList<User> userList = users.getUsers();
	
	@BeforeEach
	public void setup() {
		Users.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}
	
	@AfterEach
	public void tearDown() {
		Users.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}

	@Test
	public void testCreateValidAccount() {
		library.createAccount("emarks", "Edward", "Marks", 20, "803-454-4455");
		library.login("emarks");
		User myUser = library.getCurrentUser();
		assertEquals("emarks", myUser.getUserName());
	}
	
	@Test
	public void testCreateSaved() {
		library.createAccount("fmarks", "Frank", "Marks", 22, "803-454-4455");
		library.logout();
		library = new Library();
		library.login("fmarks");
		User currentUser = library.getCurrentUser();
		assertEquals("fmarks", currentUser.getUserName());
	}
	
	@Test
	public void testCreateDuplicateUserName() {
		library.createAccount("emarks", "Edward", "Marks", 20, "803-454-4455");
		boolean isCreated = library.createAccount("emarks", "Edwina", "Marks", 30, "803-333-4544");
		assertFalse(isCreated);
	}
	
	@Test
	public void testCreateEmptyUserName() {
		boolean isCreated = library.createAccount("", "", "", 0, "");
		assertFalse(isCreated);
	}
	
	@Test
	public void testCreateNullUserName() {
		boolean isCreated = library.createAccount(null, "", "", 0, "");
		assertFalse(isCreated);
	}
}
