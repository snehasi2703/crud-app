package com.aquent.crudapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.model.Client;
import com.aquent.crudapp.repository.ClientDao;

/**
 * Default implementation of {@link ClientService}.
 */
@Component
public class DefaultClientService implements ClientService {

    private final ClientDao clientDao;
    private final Validator validator;

    public DefaultClientService(ClientDao clientDao, Validator validator) {
        this.clientDao = clientDao;
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listPeople() {
        return clientDao.listPeople();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readPerson(Integer id) {
        return clientDao.readPerson(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Client client) {
        return clientDao.createPerson(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Client client) {
    	clientDao.updatePerson(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer id) {
    	clientDao.deletePerson(id);
    }

    @Override
    public List<String> validatePerson(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        List<String> errors = new ArrayList<String>(violations.size());
        for (ConstraintViolation<Client> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
