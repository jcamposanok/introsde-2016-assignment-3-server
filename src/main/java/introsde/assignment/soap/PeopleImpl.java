package introsde.assignment.soap;

import introsde.document.models.HealthProfileItem;
import introsde.document.models.Measure;
import introsde.document.models.Person;
import introsde.document.responses.*;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "introsde.assignment.soap.People", serviceName = "PeopleService")
public class PeopleImpl implements People {

    // Method 1
    @Override
    public PersonListResponse readPersonList() {
        PersonListResponse response = new PersonListResponse();
        List<PersonResponse> people = new ArrayList<>();
        List<Person> pojoList = Person.getAll();
        for (Person p : pojoList) {
            PersonResponse pr = new PersonResponse(p);
            people.add(pr);
        }
        response.setPeople(people);
        return response;
    }

    // Method 2
    @Override
    public PersonResponse readPerson(int id) {
        Person existing = Person.getById(id);
        if (existing == null) {
            return new PersonResponse();
        }
        return new PersonResponse(existing);
    }

    // Method 3
    @Override
    public PersonResponse updatePerson(Person p) {
        Person existing = Person.getById(p.getPersonId());
        if (existing == null) {
            return new PersonResponse(p);
        }
        return new PersonResponse(Person.updatePerson(p));
    }

    // Method 4
    @Override
    public PersonResponse createPerson(Person p) {
        Person existing = Person.getById(p.getPersonId());
        if (existing == null) {
            return new PersonResponse(Person.savePerson(p));
        }
        return new PersonResponse(p);
    }

    // Method 5
    @Override
    public int deletePerson(int id) {
        Person existing = Person.getById(id);
        if (existing == null) {
            return -1;
        }
        Person.removePerson(existing);
        return id;
    }

    // Method 6
    @Override
    public MeasureHistoryResponse readPersonHistory(int id, String measureType) {
        MeasureHistoryResponse response = new MeasureHistoryResponse();
        List<HealthProfileItemResponse> measureHistory = new ArrayList<>();
        List<HealthProfileItem> pojoList = HealthProfileItem.getAllByPersonAndType(id, measureType);
        for (HealthProfileItem i : pojoList) {
            HealthProfileItemResponse hp = new HealthProfileItemResponse(i);
             measureHistory.add(hp);
        }
        response.setHistory(measureHistory);
        return response;
    }

    // Method 7
    @Override
    public MeasureTypesResponse readMeasureTypes() {
        MeasureTypesResponse response = new MeasureTypesResponse();
        List<Measure> measureList = Measure.getAll();
        if (measureList != null & measureList.size() > 0) {
            response.setMeasureTypes(measureList);
        }
        return response;
    }

    // Method 8
    @Override
    public HealthProfileItemResponse readPersonMeasure(int id, String measureType, int mid) {
        HealthProfileItem existing = HealthProfileItem.getById(mid);
        if (existing == null) {
            return new HealthProfileItemResponse();
        }
        return new HealthProfileItemResponse(existing);
    }

    // TODO: Implement 9, 10

    // Method 9
    @Override
    public HealthProfileItemResponse savePersonMeasure(int id, HealthProfileItem healthProfileItem) {
        HealthProfileItemResponse response = new HealthProfileItemResponse();
        return response;
    }

    // Method 10
    @Override
    public HealthProfileItemResponse updatePersonMeasure(int id, HealthProfileItem healthProfileItem) {
        HealthProfileItemResponse response = new HealthProfileItemResponse();
        return response;
    }
}
