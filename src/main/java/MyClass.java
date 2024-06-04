
import java.util.stream.DoubleStream;

public class MyClass {
    public  boolean search(int[] a, int b) {

        for (int i = 0; i < a.length; i++) {
            if (a[i] == b) {
                return true;
            }
        }
        return false;
    }
}
