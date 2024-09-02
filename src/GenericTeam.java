import java.util.ArrayList;
import java.util.List;

public class GenericTeam<T> {

    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    public GenericTeam(String teamName)
    {
        this.teamName = teamName;
    }

    public void addTeamMember(T member)
    {
        teamMembers.add(member);
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder("Team: " + teamName + "\nTeam Members: ");
        for(var member : teamMembers) {
            try {
                s.append(member.getClass().getMethod("getName").invoke(member).toString()).append(", ");
            } catch (Exception ignored)
            {

            }
        }
        return s+"\b\b";
    }
}

class Cricket
{
    String playerName;
    String team;
    int centuries;

    public Cricket(String playerName, String team, int centuries) {
        this.playerName = playerName;
        this.team = team;
        this.centuries = centuries;
    }

    public String getName() {
        return playerName;
    }
}

class Football
{
    String playerName;
    String Team;
    int goals;

    public Football(String playerName, String team, int goals) {
        this.playerName = playerName;
        Team = team;
        this.goals = goals;
    }

    public String getName() {
        return playerName;
    }
}