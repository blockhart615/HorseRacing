import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class RaceTester {

    public static void main(String[] args) {
        //declarations
        Scanner input = new Scanner(System.in);
        int numRacers = 6, speed, restFactor;
        Random rand = new Random();
        String name;
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        ThreadGroup threads = new ThreadGroup("Racers");

        System.out.println("WELCOME TO THE RACE!");
        System.out.println("The race consists of 4 laps, each of length 400m.");

        //get Info about each racer
        System.out.println("Each racer requires a name, speed, and rest factor.");
        for (int i = 1; i <= numRacers; i++) {
            
            //randomly set speed for each horse and set rest factor
            speed = rand.nextInt(100) + 50;
            restFactor = 200;

            //create runnable object with speed and restFactor
            Runnable r = new Racers(speed, restFactor);

            //create thread, initialized into a threadGroup, and given the name
            Thread t = new Thread(threads, r, "Horse " + i);
            threadList.add(t);
        }
        //start the race!
        for (Thread t : threadList) {
            t.start();
        }

        //while racers are still active, wait.
        while (threads.activeCount() != 0) {
        }

        //show the end results
        System.out.println("\n\nRace Results: ");
        for (Thread t : threadList) {
            System.out.println(t.getName() + " finished #" + t.getPriority() + ".");
        }
        input.close();
    }
}
