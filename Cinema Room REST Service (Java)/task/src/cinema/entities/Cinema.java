package cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cinema {
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;
    @JsonIgnore
    private List<Token> purchasedTickets;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        this.purchasedTickets = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                if (i <= 4) {
                    availableSeats.add(new Seat(i,j,10));
                } else {
                    availableSeats.add(new Seat(i,j, 8));
                }

            }
        }
    }
    public Cinema() {
        availableSeats = new ArrayList<>();
        purchasedTickets = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "totalRows=" + totalRows +
                ", totalColumns=" + totalColumns +
                ", availableSeats=" + availableSeats +
                '}';
    }



    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    @JsonIgnore
    public List<Token> getPurchasedTickets() {
        return purchasedTickets;
    }
    @JsonIgnore
    public void setPurchasedTickets(List<Token> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

}
