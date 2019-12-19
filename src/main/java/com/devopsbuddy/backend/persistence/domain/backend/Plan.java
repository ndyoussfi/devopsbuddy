package com.devopsbuddy.backend.persistence.domain.backend;

import com.devopsbuddy.enums.PlansEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity // spring jpa will take the class name and its table name
public class Plan implements Serializable{


    /** The Serial Version UID for Serializable Classes*/
    private static final long serialVersionUID = 1L;

    // to indicate to jpa entity manager that id is the primary key, use @Id annotation
    // required for entities to be place on primary key
    @Id
    private int id;

    private String name;

    // important with entities: create empty default constructor
    // not having this will cause an error
    /**  Default constructor*/
    public Plan(){

    }

    public Plan(PlansEnum plansEnum){
        this.id = plansEnum.getId();
        this.name = plansEnum.getPlanName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id &&
                Objects.equals(name, plan.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
