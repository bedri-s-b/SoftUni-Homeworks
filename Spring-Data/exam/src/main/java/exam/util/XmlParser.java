package exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T readFromFile(String filePth, Class<T> tClass) throws JAXBException;
}
