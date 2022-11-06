package razvan.pascalau.book.user;

import razvan.pascalau.book.role.Role;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity//defaul name is User(class name)
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(name = "email_unique",columnNames = "email")})
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,generator = "user_sequence")
    private Long id;
    @Column(name = "first_name",nullable = false,columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name",nullable = false,columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email",nullable = false,columnDefinition = "TEXT")
    private String email;
    @Column(name = "password",nullable = false,columnDefinition = "TEXT")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns =@JoinColumn(name = "user_id"),inverseJoinColumns =@JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();

    public User(){
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
