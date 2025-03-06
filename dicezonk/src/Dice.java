import java.util.Random;

public class Dice {

    Random random;
    //int numberOfDice;

    Dice() {
        random = new Random();
    }

    public int[] throwDice(int numberOfDice) {
        int[] resultArray = new int[numberOfDice];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = random.nextInt(6) + 1;
        }


        return resultArray;
    }


}
