package introsde.assignment.soap;

import introsde.document.models.Measure;
import introsde.document.models.Person;
import introsde.document.responses.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface People {

    // Method 1
    @WebMethod(operationName = "readPersonList")
    @WebResult(name = "people")
    public PersonListResponse readPersonList();

    // Method 2
    @WebMethod(operationName = "readPerson")
    @WebResult(name = "person")
    public PersonResponse readPerson(@WebParam(name = "id") int id);

    // Method 3
    @WebMethod(operationName = "updatePerson")
    @WebResult(name = "person")
    public PersonResponse updatePerson(@WebParam(name = "p") Person p);

    // Method 4
    @WebMethod(operationName = "createPerson")
    @WebResult(name = "person")
    public PersonResponse createPerson(@WebParam(name = "p") Person p);

    // Method 5
    @WebMethod(operationName = "deletePerson")
    @WebResult(name = "personId")
    public int deletePerson(@WebParam(name = "id") int id);

    // Method 6
    @WebMethod(operationName = "readPersonHistory")
    @WebResult(name = "healthProfile-history")
    public MeasureHistoryResponse readPersonHistory(@WebParam(name = "id") int id, @WebParam(name = "measureType") String measureType);

    // Method 7
    @WebMethod(operationName = "readMeasureTypes")
    @WebResult(name = "measureTypes")
    public MeasureTypesResponse readMeasureTypes();

    // Method 8
    @WebMethod(operationName = "readPersonMeasure")
    @WebResult(name = "measure")
    public HealthProfileItemResponse readPersonMeasure(@WebParam(name = "id") int id, @WebParam(name = "measureType") String measureType, @WebParam(name = "mid") int mid);

    // Method 9
    @WebMethod(operationName = "savePersonMeasure")
    @WebResult(name = "measure")
    public HealthProfileItemResponse savePersonMeasure(@WebParam(name = "id") int id, @WebParam(name = "m") Measure m);

    // Method 10
    @WebMethod(operationName = "updatePersonMeasure")
    @WebResult(name = "measure")
    public HealthProfileItemResponse updatePersonMeasure(@WebParam(name = "id") int id, @WebParam(name = "m") Measure m);
}
