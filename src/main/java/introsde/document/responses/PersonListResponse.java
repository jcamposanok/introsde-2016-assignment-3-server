package introsde.document.responses;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;


public class PersonListResponse {

    private List<PersonResponse> people;

    @XmlElement(name = "person")
    public List<PersonResponse> getPeople() {
        return people;
    }

    public void setPeople(List<PersonResponse> people) {
        this.people = people;
    }

    public PersonListResponse(List<PersonResponse> people) {
        setPeople(people);
    }

    public PersonListResponse() {
        this(new ArrayList<>());
    }

}
