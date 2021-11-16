
package PlugIn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import PlugIn.StringEncrypter.EncryptionException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import logicaOffline.Manager.GameManager;
import logicaOffline.character.Monster.AbstractMonster;
import logicaOffline.character.Tower.AbstractTower;
import logicaOffline.strategy.Strategy;
import logicaOffline.utility.TowerInfo;
import logicaOffline.world.World;

public class UtilityQRCode {

	private static UtilityQRCode utilityQRCode=null;
	private static StringEncrypter encrypter;
	public static boolean catturata=false;
	private Properties properties;
	private String pathProperties;
	private static int contatore=0;
	private List<AbstractMonster> monstersUtility;  
	private int level; 
	private List<AbstractTower> towersUtility; 
	private List<TowerInfo> TowersAddInfo; 
	private Strategy strategy; 
	protected boolean endGame; 
	private World world; 
	private int idTower; 
	private String typeStrategy;
	private boolean bossInGame;


	private long GAMESPEED;
	private boolean gamePause;
	private boolean win;




	private static GameManager gameManager;

	private UtilityQRCode(GameManager gameManager) 
	{
			try {
				encrypter=new StringEncrypter("DES");
			} catch (EncryptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		properties = new Properties();
		try {
			properties.load(new FileInputStream("qrCodeBonus/count"));
			pathProperties="qrCodeBonus/count";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String valore;
		valore=properties.getProperty("count");
		contatore=Integer.valueOf(valore);



		this.gameManager=gameManager;

		monstersUtility=gameManager.getMonstersUtility();
		level=gameManager.getLevel();
		towersUtility=gameManager.getTowersUtility();
		TowersAddInfo=gameManager.getTowersAddInfo();
		endGame=gameManager.isEndGame();
		world=gameManager.getWorld();
		bossInGame=gameManager.isBossInGame();
		GAMESPEED=gameManager.getGAMESPEED();
		gamePause=gameManager.isGamePause();
		win=gameManager.isWin();
	}

	public static UtilityQRCode getIstance(GameManager gameManager) throws EncryptionException
	{
		if(utilityQRCode==null)
		{
			return utilityQRCode=new UtilityQRCode(gameManager);
		}
		return utilityQRCode;
	}


	public static UtilityQRCode getIstanceSimple()
	{
		if(utilityQRCode!=null)
		{
			return utilityQRCode;
		}
		return null;
	}




	public void generateQRCode(String stringa)
	{
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		byte[] b = null;
		try {
			
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(stringa+":"+contatore));
			b = bbuf.array();
		} catch (CharacterCodingException e) {
			System.out.println(e.getMessage());
		}

		String data = null;
		try {
			data = new String(b, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

		// get a byte matrix for the data
		BitMatrix matrix = null;
		int h = 800;
		int w = 800;
		com.google.zxing.Writer writer = new QRCodeWriter();
		try {
			matrix = writer.encode(data,
					com.google.zxing.BarcodeFormat.QR_CODE, w, h);
		} catch (com.google.zxing.WriterException e) {
			System.out.println(e.getMessage());
		}

		String filePath = stringa+contatore+".png";

		File file = new File("Bonus/"+filePath);
		
		try {
			MatrixToImageWriter.writeToFile(matrix, "PNG", file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}



		contatore++;
		properties.setProperty("count", String.valueOf(contatore));
		try {
			properties.store(new FileOutputStream(pathProperties), "");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static String readfromImage( BufferedImage p_image ) {
		Result result = null;
		BufferedImage image = null;
		BinaryBitmap binaryBitmap;

		if((p_image instanceof BufferedImage))
		{
			try {
				binaryBitmap = new BinaryBitmap( new HybridBinarizer( new BufferedImageLuminanceSource( p_image )));  
				result = new MultiFormatReader().decode(binaryBitmap);


			} catch ( Exception ex ) {
//				ex.printStackTrace();
			}

			String [] split=result.getText().split(":");

			if(split[0].equals("increasesMoney"))
			{
				boolean trovato=false;
				String tmp;
				BufferedReader br = null;
				String st_nomeF = "qrCodeBonus/Bonus.txt";
				FileReader fr;
				try {
					fr = new FileReader(st_nomeF);
					br = new BufferedReader(fr);
					while((tmp=br.readLine()) != null)
					{
						String tmp1;
						try {
							tmp1 = encrypter.decrypt(tmp);
							if(tmp1.equals(result.getText())==true)
							{
								trovato=true;
							}
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(trovato==false)
					{
						gameManager.setScore(3000);
						File file = new File(st_nomeF);

						FileWriter fileWriter = new FileWriter(file,true);

						BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
						try {
							String cr= encrypter.encrypt(result.getText());
							fileWriter.append(cr);
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bufferFileWriter.newLine();

						bufferFileWriter.close();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if(split[0].equals("increasesRange"))
			{
				boolean trovato=false;
				String tmp;
				BufferedReader br = null;
				String st_nomeF = "qrCodeBonus/Bonus.txt";
				FileReader fr;
				try {
					fr = new FileReader(st_nomeF);
					br = new BufferedReader(fr);
					while((tmp=br.readLine()) != null)
					{

						String tmp1;
						try {
							tmp1 = encrypter.decrypt(tmp);
							if(tmp1.equals(result.getText())==true)
							{
								trovato=true;
							}
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(trovato==false)
					{
						for (int i = 0; i < gameManager.getTowersUtility().size(); i++) {
							gameManager.upgradeTower(gameManager.getTowersUtility().get(i).getId(), "rangeBonus");
						}
						File file = new File(st_nomeF);

						FileWriter fileWriter = new FileWriter(file,true);

						BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
						try {
							String cr= encrypter.encrypt(result.getText());
							fileWriter.append(cr);
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						bufferFileWriter.newLine();

						bufferFileWriter.close();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if(split[0].equals("increasesFire"))
			{
				boolean trovato=false;
				String tmp;
				BufferedReader br = null;
				String st_nomeF = "qrCodeBonus/Bonus.txt";
				FileReader fr;
				try {
					fr = new FileReader(st_nomeF);
					br = new BufferedReader(fr);
					while((tmp=br.readLine()) != null)
					{

						String tmp1;
						try {
							tmp1 = encrypter.decrypt(tmp);
							if(tmp1.equals(result.getText())==true)
							{
								trovato=true;
							}
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(trovato==false)
					{
					 if(gameManager == null)
						 System.out.println("NULL");
						for (int i = 0; i < gameManager.getTowersUtility().size(); i++) {
							gameManager.upgradeTower(gameManager.getTowersUtility().get(i).getId(), "fireBonus");
						}
						File file = new File(st_nomeF);

						FileWriter fileWriter = new FileWriter(file,true);

						BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
						try {
							String cr= encrypter.encrypt(result.getText());
							fileWriter.append(cr);
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						bufferFileWriter.newLine();

						bufferFileWriter.close();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


			return result.getText();
		}
		else
			return null;

	}



	public static String readfromWebcam() {

		Dimension[] nonStandardResolutions = new Dimension[] {
				WebcamResolution.PAL.getSize(),
				WebcamResolution.HD720.getSize(),
				new Dimension(2000, 1000),
				new Dimension(1000, 500),
		};

		Webcam webcam = Webcam.getDefault(); // non-default (e.g. USB) webcam can be used too

		webcam.setCustomViewSizes(nonStandardResolutions);
		webcam.setViewSize(WebcamResolution.HD720.getSize());

		webcam.open();

		Result result = null;
		BufferedImage image = null;

		JDialog frame=new JDialog();
		frame.setLocation(400, 200);
		frame.setSize(new Dimension(400, 300));
		PanelQRCode panel = new PanelQRCode(webcam.getImage());
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		boolean premClose=false;

		while (!catturata) {
			image = webcam.getImage(); 
			panel.refresh(image);
			panel.requestFocus();
			panel.updateUI();
			if(gameManager.isWin() || gameManager.isGameOver())
			{
				premClose=true;
				break;
			}
		}
		catturata=false;

		webcam.close();
		frame.dispose();

		BinaryBitmap binaryBitmap;

		try {

			binaryBitmap = new BinaryBitmap( new HybridBinarizer( new BufferedImageLuminanceSource( image )));  
			result = new MultiFormatReader().decode(binaryBitmap);

		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
		if(result==null)
		{
			if(premClose==false)
			JOptionPane.showMessageDialog(null, "Il QR-Code non e' stato caricato correttamente, riprovare. Se il problema persiste si consiglia di caricare il bonus da file.");
		}

		if(result!=null)
		{
			String [] split=result.getText().split(":");
			if(split[0].equals("increasesMoney"))
			{	boolean trovato=false;
			String tmp;
			BufferedReader br = null;
			String st_nomeF = "qrCodeBonus/Bonus.txt";
			FileReader fr;
			try {
				fr = new FileReader(st_nomeF);
				br = new BufferedReader(fr);
				while((tmp=br.readLine()) != null)
				{
					String tmp1;
					try {
						tmp1 = encrypter.decrypt(tmp);
						if(tmp1.equals(result.getText())==true)
						{
							trovato=true;
						}
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(trovato==false)
				{
					gameManager.setScore(3000);
					File file = new File(st_nomeF);

					FileWriter fileWriter = new FileWriter(file,true);

					BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
					try {
						String cr= encrypter.encrypt(result.getText());
						fileWriter.append(cr);
					} catch (EncryptionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bufferFileWriter.newLine();

					bufferFileWriter.close();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}

			if(split[0].equals("increasesRange"))
			{
				boolean trovato=false;
				String tmp;
				BufferedReader br = null;
				String st_nomeF = "qrCodeBonus/Bonus.txt";
				FileReader fr;
				try {
					fr = new FileReader(st_nomeF);
					br = new BufferedReader(fr);
					while((tmp=br.readLine()) != null)
					{

						String tmp1;
						try {
							tmp1 = encrypter.decrypt(tmp);
							if(tmp1.equals(result.getText())==true)
							{
								trovato=true;
							}
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(trovato==false)
					{
						for (int i = 0; i < gameManager.getTowersUtility().size(); i++) {
							gameManager.upgradeTower(gameManager.getTowersUtility().get(i).getId(), "rangeBonus");
						}
						File file = new File(st_nomeF);

						FileWriter fileWriter = new FileWriter(file,true);

						BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
						try {
							String cr= encrypter.encrypt(result.getText());
							fileWriter.append(cr);
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						bufferFileWriter.newLine();

						bufferFileWriter.close();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if(split[0].equals("increasesFire"))
			{
				boolean trovato=false;
				String tmp;
				BufferedReader br = null;
				String st_nomeF = "qrCodeBonus/Bonus.txt";
				FileReader fr;
				try {
					fr = new FileReader(st_nomeF);
					br = new BufferedReader(fr);
					while((tmp=br.readLine()) != null)
					{

						String tmp1;
						try {
							tmp1 = encrypter.decrypt(tmp);
							if(tmp1.equals(result.getText())==true)
							{
								trovato=true;
							}
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(trovato==false)
					{
					 if(gameManager == null)
						 System.out.println("NULL");
						for (int i = 0; i < gameManager.getTowersUtility().size(); i++) {
							gameManager.upgradeTower(gameManager.getTowersUtility().get(i).getId(), "fireBonus");
						}
						File file = new File(st_nomeF);

						FileWriter fileWriter = new FileWriter(file,true);

						BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
						try {
							String cr= encrypter.encrypt(result.getText());
							fileWriter.append(cr);
						} catch (EncryptionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						bufferFileWriter.newLine();

						bufferFileWriter.close();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		if(result!=null)
		return result.getText();
		else
		return null;
	}


}
