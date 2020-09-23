package br.gov.sp.fatec.projetomaven.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Team;
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
    public Player registerPlayer(String firstName, String lastName, PositionEnum position, float salary, Date born,
            Team team) {
        Player player = createPlayer(position);
        player.setRosterFirstName(firstName);
        player.setRosterLastName(lastName);
        player.setRosterSalary(salary);
        player.setRosterBorn(born);
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
        String jpql = "select p from Player p INNER JOIN p.rosterTeam t where t.id = :id";
        TypedQuery<Player> query = em.createQuery(jpql, Player.class);
        query.setParameter("id", team.getId());
        return query.getResultList();
    }

}