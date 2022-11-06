package razvan.pascalau.book.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razvan.pascalau.book.api.Book;


import java.util.List;

@RestController
@RequestMapping(path = "api/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> roles(){
        return roleService.roles();
    }

    @GetMapping(path = "{roleId}")
    private Role getRole(@PathVariable("roleId") Long id){
        return roleService.role(id);
    }

    @PostMapping()
    private void addRole(@RequestBody Role role){
        roleService.addNewRole(role);
    }

    @DeleteMapping(path = "{roleId}")
    private void deleteById(@PathVariable("roleId") Long id){
        roleService.deleteRole(id);
    }

    @PutMapping(path = "{roleId}")
    private void update(@PathVariable ("roleId") Long id,@RequestParam String role){
        roleService.update(id,role);
    }

}
