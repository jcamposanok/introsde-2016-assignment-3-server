package introsde.document.responses;

import introsde.document.models.HealthProfileItem;
import introsde.document.models.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PersonResponse {
    private int personId;
    private String lastname;
    private String firstname;
    private Date birthdate;
    private List<HealthProfileItemResponse> healthProfile;

    // Constructors

    public PersonResponse(Person p) {
        personId = p.getPersonId();
        lastname = p.getLastname();
        firstname = p.getFirstname();
        birthdate = p.getBirthdate();
        setHealthProfile(p.getHealthProfile());
    }
    public PersonResponse() {
    }

    // Getters

    public int getPersonId() {
        return personId;
    }
    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public Date getBirthdate() {
        return birthdate;
    }

    @XmlElementWrapper(name = "healthHistory")
    public List<HealthProfileItemResponse> getHealthProfile() {
        return healthProfile;
    }

    @XmlElementWrapper(name = "currentHealth")
    @XmlElement(name = "measure")
    public List<HealthProfileItemResponse> getCurrentHealthProfile() {
        List<HealthProfileItemResponse> currentHealthProfile = new ArrayList<>();
        if (healthProfile != null && healthProfile.size() > 0) {
            for (HealthProfileItemResponse hp : healthProfile) {
                if (hp.isValid()) {
                    currentHealthProfile.add(hp);
                }
            }
        }
        return currentHealthProfile;
    }


    // Setters

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public void setHealthProfile(List<HealthProfileItem> healthProfileItemList) {
        healthProfile = new ArrayList<>();
        if (healthProfileItemList != null && healthProfileItemList.size() > 0) {
            for (HealthProfileItem i : healthProfileItemList) {
                healthProfile.add(new HealthProfileItemResponse(i));
            }
        }
    }
}
