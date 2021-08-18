package exam.model.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsSeedRootDto {

    @XmlElement(name = "town")
    private List<TownsSeedDto> towns;

    public List<TownsSeedDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownsSeedDto> towns) {
        this.towns = towns;
    }
}
