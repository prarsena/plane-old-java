import java.lang.reflect.*;

public class ExploreJar {
    public static void main(String args[]) {
        //String[] classList = { "Person", "org.aspectj.lang.JoinPoint", "org.aspectj.lang.Signature" };
        String[] classList = { "java.io.File", "java.lang.Process" };
	//String[] classList = { "ObjectGarden", "User" };
	Inspectigator.inspect(classList);

    }
}
