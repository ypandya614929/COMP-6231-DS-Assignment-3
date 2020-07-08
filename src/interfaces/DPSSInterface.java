package interfaces;

import java.io.IOException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.WebMethod;

/**
 * @author ypandya
 *
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DPSSInterface {
	
	/**
	 * This interface method is used to get the status of all the players
	 * @param userName username of the admin
	 * @param password password of the admin
	 * @param ipAddress ip of the admin
	 * @return String containing number of online and offline players
	 * @throws IOException
	 */
	@WebMethod
	public String getPlayerStatus(String userName, String password, String ipAddress) throws IOException;
	
	/**
	 * @param AdminUsername username of the admin
	 * @param AdminPassword password of the admin
	 * @param AdminIP ip of the admin
	 * @param UsernameToSuspend player username
	 * @return String containing success or error message
	 * @throws IOException
	 */
	@WebMethod
	public String suspendAccount(String AdminUsername, String AdminPassword, String AdminIP, String UsernameToSuspend) throws IOException;
	
	/**
	 * This interface method is used to create the player account
	 * @param firstName firstname of the player
	 * @param lastName lastname of the player
	 * @param age age of the player
	 * @param userName username of the player
	 * @param password password of the player
	 * @param ipAddress ip of the player
	 * @return String containing success or error message
	 * @throws IOException
	 */
	@WebMethod
	public String createPlayerAccount(String firstName, String lastName, String age, String userName, String password, String ipAddress) throws IOException;

	/**
	 * This interface method is used to sign in into the player account
	 * @param userName username of the player
	 * @param password password of the player
	 * @param ipAddress ip of the player
	 * @return String containing success or error message
	 * @throws IOException
	 */
	@WebMethod
	public String playerSignIn(String userName, String password, String ipAddress) throws IOException;

	/**
	 * This interface method is used to sign out from the player account
	 * @param userName username of the player
	 * @param ipAddress ip of the player
	 * @return String containing success or error message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@WebMethod
	public String playerSignOut(String userName, String ipAddress) throws IOException, InterruptedException;
	
	/**
	 * @param userName username of the player
	 * @param password password of the player
	 * @param OldIPAddress old ip of the player
	 * @param NewIPAddress new ip of the player
	 * @return String containing success or error message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@WebMethod
	public String transferAccount(String userName, String password, String OldIPAddress, String NewIPAddress) throws IOException, InterruptedException;

	
}
