package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "pla_player")
@Entity
@PrimaryKeyJoinColumn(name = "ros_id")
public class Player extends Roster{
    
    @Column(name = "pla_position")
    private String playerPosition;

}