package advjava02;


public class AdvJava02 {

    public static void main(String[] args) {
        new BBB(); // TellOMatic
        
        //InnerClassInstantiationDemo();

 
    }


    private static void InnerClassInstantiationDemo() {
        HHH h1 = new HHH();
        HHH h2 = new HHH();
        h2.a = 10;
        HHH.JJJ j = h1.new JJJ();
        System.out.println("First result: " + j.b);
        j = h2.new JJJ();
        System.out.println("Second result: " + j.b);
    }

}

class HHH {
    int a = 9;
    class JJJ {
        int b = a + 8;
    }
}
