public class Sarah {
	public static void main(String[] args) {

		int a = 1;
		char grade = 'a';
		boolean accept = true;
		boolean reject = false;

		String name = "Petar", name2 = "Sarah", name3 = "Michael";
		String destination = "Yugoslavia!", destination2 = "Serbia", destination3 = "Russia";


		String[] names = {"Petar", "Dimitar", "Igor", "Mariiia", "MOlly"};
		String[] dests = {"Yugo", "Korea", "CHina", "USA", "Germany"};

		//System.out.println(names.length);
		//System.out.println(names[names.length - 1]);

		/*
		for (String n : names){
			System.out.println("Hello, " + n  + ", welcome to " + destination);
		}
		*/

		for (int i=0; i < names.length; i++){
			System.out.println("Hi " + names[i] + " welcome to " + dests[i] );
		}

		/*
		greetUser(name, destination);
		greetUser(name2, destination2);
		greetUser(name3, destination3);
		*/

	}

	public static void greetUser(String name, String dest){
		System.out.println("Hi " + name + " enjoy your vacation in " + dest );
	}
}
