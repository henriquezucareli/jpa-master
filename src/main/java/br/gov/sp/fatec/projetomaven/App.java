package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.dao.PersistenceManager;
import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.Staff;
import br.gov.sp.fatec.projetomaven.entity.Team;
import br.gov.sp.fatec.projetomaven.entity.enums.ConferenceEnum;

public class App 
{
    public static void main( String[] args )
    {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
     
        // Instancia um novo staff
        Staff staff = new Staff();
        staff.setRosterFirstName("Gregg");
        staff.setRosterLastName("Popovich");
        staff.setRosterBorn(new Date(28/01/1949));
        staff.setRosterSalary(20000);
        staff.setStaffFunction("Coach");
        // Instancia uma nova equipe
        Team team = new Team();
        team.setTeamConference(ConferenceEnum.WEST);
        team.setTeamCity("SAN_ANTONIO");
        team.setTeamName("SPURS");
        team.setId(1L);
        // Vincula staff à equipe
        staff.setRosterTeam(team);




        // Instancia um novo Jogador
        Player player = new player(create);
        trabalho.setTitulo("Trabalho 2 - JPA");
        trabalho.setDataHoraEntrega(new Date());
        trabalho.setLocalArquivo("trabalhos");
        // Associa o professor ao trabalho como avaliador
        trabalho.setAvaliador(professor);
        // Associa o aluno ao trabalho
        trabalho.setAlunos(new HashSet<Aluno>());
        trabalho.getAlunos().add(aluno);

        // Salva aluno, professor e trabalho
        TrabalhoDao trabalhoDao = new TrabalhoDaoJpa(manager);
        trabalhoDao.salvarTrabalho(trabalho);
        // Após o salvamento os IDs estarão preenchidos

        // Limpa o cache para forçar a execução de SELECT
        manager.clear();

        // Busca o aluno pelo ID
        aluno = manager.find(Aluno.class, aluno.getId());
        System.out.println(aluno.getId());
        System.out.println(aluno.getNomeUsuario());
        for(Trabalho trab: aluno.getTrabalhos()) {
            System.out.println(trab.getTitulo());
        }

        // Limpa o cache para forçar a execução de SELECT
        manager.clear();
        
        // Busca o trabalho pelo ID
        trabalho = manager.find(Trabalho.class, trabalho.getId());
        System.out.println(trabalho.getTitulo());
        for(Aluno al: trabalho.getAlunos()) {
            System.out.println(al.getNomeUsuario());
        }

        trabalho.setTitulo("Novo Trabalho 2 - JPA");
        
        try {
            manager.getTransaction().begin();
            manager.merge(trabalho);
            manager.getTransaction().commit();
        }
        catch(PersistenceException e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }

        String queryString = "select t from Trabalho t inner join t.alunos a where a.nomeUsuario like :nome";
        TypedQuery<Trabalho> query = manager.createQuery(queryString, Trabalho.class);
        query.setParameter("nome", "%bd%");

        List<Trabalho> resultados = query.getResultList();
        for(Trabalho trab: resultados) {
            System.out.println("Título: " + trab.getTitulo());
        }

        // Apaga registros (permite re-execução)
        
        try {
            manager.getTransaction().begin();
            professor = trabalho.getAvaliador();
            trabalho.setAvaliador(null);
            Set<Aluno> alunos = trabalho.getAlunos();
            trabalho.setAlunos(null);
            manager.remove(trabalho);
            manager.remove(professor);
            for(Aluno al: alunos) {
                manager.remove(al);
            }
            manager.getTransaction().commit();
        }
        catch(PersistenceException e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }

        manager.close();
    }
}