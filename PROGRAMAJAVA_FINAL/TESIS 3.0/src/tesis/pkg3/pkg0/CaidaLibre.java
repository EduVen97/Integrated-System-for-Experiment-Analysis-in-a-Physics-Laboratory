/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.pkg3.pkg0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author eduardot
 */
@Entity
@Table(name = "caida_libre", catalog = "tesis", schema = "")
@NamedQueries({
    @NamedQuery(name = "CaidaLibre.findAll", query = "SELECT c FROM CaidaLibre c")
    , @NamedQuery(name = "CaidaLibre.findByIdCaidaLibre", query = "SELECT c FROM CaidaLibre c WHERE c.idCaidaLibre = :idCaidaLibre")
    , @NamedQuery(name = "CaidaLibre.findByTiempoX", query = "SELECT c FROM CaidaLibre c WHERE c.tiempoX = :tiempoX")
    , @NamedQuery(name = "CaidaLibre.findByTipoVarX", query = "SELECT c FROM CaidaLibre c WHERE c.tipoVarX = :tipoVarX")
    , @NamedQuery(name = "CaidaLibre.findByExpAlturay", query = "SELECT c FROM CaidaLibre c WHERE c.expAlturay = :expAlturay")
    , @NamedQuery(name = "CaidaLibre.findByTipoVarY", query = "SELECT c FROM CaidaLibre c WHERE c.tipoVarY = :tipoVarY")
    , @NamedQuery(name = "CaidaLibre.findByExperimentoidExperimento", query = "SELECT c FROM CaidaLibre c WHERE c.experimentoidExperimento = :experimentoidExperimento")})
public class CaidaLibre implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaida_Libre")
    private Integer idCaidaLibre;
    @Basic(optional = false)
    @Column(name = "tiempo_X")
    private float tiempoX;
    @Basic(optional = false)
    @Column(name = "Tipo_Var_X")
    private String tipoVarX;
    @Basic(optional = false)
    @Column(name = "ExpAltura_y")
    private float expAlturay;
    @Basic(optional = false)
    @Column(name = "Tipo_Var_Y")
    private String tipoVarY;
    @Basic(optional = false)
    @Column(name = "Experimento_id_Experimento")
    private int experimentoidExperimento;

    public CaidaLibre() {
    }

    public CaidaLibre(Integer idCaidaLibre) {
        this.idCaidaLibre = idCaidaLibre;
    }

    public CaidaLibre(Integer idCaidaLibre, float tiempoX, String tipoVarX, float expAlturay, String tipoVarY, int experimentoidExperimento) {
        this.idCaidaLibre = idCaidaLibre;
        this.tiempoX = tiempoX;
        this.tipoVarX = tipoVarX;
        this.expAlturay = expAlturay;
        this.tipoVarY = tipoVarY;
        this.experimentoidExperimento = experimentoidExperimento;
    }

    public Integer getIdCaidaLibre() {
        return idCaidaLibre;
    }

    public void setIdCaidaLibre(Integer idCaidaLibre) {
        Integer oldIdCaidaLibre = this.idCaidaLibre;
        this.idCaidaLibre = idCaidaLibre;
        changeSupport.firePropertyChange("idCaidaLibre", oldIdCaidaLibre, idCaidaLibre);
    }

    public float getTiempoX() {
        return tiempoX;
    }

    public void setTiempoX(float tiempoX) {
        float oldTiempoX = this.tiempoX;
        this.tiempoX = tiempoX;
        changeSupport.firePropertyChange("tiempoX", oldTiempoX, tiempoX);
    }

    public String getTipoVarX() {
        return tipoVarX;
    }

    public void setTipoVarX(String tipoVarX) {
        String oldTipoVarX = this.tipoVarX;
        this.tipoVarX = tipoVarX;
        changeSupport.firePropertyChange("tipoVarX", oldTipoVarX, tipoVarX);
    }

    public float getExpAlturay() {
        return expAlturay;
    }

    public void setExpAlturay(float expAlturay) {
        float oldExpAlturay = this.expAlturay;
        this.expAlturay = expAlturay;
        changeSupport.firePropertyChange("expAlturay", oldExpAlturay, expAlturay);
    }

    public String getTipoVarY() {
        return tipoVarY;
    }

    public void setTipoVarY(String tipoVarY) {
        String oldTipoVarY = this.tipoVarY;
        this.tipoVarY = tipoVarY;
        changeSupport.firePropertyChange("tipoVarY", oldTipoVarY, tipoVarY);
    }

    public int getExperimentoidExperimento() {
        return experimentoidExperimento;
    }

    public void setExperimentoidExperimento(int experimentoidExperimento) {
        int oldExperimentoidExperimento = this.experimentoidExperimento;
        this.experimentoidExperimento = experimentoidExperimento;
        changeSupport.firePropertyChange("experimentoidExperimento", oldExperimentoidExperimento, experimentoidExperimento);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaidaLibre != null ? idCaidaLibre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaidaLibre)) {
            return false;
        }
        CaidaLibre other = (CaidaLibre) object;
        if ((this.idCaidaLibre == null && other.idCaidaLibre != null) || (this.idCaidaLibre != null && !this.idCaidaLibre.equals(other.idCaidaLibre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tesis.pkg3.pkg0.CaidaLibre[ idCaidaLibre=" + idCaidaLibre + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
