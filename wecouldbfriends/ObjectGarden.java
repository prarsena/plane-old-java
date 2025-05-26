import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ObjectGarden{
	public static void main(String[] args) throws IOException {
		boolean match, authenticated = false;
		File userDb = new File("userDb.txt");
		Scanner kbd = new Scanner(System.in);
		User authUser = new User();
		ArrayList<User> allUsers = new ArrayList<User>();

		if (userDb.exists()){
			Scanner reader = new Scanner(userDb);
			while(reader.hasNext()){
				String user = reader.nextLine();
				int id = Integer.parseInt(user.split(",")[0]);
				String username = user.split(",")[1].trim();
				String realname = user.split(",")[2].trim();
				String pw = user.split(",")[3].trim();
				String school = user.split(",")[4].trim();
				String quote = user.split(",")[5].trim();
				allUsers.add(new User(id, username, realname, pw, school, quote));
			}
		}
		else {
			generateUsers(allUsers);
			User.writeAllUsersToFile(userDb, allUsers);
		}

		File friendsDir = new File("friends");
		if (!friendsDir.exists()){
			friendsDir.mkdir();
		}

		System.out.printf("%35s %n", "~~WELCOME TO THE OBJECT GARDEN~~");
		User currentUser = loginOrSignup(authUser, allUsers, authenticated, userDb, kbd);

		if (currentUser != null){
			char mainMenuChoice = 'z';
			System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + currentUser.getId() + ")!");
			do {
				System.out.println("\nType an option:");
				System.out.printf("%-30s %n", "   R - Read your friendslist from file.");
				System.out.printf("%-30s %n", "   A - Add friends.");
				System.out.printf("%-30s %n", "   D - Delete friends.");
				System.out.printf("%-30s %n%n", "   Q - Quit.");

				mainMenuChoice = kbd.nextLine().toLowerCase().charAt(0);
				System.out.println();
				if (mainMenuChoice == 's'){
					User.writeUserFriendsToFile(currentUser, true);
				}
				else if (mainMenuChoice == 'a'){
					char choice = 'y';
					String choiceStr = "";
					boolean friendFound = false;
					while (choice == 'y' || choiceStr.equals("")) {
						System.out.println("\nUserlist: ");
						User.getAllUsernames(allUsers);
						System.out.println("\nType the username of the friend you want:");
						String friend = kbd.nextLine();
						for (User u : allUsers) {
							//TODO: Add if case for friendFound == true. I guess..
							if (friend.equalsIgnoreCase(u.getUsername()) && !(currentUser.getUsername().equals(friend)) ){
								currentUser.addFriend(u);
								friendFound = true;
								System.out.print("Do u wanna add another friend? (Y/n)");
								choiceStr = kbd.nextLine();
								if (choiceStr.length() > 0){
									choice = choiceStr.toLowerCase().charAt(0);
								}
								else {
									choice = 'n';
								}
							}
						}
						if (!friendFound){
							System.out.println("Friend not found! Do you want to try again?");
							choiceStr = kbd.nextLine();
							choice = choiceStr.toLowerCase().charAt(0);
						}
					}
					if (friendFound){
						User.writeUserFriendsToFile(currentUser, true);
						currentUser.clearFriends();
					}
				}
				else if (mainMenuChoice == 'r'){
					System.out.println(currentUser.getUsername().toUpperCase() + "'S FRIENDLIST:");
					String userFriendlist = currentUser.readFriendsFromFile();
					System.out.println(userFriendlist);
				}
				else if (mainMenuChoice == 'd'){
					currentUser.deleteFriendFromFile();
				}
				else if (mainMenuChoice == 'q'){
					System.exit(69);
				}
				else {
					System.out.println("Invalid choice. Try again.");
				}
			} while (mainMenuChoice != 'q');


		}
		else {
			System.out.println("Sorry, some error!");
		}

	}

	public static void generateUsers(ArrayList<User> allUsers){
		User pete = new User("pete", "Petah Ahsenault", "", "Burlington High", "See u in the funny pages!!");
		User john = new User("jdoe", "John Doe", "password", "Springfield High", "I finally learned how to write without a pencil.");
                User jane = new User("jsmith", "Jane Smith", "password", "Riverview Academy", "I’m outta here! See you all at the 10-year reunion.");
                User alex = new User("ajohn", "Alex Johnson", "password", "Mountainview High", "I’m not a complete idiot. Some parts are missing.");
                User emily = new User("ebrown", "Emily Brown", "password", "Lakeside School", "I’m not late; I’m just early for tomorrow.");
                User michael = new User("mdavis", "Michael Davis", "password","Greenwood High", "I didn’t choose the school life; the school life chose me.");
                User sarah = new User("swilson", "Sarah Wilson", "password", "Sunnydale High", "I’m 100% certain that I am 0% sure of what I’m doing.");
                User david = new User("dmart", "David Martinez", "password","Riverside High", "I’m not arguing; I’m just explaining why I’m right.");
                User laura = new User("lgarc", "Laura Garcia", "password","Hilltop Academy", "I’m not lazy; I’m on energy-saving mode.");
                User james = new User("jlee", "James Lee", "password","Westside High", "I’m not a procrastinator; I’m just extremely productive at unimportant things.");
                User sophia = new User("skim", "Sophia Kim", "password","Eastwood High", "I’m not weird; I’m limited edition.");

		allUsers.add(pete);
		allUsers.add(john);
		allUsers.add(jane);
		allUsers.add(alex);
		allUsers.add(emily);
		allUsers.add(michael);
		allUsers.add(sarah);
		allUsers.add(david);
		allUsers.add(laura);
		allUsers.add(james);
		allUsers.add(sophia);
	}

	public static User loginOrSignup(User authUser, ArrayList<User> allUsers, boolean authenticated, File userDb, Scanner kbd) throws IOException {
		do {
			System.out.print("\nEnter username: ");
			String user = kbd.nextLine().trim();
			for (User u : allUsers) {
				if (user.equalsIgnoreCase(u.getUsername().trim())){
					System.out.print("Enter password: ");
					String password = kbd.nextLine();
					if (password.equals(u.getPassword().trim())){
						authenticated = true;
						authUser = u;
					}
				}
			}

			if (!authenticated) {
				System.out.println("Username not found. Do you want to make an account? (Y/n)");
				String mkAcctResp = kbd.nextLine().toLowerCase();
				char mkAcct = 'n';
				if (mkAcctResp.length() > 0) {
					mkAcct = mkAcctResp.charAt(0);
				}
				if (mkAcct == 'y' || mkAcctResp.equals("")){
					System.out.print("Enter username: ");
					String u = kbd.nextLine();
					System.out.print("Enter full name: ");
					String r = kbd.nextLine();
					System.out.print("Enter passsword: ");
					String p = kbd.nextLine();
					System.out.print("Enter school: ");
					String s = kbd.nextLine();
					System.out.println("Enter quote:");
					String q = kbd.nextLine();
					User tmpUser = new User(u, r, p, s, q);
					System.out.println(tmpUser);
					User.writeNewUserToFile(userDb, tmpUser);
					allUsers.add(tmpUser);
					authUser = tmpUser;
					authenticated = true;
				}
				else {
					System.out.println("OK. Press enter to try again, or type 'bye' to exit.");
					if (kbd.nextLine().toLowerCase().charAt(0) == 'b'){
						System.exit(69);
					}
				}
			}
		} while (!authenticated);
		return authUser;
	}
}


