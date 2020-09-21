package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Table(name = "sta_staff")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Staff extends Roster{
    
    @Column(name = "sta_function")
    private String staffFunction;

    public String getStaFunction() {
        return staffFunction;
    }

    public void setStaffFunction(String staffFunction) {
        this.staffFunction = staffFunction;
    }

    

}