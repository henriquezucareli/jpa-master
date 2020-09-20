package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Table(name = "sta_staff")
@Entity
@PrimaryKeyJoinColumn(name = "ros_id")
public class Staff extends Roster{
    
    @Column(name = "sta_function")
    private String staFunction;

}