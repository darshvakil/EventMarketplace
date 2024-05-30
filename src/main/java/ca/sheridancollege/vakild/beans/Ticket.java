package ca.sheridancollege.vakild.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	public int ticket_id;
    private String name;
    private double price;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private int userID;
    
}