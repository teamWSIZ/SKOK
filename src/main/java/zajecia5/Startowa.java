package zajecia5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Date;

public class Startowa {
    public static void main(String[] args) {
        SupEvent event = new SupEvent();
        event.setId(1);
        event.setComment("Deus vult");
        event.setWorkstart(new Date());

        System.out.println(event);

        SupEvent other = SupEvent.builder().id(2).comment("Non nobus")
                .title("Abra kadabra").build();


        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(event);
            System.out.println("Json: " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            SupEvent odczytany = mapper.readValue(json, SupEvent.class);
            System.out.println(odczytany);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String innyJson = "{\"comment\":\"Deus vult\",\"id\":1,\"title\":null,\"workstart\":1494067217781}";
        try {
            SupEvent innyOdczytany = mapper.readValue(innyJson, SupEvent.class);
            System.out.println(innyOdczytany);
        } catch (IOException e) {
            e.printStackTrace();
        }



        XmlMapper mapperX = new XmlMapper();
        String xml = null;
        try {
            xml = mapperX.writeValueAsString(event);
            System.out.println(xml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            SupEvent back = mapperX.readValue(xml, SupEvent.class);
            System.out.println(back);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
