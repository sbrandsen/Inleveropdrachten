import java.time.LocalDate;
import java.util.Objects;

public class Game {

    private String naam;
    private int releaseJaar;
    private double nieuwprijs;

    public int getReleaseJaar() {
        return releaseJaar;
    }

    public double getNieuwprijs() {
        return nieuwprijs;
    }

    public Game(String nm, int rj, double np) {
        this.naam = nm;
        this.releaseJaar = rj;
        this.nieuwprijs = np;
    }

    public String getNaam() {
        return naam;
    }

    public double huidigeWaarde() {
        int currentYear = LocalDate.now().getYear();
        int yearCount = currentYear - releaseJaar;

        double waarde = nieuwprijs;
        for(int i=0;i<yearCount;i++)
            waarde=((100-30)*waarde)/100;

        waarde = Math.round(waarde * 100);
        waarde = waarde/100;
        return waarde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return releaseJaar == game.releaseJaar && Double.compare(game.nieuwprijs, nieuwprijs) == 0 && Objects.equals(naam, game.naam);
    }

    @Override
    public String toString() {
        return "Game{" +
                "naam='" + naam + '\'' +
                ", releaseJaar=" + releaseJaar +
                ", nieuwprijs=" + nieuwprijs +
                '}';
    }
}