public class User{
	private int id;
	private String username;
	private String realname;
	private String password;
	private String school;
	private String quote;
	private ArrayList<User> friends = new ArrayList<User>();

	public User(){
		username = "Joe Dirt";
	}

	public User(String username, String realname, String password, String school, String quote){
		this.id = generateID();
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.school = school;
		this.quote = quote;
	}

	public User(int id, String username, String realname, String password, String school, String quote){
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.school = school;
		this.quote = quote;
	}

	public static int generateID(){
		Random rand = new Random();
		return rand.nextInt(999, 9999);
	}
	public void addFriend(User u) {
		boolean duplicateFriend = false;
		for (User f : this.getFriends()){
			if (f.getId() == u.getId()){
				System.out.println("Error - duplicate friend");
				duplicateFriend = true;
			}
		}
		if (this.getId() == u.getId()){
			System.out.println("Error - cannot add self as a friend");
			duplicateFriend = true;
		}
		if (!duplicateFriend){
			System.out.println("\nYou have added " + u.getRealname() + " ("+ u.getId() +") to your friends.");
			friends.add(u);
		}
	}
	public void clearFriends(){
		this.friends.clear();
	}

	public String getUsername(){
		return username;
	}
	public String getRealname(){
		return realname;
	}
	public String getPassword(){
		return password;
	}
	public String getSchool(){
		return school;
	}
	public String getQuote(){
		return quote;
	}
	public int getId(){
		return id;
	}
	public ArrayList<User> getFriends(){
		return friends;
	}

	@Override
	public String toString(){
                String fmt = String.format( "ID: %-8d Username: %s Name: %s %nSchool: %s %nQuote: %s%n", id, username, realname, school, quote);
		return fmt;
	}

	// todo: write friendFIlename to dir called friends/
	// also fix the error where if you run the 'a' command more than once, it adds all friends in the friends arraylsit. 
	public File getFriendFilename() {
		File f = new File("friends", this.getUsername().split(" ")[0] + this.getId() +  ".txt");
		return f;
	}

	public static void writeAllUsersToFile(File f, ArrayList<User> users) throws IOException {
		FileWriter fw = new FileWriter(f, false);
		PrintWriter pw = new PrintWriter(fw);
		for (User u : users){
			pw.println(u.id + ", " + u.username + ", " + u.realname + ", " + u.password + ", " + u.school + ", \"" + u.quote + "\"");
		}
		pw.close();
	}

