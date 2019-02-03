package nl.hsleiden.service;

import java.util.Optional;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import javax.inject.Inject;

import nl.hsleiden.model.User;


public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User> {

    private final UserService userService;

    @Inject
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) {
        User user = userService.get(basicCredentials.getUsername());

        if (user != null && basicCredentials.getPassword().equals(user.getPassword())){
            return Optional.of(userService.get(basicCredentials.getUsername()));
        }

        return Optional.empty();
    }

    @Override
    public boolean authorize(User user, String roleName) {
        return user.isAdmin();
    }
}
