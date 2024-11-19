import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class MainRepository {
    private static final String DB_USERNAME = "itis";
    private static final String DB_PASSWORD = "itis";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/itis_db_oris_n3";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        PsychologistRepository psychologistRepository = new PsychologistsRepositoryJdbcImpl(connection);

        List<Psychologist> psychologists = psychologistRepository.findAll();

        psychologists.forEach(psychologist -> System.out.println(psychologist.getName()));
    }
}

