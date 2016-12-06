package introsde.document.responses;

import introsde.document.models.Measure;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;


public class MeasureTypesResponse {

    private List<Measure> measureTypes;

    @XmlElement(name = "measureType")
    public List<Measure> getMeasureTypes() {
        return measureTypes;
    }

    public void setMeasureTypes(List<Measure> measureTypes) {
        this.measureTypes = measureTypes;
    }

}
