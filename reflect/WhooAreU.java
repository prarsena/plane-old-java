import java.lang.reflect.*;

public class WhooAreU {

  private int f1(Object p, int x) throws NullPointerException{
	 if (p == null)
		throw new NullPointerException();
	 return x;
  }

  public static void main(String args[]){
	System.out.printf("%-30s%n", "======REFLECT======");
	try {
		String[] classList = {"Person", "CreditCard"};
		for (int count=0; count<classList.length; count++){
		   	Class cls = Class.forName(classList[count]);
			Method methlist[] = cls.getDeclaredMethods();
			Field fields[] = cls.getFields();

			//todo: find all classes using cls.getDeclaredClassses()?
			System.out.println("\nCLASS " + (count+1) + ": "  + cls.getName().toUpperCase());
			System.out.println("\nFIELDS:");
			for (Field f : fields){
				System.out.println("\t"+f);
			}

			Constructor[] constr = cls.getDeclaredConstructors();
			System.out.println("\nCONSTRUCTORS:");
			for (Constructor c : constr){
				System.out.println("\t"+c);
			}

			System.out.println("\nMETHODS:");
			for (int i = 0; i < methlist.length; i++) {
			   Method m = methlist[i];
			   System.out.println("\tName: " + m.getName());
			   //System.out.println("Declaring class: " + m.getDeclaringClass());
			   Class pvec[] = m.getParameterTypes();
			   for (int j = 0; j < pvec.length; j++)
				  System.out.println("\tParameter #" + j + ": " + pvec[j]);

			   Class evec[] = m.getExceptionTypes();
			   for (int j = 0; j < evec.length; j++)
				   System.out.println("\tException #" + j + ": " + evec[j]);
				   System.out.println("\tReturn type: " + m.getReturnType());
				   System.out.println();
				}
			}
		}
		 catch (Throwable e) {
			System.err.println(e);
		 }


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

