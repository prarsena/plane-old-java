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
	//String[] classList = { "Person", "CreditCard", "WhooAreU" };
	String[] classList = { "Person", "CreditCard" };
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

	//System.out.println(c.hackMe());
	try {
		Class<?> cls = Class.forName("Person");
	 	Method methlist[] = cls.getDeclaredMethods();

		for (int i = 0; i < methlist.length; i++) {
			Method m = methlist[i];
			System.out.println("\tName: " + m.getName());
			System.out.println("\tModifier: " + m.getModifiers());

			if (m.getModifiers() == 2){
				System.out.println("\tPRIVATE METHOD");
				m.setAccessible(true);
				//System.out.println(c.hackMe());
				//Person p = new Person();
				//System.out.println(p.hackMe());
			}
		}
	} catch (Throwable e) {
            System.err.println(e);
        }
  }
}

