package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Staff;
import br.gov.sp.fatec.projetomaven.entity.Team;

import java.util.Date;

public interface StaffDao {
    
    //Cadastrar staff
    public Staff registerStaff (String firstName, String lastName, String function, float salary, Date born, Team team);
    //Salvar staff
    public Staff saveStaff (Staff staff);
    //Salvar staff sem commit
    public Staff saveStaffWithoutCommit (Staff staff);

}