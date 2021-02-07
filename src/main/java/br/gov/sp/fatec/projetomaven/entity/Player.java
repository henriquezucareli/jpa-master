package br.gov.sp.fatec.projetomaven.entity;

import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;
import com.google.gson.*;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Table(name = "pla_player")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pla_position")
@AttributeOverride(name = "id", column = @Column(name = "pla_id"))
public abstract class Player extends Identification {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

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
    private Set<Team> historic = new HashSet<>();

    public abstract PositionEnum getPosition();


    public Set<Team> getHistoric() {
        return historic;
    }

    public void setHistoric(Set<Team> historic) {
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

    public void addToHistoric(Team team) {
        historic.add(team);
        team.getHistoric().add(this);
    }

    public String toJson() {
        return "{" +
                "\"id\":" + this.getId().toString() + "," +
                "\"first_name\":\"" + this.getFirstName() + "\"," +
                "\"last_name\":\"" + this.getLastName() + "\"," +
                "\"salary\":\"" + this.getSalary() + "\"," +
                "\"born\":\"" + DATE_FORMAT.format(this.getBorn().getTime()) + "\"," +
                "\"team_id\":\"" + this.getPlayerTeam().getId() + "\"," +
                "\"pla_position\":\"" + this.getPosition().name() + "\"" +
                "}";
    }

}