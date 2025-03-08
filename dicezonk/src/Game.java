import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    int winGameScore;
    int numberOfPlayers;
    boolean hasWinner = false;
    ArrayList<Player> players;
    Dice dice;
    Scanner scanner;
    ScoreCalculator scoreCalculator;


    Game(int winGameScore, int numberOfPlayers, Scanner scanner) {
        this.winGameScore = winGameScore;
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>(numberOfPlayers);
        this.dice = new Dice();
        this.scanner = scanner;
        this.scoreCalculator = new ScoreCalculator();

    }


    void setPlayersName(String name) {
        Player player = new Player(name);
        players.add(player);
        System.out.println();
    }

    void playRound(Player player) { // бросок кубика одним игроком - полный цикл.
        int diceAmount = player.numberOfDice;
        int accumulatedRoundScore = 0;


        while (true) {
            System.out.println(player.name + " бросает кости: ");
            ArrayList<Integer> resultOfThrow = dice.throwDice(diceAmount);  // игрок бросает кость - получаем ЛИСТ результатов.
            int scoreByThrow = scoreCalculator.getScore(resultOfThrow);  //  метод, который принимает Лист Результатов броска.

            if (scoreByThrow <= 0) {
                System.out.println("Не повезло. Переход хода");
                System.out.println("_".repeat(20));
                break;
            } else {
                System.out.println(player.name + " у вас выпало :" + scoreByThrow);
                printMenuToChoose();  // выбор действия: = 1 отложить и продолжить бросок, 2 сохранить и завершить ход
                String command = scanner.nextLine();


                while (!(command.equals("1") || command.equals("2"))) {
                    System.out.println("Ошибка. Введите 1 или 2:");
                    printMenuToChoose();
                    command = scanner.nextLine();
                }

                if (command.equals("1")) {  //1. сохранить кости и продолжить бросок.
                    boolean validSelection = false;
                    ArrayList<Integer> savedResultOfThrow = new ArrayList<>();
                    int savedScore = 0;
                    int savedAmount = 0;

                    while (!validSelection) {  // цикл выбора костей
                        ArrayList<Integer> savedDices = saveDiceNumbers(scanner); // получаем номера костей для сохранения
                        savedResultOfThrow.clear(); // очищаем предыдущие данные

                        for (int idx : savedDices) {
                            if (idx < resultOfThrow.size()) {
                                savedResultOfThrow.add(resultOfThrow.get(idx));   // формируем новый лист сохраненных костей.
                            } else {
                                System.out.println("Некорректный номер кости: " + (idx + 1));
                            }
                        }

                        System.out.println("Вы выбрали " + savedResultOfThrow); //кости на сохранение
                        savedScore = scoreCalculator.getScore(savedResultOfThrow);
                        if (savedScore > 0) {
                            validSelection = true;
                            savedAmount = savedDices.size(); // сохраненное количество костей
                        } else {
                            System.out.println("Выбраны неверные кости. Попробуйте снова.");
                        }
                    }

                    accumulatedRoundScore += savedScore;  // сохраняем временный счет
                    System.out.println(player.name + " вы сохранили  :" + savedScore + " ваш ВРЕМЕННЫЙ счет : "
                            + accumulatedRoundScore);
                    diceAmount = diceAmount - savedAmount; // остаток костей в игре

                    if (diceAmount == 0) { // если сохранены все кости, то восстанавливаем дефолт
                        System.out.println("Все кости сохранены в комбинации. Перебрасываем все кости.");
                        diceAmount = player.numberOfDice;
                    }
                    continue;

                } else if (command.equals("2")) { //2. сохранить кости и передать ход
                    player.score = player.score + (scoreByThrow + accumulatedRoundScore);
                    System.out.println(player.name + " вы сохранили  :" + (scoreByThrow + accumulatedRoundScore)
                            + " ваш счет: " + player.score);
                    System.out.println("Переход хода");
                    System.out.println();
                    break;
                }
            }
        }
    }


    boolean checkScore() {
        for (Player player : players) {
            if (player.score >= winGameScore) {
                hasWinner = true;
                System.out.println(player.name + "ПОБЕДИЛ cо счётом: " + player.score);
            }
        }
        return hasWinner;
    }


    void startGame() {
        int countRound = 1;
        while (!hasWinner) {
            System.out.println("РАУНД № " + countRound);
            showPlayersInfo();
            for (Player player : this.players) {
                playRound(player);
            }
            countRound++;
            hasWinner = checkScore();
        }
    }

    void showPlayersInfo() {
        System.out.println("СТАТИСТИКА ИГРОКОВ");
        System.out.println("++".repeat(20));
        for (Player player : players) {
            System.out.println(player.name + " iD:  " + player.userId + " SCORE: " + player.score);
            System.out.println();
        }
        System.out.println("++".repeat(20));
    }

    public ArrayList<Integer> saveDiceNumbers(Scanner scanner) {  // Получаем список порядковых номеров костей
        System.out.println("Выберите номера костей для сохранения");
        String number = scanner.nextLine();
        ArrayList<Integer> savedNumbers = new ArrayList<>();
        while (!number.isEmpty()) {
            int num = Integer.parseInt(number) - 1;
            if (num >= 0 && num <= 5) {
                savedNumbers.add(num);
            } else {
                System.out.println("Выберите номера от 1 до 6, вы выбрали " + num);
            }
            number = scanner.nextLine();
        }
        return savedNumbers;  // получаем список номеров костей.
    }

    public static void printMenuToChoose() {
        System.out.println(" ВЫБЕРИТЕ ДЕЙСТВИЕ ");
        System.out.println("1. сохранить кости и продолжить бросок.");
        System.out.println("2. сохранить кости и передать ход");
        System.out.println();
    }

}
