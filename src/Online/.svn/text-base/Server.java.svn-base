package Online;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



public class Server {

	protected Collection<Connection> userConnected;

	protected List<RoomGame> rooms;
	protected List<Associated> associatedRoom;

	private Lock lock=new ReentrantLock();
	private ReadWriteLock lockRankingFile=new ReentrantReadWriteLock();
	
	private File ranking;


	public Server() {
		userConnected=new LinkedList<Connection>() ;
		rooms=new LinkedList<RoomGame>();
		associatedRoom=new LinkedList<Associated>();
		ranking=new File("FILESERVER/Ranking.txt");

	}
	public void readRankingFile()
	{
		lockRankingFile.readLock().lock();
	}
	public void writeRankingFile()
	{
		lockRankingFile.writeLock().lock();
	}

	public void freeReadRankingFile()
	{
		lockRankingFile.readLock().unlock();	
	}
	public void freeWriteRankingFile()
	{
		lockRankingFile.writeLock().unlock();	
	}
	public File getFileRanking()
	{return ranking;}
	public void addUser(Connection connection)
	{
		userConnected.add(connection);
	}

	public void removeUser(String user)
	{
		userConnected.remove(user);
	}

	public void startControlRoom(String client)
	{

		updateRooms(client);
	}

	private void updateRooms(String client)
	{
		lock.lock();
		try {
			for (RoomGame room : rooms) {
				if(room.canStartRoom(client))
				{
					room.sendPackGame("PlayRoom>play");
					room.startRoom();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{lock.unlock();}
	}

	public void addRoom(RoomGame room)
	{
		lock.lock();
		try {
			rooms.add(room);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{lock.unlock();}
	}

	public void closeConnection(String nameUser)
	{
		lock.lock();

		Connection conDelete=null;
		Associated associatedToDelete=null;
		RoomGame roomDelete=null;
		RoomGame roomToUpdate=null;
		try {
			for (RoomGame room : rooms) {
				if(room.getClient1().username.equals(nameUser))
				{
					//room.setRoomClosed(true);
					roomDelete=room;
					
				}
				if(room.getClient2()!=null && room.getClient2().username.equals(nameUser))
				{
					roomToUpdate=room;
				 room.setClient2(null);
				}
			}
			for (Associated associeted : associatedRoom) {
				if((roomDelete!=null&&associeted.nameRoom.equals(roomDelete.getId())) || associeted.nameRoom.equals(roomToUpdate.getId()) )
				{
					associatedToDelete=associeted;
				}
				
			}
			for (Connection user : userConnected) {
				if(user.username.equals(nameUser))
				{
					conDelete=user;
				}
			}
			associatedRoom.remove(associatedToDelete);
			if(roomDelete!=null)
			rooms.remove(roomDelete);
			userConnected.remove(conDelete);



			for (Connection user : userConnected) {		
				user.write("Message>RefreshRooms");
				for (RoomGame room : rooms) {
					user.write(room.getId());
				}
				user.write("ListRoom:end");
			}

			for (Connection user :userConnected) {		
				user.write("Message>RefreshRoomsAssociated");
				for (Associated room : associatedRoom) {
					user.write(room.nameRoom+":"+room.nameWorld);
				}
				user.write("ListRoomAssociated:end");
			}



			for (Connection user : userConnected) {		
				user.write("Message>RefreshUsersConnected");

				for (Connection u : userConnected) {
					user.write((String)u.username);
				}
				user.write("ListUser:end");
			}


		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}

	public void addAssociatedRoom(Associated a)
	{
		lock.lock();
		try {
			associatedRoom.add(a);
		} finally {
			lock.unlock();
		}
	}

	public void stopRoom(String idRoom)
	{
		lock.lock();
		for (RoomGame room : rooms) {
			if(room.getId().equals(idRoom))
			{
				room.stopGame();
			}
		}
		lock.unlock();
	}


	public static void main(String[] args) {
		int port=2002;

		Server server=new Server();
		server.startControlRoom("");


		try {

			@SuppressWarnings("resource")
			ServerSocket ss=new ServerSocket(port);



			while(true)
			{
				Socket s=ss.accept();
				Runnable r=new Connection(s,server.userConnected,server.rooms,server,server.associatedRoom);				Thread thread=new Thread(r);
				thread.start();

			}
		}catch(Exception e)
		{}

	}
}






