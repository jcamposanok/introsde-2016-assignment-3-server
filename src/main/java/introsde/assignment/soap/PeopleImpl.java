package introsde.assignment.soap;

import introsde.document.models.HealthProfileItem;
import introsde.document.models.Person;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "introsde.assignment.soap.People", serviceName = "PeopleService")
public class PeopleImpl implements People {
    @Override
    public List<Person> readPersonList() {
        return Person.getAll();
    }

    @Override
    public Person readPerson(long id) {
        return Person.getById((int) id);
    }

    public Person updatePerson(Person p) {
        return null;
    }

    public Person createPerson(Person p) {
        return null;
    }

    public int deletePerson(long id) {
        return 0;
    }

    public List<HealthProfileItem> readPersonHistory(long id, String measureType) {
        return null;
    }
}
