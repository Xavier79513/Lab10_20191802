package com.example.lab10_20191802.daos;

import com.example.lab10_20191802.beans.Estadio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstadioDAO extends ConnectDAO {

    public ArrayList<Estadio> listarEstadios() {
        ArrayList<Estadio> listaEstadios = new ArrayList<>();

        String sql = "SELECT * FROM estadio";

        try (Connection conn = getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt("idEstadio"));
                estadio.setNombre(rs.getString("nombre"));
                estadio.setProvincia(rs.getString("provincia"));
                estadio.setClub(rs.getString("club"));
                listaEstadios.add(estadio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEstadios;
    }

    public void crearEstadio(Estadio estadio) {
        String sql = "INSERT INTO estadio (nombre, provincia, club) VALUES (?, ?, ?)";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estadio.getNombre());
            pstmt.setString(2, estadio.getProvincia());
            pstmt.setString(3, estadio.getClub());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarEstadio(String id) {
        String sql = "DELETE FROM estadio WHERE idEstadio = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}