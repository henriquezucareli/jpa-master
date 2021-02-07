package br.gov.sp.fatec.projetomaven.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Staff;
import br.gov.sp.fatec.projetomaven.entity.Team;

public class StaffDaoJpa implements StaffDao {

    private EntityManager em;

    public StaffDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public StaffDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Staff registerStaff(String firstName, String lastName, String function, float salary, Calendar born, Team team) {
        Staff staff = new Staff();
        staff.setRosterFirstName(firstName);
        staff.setRosterLastName(lastName);
        staff.setStaffFunction(function);
        staff.setRosterSalary(salary);
        staff.setRosterBorn(born);
        staff.setStaffTeam(team);
        return saveStaff(staff);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        try {
            em.getTransaction().begin();
            saveStaffWithoutCommit(staff);
            em.getTransaction().commit();
            return staff;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Staff!", e);
        }
    }

    @Override
    public Staff saveStaffWithoutCommit(Staff staff) {
        if (staff.getId() == null) {
            em.persist(staff);
        } else {
            em.merge(staff);
        }
        return staff;
    }

    @Override
    public List<Staff> searchStaffsByTeam(Team team) {
        String jpql = "select s from Staff s INNER JOIN s.staffTeam t where t.id = :id";
        TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);
        query.setParameter("id", team.getId());
        return query.getResultList();
    }

    @Override
    public List<Staff> getAll() {
        String jpql = "select s from Staff s";
        TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);
        return query.getResultList();
    }

    @Override
    public Staff getOne(Long id) {
        String jpql = "select s from Staff s where s.id = :id";
        TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);
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