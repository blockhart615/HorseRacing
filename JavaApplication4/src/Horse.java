/*Brett Lockhart, Emil Evangelista, Dennis Lau
 *Fall 2015
 */

import java.util.concurrent.locks.ReentrantLock;

public class Horse implements Runnable {

    private static ReentrantLock raceLock;
    private int speed, restFactor, distance,
	    elapsedTime, laps, wins, losses, totalRaces;
    private double average = 0;
    private long startTime;
    private final int RACE_LENGTH = 1000, LAP_DIST = 400;
    private static int place = 1;	//static variable to keep track of race results

    //initialize racers w/ a start speed and rest factor
    public Horse(int iSpeed, int iRest) {
	speed = iSpeed;
	restFactor = iRest;
	wins = 0;
	losses = 0;
    }

    public Horse(String horseName, int wins, int losses, int totalRaces,
	    double average) {
	Thread.currentThread().setName(horseName);
	this.wins = wins;
	this.losses = losses;
	this.totalRaces = totalRaces;
	this.average = average;
    }

    //executes when the thread starts. simulates running a race
    public void run() {
	try {
	    //NEED TO IMPLEMENT LOCKS ON THE THREADS SO THAT THE HORSES DON'T TIE
	    raceLock = new ReentrantLock();
	    startTime = System.currentTimeMillis() / 1000;
	    int count = 0;
	    while (laps < (RACE_LENGTH / LAP_DIST + 1) && !Thread.interrupted()) {
		elapsedTime = (int) (System.currentTimeMillis() / 1000 - startTime);
		distance = elapsedTime * speed;
		laps = distance / LAP_DIST + 1;

		if (count % 8 == 0) {
		    System.out.println(Thread.currentThread().getName() + ":\tLap# " + laps
			    + "\tCurrent Distance = " + distance + "m.");
		}

		Thread.sleep(restFactor);
		count++;
	    }
	} catch (InterruptedException e) {
	    System.out.println("Interrupted.");
	}

	//When Racer Finishes
	//set the racers place and display to the console
	raceLock.lock();
	Thread.currentThread().setPriority(place);

	System.out.println(Thread.currentThread().getName() + " FINISHED #"
		+ Thread.currentThread().getPriority());
	System.out.println(Thread.currentThread().getName() + " finished in " + elapsedTime + " seconds.");
	++place;
	raceLock.unlock();
    }

    public String getName() {
	return Thread.currentThread().getName();
    }

    public String getWins() {
	return Integer.toString(wins);
    }

    public String getLoss() {
	return Integer.toString(losses);
    }

    public String getTotalRaces() {
	return Integer.toString(wins + losses);
    }

    public double getAverage() {
	return wins / (wins + losses);
    }

    public String toString() {
	return getName() + getWins() + getLoss()
		+ getTotalRaces() + String.format("%.3f", getAverage());
    }

}
