package SeleniumCommands;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepository {

	public Properties ReadProperties(String propertyFile) throws IOException {
	Properties prop=new Properties();
	FileInputStream fi=new FileInputStream(propertyFile);
	prop.load(fi);
	return prop;
	}
}
