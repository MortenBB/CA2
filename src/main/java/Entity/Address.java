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
import javax.persistence.ManyToOne;
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
@Table(name = "ADDRESS")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")
    , @NamedQuery(name = "Address.findByZip", query = "SELECT a FROM Address a WHERE a.cityinfo.zip = :cityinfo")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STREET")
    private String street;
    @ManyToOne
    private Cityinfo cityinfo;
    @OneToMany(mappedBy = "address")
    private List<Person> persons = new ArrayList();

    public Address() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public Cityinfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(Cityinfo cityinfo) {
        this.cityinfo = cityinfo;
    }

    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (street != null ? street.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.street == null && other.street != null) || (this.street != null && !this.street.equals(other.street))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Address[ street=" + street + " ]";
    }

}
