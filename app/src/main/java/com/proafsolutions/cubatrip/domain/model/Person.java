package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by alex on 4/15/2016.
 */
@Table(name = "person")
public class Person extends Model {

    @Column(name = "personId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long personId;

    @Column(name = "name")
    public String name;

    @Column(name = "address", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Address address;

    public Person(){
        super();
    }


    // Used to return items from another table based on the foreign key
    public List<Person> getPersons() {
        return this.getMany(Person.class, "address");
    }

}
