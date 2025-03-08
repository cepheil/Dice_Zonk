import java.util.ArrayList;

public class ScoreCalculator {
    int scoreByThrow;
    int[] onesScore = {0, 100, 200, 1000, 2000, 3000, 4000};
    int[] twosScore = {0, 0, 0, 200, 400, 600, 800};
    int[] threesScore = {0, 0, 0, 300, 600, 900, 1200};
    int[] foursScore = {0, 0, 0, 400, 800, 1200, 1600};
    int[] fivesScore = {0, 50, 100, 500, 1000, 1500, 2000};
    int[] sixesScore = {0, 0, 0, 600, 1200, 1800, 2400};


    public int getScore(ArrayList<Integer> savedDice) {
        scoreByThrow = 0;
        int[] countElements = new int[7];  // создаем массив счетчик большим размером, 0-й элемент не используем.

        for (int i = 0; i < savedDice.size(); i++) {  //  Допустим передали список из трех значений = 1, 2, 5,
            int diceValue = savedDice.get(i);
            countElements[diceValue]++;
        }

        for (int face = 1; face < 7; face++) {
            int count = countElements[face];
            switch (face) {
                case 1:
                    scoreByThrow += onesScore[count];
                    break;
                case 2:
                    scoreByThrow += twosScore[count];
                    break;
                case 3:
                    scoreByThrow += threesScore[count];
                    break;
                case 4:
                    scoreByThrow += foursScore[count];
                    break;
                case 5:
                    scoreByThrow += fivesScore[count];
                    break;
                case 6:
                    scoreByThrow += sixesScore[count];
                    break;
                default:
                    // System.out.println("неожиданное  значение")
                    break;
            }
        }


        boolean isStraight = true;   // проверка на полный стрит с 1 до 6 = 1500 очков.
        boolean isHalfStraightOne = true; // проверка на ПОЛУ стрит с 1 до 5 илм с 2 до 6 = 750 очков
        boolean isHalfStraightTwo = true;// проверка на ПОЛУ стрит с 1 до 5 илм с 2 до 6 = 750 очков.

        for (int i = 1; i < 7; i++) {   // проверка от 1 по 6 костяшку на равенство =1,
            if (countElements[i] != 1) {
                isStraight = false;
                break;
            }
        }

        for (int i = 2; i < 7; i++) {
            if (countElements[i] != 1) {
                isHalfStraightOne = false;
                break;
            }
        }

        for (int i = 1; i < 6; i++) {
            if (countElements[i] != 1) {
                isHalfStraightTwo = false;
                break;
            }
        }

        if (isStraight) {
            scoreByThrow = scoreByThrow + 1500;
        } else if (isHalfStraightOne || isHalfStraightTwo) {
            scoreByThrow = scoreByThrow + 750;
        }


        //System.out.println("за ход выпало: " + scoreByThrow);

        int scoreToSave = scoreByThrow;
        scoreByThrow = 0;

        return scoreToSave;
    }

}
