import java.util.ArrayList;

public class Persoon {
    private String naam;
    private double budget;
    private ArrayList<Game> mijnGames = new ArrayList<Game>();

    public Persoon(String nm, double bud) {
        this.naam = nm;
        this.budget = bud;
    }

    public double getBudget() {
        return budget;
    }

    public boolean koop(Game g){
        for(Game og: this.mijnGames){
            if (og.getNaam().equals(g.getNaam())){
                //al in bezit
                return false;
            }
        }

        if (g.huidigeWaarde() > budget) {
            //te weinig geld
            return false;
        } else {
            this.budget -= g.huidigeWaarde();
            this.budget = Math.round(this.budget * 100);
            this.budget = this.budget/100;
            mijnGames.add(g);
            return true;
        }
    }

    public boolean verkoop(Game g, Persoon koper){
        for(Game og: this.mijnGames){
            if (og.getNaam().equals(g.getNaam())){
                //in bezit
                if(koper.mijnGames.stream().noneMatch(o -> o.getNaam().equals(og.getNaam()))){
                    if (koper.koop(g)){
                        this.mijnGames.remove(og);
                        this.budget += og.huidigeWaarde();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String gamesString = " en bezit geen games.";
        if(mijnGames.size() > 0) {
            gamesString = "";
            for(Game g: mijnGames){
                gamesString += "\n" + g.getNaam() + ", uitgegeven in " + g.getReleaseJaar()
                        + "; nieuwprijs €" + g.getNieuwprijs() + " nu voor €" + g.huidigeWaarde();
            }
        }

        return naam + " heeft een budget van €" + budget + gamesString;
    }
}
