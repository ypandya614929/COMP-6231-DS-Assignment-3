//References:
//https://systembash.com/a-simple-java-udp-server-and-udp-client/
//https://www.geeksforgeeks.org/multithreading-in-java/
//https://www.geeksforgeeks.org/synchronized-in-java/
//https://docs.oracle.com/javaee/6/tutorial/doc/gijti.html
//https://www.javatpoint.com/web-services-tutorial
package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import interfaces.DPSSInterface;

/**
 * @author ypandya
 *
 */
public class Testing extends Thread{
	
	public static DPSSInterface conObj;
	static URL url;
	
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		
		Testing t = new Testing();
		System.out.println("--------------------------- Basic Test Cases ---------------------------\n");
		System.out.println("Test case detail");
		System.out.println("----------------");
		System.out.println("Username : testuserdata1");
		System.out.println("Password : testuserdata1");
		System.out.println("Old IP : 182.123.123.123 (Asian)");
		System.out.println("New IP : 93.123.123.123 (Europe)\n");

		System.out.println("\nTest 1 : Create player account with 182.123.123.123 (Asian) Server");
		t.create();
		System.out.println("\nTest 2 : Sign in player account in 182.123.123.123 (Asian) Server");
		t.signin();
		System.out.println("\nTest 3 : Get player status");
		t.getplayerstatus();
		System.out.println("\nTest 4 : Sign out player account from 182.123.123.123 (Asian) Server");
		t.signout();
		System.out.println("\nTest 5 : Transfer player account 182.123.123.123 (Asian) to 93.123.123.123 (Europe) Server");
		t.transferaccount();
		System.out.println("\nTest 6 : Try Sign in player account into old server (182.123.123.123 Asian) after transferting");
		t.signin();
		System.out.println("\nTest 7 : Sign in player account into new server (93.123.123.123 Europe) after transferting");
		t.signinagain();
		System.out.println("\nTest 8 : Get player status");
		t.getplayerstatusagain();
		System.out.println("\nTest 9 : Suspend player account from 93.123.123.123 (Europe) Server");
		t.suspendaccount();
		System.out.println("\nTest 10 : Try Sign in player account after suspended in 93.123.123.123 (Europe) Server");
		t.signinagain();
		System.out.println("\nTest 11 : Get player status");
		t.getplayerstatusagain();
		System.out.println();
		
		System.out.println("\n--------------------------- Advanced Test Cases ---------------------------\n");
		System.out.println("Test case detail");
		System.out.println("----------------");
		System.out.println("- I have made 3 threads that run on Asian server and transfer operations are performed on Europe server");
		System.out.println("\n- Each threads try to create 3 different players with username testuserdata1, testuserdata2 and testuserdata3");
		System.out.println("\n- Out of 9 thread calls only 3 will be successful and rest of them contains an error");
		System.out.println("\n- Each threads try to suspend testuserdata1 player account only 1 out of 3 will be successful");
		System.out.println("\n- Each threads try to transfer testuserdata1 player account if its already suspended than none of thread "
				+ "\n  will be successful otherwise only 1 out of 3 will be successful");
		System.out.println("\n- Each threads try to transfer testuserdata2 player account only 1 out of 3 will be successful");
		System.out.println("\n- Each threads try to suspend testuserdata2 player account if its already transfered than none of thread "
				+ "\n  will be successful otherwise only 1 out of 3 will be successful");
		System.out.println("\n- I have made 3 threads that run on Asian server\n");
		
		
		for (int i=0; i<3; i++){
			Testing t1 = new Testing();
			t1.start();
		}
		
		Thread.sleep(1200);
		
		System.out.println("\n- testuserdata1 and testuserdata2 is either suspended or transfered depending on operation result,"
				+ "\n however testuserdata3 is always created and offline. result of get player status after all thread execution as below\n");
		System.out.println("\nMethod : getPlayerStatus() , " + createObject("182.123.123.123").getPlayerStatus("Admin", "Admin", "182.123.123.123"));
				
