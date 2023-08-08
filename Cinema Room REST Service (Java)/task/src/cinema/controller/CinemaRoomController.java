package cinema.controller;

import cinema.entities.Cinema;
import cinema.entities.Seat;
import cinema.entities.Token;
import cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRoomController {

    private final CinemaServices cinemaServices;

    @Autowired
    public CinemaRoomController(CinemaServices cinemaServices) {
        this.cinemaServices = cinemaServices;
    }

    private static final Cinema cinema = new Cinema(9, 9);


    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        return cinemaServices.purchaseSeat(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnedTicket(@RequestBody Token token) {
        return cinemaServices.returnedTicket(token);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(name = "password", required = false) String password) {
        return cinemaServices.getStats(password);
    }

}
