package Online.UtilityOnline;

import java.io.IOException;

import javax.swing.JOptionPane;


import logicaOffline.utility.AePlayWave;
import logicaOffline.utility.TowerInfo;
import Grafica.MainFrame;
import Online.GamePanelOnLine;
import Online.PlayerOnline;
import Online.PrincipalOnLinePanel;

public class PlayerThreadListener extends Thread
{
	PlayerOnline player;
	private int countBuffer=0;
	
   	public PlayerThreadListener(PlayerOnline p) {
		player=p;
	}
   	
   	int cont=0;
   	@Override
   	public void run() {
   		String[] split=new String[2];
   		while(!player.isCloseConnection())
		{ 
   			
			try {
                
				String in=player.readFromServer();
				if(in instanceof String)
				{ 
					
					split=in.split(">");
					
					switch (split[0]) {
					case "GameSpeed":
						player.setGameSpeed(Integer.parseInt(split[1]));
						break;
					case "StopGame":
						player.setStopGame(true);
					    break;
					case "Score":
						String score[]=split[1].split("\\.");
						player.setScore(Integer.parseInt(score[0]));
						break;
					case "Level":
						player.setLevel(Integer.parseInt(split[1]));
						break;
					case "LevelOver":
						if(split[1].equals("true"))
						{
							player.setLevelOver(true);
						}
						else
						{player.setLevelOver(false);}
						break;
					case "GameOver":
						if(split[1].equals("true"))
						{
							player.setGameOver(true);
						}
						else
						{player.setGameOver(false);}
						break;
					case "LifePlayer":
						player.setLifePlayer(Integer.parseInt(split[1]));
						break;
					case "Monster":
						player.getMonstersBuffer().put(split[1]);
						countBuffer++;
						if(countBuffer>=20)
						{
							player.loaded();
						}
						break;
					case "Tower":
						System.out.println(in);
						if(!split[1].equals("addTower"))
						{player.getTowersBuffer().add(split[1]);}
						else
						{
							player.getTowersBuffer().clear();
						}
						break;
					case "Bullet":
						player.getBulletsBuffer().put(split[1]);
						break;
					case "StartTowerInfo":
						player.getTowerAddInfo().clear();
						break;
					case "TowersAddInfo":
						player.getTowerAddInfo().add(split[1]);
						break;
					case "Message":
						switch (split[1]) {
						case "RefreshRoomsAssociated":
							player.refreshListRoomsAssociated();
							break;
						case "RefreshRooms":
							player.refreshListRooms();
							break;
						case "RefreshUsersConnected":
							player.refreshListUser();
							break;
						}
						break;
					case "Chat":
							player.getMessageChat().put(split[1]);
							new AePlayWave("sound/chat.wav").start();
						break;
					case "PrivateChat":
						    player.getMessagePrivateChat().put(split[1]);
						    new AePlayWave("sound/chat.wav").start();
					break;
					case "PlayRoom":
						MainFrame.getIstanceMainframe().switchTo(new GamePanelOnLine(player, MainFrame.getIstanceMainframe()));
						break;
					case "StartUpdate":
						player.setPlayRoom(true);
						break;
					case "Ranking":
						if(split[1].equals("end"))
						{
							player.setRankingLoad(true);
						}
						else
						{   
							player.getRanking().add(split[1]);
						}
						break;
					case "Error":
						switch (split[1]) {
						case "RoomFull":
							JOptionPane.showMessageDialog(null, "Room selected is full ...", "ERRORE",  JOptionPane.ERROR_MESSAGE);
							
							break;

						default:
							break;
						}
			            break;
					}
				}
				

			} catch (ClassNotFoundException | IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
         
		}
   	}
}