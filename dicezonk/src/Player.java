import java.util.Random;
import java.util.ArrayList;

public class Player {

    String name;
    int userId;
    double balance = 1000.00;
    int score = 0;
    int numberOfDice = 6;

    Player(String name) { // Конструктор класса Игрок
        this.name = name;
        userId = new Random().nextInt(9999) + 1000;
    }

}
