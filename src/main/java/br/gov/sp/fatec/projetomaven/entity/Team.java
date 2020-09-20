package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Table(name = "tea_team")
@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private String teamId;
    @Column(name = "tea_city")
    private String teamCity;
    @Column(name = "tea_name")
    private String teamName;
    @Enumerated(EnumType.STRING)
    @Column(name = "tea_conference")
    private ConferenceEnum teamConference;
    @OneToMany(mappedBy = "rosterTeam")
    private List<Player> players;
    @OneToMany(mappedBy = "rosterTeam")
    private List<Staff> staffs;
    @ManyToMany(mappedBy = "historic")
    private List<Player> historic;

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

    public ConferenceEnum getTeamConference() {
        return teamConference;
    }

    public void setTeamConference(ConferenceEnum teamConference) {
        this.teamConference = teamConference;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }
    
}