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
    private long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "address", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private Address address;

    public Person(){
        super();
    }


    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
