public class Game {
    public static void play(Player player1,Player player2)
    {
        Player attacker = player1.getHealth()<=player2.getHealth()?player1:player2;
        Player defender = player1==attacker?player2:player1;

        while (player1.isAlive() && player2.isAlive())
        {
            int AttackRoll = Dice.roll();
            int DefenceRoll = Dice.roll();
            int attack = attacker.getAttack() * AttackRoll;
            int defence = defender.getStrength() * DefenceRoll;
            int damage = attack-defence;
            if(damage>0)
            {
                defender.setHealth(defender.getHealth()-damage);
            }


            System.out.printf(
                    "Player %s attacks and roll Dice roll is %d. Player %s defends and rolls die is: %d%n",
                    attacker.getName(),
                    AttackRoll,
                    defender.getName(),
                    DefenceRoll
            );

            System.out.printf(
                    "Attack damage is %d * %d = %d; Defending strength = %d * %d = %d; %s health reduced by %d and current health is %d%n",
                    AttackRoll, attacker.getAttack(), attack,
                    DefenceRoll, defender.getStrength(), defence,
                    defender == player1 ? "Player A" : "Player B",
                    damage<=0?0:damage,
                    defender.getHealth()
            );
            if(defender.getHealth()<=0) {
                System.out.println();
                System.out.println("Player " + attacker.getName() + " wins");

                break;
            }
            System.out.print("\n");

            Player temp = attacker;
            attacker = defender;
            defender = temp;
            

        }


    }
}
