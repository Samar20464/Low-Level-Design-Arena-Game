import java.util.HashMap;
import java.util.Map;

public class Arena {
    private Map<Integer, Player> players = new HashMap<>();
    private int playerCount = 0;
    private Dice dice;

    public Arena() {
    }

    public int addPlayer(String name, int health, int strength, int attack) {
        if (health <= 0 || strength <= 0 || attack <= 0) {
            return -1;
        }
        int id = playerCount++;
        players.put(id, new Player(name, health, strength, attack));
        return id;
    }

    public boolean deletePlayer(int id) {
        if (players.containsKey(id)) {
            players.remove(id);
            return true;
        }
        return false;
    }

    public boolean isPresent(int id) {
        return players.containsKey(id);
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Map<String, Integer> battle(int id1, int id2) {
        if (!players.containsKey(id1) || !players.containsKey(id2) || id1 == id2) {
            return new HashMap<>();
        }

        Player player1 = players.get(id1);
        Player player2 = players.get(id2);
        Player attacker = player1.getHealth() <= player2.getHealth() ? player1 : player2;
        Player defender = player1 == attacker ? player2 : player1;

        while (player1.isAlive() && player2.isAlive()) {
            int attackRoll = dice.roll();
            int defenceRoll = dice.roll();
            int attack = attacker.getAttack() * attackRoll;
            int defence = defender.getStrength() * defenceRoll;
            int damage = attack - defence;

            if (damage > 0) {
                defender.setHealth(defender.getHealth() - damage);
            }


            System.out.printf(
                    "%s attacks with a roll of %d. %s defends with a roll of %d.\n",
                    attacker.getName(), attackRoll, defender.getName(), defenceRoll);
            System.out.printf(
                    "Attack damage: %d, Defense: %d. %s's health reduced by %d (current health: %d).\n",
                    attack, defence, defender.getName(), Math.max(0, damage), defender.getHealth());


            Player temp = attacker;
            attacker = defender;
            defender = temp;
        }


        Player winner = player1.isAlive() ? player1 : player2;
        Player loser = player1.isAlive() ? player2 : player1;


        deletePlayer(loser == player1 ? id1 : id2);


        Map<String, Integer> result = new HashMap<>();
        result.put("winner", winner == player1 ? id1 : id2);
        result.put("loser", loser == player1 ? id1 : id2);
        return result;
    }
}
