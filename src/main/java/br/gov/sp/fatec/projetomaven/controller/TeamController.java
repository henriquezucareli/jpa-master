package br.gov.sp.fatec.projetomaven.controller;

import br.gov.sp.fatec.projetomaven.dao.TeamDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Team;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "TeamController", urlPatterns = "/api/team/*")
public class TeamController extends HttpServlet {

    private final TeamDaoJpa teamDaoJpa;

    private final Gson gson;

    public TeamController() {
        this.teamDaoJpa = new TeamDaoJpa();
        this.gson = new GsonBuilder().registerTypeAdapter(Team.class, new Team.TeamDeserializer()).create();
    }

    private void sendAsJson(HttpServletResponse response, Team team) throws IOException {
        response.setContentType("application/json");
        String res = team.toJson();

        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    private void sendAsJson(HttpServletResponse response, List<Team> teams) throws IOException {
        PrintWriter out = response.getWriter();
        String sb = "[" + teams.stream().map(Team::toJson).collect(Collectors.joining(",")) + "]";
        out.print(sb);
        out.flush();
    }

    // Get teams
    // GET /api/team/
    // GET /api/team/{id}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Team> teams = teamDaoJpa.getAll();
            sendAsJson(response, teams);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2 || !NumberUtils.isCreatable(splits[1])) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long teamId = NumberUtils.toLong(splits[1]);
        Team team = teamDaoJpa.getOne(teamId);
        if (team == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendAsJson(response, team);
    }

    // Adds new model in DB
    // GET /api/team/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String payload = buffer.toString();
            Team team = gson.fromJson(payload, Team.class);
            teamDaoJpa.saveTeam(team);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // Updates a model in DB
    // PUT/JavaViewer/models/id
    protected void doPut(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2 || !NumberUtils.isCreatable(splits[1])) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long teamId = NumberUtils.toLong(splits[1]);
        if (teamDaoJpa.getOne(teamId) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String payload = buffer.toString();
        Team team = gson.fromJson(payload, Team.class);
        team.setId(teamId);
        teamDaoJpa.saveTeam(team);

        sendAsJson(response, team);
    }

    // Deletes a model in DB
    // DELETE/JavaViewer/models/id
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2 || !NumberUtils.isCreatable(splits[1])) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long teamId = NumberUtils.toLong(splits[1]);
        if (teamDaoJpa.getOne(teamId) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //TeamDao.remove(teamId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}