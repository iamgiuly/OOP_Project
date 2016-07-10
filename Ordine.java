/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaccia;

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
 * @author Serena
 */
@Entity
@Table(name = "ordine", catalog = "progettoale", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ordine.findAll", query = "SELECT o FROM Ordine o"),
    @NamedQuery(name = "Ordine.findByIdOrdine", query = "SELECT o FROM Ordine o WHERE o.idOrdine = :idOrdine"),
    @NamedQuery(name = "Ordine.findByCliente", query = "SELECT o FROM Ordine o WHERE o.cliente = :cliente"),
    @NamedQuery(name = "Ordine.findByDataOrdine", query = "SELECT o FROM Ordine o WHERE o.dataOrdine = :dataOrdine"),
    @NamedQuery(name = "Ordine.findByIDmaglia", query = "SELECT o FROM Ordine o WHERE o.iDmaglia = :iDmaglia"),
    @NamedQuery(name = "Ordine.findByIDpers", query = "SELECT o FROM Ordine o WHERE o.iDpers = :iDpers"),
    @NamedQuery(name = "Ordine.findByQuantit\u00e0", query = "SELECT o FROM Ordine o WHERE o.quantit\u00e0 = :quantit\u00e0"),
    @NamedQuery(name = "Ordine.findByTaglia", query = "SELECT o FROM Ordine o WHERE o.taglia = :taglia"),
    @NamedQuery(name = "Ordine.findByPrezzoFinale", query = "SELECT o FROM Ordine o WHERE o.prezzoFinale = :prezzoFinale"),
    @NamedQuery(name = "Ordine.findByIDborse", query = "SELECT o FROM Ordine o WHERE o.iDborse = :iDborse"),
    @NamedQuery(name = "Ordine.findByIDfelpa", query = "SELECT o FROM Ordine o WHERE o.iDfelpa = :iDfelpa"),
    @NamedQuery(name = "Ordine.findByIDgiubbotto", query = "SELECT o FROM Ordine o WHERE o.iDgiubbotto = :iDgiubbotto"),
    @NamedQuery(name = "Ordine.findByIDpantalone", query = "SELECT o FROM Ordine o WHERE o.iDpantalone = :iDpantalone"),
    @NamedQuery(name = "Ordine.findByIDpubblicit\u00e0", query = "SELECT o FROM Ordine o WHERE o.iDpubblicit\u00e0 = :iDpubblicit\u00e0"),
    @NamedQuery(name = "Ordine.findByStato", query = "SELECT o FROM Ordine o WHERE o.stato = :stato")})
