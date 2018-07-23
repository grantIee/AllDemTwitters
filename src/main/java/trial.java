import java.lang.reflect.Array;
import java.util.ArrayList;

public class trial {

    public static void main(String[] args) {
        ArrayList<String> sents = new ArrayList<String>();
        sents.add("Feels like a calm before a storm");
        sents.add("I let them delete my wallet and to their amazement I restored all my Bitcoin Cash within seconds.");
        sents.add("No need to call a bank and wait on hold, no loss of funds!");
        sents.add("The exchange was hacked");
        sents.add("Bullish af on appreciation");
        sents.add("I don't listen to media views and FUD and attacks on the space");
        sents.add("Perms bulls and people that try to persuade you to hold your bags tight when in reality you are losing money");
        sents.add("CME reported that BTC futures average daily volume is up 93 percent in Q2 over Q1, open interest increased by 58 percent");
        sents.add("I know that a Bitcoin bear market sucks but at least I hope you didn't buy $XRP at $3, $TRX at $0.2 or $BCH at $3700.");
        sents.add("Boutta invest in bitcoin before it get more hype");
        CoreNlpExample.init();
        for (String sent : sents) {
            System.out.println(sent + " : " + CoreNlpExample.getSentiment(sent));
        }
    }
}