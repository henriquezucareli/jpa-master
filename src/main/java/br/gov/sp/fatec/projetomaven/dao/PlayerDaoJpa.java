package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import java.util.Date;

public class PlayerDaoJpa implements PlayerDao {
    
    private EntityManager em;

    public PlayerDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public PlayerDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Player registerPlayer(String firstName, String lastName, PositionEnum position, float salary, Date born, Team team) {
        Player player = new Player();
        player.setRosterFirstName(firstName);
        player.setRosterLastName(lastName);
        player.setPlayerPosition(position);
        player.setRosterSalary(salary);
        player.setRosterBorn(born);
        player.setRosterTeam(team);
        return savePlayer(player);
    }

    @Override
    public Player savePlayer(Player player) {
        try {
            em.getTransaction().begin();
            savePlayerWithoutCommit(player);
            em.getTransaction().commit();
            return player;
        }
        catch(PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Jogador!", e);
        }
    }

    @Override
    public Player savePlayerWithoutCommit(Player player) {
        if(player.getRosterId() == null) {
            em.persist(player);
        }
        else {
            em.merge(player);
        }
        return player;
    }

}