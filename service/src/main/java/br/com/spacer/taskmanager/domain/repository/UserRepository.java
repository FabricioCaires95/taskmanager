package br.com.spacer.taskmanager.domain.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.spacer.taskmanager.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User u SET u.name = :name, u.password = :password WHERE u.id = :uuid")
    void updateUser(@Param("uuid") UUID id, @Param("name") String name, @Param("password") String password);

}
