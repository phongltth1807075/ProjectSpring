package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import project.dto.AccountDTO;
import project.model.Accounts;
import project.model.Roles;
import project.service.AccountService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    AccountService accountService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object credential = usernamePasswordAuthenticationToken.getCredentials();
        if (credential == null) {
            throw new UsernameNotFoundException("Cannot find user with authentication token");
        }
        String token = String.valueOf(credential);
        Accounts accounts = accountService.findUserByToken(token);
        if (accounts == null) {
            throw new UsernameNotFoundException("Token invalid");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        List<Roles> roles = accountService.getRoles(accounts.getAccountId());

        accounts.setRolesList(roles);

        for (int i = 0; i < roles.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(roles.get(i).getRoleName()));
        }

//        authorities.add(new SimpleGrantedAuthority("User"));
//        authorities.add(new SimpleGrantedAuthority("Admin"));

        User user = new User(accounts.getEmail(), accounts.getPassword(), authorities);
        return user;
    }
}
