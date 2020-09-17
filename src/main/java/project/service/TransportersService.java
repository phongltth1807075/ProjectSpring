package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.Transporters;
import project.repository.TransportersRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TransportersService {


    @Autowired
    private TransportersRepository transportersRepository;

    public List<Transporters> getList() {
        return transportersRepository.findAll();
    }

    public Transporters create(Transporters transporters) {
        transporters.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        transporters.setStatus(Transporters.TransportersStatus.Active);
        return transportersRepository.save(transporters);
    }

    public Optional<Transporters> getById(int id) {
        Optional<Transporters> optionalTransporters = transportersRepository.findById(id);
        return optionalTransporters;
    }

    public boolean delete(Transporters transporters) {
        transporters.setStatus(Transporters.TransportersStatus.Deactive);
        transporters.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        transportersRepository.save(transporters);
        return true;
    }

    public Transporters update(Transporters transporters) {
        transporters.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return transportersRepository.save(transporters);
    }

}
