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
 * @date June 16, 2016
 * @copyright GNU Public License v3 GWH-2b4
 */
public class GEOgeometry {

    public static Double computeLength3D(final double startPointX, final double startPointY, final double startPointZ,
                                         final double endPointX, final double endPointY, final double endPointZ) {
        return Math.sqrt(Math.pow(horizontalProjection(startPointX, startPointY, endPointX, endPointY), 2) +
                         Math.pow(computeAltitude(startPointZ, endPointZ), 2)); 
    }

    public static Double computeAltitude(final double startPointZ, final double endPointZ) {
        return startPointZ - endPointZ;
    }

    public static Double computeLength2D(final double startPointX, final double startPointY, final double endPointX, final double endPointY) {
        return horizontalProjection(startPointX, startPointY, endPointX, endPointY);
    }

    public static Double horizontalProjection(final double startPointX, final double startPointY, final double endPointX, final double endPointY) {
        return Math.sqrt(Math.pow(startPointX - endPointX, 2) + Math.pow(startPointY - endPointY, 2));
    }

}
