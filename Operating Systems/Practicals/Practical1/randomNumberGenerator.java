package Practical1;

import java.util.Random;
public class randomNumberGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
      Random rand = new Random();
      int max = 5000;
      int n = rand.nextInt(max);
      
      System.out.println("Random Number: " + n);
      Thread.sleep(n);
      System.out.println("Some other message ");
        
       
    }
}
