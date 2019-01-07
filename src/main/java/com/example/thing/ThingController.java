package com.example.thing;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("thing")
public class ThingController {

	@RequestMapping(value = "/{thingName}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getThing(
	        @PathVariable(value = "thingName") String thingName) {
	    return "foo";
	}

	@RequestMapping(value = "",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getThings(
	        @RequestParam(value = "findByComponent", required = false) String findByComponentQuery) {
		
		if (findByComponentQuery != null) {
			return "bar -> " + findByComponentQuery;
		}
		else {
			return "bar";
		}
	}
}
