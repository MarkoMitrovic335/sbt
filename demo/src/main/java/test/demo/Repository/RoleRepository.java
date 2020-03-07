package test.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
