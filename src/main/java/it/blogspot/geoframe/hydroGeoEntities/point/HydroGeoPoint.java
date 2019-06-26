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
package it.blogspot.geoframe.hydroGeoEntities.point;

import it.blogspot.geoframe.hydroGeoEntities.HydroGeoEntity;

/**
 *
 *
 * @author sidereus, francesco.serafin.3@gmail.com
 * @version 0.1
 * date May 15, 2016
 * copyright GNU Public License v3 GWH-2b4
 */
public abstract class HydroGeoPoint extends HydroGeoEntity {

    private String message = "Operation not permitted";

    abstract public double getX();

    abstract public double getY();

    public void setElevation(final double elevation) {
        String message = "Operation not permitted";
        throw new UnsupportedOperationException(message); 
    }

    abstract public double getElevation();

    public HydroGeoPoint getStartPoint() {
        throw new UnsupportedOperationException(message);
    }

    public HydroGeoPoint getEndPoint() {
        throw new UnsupportedOperationException(message);
    }

}
