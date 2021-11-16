package Online;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import Online.UtilityOnline.MapUtil;

class Connection extends Thread
{
	private Socket socket;
	private Collection<Connection> users;
	boolean state;
	private List<RoomGame> rooms;
	private List<Associated> associatedRoom;
	private File ranking;

	String username;


	private boolean connectionClosed;
	InputStream is;
	BufferedReader InputStream; 
	BufferedWriter OutpuStream;
	Server server;

	String objectRead;

	public Connection(Socket s,Collection<Connection> map,List<RoomGame> rooms,Server server,List<Associated> associatedRoom) {
		socket=s;
		this.users=map;
		this.rooms=rooms;
		this.associatedRoom=associatedRoom;
        this.ranking=server.getFileRanking();
		username=null;
		connectionClosed=false;
		this.server=server;

		try {

			is=socket.getInputStream();
			InputStream=new BufferedReader(new InputStreamReader(is));
			OutpuStream=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public void write(String string)
	{
		try {
			this.OutpuStream.flush();
			this.OutpuStream.write(string+"\n");
			this.OutpuStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean roomExist(RoomGame roomGame)
	{
		for (RoomGame room : rooms) {
			if(room.getId().equals(roomGame.getId()))return true;

		}
		return false;
	}



	private boolean userIsConnected(String username)
	{
		for (Connection user :users) {
			if(user.username.equals(username)){return true;}
		}
		return false;
	}
	
	private void writeRanking(String record)
	{
		server.writeRankingFile();
		try {
		      BufferedReader reader = new BufferedReader(new FileReader(ranking));
		        Map<String, Double> map=new TreeMap<String, Double>();
		        String line="";
		        while((line=reader.readLine())!=null){
		        	map.put(line.split(":")[0],Double.parseDouble(line.split(":")[1]));
		        }
		        reader.close();
		        map.put(record.split(":")[0],Double.parseDouble(record.split(":")[1]));
		        map=MapUtil.sortByValue(map);
		        FileWriter writer = new FileWriter(ranking);
		        for(Entry<String, Double> entry : map.entrySet()) {
		        	writer.write(entry.getKey()+":"+entry.getValue());	
		        	writer.write('\n');

		        }
		        writer.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			server.freeWriteRankingFile();
		}
	}
	private void readRanking()
	{
		server.readRankingFile();
		try {
			 BufferedReader reader = new BufferedReader(new FileReader(ranking));
		        String line="";
		        while((line=reader.readLine())!=null){
		        	write("Ranking>"+line);
		        }
		        write("Ranking>end");
		        reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			server.freeReadRankingFile();
		}
	}
	
	@Override
	public void run() {

		try{ 
			while(!connectionClosed)
			{
				objectRead=InputStream.readLine();
				
				if( objectRead instanceof String)
				{


					String[] split=new String[4];
					split=objectRead.split("<:>");

					switch (split[0]) {
					case "User":
						if(!userIsConnected(split[1]))
						{
							users.add(this);
							this.username=split[1];
							for (Connection user : users) {	
								if(user.username.equals(split[1]))
								{
									user.write("Connected");
								}
								user.write("Message>RefreshUsersConnected");

								for (Connection u : users) {
									user.write((String)u.username);
								}
								user.write("ListUser:end");


							}
							for (Connection user : users) {	
								if(rooms.size()>0)
								{user.write("Message>RefreshRooms");
								for (RoomGame room : rooms) {
									user.write(room.getId());
								}
								user.write("ListRoom:end");}
							}

							for (Connection user : users) {		
								if(associatedRoom.size()>0)
								{
									user.write("Message>RefreshRoomsAssociated");
									for (Associated room : associatedRoom) {
										user.write(room.nameRoom+":"+room.nameWorld);
									}
									user.write("ListRoomAssociated:end");}
							}

						}
						else
						{OutpuStream.write("Error>user\n");
						OutpuStream.flush();
						connectionClosed=true;
						}

						break;

					case "Room":
						String roomName=split[1];
						String worldName=split[2];
						RoomGame roomGame=new RoomGame(roomName,worldName);

						if(!roomExist(roomGame))
						{
							roomGame.setClient1(this);
							server.addRoom(roomGame);

							Associated tmp=new Associated(roomName, worldName);
							server.addAssociatedRoom(tmp);

						}
						else
						{

							for (RoomGame r : rooms) {
								if(r.getId().equals(roomGame.getId()))
								{
									if(r.getClient2()==null)
									{
										r.setClient2(this);
										Associated tmp=new Associated(roomName, worldName);
										server.addAssociatedRoom(tmp);
									}
									else
									{
										write("Error>RoomFull");
									}
								}

							}
						}


						for (Connection user : users) {		
							user.write("Message>RefreshRooms");
							for (RoomGame room : rooms) {
								user.write(room.getId());
							}
							user.write("ListRoom:end");


							user.write("Message>RefreshRoomsAssociated");
							for (Associated room : associatedRoom) {
								user.write(room.nameRoom+":"+room.nameWorld);
							}
							user.write("ListRoomAssociated:end");

						}

						break;

					case "CloseConnection":
						if(split[1].equals("#Close#"))
						{
							server.closeConnection(split[1]);
							connectionClosed=true;
							}
						else
						{
						
							Connection userToDelete=null;
							for (Connection user : users) {
								if(user.username.equals(split[1]))
								{
									userToDelete=user;
								}
							}
							users.remove(userToDelete);
							connectionClosed=true;
						}
						
						
						for (Connection user : users) {	
							if(user.username.equals(split[1]))
							{
								user.write("Connected");
							}
							user.write("Message>RefreshUsersConnected");

							for (Connection u : users) {
								user.write((String)u.username);
							}
							user.write("ListUser:end");


						}
						
						break;



					case "CloseRoom":
						RoomGame roomDelete=null;
						Associated associatedToDelete=null;

						for (RoomGame room : rooms) {
							if(room.getId().equals(split[1]) && room.getClient1().username.equals(split[2]))
							{
								room.setRoomClosed(true);
								roomDelete=room;
							}
							if(room.getId().equals(split[1]) && !room.getClient1().username.equals(split[2]))
							{
								room.deleteClient2();
							}
						}

						if(roomDelete!=null && roomDelete.getClient1().username.equals(split[2]))
						{
							List<Associated> associatedList=new ArrayList<Associated>();
							for (Associated associeted : associatedRoom) {
								if(associeted.nameRoom.equals(split[1]))
									associatedList.add(associeted);
								
							}
							for (Associated associated : associatedList) {
								associatedRoom.remove(associated);
							}
                            
							rooms.remove(roomDelete);
						}else{

							for (Associated associeted : associatedRoom) {
								if(associeted.nameRoom.equals(split[1]))
									associatedToDelete=associeted;

							}
							associatedRoom.remove(associatedToDelete);
						}

						for (Connection user : users) {		
							user.write("Message>RefreshRooms");
							for (RoomGame room : rooms) {
								user.write(room.getId());
							}
							user.write("ListRoom:end");
						}
						for (Connection user : users) {		
							user.write("Message>RefreshRoomsAssociated");
							for (Associated room : associatedRoom) {
								user.write(room.nameRoom+":"+room.nameWorld);
							}
							user.write("ListRoomAssociated:end");
						}
						break;

					case "AddTower":
						String idRoom=split[1];
						String typeTower=split[2];

						int x=InputStream.read();
						int y=InputStream.read();

						for (RoomGame room : rooms) {
							if(room.getId().equals(idRoom))
							{
								room.enableAddTower();
								room.addTower(typeTower, x, y);
							}
						}
 
						
						break;
					case "Chat":
						for (Connection user : users) {		
							user.write("Chat>"+split[1]);
						}
						break;

					case "PrivateChat":
						for (RoomGame room : rooms) {
							if(room.getId().equals(split[1]))
							{
								room.sendPrivateMessage(split[3],split[2]);
							}
						}
						break;
					case "Play":
						for (RoomGame room : rooms) {
							if(room.getId().equals(split[1]))
							{
								room.setGameSpeed(50);
								room.setPause(false);
								if(!room.getState())
								{  room.play();}
							}
						}
						break;

					case "Start":
						state=true;
						server.startControlRoom(this.username);
						break;
					case "Stop":
						server.stopRoom(split[1]);
						break;
					case "Speed":
						for (RoomGame room : rooms) {
							if(room.getId().equals(split[1]))
							{
								room.setGameSpeed(25);
								if(!room.getState())
								{  
									room.setPause(false);
									room.play();}
							}
						}
						break;
					case "Pause":
						for (RoomGame room : rooms) {
							if(room.getId().equals(split[1]))
							{
								room.setPause(true);
							}
						}
						break;
						
					case "Ranking":
						if(split[1].equals("read"))
						{
							readRanking();
						}
						else if(split[1].equals("write"))
						{
							writeRanking(split[2]);
						}
						break;
					}

				}
			}

			//OutpuStream.flush();
			OutpuStream.close();

		}
		catch (Exception e) {
			// TODO: handle exception
		}finally{try {

			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

}

