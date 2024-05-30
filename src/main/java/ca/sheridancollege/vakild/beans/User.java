package ca.sheridancollege.vakild.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private int user_id;
	private String username;
	private String encryptedPassword;

}
