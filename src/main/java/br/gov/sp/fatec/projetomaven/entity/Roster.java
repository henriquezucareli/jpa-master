package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;


@Table(name = "ros_roster")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Roster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ros_id")
    private long rosterId;
    @Column(name = "ros_first_name")
    private String rosterFirstName;
    @Column(name = "ros_last_name")
    private String rosterLastName;
    @Column(name = "ros_salary")
    private float rosterSalary;
    @Column(name = "ros_born")
    private Date rosterBorn;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tea_id")
    private Team rosterTeam;


}