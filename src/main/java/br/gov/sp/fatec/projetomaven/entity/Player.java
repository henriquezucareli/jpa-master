package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "pla_player")
@Entity
@PrimaryKeyJoinColumn(name = "pla_id")
public class Player extends Roster{
    
    @Column(name = "pla_position")
    private String playerPosition;

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    

}