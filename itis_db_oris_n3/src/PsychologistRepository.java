import java.util.List;

public interface PsychologistRepository extends CrudRepository<Psychologist> {
    List<Psychologist> findAllByExperience(Long experience);

    List<Psychologist> findAllByAge(Integer age);

    List<Psychologist> findAllBySpecialization(String specialization);

    List<Psychologist> findAllByPhone(String phone);
}
