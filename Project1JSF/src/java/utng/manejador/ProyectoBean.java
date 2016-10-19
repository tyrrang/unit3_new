package utng.manejador;

/**
 *
 * @author tyrrang
 */
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utng.datos.ObraDAO;
import utng.datos.ProyectoDAO;
import utng.datos.UsuarioDAO;
import utng.modelo.Obra;

import utng.modelo.Proyecto;
import utng.modelo.Usuario;

@ManagedBean(name = "proyectoBean")
@SessionScoped

public class ProyectoBean implements Serializable {
   private List<Proyecto> proyectos;
    private Proyecto proyecto;
    private List<Obra> obras;
    private List<Usuario> usuarios;

    /**
     * Creates a new instance of UsuarioBean
     */
    public ProyectoBean() {
        proyecto = new Proyecto();
        proyecto.setObra(new Obra());
        proyecto.setUsuario(new Usuario());
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }    

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
    public String listar(){
        ProyectoDAO dao = new ProyectoDAO();
        try {
            proyectos = dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Proyectos";
    }
    
    public String eliminar(){
        ProyectoDAO dao = new ProyectoDAO();
        try {
            dao.delete(proyecto);
            proyectos = dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Eliminar";
    }
    
    public String iniciar(){
        proyecto = new Proyecto();
        proyecto.setUsuario(new Usuario());
        proyecto.setObra(new Obra());
        
        try {
            usuarios= new UsuarioDAO().getAll();
            obras= new ObraDAO().getAll();
            
             
        } catch (Exception e) {
                     e.printStackTrace();
        }
        return "Iniciar";
    }
    
    public String guardar(){
        ProyectoDAO dao = new ProyectoDAO();
        try {
            if(proyecto.getIdProyecto()!=0){
                dao.update(proyecto);
            } else {
                dao.insert(proyecto);
            }
            proyectos=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Guardar";
    }
    public String cancelar(){
        return "cancelar";
    }
    public String editar(Proyecto proyecto){
        this.proyecto = proyecto;
        return "Editar";
    }
    
    
}
