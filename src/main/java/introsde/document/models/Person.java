package introsde.document.models;

import introsde.document.dao.HealthDao;
import introsde.document.util.DateParser;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="Person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p ORDER BY p.personId")
@XmlRootElement
public class Person implements Serializable {

    @Id
    @GeneratedValue(generator = "sqlite_person")
    @TableGenerator(name = "sqlite_person", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Person", allocationSize = 1)
    @Column(name = "personId")
    private int personId;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HealthProfileItem> healthProfile;

    public Person() {
    }

    // Getters and setters
    @XmlElement(name = "pid")
    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @XmlElementWrapper(name = "healthProfile")
    @XmlElement(name = "measure")
    public List<HealthProfileItem> getHealthProfile() {
        return healthProfile;
    }
    public void setHealthProfile(List<HealthProfileItem> healthProfile) {
        this.healthProfile = healthProfile;
    }

    public static List<Person> getAll() {
        List<Person> list = new ArrayList<>();
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            list = em.createNamedQuery("Person.findAll").getResultList();
            em.close();
        }
        return list;
    }

    public static Person getById(int personId) {
        Person p = new Person();
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            p = em.find(Person.class, personId);
            em.close();
        }
        return p;
    }

    public static Person savePerson(Person p) {
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(p);
            tx.commit();
            em.close();
        }
        return p;
    }

    public static Person updatePerson(Person p) {
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            p = em.merge(p);
            tx.commit();
            em.close();
        }
        return p;
    }

    public static void removePerson(Person p) {
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            p = em.merge(p);
            em.remove(p);
            tx.commit();
            em.close();
        }
    }
}
