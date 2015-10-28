/*Brett Lockhart
 *May 6, 2015
 *Purpose: class contains information about the race, as well as
 *			the run method to display thread info as the race progresses
 *Inputs: Racer thread
 *Outputs: name, lap, current position, and placement of the racer */
public class Racers implements Runnable{
	private int speed, restFactor, distance, elapsedTime, laps;
	private long startTime;
	private final int RACE_LENGTH = 1600, LAP_DIST = 400;
	static int place = 1;	//static variable to keep track of race results
	
	//initialize racers w/ a start speed and rest factor
	public Racers(int iSpeed, int iRest) {
		speed = iSpeed;
		restFactor = iRest;
	}
	
	//executes when the thread starts. simulates running a race
	public void run() {
		try {
			startTime = System.currentTimeMillis() / 1000;
			
			while (laps < (RACE_LENGTH / LAP_DIST + 1) && !Thread.interrupted()) {
				elapsedTime = (int)(System.currentTimeMillis() / 1000 - startTime);
				distance = elapsedTime * speed;
				laps = distance / LAP_DIST + 1; 
				System.out.println(Thread.currentThread().getName() + ":\tLap# " + laps +
				 "\tCurrent Distance = " + distance + "m.");
				Thread.sleep(restFactor);
			}
		} 
		catch (InterruptedException e) {
			System.out.println("Interrupted.");
		}	
		//displays when racer finishes 
		Thread.currentThread().setPriority(place);
		System.out.println(Thread.currentThread().getName() + " FINISHED #"
		 + Thread.currentThread().getPriority());
		System.out.println(Thread.currentThread().getName() + " finished in " + elapsedTime + " seconds.");
		++place;
	}
}
/* When the rest factor is kept constant, and the speed is varied, the racers 
 * finished according to their speed. The racer with the highest speed finished
 * first, and the one with the lowest speed finished last.
 * 
 * When the speed was held constant, and the rest factor varied, the racers
 * with lower rest factors finished the race quicker than those with a high
 * rest factor. When the rest factor is increased, there is a longer delay 
 * between each time the racer displays its results.	*/
