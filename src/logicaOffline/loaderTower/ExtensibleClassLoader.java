package logicaOffline.loaderTower;

import java.net.URL;
import java.net.URLClassLoader;

public class ExtensibleClassLoader extends URLClassLoader
{

    public ExtensibleClassLoader(final URL[] urls, final ClassLoader parent)
    {
        super(urls, parent);
    }

    @Override
    public void addURL(final URL url)
    {
        super.addURL(url);

    }
}