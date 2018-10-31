
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

public class RaceTester {
    private static final Random rand = new Random();
    
    public static void main(String[] args) {
	//declarations
	Scanner input = new Scanner(System.in);
	int numRacers = 6, speed, restFactor;
	double betAmount = 0;
	Gambler player = new Gambler();
	final double MIN_BET = 5;	
	Runnable r;
	String name;
	ArrayList<Thread> threadList = new ArrayList<Thread>();
	ThreadGroup threads = new ThreadGroup("Racers");
	String[] horseNames = {"Ralph", "Lawrence", "Manny", "Sebastian", "Abaccus", "Kasmir"};

	System.out.println("WELCOME TO THE RACE!");
	System.out.println("The race consists of 4 laps, each of length 400m.");

	//get Info about each racer
	for (int i = 0; i < numRacers; i++) {
	    //randomly set speed for each horse and set rest factor
	    speed = rand.nextInt(100) + 50;
	    restFactor = 200;

	    try {
		FileManager fManager = new FileManager("HorseStats.csv");
	    } catch (IOException e) {
		System.out.println(e.toString());
	    }

	    //create runnable object with speed and restFactor
	    if (FileManager.fileExists()) {
		r = FileManager.nextHorse();
	    } else {
		r = new Horse(speed, restFactor);
	    }

	    //create thread, initialized into a threadGroup, and given the name
	    Thread t = new Thread(threads, r, horseNames[i]);
	    threadList.add(t);
	}
	if (!FileManager.fileExists()) {
	    FileManager.createFile("HorseStats.csv");
	}

	//Display betting options
	System.out.println("Which horse would you like to bet on?"
		+ "\n   Your Choices are: ");
	for (int i = 0; i < horseNames.length; i++) {
	    System.out.println("      " + i + ") " + horseNames[i]);
	}
	player.setHorseNumber(input.nextLine());

//	Lets player place a bet
	do {
	    if (player.getCash() >= MIN_BET) {
		System.out.println("How much would you like to bet?");
		System.out.println("You have $" + player.getCash());
		betAmount = input.nextDouble();
		player.setBetAmount(betAmount);
		if (betAmount > player.getCash()) {
		    System.out.println("You don't have enough money to place that bet."
			    + "Please place a lower bet.");
		} else if (betAmount < MIN_BET) {
		    System.out.println("You must place a minimum bet of "
			    + "$" + MIN_BET + ". Pleace place a higher bet.");
		}
	    } else {
		System.out.println("You don't have enough money to play. Go to the bank to get some cash");
	    }
	} while (betAmount > player.getCash() || betAmount < MIN_BET);

	//start the race!
	for (Thread t : threadList) {
	    t.start();
	}

	//while racers are still active, wait.
	while (threads.activeCount() != 0) {
	}

	//show the end results
	System.out.println("\nRace Results: ");
	String[] standing = new String[numRacers];
	for (Thread t : threadList) {
	    standing[t.getPriority() - 1] = t.getName();
	}

	for (int i = 0; i < numRacers; i++) {
	    System.out.println((i + 1) + " place: \t" + standing[i]);
	}

	//calculate the earnings and display them
	player.calculateReward(standing);
	System.out.println("You now have $" + player.getCash() + ".");

	input.close();

    }
}
