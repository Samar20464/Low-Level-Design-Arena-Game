public class Player {
    private int Health;
    private final int Strength;
    private final int Attack;
    private final String name;
    Player(String name,int Health, int Strength, int Attack)
    {
        this.name = name;
        this.Health = Health;
        this.Strength = Strength;
        this.Attack = Attack;
    }
    public String getName() {
        return name;
    }
    public int getHealth()
    {
        return Health;
    }

    public void setHealth(int Health)
    {
        this.Health = Health;
    }

    public int getStrength( )
    {
        return Strength;
    }

    public int getAttack()
    {
        return Attack;
    }

     public boolean isAlive()
     {
         return this.Health>0;
     }

}
