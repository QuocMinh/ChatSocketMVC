package dao;

import java.io.Serializable;
import java.util.List;

public interface UserDaoItf <T, Id extends Serializable>{
	
	/**
	 * This method will insert new User into table USER
	 * <p>
	 * If User existed, this method would return FALSE 
	 * then you must call update() method instead.
	 * @param user : User - model
	 * @return TRUE if update successful, else return FALSE
	 */
	public boolean persist(T user);
	
	/**
	 * This method will update an existing User on table USER
	 * <p>
	 * If User does not exist, this method would return FALSE 
	 * then you must call persist() method first.
	 * @param user : User - model
	 * @return TRUE if update successful, else FALSE
	 */
	public boolean update(T user);
	
	/**
	 * This method will update an existing User on table USER by email
	 * <p>
	 * If User does not exist, this method would return FALSE 
	 * then you must call persist() method first.
	 * @param user : User - model
	 * @return TRUE if update successful, else FALSE
	 * <p>
	 * * This method will faster than update(String email) *
	 */
	public boolean update(String email, T user);
	
	/**
	 * This function will get an User by <code>userId</code>
	 * @param userId
	 * @return User - model
	 * <p>
	 * * This method will slower than update(User user) *
	 */
	public T findById(Id userId);
	
	/**
	 * This function will get an User by <code>email</code>
	 * <p>
	 * If User doesn't exist, this function will return NULL
	 * @param email
	 * @return User - model
	 */
	public T findByEmail(String email);
	
	/**
	 * This function will delete User in table USER
	 * @param user : User - model
	 * @return TRUE if delete successful, else FALSE
	 */
	public boolean delete(T user);
	
	/**
	 * This function will get all Users in table USER
	 * <p>
	 * Return NULL if list is empty 
	 * @return List<User>
	 */
	public List<T> getAll();
	
	/**
	 * This function will delete all Users in table USER
	 * @return TRUE if delete successful, else FALSE
	 */
	public boolean deleteAll();
	
	/**
	 * This function will check if User Exist
	 * @param message : Message - model
	 * @return TRUE if User does exist in USER table, else return FALSE
	 */
	public boolean checkUserExist(T message);
	
}
