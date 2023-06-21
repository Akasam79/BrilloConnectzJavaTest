package pancakeshop;

import java.util.Random;

public class PancakeShopNonConcurrent {
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_PER_SHOPKEEPER = 12;
    private static final int TOTAL_USERS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        int totalTime = 0;
        int totalPancakesWasted = 0;
        int totalOrdersNotMet = 0;

        for (int i = 1; i <= 10; i++) {
            int user1Order = random.nextInt(MAX_PANCAKES_PER_USER + 1);
            int user2Order = random.nextInt(MAX_PANCAKES_PER_USER + 1);
            int user3Order = random.nextInt(MAX_PANCAKES_PER_USER + 1);

            int totalOrder = user1Order + user2Order + user3Order;
            int pancakesWasted = Math.max(0, totalOrder - MAX_PANCAKES_PER_SHOPKEEPER);

            totalPancakesWasted += pancakesWasted;
            totalOrdersNotMet += totalOrder > MAX_PANCAKES_PER_SHOPKEEPER ? 1 : 0;

            System.out.println("Starting time of the 30 seconds slot: " + totalTime);
            totalTime += 30;
            System.out.println("Ending time of the 30 seconds slot: " + totalTime);

            System.out.println("User 1 order: " + user1Order + " pancakes");
            System.out.println("User 2 order: " + user2Order + " pancakes");
            System.out.println("User 3 order: " + user3Order + " pancakes");
            System.out.println("Pancakes wasted: " + pancakesWasted);
            System.out.println("--------------------------------------");
        }

        System.out.println("Total pancakes wasted: " + totalPancakesWasted);
        System.out.println("Total orders not met: " + totalOrdersNotMet);
    }
}
