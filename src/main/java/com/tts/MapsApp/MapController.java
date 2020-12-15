package com.tts.MapsApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MapController {
	@Autowired
	private MapService mapService;
	
	
	@GetMapping(value = {"/home","/"})
	public String getDefaultMap(Model model) {
		model.addAttribute("location", new Location());
		return "index";
	}
	
	@PostMapping(value = "/home")
	public String getMapForLocation(Location location, Model model) {
		mapService.addCoordinates(location);
		model.addAttribute("location", location);
		return "index";
	}
        
        @GetMapping(value = "/random")
        public String random(Model model){
            Location randomLocation = new Location();
             //lat [-85, +85]
            double lat = Math.random() * 85;
            //50% of the time this rounds down which makes that lat negative.
            //50% of the time it rounds up, which keeps lat positive
            lat *= Math.round(Math.random())==1 ? 1 : -1;
            //lng [-180, +180]
            double lng = Math.random() * 180;
            lng *= Math.round(Math.random())==1 ? 1 : -1;
            randomLocation.setLat(String.valueOf(lat));
            randomLocation.setLng(String.valueOf(lng));
            model.addAttribute("location", randomLocation);
            
            return "index";
        }
}
