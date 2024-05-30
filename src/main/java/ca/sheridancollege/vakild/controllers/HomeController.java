package ca.sheridancollege.vakild.controllers;

import ca.sheridancollege.vakild.beans.Ticket;
import ca.sheridancollege.vakild.database.SecurityDatabase;
import ca.sheridancollege.vakild.database.TicketDatabase;
import ca.sheridancollege.vakild.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private TicketDatabase ticketDb;
	
	@Autowired
	private SecurityDatabase securityDb;
	
	@GetMapping("/user")
	public String myusermain(Authentication authentication, Model model) {
	    User user = (User) authentication.getPrincipal();

	    int userId = user.getUser_id();

	    // Now you have the user ID
	    model.addAttribute("userId", userId);

	    // Retrieve tickets based on the user ID
	    List<Ticket> tickets = ticketDb.getTicketsByuser_id(userId);
	    model.addAttribute("tickets", tickets);

	    // Add other attributes if needed
	    // For example, you might want to add user details like username and roles
	    model.addAttribute("username", user.getUsername());


	    // ... any other logic or data you want to add to the model

	    return "rootpage"; // or the appropriate view name
	}
	
    @GetMapping("/")
    public String myRoot() {
        return "rootpage";
    }

    @GetMapping("/add")
    public String addTicket(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "addpage";
    }

    @PostMapping("/submit")
    public String submitTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketDb.addTicket(ticket);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String viewTickets(Model model) {
        List<Ticket> tickets = ticketDb.getTicketsByuser_id(0);
        model.addAttribute("tickets", tickets);
        return "viewpage.html";
    }
    
    @GetMapping("/edit/{id}")
    public String editTicket(@PathVariable int id, Model model) {
        Ticket ticket = ticketDb.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "editpage";
    }

    @PostMapping("/update/{id}")
    public String updateTicket(@PathVariable int id, @ModelAttribute("ticket") Ticket ticket) {
        ticketDb.editTicket(ticket);
        return "redirect:/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable int id) {
        ticketDb.deleteTicket(id);
        return "redirect:/view";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login.html"; // Assuming you have a login.html template
    }
    
    @GetMapping("/register")
    public String register() {
        return "register.html"; // Assuming you have a register.html template
    }
    
    @PostMapping("/registerUser")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("encrypted_password") String encryptedPassword) {
        User user = new User();
        user.setUsername(username);
        user.setEncryptedPassword(encryptedPassword);

        securityDb.addUser(user);

        return "redirect:/login";
    }
}