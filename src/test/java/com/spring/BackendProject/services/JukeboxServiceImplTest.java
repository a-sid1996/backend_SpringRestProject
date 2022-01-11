package com.spring.BackendProject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.BackendProject.Entities.Component;
import com.spring.BackendProject.Entities.Jukebox;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class JukeboxServiceImplTest {

    String jukeboxJsonString = "[\n" +
            "    {\n" +
            "        \"id\": \"5ca94a8ac470d3e47cd4713c\",\n" +
            "        \"model\": \"fusion\",\n" +
            "        \"components\": [\n" +
            "            {\n" +
            "                \"name\": \"led_panel\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"amplifier\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"led_panel\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"led_panel\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"pcb\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"5ca94a8ab2c1285e53a89991\",\n" +
            "        \"model\": \"fusion\",\n" +
            "        \"components\": []\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"5ca94a8a72473ac501b99033\",\n" +
            "        \"model\": \"angelina\",\n" +
            "        \"components\": []\n" +
            "    }\n" +
            "]";

    @Test
    void testReadValue() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Jukebox[] jukeList = mapper.readValue(jukeboxJsonString, Jukebox[].class);

        assertEquals(jukeList[0].getId(), "5ca94a8ac470d3e47cd4713c");
        assertEquals(jukeList[0].getModel(), "fusion");

        assertEquals(jukeList[1].getId(), "5ca94a8ab2c1285e53a89991");
        assertEquals(jukeList[1].getModel(), "fusion");

        assertEquals(jukeList[2].getId(), "5ca94a8a72473ac501b99033");
        assertEquals(jukeList[2].getModel(), "angelina");

    }

    @Test
    void testFilterJukeboxesByModel() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Jukebox[] jukeList = mapper.readValue(jukeboxJsonString, Jukebox[].class);
        ArrayList<Jukebox> jukeboxes = new ArrayList<>(Arrays.asList(jukeList));

        List<Jukebox> jukes = jukeboxes.stream()
                .filter(
                        j -> j.getModel().equals("angelina")
                ).toList();

        assertEquals(jukes.size(), 1);
        assertEquals(jukes.get(0).getModel(), "angelina");
        assertEquals(jukes.get(0).getId(), "5ca94a8a72473ac501b99033");

    }

    @Test
    void testFilterJukeboxesBySettings() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Jukebox[] jukeList = mapper.readValue(jukeboxJsonString, Jukebox[].class);
        ArrayList<Jukebox> jukeboxes = new ArrayList<>(Arrays.asList(jukeList));

        List<Jukebox> jukes = jukeboxes.stream()
                .filter(
                        j -> Arrays.stream(j.getComponents()).map(Component::getName).collect(Collectors.toSet())
                                .containsAll(new HashSet<>(Arrays.asList("amplifier", "led_panel")))
                ).toList();

        assertEquals(jukes.size(), 1);
        assertEquals(jukes.get(0).getModel(), "fusion");
        assertEquals(jukes.get(0).getId(), "5ca94a8ac470d3e47cd4713c");

    }
}