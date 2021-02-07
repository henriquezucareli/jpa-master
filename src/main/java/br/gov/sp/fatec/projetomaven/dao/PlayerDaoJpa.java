package br.gov.sp.fatec.projetomaven.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;
import br.gov.sp.fatec.projetomaven.entity.player.Center;
import br.gov.sp.fatec.projetomaven.entity.player.PointGuard;
import br.gov.sp.fatec.projetomaven.entity.player.PowerForward;
import br.gov.sp.fatec.projetomaven.entity.player.ShootingGuard;
import br.gov.sp.fatec.projetomaven.entity.player.SmallForward;

public class PlayerDaoJpa implements PlayerDao {

    private EntityManager em;

    public PlayerDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public PlayerDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Player registerPlayer(String firstName, String lastName, PositionEnum position, float salary, Calendar born,
            Team team) {
        Player player = createPlayer(position);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setSalary(salary);
        player.setBorn(born);
        player.setPlayerTeam(team);
        player.addToHistoric(team);
        return savePlayer(player);
    }

    @Override
    public Player savePlayer(Player player) {
        try {
            em.getTransaction().begin();
            savePlayerWithoutCommit(player);
            em.getTransaction().commit();
            return player;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Jogador!", e);
        }
    }

    @Override
    public Player savePlayerWithoutCommit(Player player) {
        if (player.getId() == null) {
            em.persist(player);
        } else {
            em.merge(player);
        }
        return player;
    }

    private Player createPlayer(PositionEnum position) {
        switch (position) {
            case CENTER:
                return new Center();
            case POWER_FORWARD:
                return new PowerForward();
            case SMALL_FORWARD:
                return new SmallForward();
            case SHOOTING_GUARD:
                return new ShootingGuard();
            case POINT_GUARD:
                return new PointGuard();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Player> searchPlayersByTeam(Team team) {
        String jpql = "select p from Player p INNER JOIN p.playerTeam t where t.id = :id";
        TypedQuery<Player> query = em.createQuery(jpql, Player.class);
        query.setParameter("id", team.getId());
        return query.getResultList();
    }

    @Override
    public List searchPlayersByTeamAndPosition(Team team, PositionEnum position) {
        String jpql = "select p from " + position.getPositionClassName() + " p INNER JOIN p.playerTeam t where t.id = :id";
        TypedQuery<?> query = em.createQuery(jpql, position.getPositionClass());
        query.setParameter("id", team.getId());
        return query.getResultList();
    }

    @Override
    public List<Player> searchPlayerByConferenceAndSalary(ConferenceEnum conference, float salary) {
        String jpql = "select p from Player p INNER JOIN p.playerTeam t where t.teamConference = :conference and p.salary >= :salary";
        TypedQuery<Player> query = em.createQuery(jpql, Player.class);
        query.setParameter("conference", conference);
        query.setParameter("salary", salary);
        return query.getResultList();
    }

    @Override
    public List<Player> getAll() {
        String jpql = "select p from Player p";
        TypedQuery<Player> query = em.createQuery(jpql, Player.class);
        return query.getResultList();
    }

    @Override
    public Player getOne(Long id) {
        String jpql = "select p from Player p where p.id = :id";
        TypedQuery<Player> query = em.createQuery(jpql, Player.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {

    }
}