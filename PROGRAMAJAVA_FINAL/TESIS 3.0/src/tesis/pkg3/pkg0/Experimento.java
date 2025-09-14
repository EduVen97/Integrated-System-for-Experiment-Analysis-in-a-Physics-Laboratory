/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.pkg3.pkg0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author eduardot
 */
@Entity
@Table(name = "experimento", catalog = "tesis", schema = "")
@NamedQueries({
    @NamedQuery(name = "Experimento.findAll", query = "SELECT e FROM Experimento e")
    , @NamedQuery(name = "Experimento.findByIdExperimento", query = "SELECT e FROM Experimento e WHERE e.idExperimento = :idExperimento")
    , @NamedQuery(name = "Experimento.findByNombre", query = "SELECT e FROM Experimento e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Experimento.findByTipoexp", query = "SELECT e FROM Experimento e WHERE e.tipoexp = :tipoexp")
    , @NamedQuery(name = "Experimento.findByFechaExp", query = "SELECT e FROM Experimento e WHERE e.fechaExp = :fechaExp")
    , @NamedQuery(name = "Experimento.findByVarXName", query = "SELECT e FROM Experimento e WHERE e.varXName = :varXName")
    , @NamedQuery(name = "Experimento.findByVarYName", query = "SELECT e FROM Experimento e WHERE e.varYName = :varYName")})
public class Experimento implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Experimento")
    private Integer idExperimento;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Tipo_exp")
    private String tipoexp;
    @Basic(optional = false)
    @Column(name = "fecha_exp")
    @Temporal(TemporalType.DATE)
    private Date fechaExp;
    @Basic(optional = false)
    @Column(name = "VarXName")
    private String varXName;
    @Basic(optional = false)
    @Column(name = "VarYName")
    private String varYName;

    public Experimento() {
    }

    public Experimento(Integer idExperimento) {
        this.idExperimento = idExperimento;
    }

    public Experimento(Integer idExperimento, String nombre, String tipoexp, Date fechaExp, String varXName, String varYName) {
        this.idExperimento = idExperimento;
        this.nombre = nombre;
        this.tipoexp = tipoexp;
        this.fechaExp = fechaExp;
        this.varXName = varXName;
        this.varYName = varYName;
    }

    public Integer getIdExperimento() {
        return idExperimento;
    }

    public void setIdExperimento(Integer idExperimento) {
        Integer oldIdExperimento = this.idExperimento;
        this.idExperimento = idExperimento;
        changeSupport.firePropertyChange("idExperimento", oldIdExperimento, idExperimento);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getTipoexp() {
        return tipoexp;
    }

    public void setTipoexp(String tipoexp) {
        String oldTipoexp = this.tipoexp;
        this.tipoexp = tipoexp;
        changeSupport.firePropertyChange("tipoexp", oldTipoexp, tipoexp);
    }

    public Date getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(Date fechaExp) {
        Date oldFechaExp = this.fechaExp;
        this.fechaExp = fechaExp;
        changeSupport.firePropertyChange("fechaExp", oldFechaExp, fechaExp);
    }

    public String getVarXName() {
        return varXName;
    }

    public void setVarXName(String varXName) {
        String oldVarXName = this.varXName;
        this.varXName = varXName;
        changeSupport.firePropertyChange("varXName", oldVarXName, varXName);
    }

    public String getVarYName() {
        return varYName;
    }

    public void setVarYName(String varYName) {
        String oldVarYName = this.varYName;
        this.varYName = varYName;
        changeSupport.firePropertyChange("varYName", oldVarYName, varYName);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperimento != null ? idExperimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experimento)) {
            return false;
        }
        Experimento other = (Experimento) object;
        if ((this.idExperimento == null && other.idExperimento != null) || (this.idExperimento != null && !this.idExperimento.equals(other.idExperimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tesis.pkg3.pkg0.Experimento[ idExperimento=" + idExperimento + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
