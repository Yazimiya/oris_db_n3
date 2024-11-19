import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "itis";
    private static final String DB_PASSWORD = "itis";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/itis_db_oris_n3";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        PsychologistRepository psychologistRepository = new PsychologistsRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество психологов для добавления: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Введите имя: ");
            String name = scanner.next();
            System.out.print("Введите фамилию: ");
            String surname = scanner.next();
            System.out.print("Введите опыт работы: ");
            int experience = scanner.nextInt();
            System.out.print("Введите возраст: ");
            int age = scanner.nextInt();
            System.out.print("Введите специализацию: ");
            String specialization = scanner.next();
            System.out.print("Введите телефон: ");
            String phone = scanner.next();

            Psychologist psychologist = new Psychologist(null, name, surname, experience, age, specialization, phone);
            psychologistRepository.save(psychologist);
        }
    }
}
