package cinema.entities;

public class Statistics {
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    private Cinema cinema;

    public Statistics(Cinema cinema) {
        this.cinema = cinema;
        this.currentIncome = 0;
        this.numberOfAvailableSeats = cinema.getTotalRows() * cinema.getTotalColumns();
        this.numberOfPurchasedTickets = cinema.getPurchasedTickets().size();
    }

    public Statistics(int currentIncome, int numberOfAvailableSeats, int numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "currentIncome=" + currentIncome +
                ", numberOfAvailableSeats=" + numberOfAvailableSeats +
                ", numberOfPurchasedTickets=" + numberOfPurchasedTickets +
                '}';
    }
}
