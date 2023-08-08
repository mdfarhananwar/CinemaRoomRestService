package cinema.entities;

public class Token {
    private String token;

    private Seat ticket;

    public Token(String token, Seat seat) {
        this.token = token;
        this.ticket = seat;
    }

    public Token(String token) {
        this.token = token;
    }

    public Token() {
    }
    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat seat) {
        this.ticket = seat;
    }

    @Override
    public String toString() {
        return "TokenGenerator{" +
                "token='" + token + '\'' +
                ", seat=" + ticket +
                '}';
    }
}
