/*
 * Copyright (C) 2020 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2020 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package campuspaths;

import campuspaths.utils.CORSFilter;

import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Spark;

import java.util.Map;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        // TODO: Create all the Spark Java routes you need here.

        // READ THIS!!!
        // this server sends data to React Application using JSON.
        // Java -- GSON --> JSON

        CampusMap campusMap = new CampusMap();
        Gson gson = new Gson();

        // get a map of building short name to longname
        Spark.get("/buildingName", (req, res) -> {
            Map<String, String> buildingName = campusMap.buildingNames();
            String json = gson.toJson(buildingName);
            return json;
        });

        // buildingLocations
        // get a map of building short name to points
        Spark.get("/location", (req, res) -> {
            Map<String, Point> buildingLocations = campusMap.buildingLocations();
            String json = gson.toJson(buildingLocations);
            return json;
        });

        // route. Test
        Spark.get("/:route", (req, res) -> {
            String path = req.params(":route");
            // accept format "building-building"
            String[] temp = path.split("-");
            String origin = temp[0];
            String dest = temp[1];
            Path<Point> shortestPath = campusMap.findShortestPath(origin, dest);
            String json = gson.toJson(shortestPath);
            return json;
        });

    }

}
