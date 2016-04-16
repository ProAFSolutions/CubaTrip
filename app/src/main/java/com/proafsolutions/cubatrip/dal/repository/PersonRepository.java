package com.proafsolutions.cubatrip.dal.repository;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.proafsolutions.cubatrip.domain.model.Address;
import com.proafsolutions.cubatrip.domain.model.Person;

import java.util.List;

/**
 * Created by alex on 4/15/2016.
 */
public class PersonRepository {

    public static List<Person> getAll(Address address) {
        // This is how you execute a query
        return new Select()
                .from(Person.class)
                .where("address = ?", address.addressId)
                .execute();
    }

    public static void save(Person person) {
        person.save();
    }

    public static void delete(int personId) {
        new Delete().from(Person.class).where("personId = ?", personId).execute();
    }
}
