/*Brett Lockhart
 *May 6, 2015
 *Purpose: create racers and simulate a race using threads
 *Inputs: number of racers, and name, speed, rest factor for each racer
 *Outputs: results of race */
import java.util.ArrayList;
import java.util.Scanner;

public class RaceTester {
	public static void main(String[] args) {
		//declarations
		Scanner input = new Scanner(System.in);
		int numRacers = 0, speed, restFactor;
		String name;
		ArrayList<Thread> threadList = new ArrayList<Thread>();
		ThreadGroup threads = new ThreadGroup("Racers");
		
		System.out.println("WELCOME TO THE RACE!");
		System.out.println("The race consists of 4 laps, each of length 400m.");
		
		//determine number of racers
		System.out.println("How many racers would you like? ");
		numRacers = input.nextInt();
		input.nextLine();
		
		//get Info about each racer
		System.out.println("Each racer requires a name, speed, and rest factor.");		
		for (int i = 1; i <= numRacers; i++) {
			System.out.println("Enter info for racer #" + i);
			
			System.out.print("Name: ");
			name = input.next();
			System.out.print("Speed: ");
			speed = input.nextInt();
			System.out.print("Rest Factor: ");
			restFactor = input.nextInt();
			//create runnable object with speed and restFactor
			Runnable r = new Racers(speed, restFactor);
			//create thread, initialized into a threadGroup, and given the name
			Thread t = new Thread(threads, r, name);
			threadList.add(t);
		}
		//start the race!
		for (Thread t: threadList)
			t.start();
		
		//while racers are still active, wait.
		while (threads.activeCount() != 0) {}
		
		//show the end results
		System.out.println("\n\nRace Results: ");
		for (Thread t: threadList)
			System.out.println(t.getName() + " finished #" + t.getPriority() + ".");		
		input.close();
	}
}
