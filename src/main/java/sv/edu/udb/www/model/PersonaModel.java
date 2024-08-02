package sv.edu.udb.www.model;

import sv.edu.udb.www.beans.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaModel {
    private final String SQL_SELECT_PERSON
            = "SELECT * FROM personas";

    // Listar Todas las personas
    public List<Persona> listPersonas(){
        List<Persona> personas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PERSON);
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

}
