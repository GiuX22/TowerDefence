package Online;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import PlugIn.StringEncrypter;
import PlugIn.StringEncrypter.EncryptionException;

public class LoadIP {
	
	public static void main(String[] args) {
		LoadIP tmp=new LoadIP();
		System.out.println(tmp.getIP());
	}
	
	private Properties properties;
	private String pathProperties;
	private static StringEncrypter encrypter;
	private String value;




	public LoadIP() {
		properties = new Properties();
		try {
			encrypter=new StringEncrypter("DES");
			properties.load(new FileInputStream("qrCodeBonus/ip"));
			pathProperties="qrCodeBonus/ip";
		} catch (IOException | EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		value=properties.getProperty("ip");
		
		/*try {
			System.out.println(encrypter.encrypt(192.168.0.1));
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		
	}
	
	
	public String getIP()
	{
		try {
			return encrypter.decrypt(value);
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
