package br.gov.sp.fatec.projetomaven.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;

@Table(name = "tea_team")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "tea_id"))
public class Team extends Identification {
    
    @Column(name = "tea_city")
    private String teamCity;
    @Column(name = "tea_name")
    private String teamName;
    @Enumerated(EnumType.STRING)
    @Column(name = "tea_conference")
    private ConferenceEnum teamConference;
    @OneToMany(mappedBy = "playerTeam")
    private List<Player> players;
    @OneToMany(mappedBy = "staffTeam")
    private List<Staff> staffs;
    @ManyToMany(mappedBy = "historic")
    private List<Player> historic;

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