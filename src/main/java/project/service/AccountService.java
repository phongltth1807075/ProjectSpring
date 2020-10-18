package project.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.model.Accounts;
import project.model.Product;
import project.model.Roles;
import project.repository.AccountRepository;

import java.util.*;

@Service
public class AccountService implements UserService {

    @Autowired
    AccountRepository accountRepository;
//    @Autowired
//    AccountService accountService;


    public Page<Accounts> getList(Specification specification, int page, int limit) {
        return accountRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Accounts create(Accounts accounts) {
        String password = accounts.getPassword();
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        accounts.setStatus(Accounts.AccountStatus.Active);
        accounts.setPassword(hash);
        accounts.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        return accountRepository.save(accounts);
    }


    public List<Accounts> getAccountsList() {
        List<Accounts> accountsList = accountRepository.findAll();
        return accountsList;
    }

    public List<Roles> getListRoles(int id) {
        List<Roles> rolesList = accountRepository.getRoles(id);
        return rolesList;
    }

    public Accounts getById(int id) {
        Accounts accounts = accountRepository.findByAccountId(id);
        return accounts;
    }

    public boolean delete(Accounts accounts) {
        accounts.setStatus(Accounts.AccountStatus.Deactive);
        accounts.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        accountRepository.save(accounts);
        return true;
    }

    public Accounts update(Accounts accounts) {
        accounts.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return accountRepository.save(accounts);
    }


    public Accounts findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Accounts login(String email, String password) {
        Accounts user = accountRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String pass = encoder.encode(password);
        if (encoder.matches(password, user.getPassword())) {
            String token = UUID.randomUUID().toString() + System.currentTimeMillis();
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
