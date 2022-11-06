package razvan.pascalau.book.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.pascalau.book.role.Role;
import razvan.pascalau.book.role.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUser(User user){
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new IllegalStateException("Email taken!");
        }
        return userRepository.save(user);
    }

    public void addRoleToUser(User user, Role role){
        Optional<User> usernameByEmail = userRepository.findByEmail(user.getEmail());
        if(!usernameByEmail.isPresent()){
            throw new IllegalStateException("Email doesn't exist!");
        }
        Optional<Role> byRole = roleRepository.findByRole(role.getRole());
        if(!byRole.isPresent()){
            throw new IllegalStateException("Role doesn't exist");
        }
        user.getRoles().add(role);
    }

    public User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new IllegalStateException("Email doesn't exist!"));
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
