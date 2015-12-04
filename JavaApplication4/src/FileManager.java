
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;



public class FileManager {

    private static Scanner mScanner;
    private static File file = null;
    

    public FileManager(String fileName) throws IOException {
	mScanner = new Scanner(new FileReader(fileName));
    }
    
    
    public static void createFile(String fileName) {
    file = new File(fileName);
    }
    
    /**
     * 
     * @return 
     */
    public static Horse nextHorse() {
	if (mScanner.hasNext()) {
	    return parseHorse(mScanner.nextLine());
	}
	return null;
    }
    
    

    /**
     * 
     * @param line
     * @return 
     */
    private static Horse parseHorse(String line) {
	String[] split = line.split(",");
	String horseName = split[0];
	int wins = Integer.parseInt(split[1]);
	int losses = Integer.parseInt(split[2]);
	int totalRaces = Integer.parseInt(split[3]);
	double average = Double.parseDouble(split[4]);
	return new Horse(horseName, wins, losses, totalRaces,
		average);
    }

    /**
     * 
     * @return 
     */
    public static boolean fileExists() {
	return file != null;
    }
  
    
}
