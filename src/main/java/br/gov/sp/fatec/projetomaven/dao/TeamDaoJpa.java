package br.gov.sp.fatec.projetomaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;

public class TeamDaoJpa implements TeamDao {

    private EntityManager em;

    @Override
    public Team registerTeam(String teamCity, String teamName, ConferenceEnum teamConference) {
        Team team = new Team();
        team.setTeamCity(teamCity);
        team.setTeamName(teamName);
        team.setTeamConference(teamConference);
        return saveTeam(team);
    }

    @Override
    public Team saveTeam(Team team) {
        try {
            em.getTransaction().begin();
            saveTeamWithoutCommit(team);
            em.getTransaction().commit();
            return team;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Equipe!", e);
        }
    }

    @Override
    public Team saveTeamWithoutCommit(Team team) {
        if (team.getId() == null) {
            em.persist(team);
        } else {
            em.merge(team);
        }
        return team;
    }

    @Override
    public List<Team> searchTeamsByConference(ConferenceEnum conference) {
        String jpql = "select t from Team t where t.teamConference = :conference";
        TypedQuery<Team> query = em.createQuery(jpql, Team.class);
        query.setParameter("conference", conference);
        return query.getResultList();
    }

    

    

}