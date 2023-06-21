package uservalidation;

import java.util.List;
import java.util.Scanner;

public class UserValidationApp {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input values from the user
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        scanner.close();

        //Perform validations concurrently
        List<String> failedFields = ValidationUtils.validateFields(username, email, password, dateOfBirth);
        if (failedFields.isEmpty()) {
            // Generate JWT token
            String token = JwtUtil.generateJWT(username);
            System.out.println("Token: " + token);

            // Verify the generated token
            if (JwtUtil.verifyJWT(token)) {
                System.out.println("Verification passed.");
            } else {
                System.out.println("Verification failed.");
            }
        } else {
            System.out.println("Validation failed for the following fields:");
            for (String error : failedFields) {
                System.out.println(error);
            }
        }
    }


}
