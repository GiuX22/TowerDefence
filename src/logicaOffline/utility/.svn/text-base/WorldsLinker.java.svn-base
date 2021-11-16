package logicaOffline.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;







public class WorldsLinker {

	public static HashMap<String, String> linker;
	private static Properties properties;
	private static String propertiesPath;


	public WorldsLinker() {



		linker = new HashMap<String, String>();

		initProperties();
		link();




	}

	private void link() {
		
		
		linker.put("world1", "Sfondoclassico.jpg");
		linker.put("world2", "Sfondoclassico.jpg");
		linker.put("world3", "Sfondoclassico.jpg");
		
		
		
		
		Enumeration e = properties.keys();
		while(e.hasMoreElements())
		{
			Object obj = e.nextElement();

			linker.put(obj.toString(), properties.getProperty(obj.toString()));

		}

	}

	private void initProperties() {

		properties = new Properties();

		propertiesPath = "./FILEWORLD/worldProperties";

		try {
			properties.load(new FileInputStream(propertiesPath));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






	}

	public static void setWorld (String worldName, String worldImageName)
	{
		properties.setProperty(worldName, worldImageName);
		linker.put(worldName, worldImageName);
		try {
			properties.store(new FileOutputStream(propertiesPath), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String getImageName(String worldName)
	{

		return linker.get(worldName);
	}

}
