package introsde.assignment.soap;

import introsde.document.models.HealthProfileItem;
import introsde.document.models.Measure;
import introsde.document.models.MeasureType;
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
            return new PersonResponse();
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
        int response = -1;
        Person existing = Person.getById(id);
        if (existing != null) {
            response = existing.getPersonId();
            Person.removePerson(existing);
        }
        return response;
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
        List<MeasureType> measureTypeList = MeasureType.getAll();
        if (measureTypeList != null & measureTypeList.size() > 0) {
            response.setMeasureTypes(measureTypeList);
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

    // Method 9
    @Override
    public HealthProfileItemResponse savePersonMeasure(int id, Measure m) {
        Person person = Person.getById(id);
        if (person == null || m.getMeasureType() == null) {
            return new HealthProfileItemResponse();
        }
        MeasureType measureType = MeasureType.getByName(m.getMeasureType());
        if (measureType == null || measureType.getMeasureId() <= 0) {
            return new HealthProfileItemResponse();
        }

        HealthProfileItem existing = HealthProfileItem.getCurrentByPersonAndType(id, measureType.getName());
        if (existing == null || existing.getHealthProfileId() <= 0) {
            // No old value, just save the current one
            HealthProfileItem newHealthProfileItem = new HealthProfileItem();
            newHealthProfileItem.setPerson(person);
            newHealthProfileItem.setMeasureType(measureType);
            newHealthProfileItem.setValue(m.getMeasureValue());
            newHealthProfileItem.setValueType(m.getMeasureValueType());
            newHealthProfileItem.setCreated(m.getDateRegistered());
            newHealthProfileItem.setValid(true);
            return new HealthProfileItemResponse(HealthProfileItem.saveHealthProfileItem(newHealthProfileItem));
        }

        // Archive the old value in the history
        HealthProfileItem backupHealthProfileItem = new HealthProfileItem();
        backupHealthProfileItem.setPerson(existing.getPerson());
        backupHealthProfileItem.setMeasureType(existing.getMeasureType());
        backupHealthProfileItem.setValue(existing.getValue());
        backupHealthProfileItem.setValueType(existing.getValueType());
        backupHealthProfileItem.setCreated(existing.getCreated());
        backupHealthProfileItem.setValid(false);
        HealthProfileItem.saveHealthProfileItem(backupHealthProfileItem);

        existing.setPerson(person);
        existing.setMeasureType(measureType);
        existing.setCreated(m.getDateRegistered());
        existing.setValue(m.getMeasureValue());
        existing.setValueType(m.getMeasureValueType());
        return new HealthProfileItemResponse(HealthProfileItem.updateHealthProfileItem(existing));
    }

    // Method 10
    @Override
    public HealthProfileItemResponse updatePersonMeasure(int id, Measure m) {
        Person person = Person.getById(id);
        if (person == null || m.getMeasureType() == null) {
            return new HealthProfileItemResponse();
        }
        MeasureType measureType = MeasureType.getByName(m.getMeasureType());
        if (measureType == null || measureType.getMeasureId() <= 0) {
            return new HealthProfileItemResponse();
        }
        HealthProfileItem existing = HealthProfileItem.getById(m.getMid().intValue());
        if (existing == null || existing.getHealthProfileId() <= 0) {
            return new HealthProfileItemResponse();
        }

        existing.setPerson(person);
        existing.setMeasureType(measureType);
        existing.setCreated(m.getDateRegistered());
        existing.setValue(m.getMeasureValue());
        existing.setValueType(m.getMeasureValueType());
        return new HealthProfileItemResponse(HealthProfileItem.updateHealthProfileItem(existing));
    }
}
