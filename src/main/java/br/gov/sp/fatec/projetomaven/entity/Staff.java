package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Table(name = "sta_staff")
@Entity
@PrimaryKeyJoinColumn(name = "sta_id")
public class Staff extends Roster{
    
    @Column(name = "sta_function")
    private String staffFunction;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tea_id")
    private Team staffTeam;

    public String getStaFunction() {
        return staffFunction;
    }

    public void setStaffFunction(String staffFunction) {
        this.staffFunction = staffFunction;
    }

    public Team getStaffTeam() {
        return staffTeam;
    }

    public void setStaffTeam(Team staffTeam) {
        this.staffTeam = staffTeam;
    }

    

}