		// removing players after test execution to avoid from errors
		// and no need to restart server again to perform other actions.
		createObject("182.123.123.123").suspendAccount("Admin", "Admin", "182.123.123.123", "testuserdata3");
		createObject("93.123.123.123").suspendAccount("Admin", "Admin", "93.123.123.123", "testuserdata2");
				
	}
	
	public void create() throws IOException {
		conObj = createObject("182.123.123.123");
		System.out.println("Method : createPlayerAccount() , " + conObj.createPlayerAccount("testdata1", "userdata1", "24", "testuserdata1", "testuserdata1", "182.123.123.123"));
	}

	public void signin() throws IOException {
		conObj = createObject("182.123.123.123");
		System.out.println("Method : playerSignIn() , " + conObj.playerSignIn("testuserdata1", "testuserdata1", "182.123.123.123"));
	}
	
	public void getplayerstatus() throws IOException {
		conObj = createObject("182.123.123.123");
		System.out.println("Method : getPlayerStatus() , " + conObj.getPlayerStatus("Admin", "Admin", "182.123.123.123"));
	}
	
	public void signout() throws IOException, InterruptedException {
		conObj = createObject("182.123.123.123");
		System.out.println("Method : playerSignOut() , " + conObj.playerSignOut("testuserdata1", "182.123.123.123"));
	}
	
	public void transferaccount() throws IOException, InterruptedException {
		conObj = createObject("182.123.123.123");
		System.out.println("Method : transferAccount() , " + conObj.transferAccount("testuserdata1", "testuserdata1", "182.123.123.123", "93.123.123.123"));
	}
	
	public void signinagain() throws IOException {
		conObj = createObject("93.123.123.123");
		System.out.println("Method : playerSignIn() , " + conObj.playerSignIn("testuserdata1", "testuserdata1", "93.123.123.123"));
	}
	
	public void getplayerstatusagain() throws IOException {
		conObj = createObject("93.123.123.123");
		System.out.println("Method : getPlayerStatus() , " + conObj.getPlayerStatus("Admin", "Admin", "93.123.123.123"));
	}
	
	public void suspendaccount() throws IOException {
		conObj = createObject("93.123.123.123");
		System.out.println("Method : suspendAccount() , " + conObj.suspendAccount("Admin", "Admin", "93.123.123.123", "testuserdata1"));
	}
	
	/**
	 * @param ip
	 * @return
	 * @throws MalformedURLException
	 */
	public static DPSSInterface createObject(String ip) throws MalformedURLException {
		QName name = new QName("http://controller/", "ControllerService");
		
		if (ip.startsWith("132")){
			url = new URL("http://localhost:8080/DPSS/NA?wsdl");
		} 
		else if (ip.startsWith("93")){
			url = new URL("http://localhost:8080/DPSS/EU?wsdl");
		} 
		else if (ip.startsWith("182")){
			url = new URL("http://localhost:8080/DPSS/AS?wsdl");
		}
		
		Service service = Service.create(url, name);
		return service.getPort(DPSSInterface.class);
	}
	
	/**
	 *
	 */
	public void run(){
		
		try{
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : createPlayerAccount() , " + " Username : testuserdata1 , " + createObject("182.123.123.123").createPlayerAccount("testdata1", "userdata1", "24", "testuserdata1", "testuserdata1", "182.123.123.123"));
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : createPlayerAccount() , " + " Username : testuserdata2 , " + createObject("182.123.123.123").createPlayerAccount("testdata2", "userdata2", "24", "testuserdata2", "testuserdata2", "182.123.123.123"));
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : createPlayerAccount() , " + " Username : testuserdata3 , " + createObject("182.123.123.123").createPlayerAccount("testdata3", "userdata3", "24", "testuserdata3", "testuserdata3", "182.123.123.123"));
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : suspendAccount() , " + createObject("182.123.123.123").suspendAccount("Admin", "Admin", "182.123.123.123", "testuserdata1"));
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : transferAccount() , " + createObject("182.123.123.123").transferAccount("testuserdata1", "testuserdata1", "182.123.123.123", "93.123.123.123"));
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : transferAccount() , " + createObject("182.123.123.123").transferAccount("testuserdata2", "testuserdata2", "182.123.123.123", "93.123.123.123"));
			System.out.println("Thread " + Thread.currentThread().getId() + " Method : suspendAccount() , " + createObject("182.123.123.123").suspendAccount("Admin", "Admin", "182.123.123.123", "testuserdata2"));
			
		} catch (Exception e){
			System.out.println("Exception is caught");
		}
	}
	
}