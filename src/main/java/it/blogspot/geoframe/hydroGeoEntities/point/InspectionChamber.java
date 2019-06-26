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

import org.geotools.graph.util.geom.Coordinate2D;

/**
 *
 *
 * @author sidereus, francesco.serafin.3@gmail.com
 * @author ftt01, dallatorre.daniele@gmail.com
 *
 * @version 0.1
 * date May 15, 2016
 * copyright GNU Public License v3 GWH-2b4
 */
public class InspectionChamber extends HydroGeoPoint {

    final private Coordinate2D coordinate;
    final public double terrainElevation;
    private double elevation;
    private double upWall;
    private double downWall;

    public InspectionChamber(final double x, final double y, final double z, final double terrainElevation,
                             double upWall, double downWall) {
        this.coordinate = new Coordinate2D(x, y);
        this.upWall = upWall;
        this.downWall = downWall;
        this.elevation = z;
        this.terrainElevation = terrainElevation;
    }

    @Override
    public double getX() {
        return coordinate.x;
    }

    @Override
    public double getY() {
        return coordinate.y;
    }

    @Override
    public double getElevation() {
        return elevation;
    }

    @Override
    public void setElevation(final double elevation) {
        this.elevation = elevation;
    }

    public double getUpWall() { return upWall; }
    public void setUpWall(double upWall) { this.upWall = upWall; }

    public double getDownWall() { return downWall; }
    public void setDownWall(double downWall) { this.upWall = downWall; }
}
