
public class Gambler {

    double cash, betAmount;
    String horseBet;
    String name = "";
    BankAccount account = new BankAccount();
    
    /**
     * Default Constructor for a gambler
     */
    public Gambler() {
        cash = 100;
        betAmount = 0;
        horseBet = "Horse 0";
        name = "Guest";
    }

    /**
     * Get player's current cash
     * @return cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * get the player's bet amount
     * @return betAmount
     */
    public double getBetAmount() {
        return betAmount;
    }

    /**
     * SEt the Bet Amount
     * @param amount 
     */
    public void setBetAmount(double amount) {
        betAmount = amount;
    }

    /**
     * get the horse the player bet on
     * @return horse's name
     */
    public String getHorseNumber() {
        return horseBet;
    }

    /**
     * Set the horse the player wants to bet on
     * @param horse 
     */
    public void setHorseNumber(String horse) {
        horseBet = horse;
    }
    
    /**
     * Calculate the earnings after the race has finished
     * @param standing - list of horses and the order they placed
     */
    public void calculateReward(String[] standing){
	double earnings = 0;
	if (standing[0].equals(horseBet))
	    earnings = betAmount * 3;
	else if (standing[1].equals(horseBet))
	    earnings = betAmount * 2;
	else if (standing[2].equals(horseBet))
	    earnings = betAmount * 1.5;
	else 
	    earnings = betAmount * -1;
	cash = cash + earnings;
    }

}
