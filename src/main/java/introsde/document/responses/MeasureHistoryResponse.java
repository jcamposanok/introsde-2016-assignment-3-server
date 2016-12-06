package introsde.document.responses;

import introsde.document.models.HealthProfileItem;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;


public class MeasureHistoryResponse {

    private List<HealthProfileItemResponse> history;

    @XmlElement(name = "measure")
    public List<HealthProfileItemResponse> getHistory() {
        return history;
    }

    public void setHistory(List<HealthProfileItemResponse> history) {
        this.history = history;
    }

    public MeasureHistoryResponse(List<HealthProfileItemResponse> history) {
        setHistory(history);
    }

    public MeasureHistoryResponse() {
        this(new ArrayList<>());
    }

}
