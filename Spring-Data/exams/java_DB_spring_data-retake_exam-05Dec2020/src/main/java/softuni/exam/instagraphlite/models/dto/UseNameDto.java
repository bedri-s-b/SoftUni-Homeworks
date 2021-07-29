package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class UseNameDto {

    @XmlElement(name = "username")
    private String name;

    @Size(min = 2,max = 18)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
