package introsde.document.models;

import introsde.document.dao.HealthDao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Measure")
@NamedQueries({
        @NamedQuery(name="Measure.findAll", query="SELECT m FROM MeasureType m ORDER BY m.measureId"),
        @NamedQuery(name = "Measure.findByName", query = "SELECT m FROM MeasureType m WHERE m.name = :name")
})
@XmlRootElement
public class MeasureType implements Serializable {

    @Id
    @GeneratedValue(generator = "sqlite_measure")
    @TableGenerator(name = "sqlite_measure", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MeasureType", allocationSize = 1)
    @Column(name = "measureId")
    private int measureId;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "measureType", fetch = FetchType.EAGER)
    private List<HealthProfileItem> measureHistory;

    @XmlTransient
    public int getMeasureId() {
        return measureId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static List<MeasureType> getAll() {
        List<MeasureType> list = new ArrayList<>();
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            list = em.createNamedQuery("Measure.findAll").getResultList();
            em.close();
        }
        return list;
    }

    public static MeasureType getByName(String name) {
        MeasureType m = new MeasureType();
        EntityManager em = HealthDao.createEntityManager();
        if (em != null) {
            List<MeasureType> measureTypeList = em.createNamedQuery("Measure.findByName")
                    .setParameter("name", name).getResultList();
            if (measureTypeList != null && measureTypeList.size() > 0) {
                m = measureTypeList.get(0);
            }
            em.close();
        }
        return m;
    }
}
