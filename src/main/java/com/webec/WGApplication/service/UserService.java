
package com.webec.WGApplication.service;

        import com.webec.WGApplication.model.UserEntry;
        import com.webec.WGApplication.model.entity.User;
        import com.webec.WGApplication.model.repository.UserRepository;
        import org.springframework.stereotype.Service;

        import java.util.List;

        import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public List<UserEntry> getAllUsers() {
        return repo.findAll().stream().map(u -> new UserEntry(
                u.getId(),
                u.getUsername(),
                u.getPassword(),
                u.getAuthorities()))
                .collect(toList());
    }
    public UserEntry getUserById(int id){
        User u = repo.findById(id).get();
        return new UserEntry(
                u.getId(),
                u.getUsername(),
                u.getPassword(),
                u.getAuthorities());
    }

    public UserEntry getUserByUsername(String username) {
        User u = repo.findByUsername("Sophia").get();
        return new UserEntry(
                u.getId(),
                u.getUsername(),
                u.getPassword(),
                u.getAuthorities());
    }
}
