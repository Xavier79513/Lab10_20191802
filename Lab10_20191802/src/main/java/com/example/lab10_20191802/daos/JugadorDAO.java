package com.example.lab10_20191802.daos;

import com.example.lab10_20191802.beans.Jugador;
import com.example.lab10_20191802.beans.Seleccion;
import java.sql.*;
import java.util.ArrayList;

public class JugadorDAO extends ConnectDAO {

    public ArrayList<Jugador> listarJugadores() {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        String sql = "SELECT j.idJugador, j.nombre, j.edad, j.posicion, j.club, s.nombre FROM jugador j JOIN seleccion s ON j.sn_idSeleccion = s.idSeleccion";

        try (Connection conn = getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Jugador jugador = new Jugador();
                Seleccion seleccion = new Seleccion();
                jugador.setIdJugador(rs.getInt("j.idJugador"));
                jugador.setNombre(rs.getString("j.nombre"));
                jugador.setEdad(rs.getInt("j.edad"));
                jugador.setPosicion(rs.getString("j.posicion"));
                jugador.setClub(rs.getString("j.club"));
                seleccion.setNombre(rs.getString("s.nombre"));
                jugador.setSn_idSeleccion(seleccion);
                listaJugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return listaJugadores;
    }

    public ArrayList<Seleccion> listarSeleccion() {
        ArrayList<Seleccion> listaSeleccion = new ArrayList<>();
        String sql = "SELECT * FROM seleccion";

        try (Connection conn = getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Seleccion seleccion = new Seleccion();
                seleccion.setIdSeleccion(rs.getInt("idSeleccion"));
                seleccion.setNombre(rs.getString("nombre"));
                listaSeleccion.add(seleccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return listaSeleccion;
    }

    public Seleccion buscarSeleccionPorID(String id) {
        Seleccion seleccion = null;
        String sql = "SELECT * FROM seleccion WHERE idSeleccion = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    seleccion = new Seleccion();
                    seleccion.setIdSeleccion(rs.getInt("idSeleccion"));
                    seleccion.setNombre(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return seleccion;
    }

    public void crearJugador(Jugador jugador) {
        String sql = "INSERT INTO jugador (nombre, edad, posicion, club, sn_idSeleccion) VALUES (?,?,?,?,?)";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jugador.getNombre());
            pstmt.setInt(2, jugador.getEdad());
            pstmt.setString(3, jugador.getPosicion());
            pstmt.setString(4, jugador.getClub());
            pstmt.setInt(5, jugador.getSn_idSeleccion().getIdSeleccion());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Jugador buscarJugadorPorID(String id) {
        Jugador jugador = null;
        String sql = "SELECT j.idJugador, j.nombre, j.edad, j.posicion, j.club, s.nombre " +
                "FROM jugador j JOIN seleccion s ON j.sn_idSeleccion = s.idSeleccion WHERE j.idJugador = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    jugador = new Jugador();
                    Seleccion seleccion = new Seleccion();
                    jugador.setIdJugador(rs.getInt("j.idJugador"));
                    jugador.setNombre(rs.getString("j.nombre"));
                    jugador.setEdad(rs.getInt("j.edad"));
                    jugador.setPosicion(rs.getString("j.posicion"));
                    jugador.setClub(rs.getString("j.club"));
                    seleccion.setNombre(rs.getString("s.nombre"));
                    jugador.setSn_idSeleccion(seleccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return jugador;
    }

    public void borrarJugador(String id) {
        String sql = "DELETE FROM jugador WHERE idJugador = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}