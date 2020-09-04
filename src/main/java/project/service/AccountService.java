package project.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.model.Accounts;
import project.repository.AccountRepository;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService implements UserService{

    @Autowired
    AccountRepository accountRepository;


    public Page<Accounts> getList(Specification specification, int page, int limit) {
        return accountRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Accounts create(Accounts accounts) {
        String password = accounts.getPassword();
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        accounts.setPassword(hash);
        accounts.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        return accountRepository.save(accounts);
    }

    public Optional<Accounts> getById(int id) {
        Optional<Accounts> optionalProduct = accountRepository.findById(id);
        return optionalProduct;
    }

    public boolean delete(Accounts accounts) {
        accounts.setStatus(-1);
        accounts.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        accountRepository.save(accounts);
        return true;
    }

    public Accounts update(Accounts accounts) {
        accounts.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return accountRepository.save(accounts);
    }

    @Override
    public Accounts login(String email, String password) {
        Accounts user = accountRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pass = encoder.encode(password);
        if (encoder.matches(password, user.getPassword())) {
            String token = UUID.randomUUID().toString()+System.currentTimeMillis();
            user.setToken(token);
            Accounts userNew = accountRepository.save(user);
            return userNew;
        }
        return null;
    }

    @Override
    public Accounts findUserByToken(String token) {
        return accountRepository.findByToken(token);
    }
}
