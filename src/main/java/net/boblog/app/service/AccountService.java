package net.boblog.app.service;

import net.boblog.app.entity.Account;
import net.boblog.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dave on 16/5/19.
 */
@Service
public class AccountService {
    @Autowired AccountRepository repository;

    public Account save(Account a) {
        return repository.save(a);
    }

    public Account findById(Long id) {
        return repository.findOne(id);
    }

    public Account findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Account findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Account findByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}
