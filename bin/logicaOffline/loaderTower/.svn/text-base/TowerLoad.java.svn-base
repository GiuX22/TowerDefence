package logicaOffline.loaderTower;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;

import logicaOffline.character.Tower.Tower;

public class TowerLoad {


	public TowerLoad() {
		
	}

	public ArrayList<Tower> loadTower()throws Exception
	{
		final ArrayList<Tower> towerList = new ArrayList<Tower>();
		final File directory = new File("tower");
		final String[] list = directory.list(new FilenameFilter()
		{
			@Override
			public boolean accept(final File dir, final String name)
			{
				return name.toUpperCase().endsWith(".JAR");
			}
		});
		final ExtensibleClassLoader classLoader = new ExtensibleClassLoader(new URL[] {}, Thread.currentThread()
				.getContextClassLoader());
		for (final String fileName : list)
		{
			classLoader.addURL(new File("tower" + File.separator + fileName).toURI().toURL());
			final String className = fileName.substring(0, fileName.length() - 4);
			final Class<? > loadedClass = classLoader.loadClass(className);
			if (Tower.class.isAssignableFrom(loadedClass))
			{
				final Tower newInstance = (Tower) loadedClass.newInstance();
				towerList.add(newInstance);
			}
		}
		return towerList;
	}
}


