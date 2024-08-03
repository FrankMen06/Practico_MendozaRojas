package sv.edu.udb.www.model;

import sv.edu.udb.www.beans.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaModel {
    private final String SQL_SELECT = "SELECT * FROM personas";
    private final String SQL_SELECT_PERSON = "SELECT * FROM personas WHERE id = ?";
    private final String SQL_UPDATE_PERSON = "UPDATE personas SET Identificacion = ?, Nombre = ?, Direccion = ?, Email = ?, FechaNacimiento = ?, Telefono = ?, Sexo = ? WHERE Id = ?";

    // Listar Todas las personas
    public List<Persona> listPersonas(){
        List<Persona> personas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while(rs.next()){
                Persona person = new Persona();
                person.setId(rs.getInt("Id"));
                person.setIdentificacion(rs.getString("Identificacion"));
                person.setNombres(rs.getString("Nombre"));
                person.setDireccion(rs.getString("Direccion"));
                person.setEmail(rs.getString("Email"));
                person.setFecha_nac(rs.getDate("FechaNacimiento"));
                person.setTelefono(rs.getString("Telefono"));
                person.setSexo(rs.getString("Sexo"));

                personas.add(person);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return personas;
    }

    // Buscar persona para hacer update
    public Persona findPersonaById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona person = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PERSON);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Persona();
                person.setId(rs.getInt("Id"));
                person.setIdentificacion(rs.getString("Identificacion"));
                person.setNombres(rs.getString("Nombre"));
                person.setDireccion(rs.getString("Direccion"));
                person.setEmail(rs.getString("Email"));
                person.setFecha_nac(rs.getDate("FechaNacimiento"));
                person.setTelefono(rs.getString("Telefono"));
                person.setSexo(rs.getString("Sexo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return person;
    }

    // Update del registro
    public void update(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_PERSON);
            stmt.setString(1, persona.getIdentificacion());
            stmt.setString(2, persona.getNombres());
            stmt.setString(3, persona.getDireccion());
            stmt.setString(4, persona.getEmail());
            stmt.setDate(5, new Date(persona.getFecha_nac().getTime()));
            stmt.setString(6, persona.getTelefono());
            stmt.setString(7, persona.getSexo());
            stmt.setInt(8, persona.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
