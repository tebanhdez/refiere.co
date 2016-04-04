package co.refiere.mailer;

import java.io.InputStream;
import java.net.URL;

public class ResourceManager {

	public static InputStream getResourceAsInputStream(String resourceName){
		InputStream inputStreamFile = ClassLoader.getSystemResourceAsStream(resourceName);
		return inputStreamFile;
	}
	
	public static URL getHtmlXslAsInputStream(){
		return getResourceUrl("HtmlXsl.xsl");
	}
	
	public static URL getResourceUrl(String resourceName){
		return ClassLoader.getSystemResource(resourceName);
	}
}
