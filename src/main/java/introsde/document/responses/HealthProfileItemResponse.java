package introsde.document.responses;

import introsde.document.models.HealthProfileItem;
import introsde.document.models.MeasureType;
import introsde.document.models.Person;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;


public class HealthProfileItemResponse {

    private int healthProfileId;
    private Person person;
    private MeasureType measureType;
    private Float value;
    private Date created;
    private boolean isValid;
    private String measureName;
    private String valueType;


    // Constructors

    public HealthProfileItemResponse(HealthProfileItem i) {
        healthProfileId = i.getHealthProfileId();
        person = i.getPerson();
        measureType = i.getMeasureType();
        value = i.getValue();
        valueType = i.getValue().getClass().getName();
        created = i.getCreated();
        isValid = i.isValid();
    }
    public HealthProfileItemResponse() {

    }


    // Getters

    @XmlElement(name = "mid")
    public int getHealthProfileId() {
        return healthProfileId;
    }

    @XmlElement(name = "measureType")
    public String getMeasureName() {
        if (measureType != null) {
            return measureType.getName();
        }
        return measureName;
    }

    @XmlElement(name = "measureValue")
    public Float getValue() {
        return value;
    }

    @XmlElement(name = "measureValueType")
    public String getValueType() {
        return valueType;
    }

    @XmlElement(name = "dateRegistered")
    public Date getCreated() {
        return created;
    }

    public boolean isValid() {
        return isValid;
    }


    // Setters

    public void setHealthProfileId(int healthProfileId) {
        this.healthProfileId = healthProfileId;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public void setMeasureType(MeasureType measureType) {
        this.measureType = measureType;
    }
    public void setValue(Float value) {
        this.value = value;
    }
    public void setValueType(String valueType) { this.valueType = valueType; }
    public void setCreated(Date created) {
        this.created = created;
    }
    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }
}
