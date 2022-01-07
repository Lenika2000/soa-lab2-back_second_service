package ru.itmo.soalab2.controller;

import java.util.Map;

public class CityRequestParams {
    public String name;
    public String[] x;
    public String[] y;
    public String[] creationDate;
    public String[] area;
    public String[] population;
    public String[] metersAboveSeaLevel;
    public String[] timezone;
    public String[] government;
    public String[] standardOfLiving;
    public String[] birthday;
    public String[] height;
    public String[] sort;
    public int page;
    public int size;

    private static final String NAME_PARAM = "name";
    private static final String X_PARAM = "x";
    private static final String Y_PARAM = "y";
    private static final String AREA_PARAM = "area";
    private static final String CREATION_DATE_PARAM = "creation_date";
    private static final String POPULATION_PARAM = "population";
    private static final String METERS_ABOVE_SEA_LEVEL_PARAM = "meters_above_sea_level";
    private static final String TIMEZONE_PARAM = "timezone";
    private static final String GOVERNMENT_PARAM = "government";
    private static final String STANDARD_OF_LIVING_PARAM = "standard_of_living";
    private static final String HEIGHT_PARAM = "height";
    private static final String BIRTHDAY_PARAM = "birthday";
    private static final String SORTING_PARAM = "sort";
    private static final String PAGE_INDEX = "page";
    private static final String PAGE_SIZE_PARAM = "size";


    CityRequestParams(Map<String, String[]> info) {
        setCityRequestParams(info.get(NAME_PARAM),
                info.get(X_PARAM),
                info.get(Y_PARAM),
                info.get(AREA_PARAM),
                info.get(CREATION_DATE_PARAM),
                info.get(POPULATION_PARAM),
                info.get(METERS_ABOVE_SEA_LEVEL_PARAM),
                info.get(TIMEZONE_PARAM),
                info.get(GOVERNMENT_PARAM),
                info.get(STANDARD_OF_LIVING_PARAM),
                info.get(BIRTHDAY_PARAM),
                info.get(HEIGHT_PARAM),
                info.get(SORTING_PARAM),
                info.get(PAGE_INDEX),
                info.get(PAGE_SIZE_PARAM)
        );
    }

    private void setCityRequestParams(String[] name,
                                     String[] x,
                                     String[] y,
                                     String[] creationDate,
                                     String[] area,
                                     String[] population,
                                     String[] metersAboveSeaLevel,
                                     String[] timezone,
                                     String[] government,
                                     String[] standardOfLiving,
                                     String[] birthday,
                                     String[] height,
                                     String[] sort,
                                     String[] page,
                                     String[] size) {
        this.name = name == null ? null : name[0];
        this.x = x;
        this.y = y;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.timezone = timezone;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.birthday = birthday;
        this.height = height;
        this.sort = sort;
        this.page = page == null ? 0 : Integer.parseInt(page[0]);
        this.size = size == null ? 5 : Integer.parseInt(size[0]);
    }
}
