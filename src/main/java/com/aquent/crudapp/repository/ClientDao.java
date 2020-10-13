package com.aquent.crudapp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aquent.crudapp.model.Client;

/**
 * Operations on the "client" table.
 */
@Repository
public interface ClientDao {

    /**
     * Retrieves all of the person records.
     *
     * @return list of person records
     */
    List<Client> listPeople();

    /**
     * Creates a new person record.
     *
     * @param person the values to save
     * @return the new person ID
     */
    Integer createPerson(Client person);

    /**
     * Retrieves a person record by ID.
     *
     * @param id the person ID
     * @return the person record
     */
    Client readPerson(Integer id);

    /**
     * Updates an existing person record.
     *
     * @param person the new values to save
     */
    void updatePerson(Client person);

    /**
     * Deletes a person record by ID.
     *
     * @param id the person ID
     */
    void deletePerson(Integer id);
}
