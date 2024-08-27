public class Main {
    public static void main(String[] args) {
        Player playerA = new Player("A", 50, 5, 10);
        Player playerB = new Player("B", 100, 10, 5);
        Game.play(playerA, playerB);
    }
}