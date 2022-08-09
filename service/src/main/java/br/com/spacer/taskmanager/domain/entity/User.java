package br.com.spacer.taskmanager.domain.entity;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public User() {
    }

    private User(Builder builder) {
        this.id = builder.uuid;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.tasks = builder.tasks;
        this.createdAt = builder.createAt;
        this.updatedAt = builder.updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(tasks, user.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, tasks);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tasks=" + tasks +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static Builder newUser() {
        return new Builder();
    }

    public static final class Builder {
        private UUID uuid;
        private String name;
        private String email;
        private String password;
        private Set<Task> tasks = new HashSet<>();
        private OffsetDateTime createAt;
        private OffsetDateTime updatedAt;

        private Builder() {
        }

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder tasks(Set<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createAt = createdAt;
            return this;
        }

        public Builder updateAt(OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
