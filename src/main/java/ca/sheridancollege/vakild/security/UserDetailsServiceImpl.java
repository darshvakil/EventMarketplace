package ca.sheridancollege.vakild.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ca.sheridancollege.vakild.beans.User;
import ca.sheridancollege.vakild.database.SecurityDatabase;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityDatabase secRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = secRepo.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }

        List<String> roles = secRepo.getRolesById(user.getuser_id());
        List<GrantedAuthority> grantList = new ArrayList<>();

        for (String role : roles) {
            grantList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        // Using Spring Security's User class directly
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