public class Ordine implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdine")
    private Integer idOrdine;
    @Basic(optional = false)
    @Column(name = "Cliente")
    private String cliente;
    @Basic(optional = false)
    @Column(name = "DataOrdine")
    @Temporal(TemporalType.DATE)
    private Date dataOrdine;
    @Column(name = "IDmaglia")
    private Integer iDmaglia;
    @Basic(optional = false)
    @Column(name = "IDpers")
    private int iDpers;
    @Basic(optional = false)
    @Column(name = "Quantit\u00e0")
    private int quantità;
    @Basic(optional = false)
    @Column(name = "Taglia")
    private String taglia;
    @Basic(optional = false)
    @Column(name = "PrezzoFinale")
    private float prezzoFinale;
    @Column(name = "IDborse")
    private Integer iDborse;
    @Column(name = "IDfelpa")
    private Integer iDfelpa;
    @Column(name = "IDgiubbotto")
    private Integer iDgiubbotto;
    @Column(name = "IDpantalone")
    private Integer iDpantalone;
    @Column(name = "IDpubblicit\u00e0")
    private Integer iDpubblicità;
    @Basic(optional = false)
    @Column(name = "Stato")
    private String stato;

    public Ordine() {
    }

    public Ordine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Ordine(Integer idOrdine, String cliente, Date dataOrdine, int iDpers, int quantità, String taglia, float prezzoFinale, String stato) {
        this.idOrdine = idOrdine;
        this.cliente = cliente;
        this.dataOrdine = dataOrdine;
        this.iDpers = iDpers;
        this.quantità = quantità;
        this.taglia = taglia;
        this.prezzoFinale = prezzoFinale;
        this.stato = stato;
    }

    public Integer getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(Integer idOrdine) {
        Integer oldIdOrdine = this.idOrdine;
        this.idOrdine = idOrdine;
        changeSupport.firePropertyChange("idOrdine", oldIdOrdine, idOrdine);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        String oldCliente = this.cliente;
        this.cliente = cliente;
        changeSupport.firePropertyChange("cliente", oldCliente, cliente);
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        Date oldDataOrdine = this.dataOrdine;
        this.dataOrdine = dataOrdine;
        changeSupport.firePropertyChange("dataOrdine", oldDataOrdine, dataOrdine);
    }

    public Integer getIDmaglia() {
        return iDmaglia;
    }

    public void setIDmaglia(Integer iDmaglia) {
        Integer oldIDmaglia = this.iDmaglia;
        this.iDmaglia = iDmaglia;
        changeSupport.firePropertyChange("IDmaglia", oldIDmaglia, iDmaglia);
    }

    public int getIDpers() {
        return iDpers;
    }

    public void setIDpers(int iDpers) {
        int oldIDpers = this.iDpers;
        this.iDpers = iDpers;
        changeSupport.firePropertyChange("IDpers", oldIDpers, iDpers);
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        int oldQuantità = this.quantità;
        this.quantità = quantità;
        changeSupport.firePropertyChange("quantit\u00e0", oldQuantità, quantità);
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        String oldTaglia = this.taglia;
        this.taglia = taglia;
        changeSupport.firePropertyChange("taglia", oldTaglia, taglia);
    }

    public float getPrezzoFinale() {
        return prezzoFinale;
    }

    public void setPrezzoFinale(float prezzoFinale) {
        float oldPrezzoFinale = this.prezzoFinale;
        this.prezzoFinale = prezzoFinale;
        changeSupport.firePropertyChange("prezzoFinale", oldPrezzoFinale, prezzoFinale);
    }

    public Integer getIDborse() {
        return iDborse;
    }

    public void setIDborse(Integer iDborse) {
        Integer oldIDborse = this.iDborse;
        this.iDborse = iDborse;
        changeSupport.firePropertyChange("IDborse", oldIDborse, iDborse);
    }

    public Integer getIDfelpa() {
        return iDfelpa;
    }

    public void setIDfelpa(Integer iDfelpa) {
        Integer oldIDfelpa = this.iDfelpa;
        this.iDfelpa = iDfelpa;
        changeSupport.firePropertyChange("IDfelpa", oldIDfelpa, iDfelpa);
    }

    public Integer getIDgiubbotto() {
        return iDgiubbotto;
    }

    public void setIDgiubbotto(Integer iDgiubbotto) {
        Integer oldIDgiubbotto = this.iDgiubbotto;
        this.iDgiubbotto = iDgiubbotto;
        changeSupport.firePropertyChange("IDgiubbotto", oldIDgiubbotto, iDgiubbotto);
    }

    public Integer getIDpantalone() {
        return iDpantalone;
    }

    public void setIDpantalone(Integer iDpantalone) {
        Integer oldIDpantalone = this.iDpantalone;
        this.iDpantalone = iDpantalone;
        changeSupport.firePropertyChange("IDpantalone", oldIDpantalone, iDpantalone);
    }

    public Integer getIDpubblicità() {
        return iDpubblicità;
    }

    public void setIDpubblicità(Integer iDpubblicità) {
        Integer oldIDpubblicità = this.iDpubblicità;
        this.iDpubblicità = iDpubblicità;
        changeSupport.firePropertyChange("IDpubblicit\u00e0", oldIDpubblicità, iDpubblicità);
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        String oldStato = this.stato;
        this.stato = stato;
        changeSupport.firePropertyChange("stato", oldStato, stato);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdine != null ? idOrdine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordine)) {
            return false;
        }
        Ordine other = (Ordine) object;
        if ((this.idOrdine == null && other.idOrdine != null) || (this.idOrdine != null && !this.idOrdine.equals(other.idOrdine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interfaccia.Ordine[ idOrdine=" + idOrdine + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
