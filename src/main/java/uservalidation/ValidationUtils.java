package uservalidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private static final String DEFAULT_USERNAME = "SamuelAkabuike";
    private static final String DEFAULT_PASSWORD = "SomePassword@%12";

    public static List<String> validateFields(String username, String email, String password, String dateOfBirth) {
        List<String> failedFields = new ArrayList<>();

        if (!validateUsername(username)) {
            if (username.isEmpty()) {
                failedFields.add("Username: Not empty.");
            }
            if (username.length() < 4) {
                failedFields.add("Username: Must be at least 4 characters long.");
            }
            if(username.equals(DEFAULT_USERNAME)){
                failedFields.add("Username: Invalid username");
            }
        }

        if (!validateEmail(email)) {
            if (email == null || email.isEmpty()) {
                failedFields.add("Email: Not empty.");
            }
            if (email != null && !EMAIL_PATTERN.matcher(email).matches()) {
                failedFields.add("Email: Invalid email address.");
            }
        }

        if (!validatePassword(password)) {
            if (password.isEmpty()) {
                failedFields.add("Password: Not empty.");
            }
            if (password.length() < 8) {
                failedFields.add("Password: Must be at least 8 characters long.");
            }
            if (!Pattern.matches(passwordRegex, password)) {
                failedFields.add("Password: Must contain at least one uppercase letter, one digit, and one special character (!@#$%^&*).");
            }
            if(!password.equals(DEFAULT_PASSWORD)){
                failedFields.add("Password: Invalid password");
            }
        }

        if (!validateDateOfBirth(dateOfBirth)) {
            if (dateOfBirth == null || dateOfBirth.isEmpty()) {
                failedFields.add("Date of Birth: Not empty.");
            }
            if (dateOfBirth != null) {
                try {
                    LocalDate birthDate = LocalDate.parse(dateOfBirth);
                    LocalDate currentDate = LocalDate.now();
                    LocalDate minDate = currentDate.minusYears(16);
                    if (!birthDate.isBefore(minDate)) {
                        failedFields.add("Date of Birth: Should be 16 years or greater.");
                    }
                } catch (Exception e) {
                    failedFields.add("Date of Birth: Invalid date format.");
                }
            }
        }

        return failedFields;
    }

    public static boolean validateUsername(String username) {
        return username.length() >= 4 && username.equals(DEFAULT_USERNAME);
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {

        return !password.isEmpty() && Pattern.matches(passwordRegex, password)
                && password.equals(DEFAULT_PASSWORD);
    }

    public static boolean validateDateOfBirth(String dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            return false;
        }

        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate currentDate = LocalDate.now();
            LocalDate minDate = currentDate.minusYears(16);

            return birthDate.isBefore(minDate);
        } catch (Exception e) {
            return false;
        }
    }
}
