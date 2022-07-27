package cn.bobdeng.rbac;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@EqualsAndHashCode
public class Tenant implements Entity<Integer, TenantDescription> {
    private Integer id;
    private TenantDescription tenantDescription;
    @Setter
    private Users users;

    public Tenant(TenantDescription tenantDescription) {

        this.tenantDescription = tenantDescription;
    }

    public Tenant(Integer id, TenantDescription tenantDescription) {
        this.id = id;
        this.tenantDescription = tenantDescription;
    }

    @Override
    public Integer identity() {
        return id;
    }

    @Override
    public TenantDescription description() {
        return tenantDescription;
    }

    public void addUser(UserDescription userDescription) {
        users.save(new User(userDescription));
    }

    public interface Users {
        User save(User user);

        Optional<User> findById(Integer id);

        List<User> findByName(String name);

        int size();
    }
}