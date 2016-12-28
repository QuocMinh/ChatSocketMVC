package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;
import model.User;

public class UserDao implements UserDaoItf<User, String> {
	
	private Session session;
	private Transaction trans;
	
	public void openSession() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void openSessionWithBeginTransaction() {
		session = HibernateUtil.getSessionFactory().openSession();
		trans = session.beginTransaction();
	}

	@Override
	public User findById(String id) {
		openSession();
		User user = null;
		
		try {
			user = (User) session.get(User.class, Integer.parseInt(id));
		} catch (HibernateException e) {
			System.out.println("Find User by Id failed!");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return user;
	}

	@Override
	public List<User> getAll() {
		openSession();
		@SuppressWarnings("unchecked")
		List<User> list = session.createCriteria(User.class).list();
		session.close();
		
		return list;
	}

	@Override
	public User findByEmail(String email) {
		openSession();
		@SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class)
				.add(Restrictions.like("email", email))
				.list();
		session.close();
		
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public boolean checkUserExist(User user) {
		User userCheck = null;
		
		openSession();
		
		try {
			user = (User) session.get(User.class, user.getUserId());
		} catch (HibernateException e) {
			System.out.println("Check User exist failed!");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return userCheck == null ? false : true;
	}

	@Override
	public boolean persist(User user) {
		openSessionWithBeginTransaction();
		boolean check = false;
		
		try {
			if(session.createCriteria(User.class)
					.add(Restrictions.like("email", user.getEmail()))
					.list()
					.isEmpty()) {
				session.save(user);
				trans.commit();
				session.flush();
				check = true;
			}
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Persist User failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}		
		
		return check;
	}

	@Override
	public boolean update(User newUser) {
		openSessionWithBeginTransaction();
		boolean check = false;
		
		try {
			session.update(newUser);
			session.refresh(newUser);
			trans.commit();
			session.flush();
			check = true;
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Update User failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}
		
		return check;
	}

	@Override
	public boolean delete(User user) {
		openSessionWithBeginTransaction();
		boolean check = false;
		
		try {
			session.delete(user);
			trans.commit();
			session.flush();
			check = true;
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Delete User failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}
		
		return check;
	}

	@Override
	public boolean deleteAll() {
		openSessionWithBeginTransaction();
		boolean check = false;
		
		try {
			check = session.createQuery("delete from USER").executeUpdate() < 1 ? false : true;
			trans.commit();
			session.flush();
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Delete all User failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}
		
		return check;
	}

	@Override
	public boolean update(String email, User newUser) {
		User oldUser = findByEmail(email); // Get old user on database
		boolean check = false;
		
		// Update local user ... 
		newUser.setUserId(oldUser.getUserId());
		// ... end update
		
		openSessionWithBeginTransaction();
		
		try {
			session.update(newUser);
			trans.commit();
			session.flush();
			check = true;
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Update User failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}
		
		return check;
	}

}
