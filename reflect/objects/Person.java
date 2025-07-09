import java.util.Random;

public class Person{
	private String name;
	public int age;
	public CreditCard card;

	public Person(){
		name = "Johnny Appleseed";
		age = 99;
		card = CreditCard.createBank();
	}

	public Person(String name, int age){
		this.name = name;
		this.age = age;
		card = CreditCard.createBank();
	}

	public Person(String name, int age, String bank){
		this.name = name;
		this.age = age;
		card = Person.buildCard(bank);
	}

	public Person(String name, int age, CreditCard card){
		this.name = name;
		this.age = age;
		this.card = card;
	}

	private static String hackMe(){
		return "IT'S A ROBBERY. CALL THE POLICE FOR ME";
	}

	public String getName(){
		return name;
	}

	public static CreditCard buildCard(String bank){
		return new CreditCard(bank);
	}

	@Override
	public String toString(){
		return String.format("Name: %s%nAge: %d%nBank: %s", name, age, card.getBank());
	}

}

class CreditCard {
	private String bank;
	public int number;
	private int exp_month;
	private int exp_year;
	private int security_code;

	public CreditCard(String bank){
		this.bank = bank;
		Random randInt  = new Random();
		this.number = randInt.nextInt(11112222,99998888);
		this.exp_month = 12;
		this.exp_year = 2028;
		this.security_code = randInt.nextInt(99,999);
	}

	public static CreditCard createBank(){
		return new CreditCard("Bank of Amerika");
	}

	public String getBank(){
		return bank;
	}

	@Override
	public String toString(){
		return String.format("Bank: %s%nNumber: %d%nExp Month: %d Exp year: %d%nCode: %d", bank, number, exp_month, exp_year, security_code);
	}
}
