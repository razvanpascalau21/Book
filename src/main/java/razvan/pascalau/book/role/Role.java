package razvan.pascalau.book.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import razvan.pascalau.book.user.User;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @SequenceGenerator(name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,generator = "role_sequence")
    private Long id;
    @Column(name = "role",nullable = false,columnDefinition = "TEXT")
    private String role;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users=new HashSet<>();

    public Role(){}

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
