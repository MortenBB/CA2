/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Morten
 */
@Entity
@Table(name = "CITYINFO")
@NamedQueries({
    @NamedQuery(name = "Cityinfo.findAll", query = "SELECT c FROM Cityinfo c")
    , @NamedQuery(name = "Cityinfo.findByZip", query = "SELECT c FROM Cityinfo c WHERE c.zip = :zip")
    , @NamedQuery(name = "Cityinfo.findByCity", query = "SELECT c FROM Cityinfo c WHERE c.city = :city")})
public class Cityinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZIP")
    private Integer zip;
    @Size(max = 30)
    @Column(name = "CITY")
    private String city;    
    @OneToMany(mappedBy = "cityinfo")
    private List<Address> addresses = new ArrayList();

    public Cityinfo() {
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddresses(Address a) {
        addresses.add(a);
    }

    public Cityinfo(Integer zip) {
        this.zip = zip;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zip != null ? zip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cityinfo)) {
            return false;
        }
        Cityinfo other = (Cityinfo) object;
        if ((this.zip == null && other.zip != null) || (this.zip != null && !this.zip.equals(other.zip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Cityinfo[ zip=" + zip + " ]";
    }
    
}
