package com.aquent.crudapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.model.Client;

/**
 * Spring JDBC implementation of {@link PersonDao}.
 */
@Component
public class JdbcClientDao implements ClientDao {

    private static final String SQL_LIST_PEOPLE = "SELECT * FROM client ORDER BY company_name, client_id";
    private static final String SQL_READ_PERSON = "SELECT * FROM client WHERE client_id = :clientId";
    private static final String SQL_DELETE_PERSON = "DELETE FROM client WHERE client_id = :clientId";
    private static final String SQL_UPDATE_PERSON = "UPDATE client SET (company_name, website_url, phone_number, street_address, city, state, zip_code)"
                                                  + " = (:companyName, :websiteUrl, :phoneNumber, :streetAddress, :city, :state, :zipCode)"
                                                  + " WHERE client_id = :clientId";
    private static final String SQL_CREATE_PERSON = "INSERT INTO client (company_name, website_url, phone_number, street_address, city, state, zip_code)"
                                                  + " VALUES (:companyName, :websiteUrl, :phoneNumber, :streetAddress, :city, :state, :zipCode)";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcClientDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listPeople() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_PEOPLE, new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readPerson(Integer clientId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_PERSON, Collections.singletonMap("clientId", clientId), new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer clientId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PERSON, Collections.singletonMap("clientId", clientId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Client client) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON, new BeanPropertySqlParameterSource(client));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON, new BeanPropertySqlParameterSource(client), keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Row mapper for client records.
     */
    private static final class PersonRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Client client = new Client();
            client.setClientId(rs.getInt("client_id"));
            client.setCompanyName(rs.getString("company_name"));
            client.setPhoneNumber(rs.getString("phone_number"));
            client.setWebsiteUrl(rs.getString("website_url"));
            client.setStreetAddress(rs.getString("street_address"));
            client.setCity(rs.getString("city"));
            client.setState(rs.getString("state"));
            client.setZipCode(rs.getString("zip_code"));
            client.setContacts(null);
            return client;
        }
    }
}
