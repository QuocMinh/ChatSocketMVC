package dao;

import java.util.ArrayList;

import model.User;
import junit.framework.TestCase;

public class UserDaoTest extends TestCase {

	UserDao userDao = new UserDao();

	public void testPersist() {
		// TH 1: User moi: TRUE -> OK
		// TH 2: User da ton tai: FALSE -> OK
		User user1 = new User();
		user1.setEmail("memy19992@gmail.com");
		user1.setFirstName("Chau");
		user1.setLastName("Quoc Minh");
		user1.setPassword("quocminh1995");
		user1.setDob("28/10/1995");

		// TH 3: User rong: FALSE -> OK
		@SuppressWarnings("unused")
		User user2 = new User();
		
		// TH 4: User khong co password: FALSE -> OK
		User user3 = new User();
		user3.setEmail("abcd@gmail.com");
		user3.setFirstName("Chau");
		user3.setLastName("Quoc Minh");
		user3.setDob("28/10/1995");
		
		// Insert User
		assertEquals(false, userDao.persist(user3));
	}

	public void testUpdate() {
		// TH1: User chua ton tai: FALSE -> OK
		// TH2: User da ton tai: TRUE -> progressing ...
		User user1 = new User();
		user1.setEmail("memy19992@gmail.com");
		user1.setFirstName("Chau");
		user1.setLastName("Quoc Minh");
		user1.setPassword("quocminh1995");
		user1.setDob("10/12/1995");
		
		// TH3: Set password NULL: FALSE ->
		// TH4: Set Email trung voi Email da co truoc do: FALSE ->

		assertEquals(true, userDao.update(user1.getEmail(), user1));
	}

	public void testFindById() {
		User user = userDao.findById("1");
		assertEquals("Chau", user.getFirstName());
	}

	public void testDelete() {
	}

	public void testGetAll() {
		ArrayList<User> users = (ArrayList<User>) userDao.getAll();
		for (User user : users) {
			System.out.println("User " + user.getUserId() + ": "
					+ user.getDob());
		}
		assertEquals(true, users.size() > 0);
	}

	public void testDeleteAll() {
	}

	public void testFindByEmail() {
		String firstName = userDao.findByEmail("cqminh1995@gmail.com")
				.getFirstName();
		assertEquals("Chau", firstName);
	}

}
