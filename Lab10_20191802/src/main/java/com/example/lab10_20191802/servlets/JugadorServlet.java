package com.example.lab10_20191802.servlets;

import com.example.lab10_20191802.beans.Jugador;
import com.example.lab10_20191802.beans.Seleccion;
import com.example.lab10_20191802.daos.JugadorDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "JugadorServlet", value = "/JugadorServlet")
public class JugadorServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "listaJugador" : request.getParameter("action");
        JugadorDAO jugadorDao = new JugadorDAO();

        switch (action){
            case "listaJugador":
                request.setAttribute("listaJugador", jugadorDao.listarJugadores());
                request.getRequestDispatcher("WEB-INF/ListaJugadores.jsp").forward(request, response);
                break;
            case "nuevoJugador":
                ArrayList<Seleccion> listaSeleccion = jugadorDao.listarSeleccion();
                request.setAttribute("listaSeleccion", listaSeleccion);
                request.getRequestDispatcher("WEB-INF/NuevoJugador.jsp").forward(request, response);
                break;
            case "eliminarJugador":
                String idDelete = request.getParameter("id");
                System.out.println("idDelete: " + idDelete);
                Jugador jugadorDelete = jugadorDao.buscarJugadorPorID(idDelete);
                System.out.println("jugadorDelete: " + jugadorDelete);

                if(jugadorDelete!= null){
                    jugadorDao.borrarJugador(idDelete);
                }
                response.sendRedirect(request.getContextPath() + "/JugadorServlet?action=listaJugador");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        JugadorDAO jugadorDao = new JugadorDAO();
        String action = request.getParameter("action");

        switch (action){
            case "crearJugador":
                Jugador jugador = new Jugador();
                jugador.setNombre(request.getParameter("Nombre"));
                jugador.setEdad(Integer.parseInt(request.getParameter("Edad")));
                jugador.setPosicion(request.getParameter("Posicion"));
                jugador.setClub(request.getParameter("Club"));
                Seleccion seleccion = jugadorDao.buscarSeleccionPorID(request.getParameter("Seleccion"));
                jugador.setSn_idSeleccion(seleccion);

                jugadorDao.crearJugador(jugador);

                response.sendRedirect(request.getContextPath() + "/JugadorServlet?action=listaJugadores");
                break;
        }
    }
}
