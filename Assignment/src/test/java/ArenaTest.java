import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.util.Map;
import static org.junit.Assert.*;
public class ArenaTest {
    private Arena arena;

    @Before  // This annotation is critical
    public void setUp() {
        arena = new Arena();
    }

    @Test
    public void testPlayerWithInvalidHealth() {
        assertEquals(-1, arena.addPlayer("Player", -1, 100, 100));
        assertEquals(-1, arena.addPlayer("Player", 0, 100, 100));
    }

    @Test
    public void testPlayerWithInvalidStrength() {
        assertEquals(-1, arena.addPlayer("Player", 100, -1, 100));
        assertEquals(-1, arena.addPlayer("Player", 100, 0, 100));
    }

    @Test
    public void testPlayerWithInvalidAttack() {
        assertEquals(-1, arena.addPlayer("Player", 100, 100, -1));
        assertEquals(-1, arena.addPlayer("Player", 100, 100, 0));
    }

    @Test
    public void testPlayerCountIncreases() {
        int initialCount = arena.getPlayerCount();
        arena.addPlayer("Player", 100, 100, 100);
        assertEquals(initialCount + 1, arena.getPlayerCount());
    }

    @Test
    public void testDeletePlayer() {
        int id = arena.addPlayer("Player", 100, 100, 100);
        assertTrue(arena.deletePlayer(id));
        assertFalse(arena.isPresent(id));
    }

    @Test
    public void testDeleteNonExistentPlayer() {
        int initialCount = arena.getPlayerCount();
        assertFalse(arena.deletePlayer(999)); // assuming 999 is an ID that doesn't exist
        assertEquals(initialCount, arena.getPlayerCount());
    }

    @Test
    public void testBattleWithNonExistentPlayers() {
        assertTrue(arena.battle(0, 1).isEmpty());
    }

    @Test
    public void testBattleBetweenPlayers() {
        try (MockedStatic<Dice> mockedDice = Mockito.mockStatic(Dice.class)) {
            // Configure the static mock for Dice.roll()
            mockedDice.when(Dice::roll).thenReturn(3, 1); // Set specific returns for dice rolls during the test

            int id1 = arena.addPlayer("Player A", 100, 100, 50);
            int id2 = arena.addPlayer("Player B", 100, 50, 100);
            Map<String, Integer> result = arena.battle(id1, id2);

            assertNotNull("Result should not be null", result);
            assertTrue("Result should contain a winner key", result.containsKey("winner"));
            assertTrue("Result should contain a loser key", result.containsKey("loser"));
            assertEquals("Player A should be the winner because Player B's health reached 0", Integer.valueOf(id1), result.get("winner"));
        }
}
}
