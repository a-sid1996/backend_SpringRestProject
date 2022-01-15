package com.spring.BackendProject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.BackendProject.Entities.Component;
import com.spring.BackendProject.Entities.Jukebox;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JukeboxServiceImpl implements JukeboxService{

    /**
     * Calls to retrieve mock API responses. These will be called when the class is instantiated
     * in order to save time in subsequent calls.
     */
    ObjectMapper mapper = new ObjectMapper();
    String jukes = callRemoteAPI("jukes");
    String settings = callRemoteAPI("settings");

    /**
     * Implementation of function as declared in the JukeboxService interface.
     * Utilizes JSON string retrieved by callRemoteAPI. Filters jukebox list
     * as per the specifications provided by the user.
     * @param settingId
     * @param model
     * @param offset
     * @param limit
     * @return Final filtered list of Jukeboxes
     */
    @Override
    public List<Jukebox> getJukeboxes(String settingId, String model, String offset, String limit) {

        List<Jukebox> jukeboxList = readJukesJson(jukes);
        Set<String> settingsList = readSettingsJson(settings, settingId);

        jukeboxList = filterJukeboxesBySettingId(jukeboxList, settingsList);

        if(model != null){
            jukeboxList = filterJukeboxesByModel(jukeboxList, model);
        }

        if(limit != null && offset != null){
            jukeboxList = filterJukeboxesByLimitOffset(jukeboxList, Integer.parseInt(limit), Integer.parseInt(offset));
        }

        return jukeboxList;
    }

    /**
     * Applies limit and offset to filtered Jukebox list if provided by the user.
     * @param jukeboxList
     * @param limit
     * @param offset
     * @return
     */
    private List<Jukebox> filterJukeboxesByLimitOffset(List<Jukebox> jukeboxList, int limit, int offset) {
        return jukeboxList.subList(
                Math.min(jukeboxList.size(), offset),
                Math.min(jukeboxList.size(), offset + limit)
        );
    }

    /**
     * Filters list based on model if provided by the user.
     * @param jukeboxList
     * @param model
     * @return Filtered list of Jukeboxes.
     */
    private List<Jukebox> filterJukeboxesByModel(List<Jukebox> jukeboxList, String model) {
        return jukeboxList.stream()
                .filter(
                        j -> j.getModel().equals(model)
                ).toList();
    }

    /**
     * Applies filter to list of Jukeboxes as per the components returned by the settingID.
     * Utilizes stream functionality provided by Java 8+. The components in each jukebox are
     * first converted to a set then matched with the set of components in settingID.
     * The filter is applied to all jukeboxes and the result list is returned.
     * @param jukeboxList
     * @param settingsList
     * @return Filtered list of Jukeboxes
     */
    private List<Jukebox> filterJukeboxesBySettingId(List<Jukebox> jukeboxList, Set<String> settingsList) {

        if (settingsList == null){
            return new ArrayList<Jukebox>();
        }

        return jukeboxList.stream()
                .filter(
                        j -> Arrays.stream(j.getComponents()).map(Component::getName).collect(Collectors.toSet())
                                .containsAll(settingsList)
                ).toList();
    }

    /**
     * Reads raw JSON string retrieved by mock APIs. Returns a set of components
     * required as per the settingID passed by the user. Chose Set data structure
     * since some components returned by the mock APIs were duplicates. Using set
     * made sure that each component was listed just once.
     * @param settings
     * @param settingId
     * @return Set of components
     */
    private Set<String> readSettingsJson(String settings, String settingId) {

        try {
            JsonNode settings1 = mapper.readTree(settings);
            for (JsonNode j : settings1.get("settings")){
                if (j.get("id").asText().equals(settingId)){
                    Set<String> temp = new HashSet<>();
                    for (JsonNode k : j.get("requires")){
                        temp.add(k.asText());
                    }
                    return temp;
                }
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts raw string retrieved from mock API to Java Objects.
     * Function utilizes Jackson library to parse JSON.
     * @param jukes
     * @return ArrayList of Java Jukebox Objects
     */
    private ArrayList<Jukebox> readJukesJson(String jukes) {
        try {
            Jukebox[] jukeList = mapper.readValue(jukes, Jukebox[].class);
            return new ArrayList<>(Arrays.asList(jukeList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calls mock API to get list of Jukebox and settings. This is the list which will eventually be filtered.
     * @param mode
     * @return JSON in string format
     */

    private String callRemoteAPI(String mode) {

        try {
            String ur = "http://my-json-server.typicode.com/touchtunes/tech-assignment/" + mode;
            URL url = new URL(ur);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            return new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
