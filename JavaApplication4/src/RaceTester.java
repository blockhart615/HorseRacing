
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class RaceTester {

    public static void main(String[] args) {
        //declarations
        Scanner input = new Scanner(System.in);
        int numRacers = 6, speed, restFactor;
        double betAmount = 0;
        Gambler player = new Gambler();
        final double MIN_BET = 5;

        Random rand = new Random();
        String name;
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        ThreadGroup threads = new ThreadGroup("Racers");

        System.out.println("WELCOME TO THE RACE!");
        System.out.println("The race consists of 4 laps, each of length 400m.");

        //get Info about each racer
        for (int i = 1; i <= numRacers; i++) {
            //randomly set speed for each horse and set rest factor
            speed = rand.nextInt(100) + 50;
            restFactor = 200;
            //create runnable object with speed and restFactor
            Runnable r = new Horse(speed, restFactor);
            //create thread, initialized into a threadGroup, and given the name
            Thread t = new Thread(threads, r, "Horse " + i);
            threadList.add(t);
        }

        //USER PLACES HIS/HER BET
        System.out.println("Which horse would you like to bet on?");
        player.setHorseNumber(input.nextLine());

        
        do {
        if (player.getCash() >= MIN_BET) {
            System.out.println("How much would you like to bet?");
            System.out.println("You have $" + player.getCash());
            betAmount = input.nextDouble();
            player.setBetAmount(betAmount);
            if (betAmount > player.getCash())
                System.out.println("You don't have enough money to place that bet."
                        + "Please place a lower bet.");
            else if (betAmount < MIN_BET)
                System.out.println("You must place a minimum bet of "
                        + "$" + MIN_BET + ". Pleace place a higher bet.");
        }
        else
            System.out.println("You don't have enough money to play. Go to the bank to get some cash");
        } while (betAmount > player.getCash() || betAmount < MIN_BET);
        
        
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

        //CALCULATE EARNINGS
        //gambler.calculateReward
    }
}
