import java.util.Random;
import java.util.ArrayList;

public class Dice {
    Random random;

    Dice() {
        random = new Random();
    }

    public ArrayList<Integer> throwDice(int numberOfDice) {
        ArrayList<Integer> resultOfThrow = new ArrayList<>(numberOfDice);
        System.out.println("Выпало: ");
        for (int i = 0; i < numberOfDice; i++) {
            Integer element = random.nextInt(6) + 1;
            resultOfThrow.add(i, element);
            System.out.println((i + 1) + "-я КОСТЬ :" + resultOfThrow.get(i));
        }
        return resultOfThrow;
    }

}
