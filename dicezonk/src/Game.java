import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    int winGameScore = 2000;
    int numberOfPlayers;
    boolean hasWinner = false;
    ArrayList<Player> players;


    Game(int winGameScore, int numberOfPlayers) { //, Scanner scanner
        this.winGameScore = winGameScore;
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>(numberOfPlayers);
        //this.scanner = scanner;

    }








        //метод настройки игры = игровой счет.






    //метод создание массива/списка/таблицы игроков
    void setPlayersName (String name) {
            Player player = new Player(name);
            players.add(player);
            System.out.println();
    }



}
