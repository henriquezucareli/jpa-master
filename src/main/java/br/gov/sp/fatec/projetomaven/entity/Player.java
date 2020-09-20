package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@Table(name = "pla_player")
@Entity
@PrimaryKeyJoinColumn(name = "pla_id")
public class Player extends Roster{
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pla_position")
    private PositionEnum playerPosition;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pla_historic", 
        joinColumns = @JoinColumn(name = "pla_id"),
        inverseJoinColumns = @JoinColumn(name = "tea_id")
    )
    private List<Team> historic;

    public PositionEnum getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PositionEnum playerPosition) {
        this.playerPosition = playerPosition;
    }

	public List<Team> getHistoric() {
		return historic;
	}

	public void setHistoric(List<Team> historic) {
		this.historic = historic;
	}

    

}