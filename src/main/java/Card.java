
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Card {
    Suit suit;
    Name name;

    private Card(Suit suit, Name name) {
        this.suit = suit;
        this.name = name;
    }

    static public ArrayList<Card> allCards = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Name name : Name.values()) {
                allCards.add(new Card(suit, name));
            }
        }
    }

    public static Stack<Card> getNewDeck() {
        Stack<Card> deck = new Stack<>();
        for (Card card : allCards) {
            deck.push(card);
        }

        Collections.shuffle(deck);
        return deck;
    }

    public int compareByValue(Card other) {
        return Integer.compare(name.value, other.name.value);
    }
}
