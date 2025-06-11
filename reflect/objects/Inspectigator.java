import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Inspectigator {

    public static void inspect(String[] classList) {
        System.out.printf("%-30s%n", "======REFLECT======");
        try {
            for (int count = 0; count < classList.length; count++) {
                Class<?> cls = Class.forName(classList[count]);
                Method methlist[] = cls.getDeclaredMethods();
                Field fields[] = cls.getFields();

                // todo: find all classes using cls.getDeclaredClassses()?
                System.out.println("\nCLASS " + (count + 1) + ": " + cls.getName().toUpperCase());
                System.out.println("\nFIELDS:");
                for (Field f : fields) {
                    System.out.println("\t" + f);
                }

                Constructor<?>[] constr = cls.getDeclaredConstructors();
                System.out.println("\nCONSTRUCTORS:");
                for (Constructor c : constr) {
                    System.out.println("\t" + c);
                }

                System.out.println("\nMETHODS:");
                for (int i = 0; i < methlist.length; i++) {
                    Method m = methlist[i];
                    System.out.println("\tName: " + m.getName());
                    System.out.println("\tModifier: " + m.getModifiers());
                    Annotation[] annotations = m.getAnnotations();
                    for (int q = 0; q < annotations.length; q++) {
                        System.out.println("\tAnnotation #" + q + ": " + annotations[q]);
                    }
                    if (m.getAnnotations().length > 0) {
                        System.out.println("Annotations: " + m.getAnnotations()[0]);
                    }

                    Class<?> pvec[] = m.getParameterTypes();
                    for (int j = 0; j < pvec.length; j++) {
                        System.out.println("\tParameter #" + j + ": " + pvec[j]);
                    }

                    Class<?> evec[] = m.getExceptionTypes();
                    for (int j = 0; j < evec.length; j++)
                        System.out.println("\tException #" + j + ": " + evec[j]);
                    System.out.println("\tReturn type: " + m.getReturnType());
                    System.out.println();
                }
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
