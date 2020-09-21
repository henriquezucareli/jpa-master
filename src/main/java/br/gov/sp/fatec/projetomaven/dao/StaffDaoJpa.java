package br.gov.sp.fatec.projetomaven.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.gov.sp.fatec.projetomaven.entity.Staff;
import br.gov.sp.fatec.projetomaven.entity.Team;

public class StaffDaoJpa implements StaffDao {

    private EntityManager em;

    @Override
    public Staff registerStaff(String firstName, String lastName, String function, float salary, Date born, Team team) {
        Staff staff = new Staff();
        staff.setRosterFirstName(firstName);
        staff.setRosterLastName(lastName);
        staff.setStaffFunction(function);
        staff.setRosterSalary(salary);
        staff.setRosterBorn(born);
        staff.setRosterTeam(team);
        return saveStaff(staff);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        try {
            em.getTransaction().begin();
            saveStaffWithoutCommit(staff);
            em.getTransaction().commit();
            return staff;
        }
        catch(PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Staff!", e);
        }
    }

    @Override
    public Staff saveStaffWithoutCommit(Staff staff) {
        if(staff.getId() == null) {
            em.persist(staff);
        }
        else {
            em.merge(staff);
        }
        return staff;
    }
    
}