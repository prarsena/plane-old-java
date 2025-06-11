import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class WhooAreU {

  @SuppressWarnings("unused")
  private int f1(Object p, int x) throws NullPointerException{
	 if (p == null)
		throw new NullPointerException();
	 return x;
  }

  public static void main(String args[]){
	String[] classList = { "Person", "CreditCard", "WhooAreU" };
	Inspectigator.inspect(classList);

	System.out.printf("%n%-30s%n", "======RUNNING OBJECTS======");
	Person a = new Person();
	Person b = new Person("Tony Hawk", 55);
	Person c = new Person("Sarah Kirchmaier", 31, "Sparkase");
	Person d = new Person("Lou Reed", 69, Person.buildCard("Bank of UNmerica"));


	System.out.println(a + "\n");
	System.out.println(b + "\n");
	System.out.println(c + "\n");
	System.out.println(d + "\n");

	System.out.println("INSECURE INFO!\n");
	System.out.println("Customer: " + c.getName());
	System.out.println(c.card);

  }
}

