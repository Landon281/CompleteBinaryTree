//Tester Class
import java.util.Random;

public class Tester {
    public static void main(String[] args){

        CompleteBinaryTree<Integer> t2 = new CompleteBinaryTree<Integer>(0);
        Random r = new Random();


        for(int i = 1; i <= 100; i ++){
            double random = Math.random() * 49 + 1;
            t2.add((int)random);
        }//end for


        System.out.println("My Tree:");
        //test methods for CBT objects
        System.out.println(t2.toString());
        System.out.println(t2.size());
        System.out.println(t2.contains(100));

    }

}
