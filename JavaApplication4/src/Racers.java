/*Brett Lockhart, Emil Evangelista, Dennis Lau
 *Fall 2015
 */
import java.util.concurrent.locks.ReentrantLock;

public class Racers implements Runnable {
    static ReentrantLock raceLock;
    private int speed, restFactor, distance, elapsedTime, laps;
    private long startTime;
    private final int RACE_LENGTH = 1000, LAP_DIST = 400;
    static int place = 1;	//static variable to keep track of race results

    //initialize racers w/ a start speed and rest factor
    public Racers(int iSpeed, int iRest) {
        speed = iSpeed;
        restFactor = iRest;
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
}