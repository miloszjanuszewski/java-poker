
public class Player {
    int coins;
    int pot;
    String name;
    boolean pass;
    Card first;
    Card second;

    public boolean Bet(int val) {
        if (val > coins) {
            return false;
        }
        coins -= val;
        pot += val;
        return true;
    }

    public int Win(int val) {
        coins += val;
        return coins;
    }

    public void Pass() {
        pass = true;
    }

    public int Pot() {
        return pot;
    }

    public void clearPot() {
        pot = 0;
    }

    public int Coins() {
        return coins;
    }

    public String Name() {
        return name;
    }

    public Card FCard() {
        return first;
    }

    public Card SCard() {
        return second;
    }

    public int Lose(int val) {
        coins -= val;
        return coins;
    }

    boolean InGame() {
        return coins > 0;
    }

    public int AllIn() {
        int to_return = coins;
        pot += coins;
        coins = 0;
        return to_return;
    }

    Player(String name, int coins) {
        this.name = name;
        this.coins = coins;
        this.pass = false;
        this.pot = 0;
    }
}