	/*TODO: donee.
		When writing a friend to a userFriend file, store only the userID.
		When reading the friendlist from a userFriend file, match the userID with an id in userDb.txt.
	*/
	public String getFriendlist(){
		String friendUsernames = "";
		for (User f : friends) {
			friendUsernames += f.getId() + "\n";
		}
		return friendUsernames.trim();
	}

	public static void writeUserFriendsToFile(User u, boolean updateFile) throws IOException {
		File f = u.getFriendFilename();
		boolean existingFile = false;
		if (f.exists()){
			existingFile = true;
		}
		FileWriter fw = new FileWriter(f, updateFile);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(u.getFriendlist());
		pw.close();
		if (existingFile) {
			System.out.println("File updated: " + f);
		}
		else {
			System.out.println("File created: " + f);
		}
	}

	public ArrayList<Integer> readFriendIdsFromFile(){
		File f = this.getFriendFilename();
		ArrayList<Integer> friendIds = new ArrayList<Integer>();
		try {
	 		Scanner scan = new Scanner(f);
			if (f.exists()){
				while(scan.hasNext()){
					String scanLine = scan.nextLine();
					friendIds.add(Integer.parseInt(scanLine));
				}
			}
			scan.close();
		}
		catch(Exception e){
			System.out.println("File doesn't exist" + e);
		}
		return friendIds;
	}

	public String readFriendsFromFile() {
		File userDb = new File("userDb.txt");
		String friendlist = "";
		ArrayList<Integer> friendIds = this.readFriendIdsFromFile();

		try {
			Scanner userScan = new Scanner(userDb);
			while (userScan.hasNext()){
				String user = userScan.nextLine();
				int userId = Integer.parseInt(user.split(",")[0]);
				String username = user.split(",")[1].trim();
				String realname = user.split(",")[2].trim();
				String pw = user.split(",")[3].trim();
				String school = user.split(",")[4].trim();
				String quote = "";

				if (user.split(",").length > 5){
					for (int i = 5; i < user.split(",").length; i++){
						quote += user.split(",")[i];
					}
					quote.trim();
				} else {
					quote = user.split(",")[5].trim();
				}

				String friendEntry = String.format("%s (%s, %d) from %s %n\t%s %n", realname, username, userId, school, quote);
				// the order needs  to be non-ascending. aka
				// match by user ID to friend ids.
				for (int i : friendIds){
					if (userId == i){
						friendlist += friendEntry;
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("File doesn't exist" + e);
		}

		return friendlist;
	}

	public static void writeNewUserToFile(File f, User u) throws IOException {
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(u.id + ", " + u.username + ", " + u.realname + ", " + u.password + ", " + u.school + ", \"" + u.quote + "\"");
		pw.close();
		System.out.println("Userlist file updated: " + f);
	}

	public static void getAllUsernames(ArrayList<User> users){
		//TODO: Make instance method and remove current user from list.
		for (int i=0; i < users.size(); i++){
			System.out.printf("%-20s", users.get(i).getUsername());
			if ((i+1)%2==0) System.out.println("");
		}
	}

	/*
	TODO: Add method to edit quote / profile info for current user.
	*/

	/*
	TODO: Delete friends
	*/
	public void deleteFriendFromFile() throws IOException {
		File f = this.getFriendFilename();
		boolean removeFriendIdFound = false;

		System.out.println("YOUR FRIENDS LIST");
		System.out.println(this.readFriendsFromFile());
		ArrayList<Integer> friendIds = this.readFriendIdsFromFile();

		Scanner kbd = new Scanner(System.in);
		System.out.println("Type the ID of the friend you want to delete:");
		int removeFriendId = kbd.nextInt();

		for (int i : friendIds){
			if (i == removeFriendId) {
				System.out.println("Removing: " + i);
				removeFriendIdFound = true;
			}
		}
		if (removeFriendIdFound){
			FileWriter fw = new FileWriter(f, false);
			PrintWriter pw = new PrintWriter(fw);
			friendIds.remove(friendIds.indexOf(removeFriendId));
			for (int k : friendIds){
				pw.println(k);
			}
			pw.close();
		}
		else {
			System.out.println("Inavlid friend ID: " + removeFriendId);
		}
	}
	/* Add method to send message to a friend*/
	public void messageFriend(User u){
		ArrayList<Integer> friendIds = this.readFriendIdsFromFile();
		
		boolean existingFriend = false;
		for (int id : friendIds){
			if (u.getId() == id){
				existingFriend=true;
			}
		}
		if (existingFriend){
			System.out.println("SEnding message to " + u.getUsername());
		} else {
			System.out.println("You are not friends with " + u.getUsername() + ". would you like to add them");
		}
	}


}
