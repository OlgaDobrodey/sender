package org.dobrodey.sender;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JSONParser<T> {

    public static <T> List<T> fromUrlToJSON(URL url, T type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = mapper.readValue(url,
                mapper.getTypeFactory().constructCollectionType(List.class, type.getClass()));
        return list;
    }
}
