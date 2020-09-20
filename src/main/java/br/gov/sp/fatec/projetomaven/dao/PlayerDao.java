package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

import java.util.Date;

public interface PlayerDao {
    
    //Cadastrar jogador
    public Player registerPlayer (String firstName, String lastName, PositionEnum position, float salary, Date born, Team team);
    //Salvar jogador
    public Player savePlayer (Player player);
    //Salvar jogador sem commit
    public Player savePlayerWithoutCommit (Player player);



}