package br.gov.sp.fatec.projetomaven.dao;

import java.util.Calendar;
import java.util.List;

import br.gov.sp.fatec.projetomaven.entity.Staff;
import br.gov.sp.fatec.projetomaven.entity.Team;

public interface StaffDao {
    
    //Cadastrar staff
    public Staff registerStaff (String firstName, String lastName, String function, float salary, Calendar born, Team team);
    //Salvar staff
    public Staff saveStaff (Staff staff);
    //Salvar staff sem commit
    public Staff saveStaffWithoutCommit (Staff staff);
    //Buscar staff por time
    public List<Staff> searchStaffsByTeam (Team team);

}