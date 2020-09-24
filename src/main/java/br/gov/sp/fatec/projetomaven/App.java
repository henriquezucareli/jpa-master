package br.gov.sp.fatec.projetomaven;

import java.util.Date;

import br.gov.sp.fatec.projetomaven.dao.PlayerDao;
import br.gov.sp.fatec.projetomaven.dao.PlayerDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.StaffDao;
import br.gov.sp.fatec.projetomaven.dao.StaffDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.TeamDao;
import br.gov.sp.fatec.projetomaven.dao.TeamDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;


public class App 
{
    public static void main( String[] args )
    {
        TeamDao teamDao = new TeamDaoJpa();
        PlayerDao playerDao = new PlayerDaoJpa();
        StaffDao staffDao = new StaffDaoJpa();


        //Registra Times
        Team mia = teamDao.registerTeam("Miami", "Heat", ConferenceEnum.EAST);
        Team sas = teamDao.registerTeam("San Antonio", "Spurs", ConferenceEnum.WEST);
        Team phx = teamDao.registerTeam("Phoenix", "Suns", ConferenceEnum.WEST);

        //Registra Jogadores
        playerDao.registerPlayer("Tyler", "Herro", PositionEnum.SHOOTING_GUARD, 3640200f, new Date(20/1/2000), mia);
        playerDao.registerPlayer("Jimmy", "Butler", PositionEnum.SMALL_FORWARD, 32740000f, new Date(14/9/1989), mia);
        playerDao.registerPlayer("Edrice", "Adebayo", PositionEnum.CENTER, 3454080f, new Date(18/7/1997), mia);
        playerDao.registerPlayer("Dejounte", "Murray", PositionEnum.POINT_GUARD, 2321735f, new Date(19/7/1996), sas);
        
        //Registra Staff
        staffDao.registerStaff("Gregg", "Popovich", "Head Coach", 8000000f, new Date(28/1/1949), sas);
        staffDao.registerStaff("Tim", "Duncan", "Assistant Coach", 1000000f, new Date(25/4/1976), sas);
        staffDao.registerStaff("Tom", "Thibodeau", "Head Coach", 0f, new Date(17/1/1958), phx);


        //Busca jogador por time
        System.out.println(playerDao.searchPlayersByTeam (mia));

        //Busca Equipe por Conferência
        System.out.println(teamDao.searchTeamsByConference (ConferenceEnum.WEST));

        //Busca Staff por time
        System.out.println(staffDao.searchStaffsByTeam(sas));

    }
}