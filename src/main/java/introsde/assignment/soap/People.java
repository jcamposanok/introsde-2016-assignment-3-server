package introsde.assignment.soap;

import introsde.document.models.HealthProfileItem;
import introsde.document.models.Person;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface People {
    @WebMethod(operationName="readPersonList")
    @WebResult(name="people")
    public List<Person> readPersonList();

    @WebMethod(operationName="readPerson")
    @WebResult(name="person")
    public Person readPerson(@WebParam(name="id") long id);

    @WebMethod(operationName="updatePerson")
    @WebResult(name="person")
    public Person updatePerson(@WebParam(name="p") Person p);

    @WebMethod(operationName="createPerson")
    @WebResult(name="person")
    public Person createPerson(@WebParam(name="p") Person p);

    @WebMethod(operationName="deletePerson")
    @WebResult(name="personId")
    public int deletePerson(@WebParam(name="personId") long id);

    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="healthProfile-history")
    public List<HealthProfileItem> readPersonHistory (@WebParam(name="id") long id, @WebParam(name="measureType") String measureType);
}
