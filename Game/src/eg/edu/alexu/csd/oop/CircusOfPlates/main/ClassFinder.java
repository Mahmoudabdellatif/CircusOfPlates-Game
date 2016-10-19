package eg.edu.alexu.csd.oop.CircusOfPlates.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.CircusOfPlates.strategy.Difficulty;

public class ClassFinder {

	private static ClassFinder classFinder;
	private Logger log = Logger.getLogger(ClassFinder.class);

	private ClassFinder() {
	}

	public static ClassFinder getInstance() {
		if (classFinder == null) {
			classFinder = new ClassFinder();
		}
		return classFinder;
	}

	public List<Class<? extends Difficulty>> find() {
		String classPath = System.getProperty("java.class.path");
		String[] paths = classPath.split(System.getProperty("path.separator"));
		List<Class<? extends Difficulty>> list = new LinkedList<Class<? extends Difficulty>>();
		log.info("class loader");
		for (String path : paths) {
			if (detectJarFile(path)) {
				try {
					JarFile jarFile = new JarFile(path);
					list = jarFileIterator(jarFile, true, null, list);
					jarFile.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		return findJars(list);
	}

	private List<Class<? extends Difficulty>> findJars(List<Class<? extends Difficulty>> list) {
		File file = new File(
				System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "shady.txt");
		if (file.exists())
			try {
				Scanner reader = new Scanner(file);
				while (reader.hasNextLine()) {
					String pathToJar = reader.nextLine();
					JarFile jarFile = new JarFile(pathToJar);
					list = jarFileIterator(jarFile, false, pathToJar, list);
					jarFile.close();
				}
				reader.close();
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		return list;
	}

	@SuppressWarnings({ "unchecked" })
	private List<Class<? extends Difficulty>> jarFileIterator(JarFile file, boolean check, String pathToJar,
			List<Class<? extends Difficulty>> list) {
		try {
			URLClassLoader cl = null;
			if (check == false) {
				URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
				cl = URLClassLoader.newInstance(urls);
			}
			Enumeration<?> e = file.entries();
			while (e.hasMoreElements()) {
				try {
					JarEntry je = (JarEntry) e.nextElement();
					if (je.isDirectory() || !je.getName().endsWith(".class")
							|| je.getName().startsWith("org/apache/log4j")) {
						continue;
					}
					String className = je.getName().substring(0, je.getName().length() - 6);
					className = className.replace('/', '.');
					Class<?> c;

					if (check == true) {
						c = Class.forName(className);
					} else {
						c = cl.loadClass(className);
					}
					if (Difficulty.class.isAssignableFrom(c) && !c.isInterface()
							&& !Modifier.isAbstract(c.getModifiers())) {
						list.add((Class<? extends Difficulty>) c);
					}
				} catch (Exception e1) {
				}
			}
		} catch (Exception e2) {
		}
		return list;
	}

	private boolean detectJarFile(String file) {
		try {
			JarFile jarFile = new JarFile(file);
			jarFile.close();
		} catch (java.util.zip.ZipException e) {
			return false;
		} catch (IOException e) {

			return false;
		}
		return true;
	}
}