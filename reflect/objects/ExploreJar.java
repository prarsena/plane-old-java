import java.lang.reflect.*;

public class ExploreJar {
    public static void main(String args[]) {
        String[] classList = { "Person", "org.aspectj.lang.JoinPoint", "org.aspectj.lang.Signature" };
        Inspectigator.inspect(classList);

    }
}