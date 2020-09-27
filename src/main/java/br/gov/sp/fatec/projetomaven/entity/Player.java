package br.gov.sp.fatec.projetomaven.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@Table(name = "pla_player")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pla_position")
@AttributeOverride(name = "id", column = @Column(name = "pla_id"))
public abstract class Player extends Identification{
    
    @Column(name = "pla_first_name")
    private String firstName;
    @Column(name = "pla_last_name")
    private String lastName;
    @Column(name = "pla_salary")
    private float salary;
    @Column(name = "pla_born")
    @Temporal(TemporalType.DATE)
    private Calendar born;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tea_id")
    private Team playerTeam;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pla_historic", 
        joinColumns = @JoinColumn(name = "pla_id"),
        inverseJoinColumns = @JoinColumn(name = "tea_id")
    )
    private List<Team> historic;

    public abstract PositionEnum getPosition();


	public List<Team> getHistoric() {
		return historic;
	}

	public void setHistoric(List<Team> historic) {
		this.historic = historic;
	}

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Calendar getBorn() {
        return born;
    }

    public void setBorn(Calendar born) {
        this.born = born;
    }



    

}