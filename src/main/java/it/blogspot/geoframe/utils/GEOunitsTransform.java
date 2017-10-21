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
 * @author ftt01, dallatorre.daniele@gmail.com
 * @version 0.1, 05/15/16
 */
public class GEOunitsTransform {

    public static double percentage2radiant(Double inputValue) {
        GEOchecks.isNull(inputValue); // precondition

        if (inputValue > 1) inputValue = normalizeToOne(inputValue);
        return inputValue * Math.PI / 4;
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

    /** Units transform from inches to millimiters.
     * @param inches
     * @return millimiters
     * @brief Inches to millimiters
     */
    public static Double inches2millimiters(final Double inches) {
        final double millimitersOverInches = 25.4;
        return inches * millimitersOverInches;
    }

    /** Units transform from millimiters to inches.
     * @param millimiters
     * @return inches
     * @brief Millimiters to inches
     */
    public static Double millimiters2inches(final Double millimiters) {
        final double millimitersOverInches = 25.4;
        return millimiters / millimitersOverInches;
    }

    /** Units transform from feet to meters.
     * @param feet
     * @return meters
     * @brief Feet to meters
     */
    public static Double feet2meters(final Double feet) {
        final double metersOverFeet = 0.3048;
        return feet * metersOverFeet;
    }

    /** Units transform from meters to feet.
     * @param meters
     * @return feet
     * @brief Meters to feet
     */
    public static Double meters2feet(final Double meters) {
        final double metersOverFeet = 0.3048;
        return meters / metersOverFeet;
    }

    /** Units transform from yards to meters.
     * @param yards
     * @return meters
     * @brief Yards to meters
     */
    public static Double yards2meters(final Double yards) {
        final double metersOverYards = 0.9144;
        return yards * metersOverYards;
    }

    /** Units transform from meters to yards.
     * @param meters
     * @return yards
     * @brief  Meters to yards
     */
    public static Double meters2yards(final Double meters) {
        final double metersOverYards = 0.9144;
        return meters / metersOverYards;
    }

    /** Units transform from miles to kilometers.
     * @param miles
     * @return kilometers
     * @brief Miles to kilometers
     */
    public static Double miles2kilometers(final Double miles) {
        final double kilometersOverMiles = 1.609344;
        return miles * kilometersOverMiles;
    }

    /** Units transform from kilometers to miles.
     * @param kilometers
     * @return miles
     * @brief Miles to kilometers
     */
    public static Double kilometers2miles(final Double kilometers) {
        final double kilometersOverMiles = 1.609344;
        return kilometers / kilometersOverMiles;
    }

    /** Units transform from square feet to square meters.
     * @param squareFeet
     * @return squareMeters
     * @brief Square feet to square meters
     */
    public static Double squareFeet2squareMeters(final Double squareFeet) {
        final double squareMetersOverSquareFeet = 0.09290304;
        return squareFeet * squareMetersOverSquareFeet;
    }

    /** Units transform from square meters to square feet.
     * @param squareMeters
     * @return squareFeet
     * @brief Square meters to square feet
     */
    public static Double squareMeters2squareFeet(final Double squareMeters) {
        final double squareFeetOverSquareMeters = 10.7639104;
        return squareMeters * squareFeetOverSquareMeters;
    }

    /** Units transform from square miles to square kilometers.
     * @param squareMiles
     * @return squareKilometers
     * @brief Square miles to square kilometers
     */
    public static Double squareMiles2squareKilometers(final Double squareMiles) {
        final double squareKilometersOverSquareMiles = 2.589988110336;
        return squareMiles * squareKilometersOverSquareMiles;
    }

    /** Units transform from square kilometers to square miles.
     * @param squareKilometers
     * @return squareMiles
     * @brief Square kilometers to square miles
     */
    public static Double squareKilometers2squareMiles(final Double squareKilometers) {
        final double squareMilesOverSquareKilometers = 0.38610216;
        return squareKilometers * squareMilesOverSquareKilometers;
    }

    /** Units transform from acres to square meters.
     * Acre is equal to 1/640 square mile.
     * @param acres
     * @return squareMeters
     * @brief Acres to square meters
     */
    public static Double acres2squareMeters(final Double acres) {
        final double squareMetersOverAcres = 4046.8564224;
        return acres * squareMetersOverAcres;
    }

    /** Units transform from square meters to acres.
     * @param squareMeters
     * @return acres
     * @brief Acres to square meters
     */
    public static Double squareMeters2acres(final Double squareMeters) {
        final double acresOverSquareMeters = 0.00024710538;
        return squareMeters * acresOverSquareMeters;
    }
}
