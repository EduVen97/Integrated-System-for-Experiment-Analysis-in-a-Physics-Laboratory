/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.pkg3.pkg0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "temperatura", catalog = "tesis", schema = "")
@NamedQueries({
    @NamedQuery(name = "Temperatura_1.findAll", query = "SELECT t FROM Temperatura_1 t")
    , @NamedQuery(name = "Temperatura_1.findByIdTemperatura", query = "SELECT t FROM Temperatura_1 t WHERE t.idTemperatura = :idTemperatura")
    , @NamedQuery(name = "Temperatura_1.findByTempY", query = "SELECT t FROM Temperatura_1 t WHERE t.tempY = :tempY")
    , @NamedQuery(name = "Temperatura_1.findByTipoY", query = "SELECT t FROM Temperatura_1 t WHERE t.tipoY = :tipoY")
    , @NamedQuery(name = "Temperatura_1.findByTiempoX", query = "SELECT t FROM Temperatura_1 t WHERE t.tiempoX = :tiempoX")
    , @NamedQuery(name = "Temperatura_1.findByTipoX", query = "SELECT t FROM Temperatura_1 t WHERE t.tipoX = :tipoX")
    , @NamedQuery(name = "Temperatura_1.findByExperimentoidExperimento", query = "SELECT t FROM Temperatura_1 t WHERE t.experimentoidExperimento = :experimentoidExperimento")})
public class Temperatura_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Temperatura")
    private Integer idTemperatura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Temp_Y")
    private BigDecimal tempY;
    @Column(name = "Tipo_Y")
    private String tipoY;
    @Basic(optional = false)
    @Column(name = "Tiempo_X")
    private BigDecimal tiempoX;
    @Basic(optional = false)
    @Column(name = "Tipo_X")
    private String tipoX;
    @Basic(optional = false)
    @Column(name = "Experimento_id_Experimento")
    private int experimentoidExperimento;

    public Temperatura_1() {
    }

    public Temperatura_1(Integer idTemperatura) {
        this.idTemperatura = idTemperatura;
    }

    public Temperatura_1(Integer idTemperatura, BigDecimal tempY, BigDecimal tiempoX, String tipoX, int experimentoidExperimento) {
        this.idTemperatura = idTemperatura;
        this.tempY = tempY;
        this.tiempoX = tiempoX;
        this.tipoX = tipoX;
        this.experimentoidExperimento = experimentoidExperimento;
    }

    public Integer getIdTemperatura() {
        return idTemperatura;
    }

    public void setIdTemperatura(Integer idTemperatura) {
        Integer oldIdTemperatura = this.idTemperatura;
        this.idTemperatura = idTemperatura;
        changeSupport.firePropertyChange("idTemperatura", oldIdTemperatura, idTemperatura);
    }

    public BigDecimal getTempY() {
        return tempY;
    }

    public void setTempY(BigDecimal tempY) {
        BigDecimal oldTempY = this.tempY;
        this.tempY = tempY;
        changeSupport.firePropertyChange("tempY", oldTempY, tempY);
    }

    public String getTipoY() {
        return tipoY;
    }

    public void setTipoY(String tipoY) {
        String oldTipoY = this.tipoY;
        this.tipoY = tipoY;
        changeSupport.firePropertyChange("tipoY", oldTipoY, tipoY);
    }

    public BigDecimal getTiempoX() {
        return tiempoX;
    }

    public void setTiempoX(BigDecimal tiempoX) {
        BigDecimal oldTiempoX = this.tiempoX;
        this.tiempoX = tiempoX;
        changeSupport.firePropertyChange("tiempoX", oldTiempoX, tiempoX);
    }

    public String getTipoX() {
        return tipoX;
    }

    public void setTipoX(String tipoX) {
        String oldTipoX = this.tipoX;
        this.tipoX = tipoX;
        changeSupport.firePropertyChange("tipoX", oldTipoX, tipoX);
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
        hash += (idTemperatura != null ? idTemperatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temperatura_1)) {
            return false;
        }
        Temperatura_1 other = (Temperatura_1) object;
        if ((this.idTemperatura == null && other.idTemperatura != null) || (this.idTemperatura != null && !this.idTemperatura.equals(other.idTemperatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tesis.pkg3.pkg0.Temperatura_1[ idTemperatura=" + idTemperatura + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
