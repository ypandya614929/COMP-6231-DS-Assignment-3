package server;
//References:
//https://systembash.com/a-simple-java-udp-server-and-udp-client/
//https://www.geeksforgeeks.org/multithreading-in-java/
//https://www.geeksforgeeks.org/synchronized-in-java/
//https://docs.oracle.com/javaee/6/tutorial/doc/gijti.html
//https://www.javatpoint.com/web-services-tutorial
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.ws.Endpoint;

import constants.Constants;
/**
 *
 * @author ypandya
 */
public class GameServer {
	
	/**
	 * This is the GameServer class
	 */
	
	/**
	 * main method to run all the servers
	 * @param args args for main function
	 */
	public static void main(String args[]) {
		
		buildLogDirectory(Constants.LOGS_DIRECTORY);
		
		try {
						
			EUServer europe = new EUServer();
    		ASServer asia = new ASServer();
    		NAServer northamerica = new NAServer();
    		
    		Endpoint europeEndPoint = Endpoint.publish(Constants.EU_ENDPOINT_URL, europe);
    		Endpoint northamericaEndPoint = Endpoint.publish(Constants.NA_ENDPOINT_URL, northamerica);
    		Endpoint asiaEndPoint = Endpoint.publish(Constants.AS_ENDPOINT_URL, asia);

    		System.out.println("Europe service started : " + europeEndPoint.isPublished());
    		System.out.println("North America service started : " + northamericaEndPoint.isPublished());
    		System.out.println("Asia service started : " + asiaEndPoint.isPublished());
    		
            System.out.println("Server(s) are Started");
    		
    		loadData(europe, northamerica, asia);
    		
    		System.out.println("Initial data loaded into server");
    		
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
				
	}
	
	/**
	 * This method is used to load the initial player data
	 * @param europe europe controller object 
	 * @param northamerica northamerica controller object 
	 * @param asia asia controller object 
	 */
	static void loadData(EUServer europe, NAServer northamerica, ASServer asia) {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(Constants.DATA_FILE_PATH));
			String line = reader.readLine();
			while (line != null) {
				String[] listParts = line.split(",");
				String firstName = listParts[0];
				String lastName = listParts[1];
				String age = listParts[2];
				String userName = listParts[3];
				String password = listParts[4];
				String ipAddress = listParts[5];
				
				if (ipAddress.startsWith("132")) {
					northamerica.createPlayerAccount(firstName, lastName, age, userName, password, ipAddress);
				} else if (ipAddress.startsWith("93")) {
					europe.createPlayerAccount(firstName, lastName, age, userName, password, ipAddress);
				} else if (ipAddress.startsWith("182")) {
					asia.createPlayerAccount(firstName, lastName, age, userName, password, ipAddress);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to create logs directory to store the logs
	 * @param path location of the logs folder
	 */
	public static void buildLogDirectory(String path) {
		File outputDir = new File(path);
		if (!outputDir.exists()) {
			outputDir.mkdir();
		}
	}
	
}