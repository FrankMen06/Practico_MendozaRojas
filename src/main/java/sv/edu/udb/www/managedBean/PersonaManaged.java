package sv.edu.udb.www.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sv.edu.udb.www.beans.Persona;
import sv.edu.udb.www.model.PersonaModel;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean(name = "personaManaged")
@SessionScoped

public class PersonaManaged {
    private Persona persona;
    private PersonaModel personaModel = new PersonaModel();

    public PersonaManaged(){this.persona = new Persona();}

    public List<Persona> listPersonas() throws IOException {
        return personaModel.listPersonas();
    }
}
