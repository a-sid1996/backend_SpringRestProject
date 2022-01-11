package com.spring.BackendProject.MyController;

import com.spring.BackendProject.Entities.Jukebox;
import com.spring.BackendProject.services.JukeboxService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MyController {

    /**
     * Instantiates interface in order to separate and hide the inner working of API function.
     * This is done to ensure the structure is production ready. If inner working needs to be changed later
     * it can be done without making any changes here.
     */
    @Autowired
    private JukeboxService service;

    /**
     * Gets invoked when '/jukeboxes' API endpoint is invoked. The call invokes function from the interface.
     * A list of components based on the settings ID will be generated with mock API.
     * Jukeboxes that contain these components will be filtered and returned to the user.
     * @param settingId : required (retrieve components based on this ID)
     * @param model : not required
     * @param offset : not required
     * @param limit : not required
     * @return ArrayList of filtered Jukeboxes
     */
    @Operation(summary = "This is to return a list of Jukeboxes which have the components as provided by the settingsID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                    responseCode = "200",
                    description = "Returns list of Jukeboxes containing required components",
                    content = {@Content(mediaType = "application/json")}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = {@Content}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while filtering list as per requirements",
                            content = {@Content}
                    )
    })
    @GetMapping("/jukeboxes")
    public List<Jukebox> getJukeboxes(@RequestParam(name = "settingId") String settingId,
                                      @RequestParam(required = false, name = "model") String model,
                                      @RequestParam(required = false, name = "offset") String offset,
                                      @RequestParam(required = false, name = "limit") String limit ){
        return this.service.getJukeboxes(settingId, model, offset, limit);
    }
}
