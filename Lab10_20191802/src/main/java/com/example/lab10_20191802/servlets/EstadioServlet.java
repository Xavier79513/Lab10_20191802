package com.example.lab10_20191802.servlets;

import com.example.lab10_20191802.beans.Estadio;
import com.example.lab10_20191802.daos.EstadioDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EstadioServlet", value = "/EstadioServlet")
public class EstadioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "listaEstadios" : request.getParameter("action");
        EstadioDAO estadioDao = new EstadioDAO();

        switch (action) {
            case "listaEstadios":
                request.setAttribute("listaEstadios", estadioDao.listarEstadios());
                request.getRequestDispatcher("WEB-INF/ListaEstadios.jsp").forward(request, response);
                break;
            case "nuevoEstadio":
                request.getRequestDispatcher("WEB-INF/NuevoEstadio.jsp").forward(request, response);
                break;
            case "eliminarEstadio":
                String idDelete = request.getParameter("id");
                estadioDao.borrarEstadio(idDelete);
                response.sendRedirect(request.getContextPath() + "/EstadioServlet?action=listaEstadios");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/EstadioServlet?action=listaEstadios");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        EstadioDAO estadioDao = new EstadioDAO();
        String action = request.getParameter("action");

        switch (action) {
            case "crearEstadio":
                Estadio estadio = new Estadio();
                estadio.setNombre(request.getParameter("Nombre"));
                estadio.setProvincia(request.getParameter("Provincia"));
                estadio.setClub(request.getParameter("Club"));

                estadioDao.crearEstadio(estadio);

                response.sendRedirect(request.getContextPath() + "/EstadioServlet?action=listaEstadios");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/EstadioServlet?action=listaEstadios");
        }
    }
}