package com.webec.WGApplication;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.model.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Set;

@Component
public class InitialUserAdder {

    private final UserRepository userRepo;

    public InitialUserAdder(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepo.findAll().isEmpty()) {
            var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            // DANI
            var password1 = "daniel";
            var encoded1 = encoder.encode(password1);
            var daniel = new User("Daniel", encoded1, Set.of("ROLE_ADMIN"));
            userRepo.save(daniel);
            // SOPHIA
            var password2 = "sophia";
            var encoded2 = encoder.encode(password2);
            var sophia = new User("Sophia", encoded2, Set.of("ROLE_ADMIN"));
            userRepo.save(sophia);
            // FELIX
            var password3 = "felix";
            var encoded3 = encoder.encode(password3);
            var felix = new User("Felix", encoded3, Set.of("ROLE_ADMIN"));
            userRepo.save(felix);
            // FELIX
            var password4 = "sabrina";
            var encoded4 = encoder.encode(password4);
            var sabrina = new User("Sabrina", encoded4, Set.of("ROLE_ADMIN"));
            userRepo.save(sabrina);
        }
    }
}
