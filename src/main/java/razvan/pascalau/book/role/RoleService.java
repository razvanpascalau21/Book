package razvan.pascalau.book.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.pascalau.book.author.Author;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> roles(){
        return roleRepository.findAll();
    }

    public Role role(long id){
        return roleRepository.findById(id).orElseThrow(()->new IllegalStateException("book with id="+id+" doesn't exist!"));
    }

    public void addNewRole(Role role){
        Role byRole = roleRepository.findByRole(role.getRole());
//        if(byRole.isPresent()){
//            throw new IllegalStateException("Role"+ role.getRole()+" is present");
//        }
        if(byRole!=null){
            throw new IllegalStateException("Role"+ role.getRole()+" is present");
        }else {
            roleRepository.save(role);
        }
    }

    @Transactional
    public void update(Long id,String role){
        Role roleId = roleRepository.findById(id).orElseThrow(()->new IllegalStateException("role with "+id+" doesn't exist!"));
        if(role!=null&& role.length()>0&& !Objects.equals(roleId.getRole(),role)){
            Role findByRole = roleRepository.findByRole(role);
//            if(findByRole.isPresent()){
//                throw new IllegalStateException("Role "+ role+" exist");
//            }
            if(findByRole!=null){
                throw new IllegalStateException("Role "+ role+" exist");
            }
            roleId.setRole(role);
        }
    }

    public void deleteRole(long id){
        if(!roleRepository.existsById(id)){
            throw new IllegalStateException("role with id="+id+" doesn't exist!");
        }
        roleRepository.deleteById(id);
    }
}
