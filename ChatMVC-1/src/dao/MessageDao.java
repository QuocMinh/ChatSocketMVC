package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;
import model.Message;

public class MessageDao implements MessageDaoItf<Message, String> {

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
	public boolean persist(Message message) {
		boolean successful = false;

		try {
			openSessionWithBeginTransaction();
			session.persist(message);
			trans.commit();
			successful = true;
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Insert failed");
			e.printStackTrace(System.out);
		} finally {
			session.flush();
			session.close();
		}

		return successful;
	}

	@Override
	public boolean update(Message message) {
		boolean successful = false;

		try {
			openSessionWithBeginTransaction();
			session.update(message);
			trans.commit();
			successful = true;
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Update failed");
			e.printStackTrace(System.out);
		} finally {
			session.flush();
			session.close();
		}

		return successful;
	}

	@Override
	public boolean delete(Message message) {
		boolean successful = false;

		try {
			openSessionWithBeginTransaction();
			session.delete(message);
			trans.commit();
			successful = true;
		} catch (HibernateException e) {
			trans.rollback();
			System.out.println("Delete failed");
			e.printStackTrace(System.out);
		} finally {
			session.flush();
			session.close();
		}

		return successful;
	}

	@Override
	public boolean deleteAll() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAll() {
		List<Message> list = null;

		try {
			openSession();
			list = session.createCriteria(Message.class).list();
		} catch (HibernateException e) {
			System.out.println("Get all failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}

		return list.isEmpty() ? null : list;
	}

	@Override
	public Message getByMsgId(String messageId) {
		Message msg = null;

		openSession();

		try {
			msg = (Message) session.get(Message.class, messageId);
		} catch (HibernateException e) {
			System.out.println("Get message by Id failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}

		return msg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getBySenderId(String senderId) {
		List<Message> list = null;

		openSession();

		try {
			list = session.createCriteria(Message.class)
					.add(Restrictions.like("senderId", senderId)).list();
		} catch (HibernateException e) {
			System.out.println("Get message by UserID failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}

		return list.isEmpty() ? null : list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getByUserId(String senderId, String receiverId) {
		List<Message> list = null;

		openSession();

		try {
			list = session
					.createCriteria(Message.class)
					.add(Restrictions.and(
							Restrictions.like("receiverId", receiverId),
							Restrictions.like("senderId", senderId))).list();
		} catch (HibernateException e) {
			System.out.println("Get message by UserID failed!");
			e.printStackTrace(System.out);
		} finally {
			session.close();
		}

		return list.isEmpty() ? null : list;
	}

}
