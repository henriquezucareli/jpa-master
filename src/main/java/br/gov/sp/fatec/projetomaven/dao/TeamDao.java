package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;

public interface TeamDao {
    
    //Cadastrar equipe
    public Team registerTeam (String teamCity, String teamName, ConferenceEnum teamConference);
    //Salvar equipe
    public Team saveTeam (Team team);
    //Salvar jogador sem commit
    public Team saveTeamWithoutCommit (Team team);


}