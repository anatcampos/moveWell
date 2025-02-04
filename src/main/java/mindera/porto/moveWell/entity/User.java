package mindera.porto.moveWell.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    private Integer age;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "userCreator")
    private List<Video> videosCreated;

    @OneToOne //validar se há problema deixar no singular
    private Subscription subscription;

    @OneToMany(mappedBy = "userViewer")
    private List<Views> views;

    public User() {
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User(Long id, String username, String name, String password, Integer age, Role role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
