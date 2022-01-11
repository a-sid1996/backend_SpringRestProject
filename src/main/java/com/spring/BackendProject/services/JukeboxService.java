package com.spring.BackendProject.services;

import com.spring.BackendProject.Entities.Jukebox;
import java.util.List;

/**
 * Interface contains the function called by main controller file.
 * Redirects to implementation of this function in a different file.
 */
public interface JukeboxService {
    List<Jukebox> getJukeboxes(String settingId, String model, String offset, String limit);
}
