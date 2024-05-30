package ca.sheridancollege.vakild.database;

import ca.sheridancollege.vakild.beans.User;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SecurityDatabase {

    private final NamedParameterJdbcTemplate jdbc;

    public User findUserById(int user_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users WHERE user_id = :user_id";
        parameters.addValue("user_id", user_id);

        return jdbc.queryForObject(query, parameters, new BeanPropertyRowMapper<>(User.class));
    }

    public org.springframework.security.core.userdetails.User findUserByUsername(String username) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users WHERE username = :username";
        parameters.addValue("username", username);

        return jdbc.queryForObject(query, parameters, new BeanPropertyRowMapper<>(User.class));
    }
    
    public void addUser(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO users (username, encrypted_password, role) VALUES (:username, :encryptedPassword, 'GUEST')";
        parameters.addValue("username", user.getUsername());
        parameters.addValue("encryptedPassword", user.getEncryptedPassword());

        jdbc.update(query, parameters);
    }
    
    public List<String> getRolesById(int user_id) {
        String query = "SELECT role FROM users WHERE user_id = :user_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", user_id);

        return (List<String>) jdbc.queryForList(query, parameters, String.class);
    }

}