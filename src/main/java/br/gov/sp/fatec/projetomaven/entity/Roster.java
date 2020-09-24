package br.gov.sp.fatec.projetomaven.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Table(name = "ros_roster")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Roster extends Identification {
    
    @Column(name = "ros_first_name")
    private String rosterFirstName;
    @Column(name = "ros_last_name")
    private String rosterLastName;
    @Column(name = "ros_salary")
    private float rosterSalary;
    @Column(name = "ros_born")
    @Temporal(TemporalType.DATE)
    private Calendar rosterBorn;

    public String getRosterFirstName() {
        return rosterFirstName;
    }

    public void setRosterFirstName(String rosterFirstName) {
        this.rosterFirstName = rosterFirstName;
    }

    public String getRosterLastName() {
        return rosterLastName;
    }

    public void setRosterLastName(String rosterLastName) {
        this.rosterLastName = rosterLastName;
    }

    public float getRosterSalary() {
        return rosterSalary;
    }

    public void setRosterSalary(float rosterSalary) {
        this.rosterSalary = rosterSalary;
    }

    public Calendar getRosterBorn() {
        return rosterBorn;
    }

    public void setRosterBorn(Calendar rosterBorn) {
        this.rosterBorn = rosterBorn;
    }

}