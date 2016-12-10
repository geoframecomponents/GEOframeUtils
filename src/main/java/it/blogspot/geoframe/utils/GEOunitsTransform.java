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
 * @version 0.1, 05/15/16
 */
public class GEOunitsTransform {

    public static double percentage2radiant(Double inputValue) {
        GEOchecks.isNull(inputValue); // precondition

        if (inputValue > 1) inputValue = normalizeToOne(inputValue);
        return inputValue * Math.PI/4;
    }

    public static double normalizeToOne(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        if (inputValue < 100)
            return inputValue / 100;
        else {
            String message = "The input value cannot be convert to 1\n";
            message += "It is not a percentage.";
            throw new IllegalArgumentException(message);
        }

    }

    public static double minutes2seconds(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int secondsOverMinutes = 60;
        return inputValue * secondsOverMinutes;
    }

    public static double seconds2minutes(final Double inputValue) {
        GEOchecks.isNull(inputValue); // precondition

        final int secondsOverMinutes = 60;
        return inputValue / secondsOverMinutes;
    }

    public static double hours2minutes(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int minutesOverHours = 60;
        return inputValue * minutesOverHours;
    }

    public static double millimiters2meters(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int millimitersOverMeters = 1000;
        return inputValue / millimitersOverMeters;
    }

    public static double meters2centimeters(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int centimetersOverMeters = 100;
        return inputValue * centimetersOverMeters;
    }

    public static double centimeters2meters(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int centimitersOverMeters = 100;
        return inputValue / centimitersOverMeters;
    }

    public static double cubicMeters2liters(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int litersOverCubicMeters = 1000;
        return inputValue * litersOverCubicMeters;
    }

    public static double hectars2meters(final Double inputValue) {
        GEOchecks.isNull(inputValue); //precondition

        final int metersOverHectares = 10000;
        return inputValue * metersOverHectares;
    }

}
