package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.model.Accounts;
import project.model.Roles;
import project.repository.RoleRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Roles> getList() {
        return roleRepository.findAll();
    }

    public Roles create(Roles roles) {
        roles.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        roles.setStatus(1);
        return roleRepository.save(roles);
    }

    public Optional<Roles> getById(int id) {
        Optional<Roles> optionalRoles = roleRepository.findById(id);
        return optionalRoles;
    }

    public boolean delete(Roles roles) {
        roles.setStatus(-1);
        roles.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        roleRepository.save(roles);
        return true;
    }

    public Roles update(Roles roles) {
        roles.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return roleRepository.save(roles);
    }
}
