package introsde.document.responses;

import introsde.document.models.MeasureType;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;


public class MeasureTypesResponse {

    private List<MeasureType> measureTypes;

    @XmlElement(name = "measureType")
    public List<MeasureType> getMeasureTypes() {
        return measureTypes;
    }

    public void setMeasureTypes(List<MeasureType> measureTypes) {
        this.measureTypes = measureTypes;
    }

}
