package dao;

import java.io.Serializable;
import java.util.List;

public interface MessageDaoItf <T, Id extends Serializable> {
	
	/**
	 * Insert a message into MESSAGE table
	 * @param message : Message - model
	 * @return TRUE - insert successfully, FALSE - insert fail
	 */
	public boolean persist(T message);
	
	/**
	 * Update a message on MESSAGE table
	 * @param message : Message - model
	 * @return TRUE - update successfully, FALSE - update fail
	 */
	public boolean update(T message);
	
	/**
	 * Delete a message form MESSAGE table
	 * @param message : Message - model
	 * @return TRUE - delete successfully, FALSE - delete fail
	 */
	public boolean delete(T message);
	
	/**
	 * Delete all messages from MESSAGE table
	 * @return TRUE - delete successfully, FALSE - delete fail
	 */
	public boolean deleteAll();
	
	/**
	 * Get all Messages on MESSAGE table
	 * @return 
	 * List<Message - model> - If MESSAGE table isn't empty, 
	 * NULL - If MESSAGE table is empty
	 */
	public List<T> getAll();
	
	/**
	 * Get a message on MESSAGE table by message ID
	 * @param messageId
	 * @return Message - model
	 */
	public T getByMsgId(Id messageId);
	
	/**
	 * Get all message on MESSAGE table by Sender ID who sent this messages
	 * @param senderId
	 * @return 
	 * List<Message - model>, 
	 * NULL - If MESSAGE table does not contains any message of UserId
	 */
	public List<T> getBySenderId(Id senderId);
	
	/**
	 * Get all message by senderID who sent this messages and receiverID who received this messages
	 * @param senderId
	 * @param receiverId
	 * @return 
	 * List<Message - model>, 
	 * NULL - If empty
	 */
	public List<T> getByUserId(Id senderId, Id receiverId);
	
}
