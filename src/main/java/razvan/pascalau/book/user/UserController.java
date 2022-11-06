package razvan.pascalau.book.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    private RoleToUserForm roleToUserForm;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @PostMapping("user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PostMapping("user/addroltouser")
    public ResponseEntity<User> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        userService.addRoleToUser(roleToUserForm.getUser(),roleToUserForm.getRole());
        return ResponseEntity.ok().build();
    }
}
