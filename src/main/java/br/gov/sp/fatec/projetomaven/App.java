package br.gov.sp.fatec.projetomaven;

import java.util.Calendar;

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
        playerDao.registerPlayer("Tyler", "Herro", PositionEnum.SHOOTING_GUARD, 3640200f, getByDate(2000,1,20), mia);
        playerDao.registerPlayer("Jimmy", "Butler", PositionEnum.SMALL_FORWARD, 32740000f, getByDate(1989,9,14), mia);
        playerDao.registerPlayer("Edrice", "Adebayo", PositionEnum.CENTER, 3454080f, getByDate(1997,7,18), mia);
        playerDao.registerPlayer("Dejounte", "Murray", PositionEnum.POINT_GUARD, 2321735f, getByDate(1996,7,19), sas);
        
        //Registra Staff
        staffDao.registerStaff("Gregg", "Popovich", "Head Coach", 8000000f, getByDate(1949,1,28), sas);
        staffDao.registerStaff("Tim", "Duncan", "Assistant Coach", 1000000f, getByDate(1976,4,25), sas);
        staffDao.registerStaff("Tom", "Thibodeau", "Head Coach", 0f, getByDate(1958,1,17), phx);


        //Busca jogador por time
        System.out.println(playerDao.searchPlayersByTeam (mia));

        //Busca Equipe por Conferência
        System.out.println(teamDao.searchTeamsByConference (ConferenceEnum.WEST));

        //Busca Staff por time
        System.out.println(staffDao.searchStaffsByTeam(sas));


    }

    private static Calendar getByDate(int year, int month, int day) {
        Calendar born = Calendar.getInstance();
        born.set(year, month, day);
        return born;
    }
}