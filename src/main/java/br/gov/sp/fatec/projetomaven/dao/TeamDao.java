package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Conference;
import br.gov.sp.fatec.projetomaven.entity.Team;

public interface TeamDao {
    
    //Cadastrar equipe
    public Team registerTeam (String teamId, String teamCity, String teamName, Conference teamConference);
    //Salvar equipe
    public Team saveTeam (Team team);
    //Salvar jogador sem commit
    public Team saveTeamWithoutCommit (Team team);


}