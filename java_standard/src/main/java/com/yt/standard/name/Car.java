package com.yt.standard.name;

import java.util.List;

/**
 * TODO
 *
 * @author: 杨涛
 * @date: 2021/11/12/012
 */
public interface Car {

    /**
     * get car name
     * @return car name
     */
    String getCarName();

    /**
     * get all car list
     * @return cars
     */
    List<String> getCarsList();

    /**
     * get all car number from cars[]
     * @return car number
     */
    int getCount();

    /**
     * remove cars[]'s value
     */
    void remove();
}
