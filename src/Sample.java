import java.util.*;

public class Sample{
    public void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args){
        Sample sample = new Sample();
        sample.print(args[0]);

    }
}
