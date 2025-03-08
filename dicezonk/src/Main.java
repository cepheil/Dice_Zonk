import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game;

        while (true) {
            printMenu();
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "1":
                    System.out.println("вы выбрали пункт НОВАЯ ИГРА");

                    System.out.println("введите победный счет");
                    int newWinScore = scanner.nextInt();
                    System.out.println("введите количество игроков");
                    int numberOfPlayers = scanner.nextInt();
                    scanner.nextLine();

                    game = new Game(newWinScore, numberOfPlayers, scanner);

                    for (int i = 0; i < numberOfPlayers; i++) {    // в цикле создаем список игроков с именами
                        System.out.print("Введите имя " + (i + 1) + "го игрока _");
                        String name = scanner.nextLine();
                        game.setPlayersName(name);
                    }

                    game.startGame();

                    break;
                case "0":
                    return;
                default:
                    System.out.println("Ошибка, вы ввели " + cmd);
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("_".repeat(20));
        System.out.println("добро пожаловать в DICE");
        System.out.println("выберите пункт меню:");
        System.out.println("1. НОВАЯ ИГРА");
        System.out.println("0. Выход");
        System.out.println("_".repeat(20));
    }

}













