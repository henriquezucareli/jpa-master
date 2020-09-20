package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;


@Table(name = "tea_team")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private String teamId;
    @Column(name = "tea_city")
    private String teamCity;
    @Column(name = "tea_name")
    private String teamName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "con_name")
    private Conference teamConference;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Conference getTeamConference() {
        return teamConference;
    }

    public void setTeamConference(Conference teamConference) {
        this.teamConference = teamConference;
    }
    
}