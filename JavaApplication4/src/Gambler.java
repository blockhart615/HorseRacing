
public class Gambler {

    double cash, betAmount;
    String horseBet;
    String name = "";
    BankAccount account = new BankAccount();

    public Gambler() {
        cash = 100;
        betAmount = 0;
        horseBet = "Horse 0";
        name = "Guest";
    }

    //get cash
    public double getCash() {
        return cash;
    }

    //Get Bet amount
    public double getBetAmount() {
        return betAmount;
    }

    //set Bet amount

    public void setBetAmount(double amount) {
        if (amount > cash)
        betAmount = amount;
    }

    //get horse#
    public String getHorseNumber() {
        return horseBet;
    }

    //set horse##
    public void setHorseNumber(String horse) {
        horseBet = horse;
    }

    //Bet Multiplier
    public void betMultiplier(int place) {
        //if (horseBet)

    }

}
