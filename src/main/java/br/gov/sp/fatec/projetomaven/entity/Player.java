package br.gov.sp.fatec.projetomaven.entity;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@Table(name = "pla_player")
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pla_position")
public abstract class Player extends Roster{
    
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

    

}