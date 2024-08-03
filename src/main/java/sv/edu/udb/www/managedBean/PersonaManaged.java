package sv.edu.udb.www.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;
import jakarta.persistence.EntityManager;
import sv.edu.udb.www.beans.Persona;
import sv.edu.udb.www.model.PersonaModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.udb.www.beans.Persona;
import sv.edu.udb.www.model.PersonaModel;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;


@ManagedBean(name = "personaManaged")
@SessionScoped

public class PersonaManaged implements Serializable {
    private static final Logger logger = Logger.getLogger(PersonaManaged.class.getName());
    public Persona persona;
    private Persona selectedPersona;
    private PersonaModel personaModel = new PersonaModel();

    // Getter y setter para persona
    public Persona getPersona() {
        return persona;
    }
    public PersonaManaged(){
        this.persona = new Persona();
    }
        public List<Persona> listPersonas() throws IOException {
        return personaModel.listPersonas();
    }

    public String editarPersona(Persona persona, String pagina) {
        this.persona = persona;
        return pagina;
    }


    public String editarCambios() {
        if (!validarFecha()) {
            return null;
        }else{
            personaModel.update(persona);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Se ha actualizó con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "index?faces-redirect=true";
        }
    }

    public String guardarCambios() {
        personaModel.insert(persona);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Se ha registró la Persona");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index?faces-redirect=true";
    }

        //Creamos metodo para eliminar personas
    public void eliminarPersona() {
        try {
            personaModel.delete(persona);
            // agregamos un mensaje para confirmar la eliminación
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Persona eliminada exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la persona"));
            e.printStackTrace();
        }
    }

    public String retorna(){
        return "index";
    }

    private boolean validarFecha(){
        FacesContext context = FacesContext.getCurrentInstance();
        boolean valid = true;
        java.util.Date fechaActual = new java.util.Date();
        if (persona.getFecha_nac().after(fechaActual)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fecha de nacimiento no puede ser mayor a la fecha actual.");
            context.addMessage(null, msg);
            valid = false;
        }

        return valid;
    }
}
