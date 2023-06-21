package pancakeshop;

import java.util.Random;

public class PancakeShopConcurrent {
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_PER_SHOPKEEPER = 12;
    private static final int TOTAL_USERS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        int totalTime = 0;
        int totalPancakesWasted = 0;
        int totalOrdersNotMet = 0;

        for (int i = 1; i <= 10; i++) {
            int[] userOrders = new int[TOTAL_USERS];
            int totalOrder = 0;

            for (int j = 0; j < TOTAL_USERS; j++) {
                userOrders[j] = random.nextInt(MAX_PANCAKES_PER_USER + 1);
                totalOrder += userOrders[j];
            }

            int pancakesWasted = Math.max(0, totalOrder - MAX_PANCAKES_PER_SHOPKEEPER);
            totalPancakesWasted += pancakesWasted;
            totalOrdersNotMet += totalOrder > MAX_PANCAKES_PER_SHOPKEEPER ? 1 : 0;

            System.out.println("Starting time of the 30 seconds slot: " + totalTime);
            totalTime += 30;
            System.out.println("Ending time of the 30 seconds slot: " + totalTime);

            for (int j = 0; j < TOTAL_USERS; j++) {
                System.out.println("User " + (j + 1) + " order: " + userOrders[j] + " pancakes");
            }

            System.out.println("Pancakes wasted: " + pancakesWasted);
            System.out.println("--------------------------------------");
        }

        System.out.println("Total pancakes wasted: " + totalPancakesWasted);
        System.out.println("Total orders not met: " + totalOrdersNotMet);
    }
}
