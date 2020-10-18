package br.gov.sp.fatec.projetomaven.dao;

import java.util.Calendar;
import java.util.List;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

public interface PlayerDao {
    
    //Cadastrar jogador
    public Player registerPlayer (String firstName, String lastName, PositionEnum position, float salary, Calendar born, Team team);
    //Salvar jogador
    public Player savePlayer (Player player);
    //Salvar jogador sem commit
    public Player savePlayerWithoutCommit (Player player);
    //Buscar jogador por time
    public List<Player> searchPlayersByTeam (Team team);
    //Busca jogador por time e posição
    public List<Player> searchPlayersByTeamAndPosition(Team team, PositionEnum position);
    //Busca jogador por conferência e salário
    public List<Player> searchPlayerByConferenceAndSalary(ConferenceEnum conference, float salary);



}