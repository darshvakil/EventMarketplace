package ca.sheridancollege.vakild.database;

import ca.sheridancollege.vakild.beans.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@AllArgsConstructor
public class TicketDatabase {

    private NamedParameterJdbcTemplate jdbc;

    public void addTicket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "INSERT INTO tickets (name, price, age, gender, phone, address, user_id) VALUES (:name, :price, :age, :gender, :phone, :address, :user_id)";
        parameters.addValue("name", ticket.getName());
        parameters.addValue("price", ticket.getPrice());
        parameters.addValue("age", ticket.getAge());
        parameters.addValue("gender", ticket.getGender());
        parameters.addValue("phone", ticket.getPhone());
        parameters.addValue("address", ticket.getAddress());
        parameters.addValue("user_id", ticket.getUserID());

        jdbc.update(sql, parameters);
    }

    public Ticket getTicketById(int ticket_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "SELECT * FROM tickets WHERE ticket_id = :ticket_id";
        parameters.addValue("ticket_id", ticket_id);

        return jdbc.queryForObject(sql, parameters, new BeanPropertyRowMapper<>(Ticket.class));
    }

    public void editTicket(int ticket_id, String name, double price, int age, String gender, String phone, String address, int user_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "UPDATE tickets SET name = :name, price = :price, age = :age, gender = :gender, phone = :phone, address = :address, user_id = :user_id WHERE ticket_id = :ticket_id";
        parameters.addValue("name", name);
        parameters.addValue("price", price);
        parameters.addValue("age", age);
        parameters.addValue("gender", gender);
        parameters.addValue("phone", phone);
        parameters.addValue("address", address);
        parameters.addValue("user_id", user_id);
        parameters.addValue("ticketId", ticket_id);

        jdbc.update(sql, parameters);
    }

    public List<Ticket> getTicketsByuser_id(int user_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "SELECT * FROM tickets WHERE user_id = :user_id";
        parameters.addValue("user_id", user_id);

        return jdbc.query(sql, parameters, new BeanPropertyRowMapper<>(Ticket.class));
    }

    public List<Ticket> getAllTicketsForVendor() {
        String sql = "SELECT * FROM tickets";

        return jdbc.query(sql, new BeanPropertyRowMapper<>(Ticket.class));
    }
    
    public void editTicket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "UPDATE tickets SET name = :name, price = :price, age = :age, gender = :gender, phone = :phone, address = :address, user_id = :user_id WHERE ticket_id = :ticket_id";
        parameters.addValue("name", ticket.getName());
        parameters.addValue("price", ticket.getPrice());
        parameters.addValue("age", ticket.getAge());
        parameters.addValue("geit nder", ticket.getGender());
        parameters.addValue("phone", ticket.getPhone());
        parameters.addValue("address", ticket.getAddress());
        parameters.addValue("userId", ticket.getUserID());
        parameters.addValue("ticket_id", ticket.getTicket_id());

        jdbc.update(sql, parameters);
    }

    public void deleteTicket(int ticketId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String sql = "DELETE FROM tickets WHERE ticket_id = :ticketId";
        parameters.addValue("ticketId", ticketId);

        jdbc.update(sql, parameters);
    }
}
