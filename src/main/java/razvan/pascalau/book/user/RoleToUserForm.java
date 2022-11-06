package razvan.pascalau.book.user;

import razvan.pascalau.book.role.Role;

public class RoleToUserForm {
    private User user;
    private Role role;

    public RoleToUserForm(){}

    public RoleToUserForm(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleToUserForm{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
