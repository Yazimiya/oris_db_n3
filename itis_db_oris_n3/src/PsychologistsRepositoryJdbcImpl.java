import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PsychologistsRepositoryJdbcImpl implements PsychologistRepository {
    private Connection connection;
    private static final String SQL_SELECT_ALL_FROM_PSYCHOLOGIST = "SELECT * FROM psychologist";

    public PsychologistsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Psychologist> findAll() {
        List<Psychologist> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_PSYCHOLOGIST)) {
            while (resultSet.next()) {
                Psychologist psychologist = new Psychologist(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("age"),
                        resultSet.getString("specialization"),
                        resultSet.getString("phone")
                );
                result.add(psychologist);
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return result;
    }

    @Override
    public Optional<Psychologist> findById(Long id) {
        String sql = "SELECT * FROM psychologist WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Psychologist psychologist = new Psychologist(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("experience"),
                            resultSet.getInt("age"),
                            resultSet.getString("specialization"),
                            resultSet.getString("phone")
                    );
                    return Optional.of(psychologist);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return Optional.empty();
    }

    @Override
    public void save(Psychologist entity) {
        String sql = "INSERT INTO psychologist (name, surname, experience, age, specialization, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setInt(3, entity.getExperience());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getSpecialization());
            preparedStatement.setString(6, entity.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void update(Psychologist entity) {
        String sql = "UPDATE psychologist SET name = ?, surname = ?, experience = ?, age = ?, specialization = ?, phone = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setInt(3, entity.getExperience());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getSpecialization());
            preparedStatement.setString(6, entity.getPhone());
            preparedStatement.setLong(7, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void remove(Psychologist entity) {
        removeById(entity.getId());
    }

    @Override
    public void removeById(Long id) {
        String sql = "DELETE FROM psychologist WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<Psychologist> findAllByExperience(Long experience) {
        return filterByField("experience", experience);
    }

    @Override
    public List<Psychologist> findAllByAge(Integer age) {
        return filterByField("age", age);
    }

    @Override
    public List<Psychologist> findAllBySpecialization(String specialization) {
        return filterByField("specialization", specialization);
    }

    @Override
    public List<Psychologist> findAllByPhone(String phone) {
        return filterByField("phone", phone);
    }

    private <T> List<Psychologist> filterByField(String fieldName, T value) {
        String sql = "SELECT * FROM psychologist WHERE " + fieldName + " = ?";
        List<Psychologist> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, value);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Psychologist psychologist = new Psychologist(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("experience"),
                            resultSet.getInt("age"),
                            resultSet.getString("specialization"),
                            resultSet.getString("phone")
                    );
                    result.add(psychologist);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return result;
    }
}

