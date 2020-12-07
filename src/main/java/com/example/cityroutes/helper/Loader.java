package com.example.cityroutes.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Loads the city configuration and determines if there is a route between two cities
 */
@Component
public class Loader {
    @Autowired
    ResourceLoader resourceLoader;

    private Map<String,City> cityMap = new HashMap<>();

    @PostConstruct
    public void postInitialize() {
        try {
            List<String> cities = Files.readAllLines(Utils.loadFile("city.txt").toPath());
            for (String temp : cities) {
                String[] source = temp.split(",");
                String from = source[0];
                String to = source[1];
                //Load from City
                if (cityMap.get(from)==null) {
                    City fromCity = new City(from);
                    cityMap.put(from,fromCity);
                }
                City toCity = null;
                //Load to City
                if (cityMap.get(to)==null) {
                    toCity = new City(to);
                    cityMap.put(to,toCity);
                }
                else{
                    toCity = cityMap.get(to);
                }
                //Maps from and to City
                if (cityMap.get(from).connectedCities!=null) {
                    City finalToCity = toCity;
                    Optional<City> targetCity = cityMap.get(from).connectedCities.stream().filter(city -> finalToCity.equals(city)).findAny();
                    City tempToCity = targetCity.orElse(cityMap.get(to)) ;
                    toCity = tempToCity!=null ? tempToCity : toCity;
                }
                    cityMap.get(from).addVisitableCities(toCity);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found");
        }
    }

    /**
     * Validates if there is route between two cities
     * @param source
     * @param destination
     * @return
     */
    public String checkRoute(String source, String destination){
           City c1 = this.cityMap.get(source);
           if(c1==null) {
               return "no";
           }
           City c2 = new City(destination);
           Set<City> visited = new HashSet<>();
        return isRouteExists(c1,c2,visited) ? "yes" :"no";
    }

    private boolean isRouteExists(City from, City to, Set<City> visited) {
       if(from.equals(to)){
            return true;
        }
        visited.add(from);
       if(from.connectedCities!=null && from.connectedCities.size() >0) {
           for (City neigbour : from.getConnectedCities()) {
               if (!visited.contains(neigbour) && isRouteExists(neigbour, to,visited)) {
                   return true;
               }
           }
       }
    return false;
    }

    /**
     * Entity to represent the City and its visitable cities
     */
    private static class City {
        String name;
        private Set<City> connectedCities;

        public Set<City> getConnectedCities() {
            return connectedCities;
        }
        public City(String name) {
            this.name = name;
        }

        public void addVisitableCities(City visitableCity) {
            if (connectedCities == null) {
                connectedCities = new HashSet<>();
            }
            connectedCities.add(visitableCity);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            City city = (City) o;
            return Objects.equals(name, city.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", connectedCities=" + connectedCities +
                    '}';
        }
    }


}
