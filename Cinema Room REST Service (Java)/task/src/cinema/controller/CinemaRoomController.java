package cinema.controller;

import cinema.entities.Cinema;
import cinema.entities.Seat;
import cinema.entities.Statistics;
import cinema.entities.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaRoomController {

    private static final Cinema cinema = new Cinema(9, 9);
    List<Token> purchasedTickets = getCinema().getPurchasedTickets();
    List<Seat> availableSeats = getCinema().getAvailableSeats();
    private final Statistics statistics = new Statistics(cinema);
    int numberOfAvailableSeats = statistics.getNumberOfAvailableSeats();
    int currentIncome = statistics.getCurrentIncome();
    int numberOfPurchasedTickets = statistics.getNumberOfPurchasedTickets();

    @GetMapping("/seats")
    public Cinema getCinema() {
        // return new Cinema(9, 9);
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        getCinema();
        if (seat.getRow() > 9 || seat.getColumn() > 9
                || seat.getRow() < 1 || seat.getColumn() < 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "The number of a row or a column is out of bounds!"));
        }
        for (Seat seat1 : availableSeats) {
            if (seat1.getRow() == seat.getRow() && seat1.getColumn() == seat.getColumn()) {
                availableSeats.remove(seat1);
                getCinema().setAvailableSeats(availableSeats);
                String token = UUID.randomUUID().toString();
                Token result = new Token(token, seat1);
                purchasedTickets.add(result);
                numberOfPurchasedTickets++;
                numberOfAvailableSeats--;
                currentIncome += seat1.getPrice();
                return ResponseEntity.of(Optional.of(result));
            }
        }
        System.out.println(availableSeats.size());
        return ResponseEntity.badRequest().body(Map.of("error", "The ticket has been already purchased!"));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnedTicket(@RequestBody Token token) {
        for (Token token1 : purchasedTickets) {
            if (token.getToken().equals(token1.getToken())) {
                Seat ticket1 = token1.getTicket();
                numberOfPurchasedTickets--;
                numberOfAvailableSeats++;
                currentIncome -= ticket1.getPrice();
                availableSeats.remove(ticket1);
                return ResponseEntity.ok(Map.of("returned_ticket", token1.getTicket()));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong token!"));
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(name = "password", required = false) String password) {
        Statistics statistics1 = new Statistics(currentIncome, numberOfAvailableSeats, numberOfPurchasedTickets);
        if (password == null || !password.equals("super_secret")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "The password is wrong!"));
        }
        return ResponseEntity.of(Optional.of(statistics1));
    }

}
