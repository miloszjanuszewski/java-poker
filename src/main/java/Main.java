import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static class Round {
        int prize;
        List<Player> players;
        int indexOfBigBlind;
        int valueOfBigBlind;
        Scanner scanner;

        boolean bettingIsFinished(int maxBet){
            for(Player player : players){
                if(player.Pot() < maxBet)return false;
            }
            return true;
        }

        public Round(List<Player> players, int indexOfBigBlind, int valueOfBigBlind, Scanner scanner) {
            this.players = players;
            this.indexOfBigBlind = indexOfBigBlind;
            this.valueOfBigBlind = valueOfBigBlind;
            this.scanner = scanner;
        }

        void playRound() {
            int indexOfSmallBlind = (indexOfBigBlind + players.size() - 1)% players.size();

            Player smallBlindPlayer = players.get(indexOfSmallBlind);
            if (!smallBlindPlayer.Bet(this.valueOfBigBlind / 2)) {
                smallBlindPlayer.AllIn();
            }

            Player bigBlindPlayer = players.get(indexOfBigBlind);
            if (!bigBlindPlayer.Bet(indexOfBigBlind)) {
                bigBlindPlayer.AllIn();
            }

            int indexOfActualPlayer = (indexOfBigBlind + 1) % players.size();
            int maxBet = valueOfBigBlind;
            while(bettingIsFinished(maxBet)) {
                Player curPlayer = players.get(indexOfActualPlayer);

                int value = scanner.nextInt();
                if(value>0 && curPlayer.Bet(value)){
                    maxBet = Math.max(maxBet, value);
                    indexOfActualPlayer++;
                }else{
                    players.remove(curPlayer);
                }
                indexOfActualPlayer = indexOfActualPlayer%players.size();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        ProcessBuilder pb = new ProcessBuilder("clear");
//        Process startProcess = pb.inheritIO().start();
//
//        startProcess.waitFor();

        int numberOfPlayers = 4;
        int startingMoney = 1000;
        int valueOfBigBlind = 20;
        Random randomNumberGenerator = new Random();
        int indexOfBigBlind = randomNumberGenerator.nextInt() % numberOfPlayers;
        List<Player> players = new LinkedList<>();
        for(int i=0;i<numberOfPlayers;i++) {
            System.out.print("Enter name: ");
            players.add(new Player(scanner.next(), startingMoney));
        }


        while(true) {
            Round actualRound = new Round(players, indexOfBigBlind, valueOfBigBlind, scanner);

        }

    }
}
