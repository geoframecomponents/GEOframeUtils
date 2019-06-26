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
package it.blogspot.geoframe.hydroGeoEntities.line;

import it.blogspot.geoframe.hydroGeoEntities.point.HydroGeoPoint;

import net.jcip.annotations.Immutable;

/**
 *
 *
 * @author sidereus, francesco.serafin.3@gmail.com
 * @version 0.1
 * date May 15, 2016
 * copyright GNU Public License v3 GWH-2b4
 */
@Immutable
public class River extends HydroGeoLine {

    final private HydroGeoPoint startPoint;
    final private HydroGeoPoint endPoint;
    final private String message = "Nothing implemented yet";

    public River(final HydroGeoPoint startPoint, final HydroGeoPoint endPoint) {

        this.startPoint = startPoint;
        this.endPoint = endPoint;

    }

    @Override
    public HydroGeoPoint getStartPoint() {
        return startPoint;
    }

    @Override
    public HydroGeoPoint getEndPoint() {
        return endPoint;
    }

    @Override
    public double getLength() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getDischarge() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getSlope() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getVelocity() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public double getGaucklerStricklerCoefficient() {
        throw new UnsupportedOperationException(message);
    }

}
