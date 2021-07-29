package softuni.exam.instagraphlite.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T readFromFile(String filePath, Class<T> tClass) throws JAXBException;
}
