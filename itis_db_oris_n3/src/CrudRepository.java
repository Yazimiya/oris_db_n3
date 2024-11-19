import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    List<T> findAll();
    Optional<T> findById();

    void save(T entity);
    void update(T entity);
    void remove(T entity);
    void removeById(Long id);
}