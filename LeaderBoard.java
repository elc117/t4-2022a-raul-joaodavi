import java.time.LocalTime;
import java.util.Comparator;
import java.util.TreeSet;

public class LeaderBoard
{
    private Set<Play> board;

    public LeaderBoard(Boolean compareByScore)
    {
        board = new TreeSet<>(new Comparator<T>(){
            @Override
            public int compare(Play p1, Play p2)
            {
                if(compareByScore)
                {
                    return p1.getScore().compareTo(p2.getScore());
                }
                else {
                    return p1.getTime().compareTo(p2.getTime());
                }
            }
        });
    }
    

    public void addPlay(Play play)
    {
        board.add(play);
        

    }

    public void printList()
    {
        this.board.foreach(b -> System.out.println(b));
    }

}

public class Play implements Comparable
{
    private User player;
    private LocalTime playTime;
    private Double time;
    private Double score;

    public Play(User player, LocalTime playTime)
    {
        this.player = player;
        this.playTime = playTime;
    }

    public Double getTime() {
        return time;
    }
    public void setTime(Double time) {
        this.time = time;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return this.player.getName();
    }

}

public class User
{
    private String name;
    private Integer id;

    public User(String name, Integer id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
   
    public Integer getId() {
        return id;
    }
}

public class Teste
{
    public static void main(String[] args) {
        LeaderBoard a = new LeaderBoard(true);
        User u1 = new User("Joao", 1);
        User u2 = new User("Carlin", 2);
        Play p1 = new Play(u1, 0.5);
        Play p2 = new Play(u2, 3.0);
        p1.setScore(5);
        p2.setScore(2);
        a.addPlay(p1);
        a.addPlay(p2);
        a.printList();
    }
}