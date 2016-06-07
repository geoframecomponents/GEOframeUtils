/*
 * GNU GPL v3 License
 *
 * Copyright 2015 AboutHydrology (Riccardo Rigon)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.blogspot.geoframe.utils;

/**
 *
 *
 * @author sidereus, francesco.serafin.3@gmail.com
 * @version 0.1
 * @date May 15, 2016
 * @copyright GNU Public License v3 GWH-2b4 (Riccardo Rigon)
 */
public class GEOunitsTransform {

    public static Double percentage2radiant(Double inputValue) {
        if (inputValue > 1) inputValue = normalizeToOne(inputValue);
        return inputValue * Math.PI/4;
    }

    public static Double normalizeToOne(final Double inputValue) {

        if (inputValue < 100)
            return inputValue / 100;
        else {
            String message = "The input value cannot be convert to 1\n";
            message += "It is not a percentage.";
            throw new IllegalArgumentException(message);
        }

    }

    public static Double minutes2seconds(final Double inputValue) {
        final int secondsOverMinutes = 60;
        return inputValue * secondsOverMinutes;
    }

}
