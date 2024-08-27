import java.util.Random;

public class Dice {
    private static final Random rand = new Random();
    public static int roll()
    {
        return 1 + rand.nextInt(6);
    }
}
