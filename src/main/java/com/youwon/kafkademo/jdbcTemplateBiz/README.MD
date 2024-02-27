    
# 프로젝트 구성하기

## VO
- Request 하는 Parameter가 필요한 경우 선언
- 단순 데이터 담고 이동을 위한 객체
```java

@getter
@setter
@toString
public class UserVO {

    private String name;
    private String email;
    private int age;
    // ...

}
```

## Domain
- Response 하는 Data를 담는 객체
- 컬럼 단위로 비즈니스 로직을 담아야 할 경우 사용
- Qeury한 데이터를 변경해야 하는 경우 이 곳에서 로직 작성 할 것
- Naming Rule은 따로 없으나, 직관적으로 알아볼 수 있도록 작성 할 것
```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int age;

    // ...

    public void changePassword(String password) {
        // 비밀번호 변경 로직
    }

}
```

## Repository
- interface는 생략 가능
- Query가 길어지는 경우, 혹은 if문을 사용해야 하는 경우는 StringBuilder 혹은 StringBuffer를 선언해서 사용할 것

```java
public interface UserRepository {

    User findById(Long id);
    List<User> findAll();
    void save(User user);
    void delete(User user);

}
```
```java
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, User.class);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (name, email) VALUES (?, ?)", new Object[]{user.getName(), user.getEmail()});
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", new Object[]{user.getId()});
    }

}
```

## Service 
- 다른 Repository를 호출하여 비즈니스 로직이 필요한 경우 해당 부분에 정의 할 것
- 다른 API를 호출하여 비즈니스 로직이 필요한 경우  Controller에 작성할 것
```java
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
```

