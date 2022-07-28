package cn.bobdeng.rbac;

import lombok.*;

import java.util.List;
import java.util.Optional;

@EqualsAndHashCode
@NoArgsConstructor
@Data
public class Tenant implements Entity<Integer, TenantDescription> {
    private Integer id;
    private TenantDescription description;
    private Users users;
    private LoginNames loginNames;


    public Tenant(TenantDescription tenantDescription) {

        this.description = tenantDescription;
    }

    public Tenant(Integer id, TenantDescription tenantDescription) {
        this.id = id;
        this.description = tenantDescription;
    }

    @Override
    public Integer identity() {
        return id;
    }

    @Override
    public TenantDescription description() {
        return description;
    }

    public User addUser(UserDescription userDescription) {
        return users.save(new User(userDescription));
    }

    public LoginName addLoginName(LoginNameDescription description) {
        if (loginNames.findByLoginName(description.getName()).isPresent()) {
            throw new DuplicateLoginNameException();
        }
        return loginNames.save(new LoginName(description));
    }

    public interface Users extends EntityList<Integer, User> {
        User save(User user);

        List<User> findByName(String name);

        Optional<User> findByAccount(String account);
    }

    public interface LoginNames extends EntityList<Integer, LoginName> {

        Optional<LoginName> findByLoginName(String name);
    }
}
