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

import java.util.HashMap;

import it.blogspot.geoframe.hydroGeoEntities.point.HydroGeoPoint;
import it.blogspot.geoframe.utils.GEOchecks;
import it.blogspot.geoframe.utils.GEOgeometry;

/**
 *
 *
 * @author sidereus, francesco.serafin.3@gmail.com
 * @version 0.1
 * date May 15, 2016
 * copyright GNU Public License v3 GWH-2b4
 */
public class Pipe extends HydroGeoLine {

    final private HashMap<Integer, HydroGeoPoint> inspectionChambers = new HashMap<>();
    final private HydroGeoPoint startInspectionChamber;
    final private HydroGeoPoint endInspectionChamber;
    final private double gaucklerStricklerCoefficient;
    private Double fillCoefficient;
    private Double length = null;
    private Double discharge = null;
    private Double slope = null;
    private Double diameter = null;
    private Double velocity = null;

    public Pipe(final double gaucklerStricklerCoefficient, final double fillCoefficient, HydroGeoPoint... inspectionChambers) {

        this.gaucklerStricklerCoefficient = gaucklerStricklerCoefficient;
        this.fillCoefficient = fillCoefficient;

        // check if inspectionChambers length is at least 2
        Integer index = 1;
        for (HydroGeoPoint inspectionChamber : inspectionChambers) {
            this.inspectionChambers.put(index, inspectionChamber);
            index++;
        }

        this.startInspectionChamber = this.inspectionChambers.get(1);
        this.endInspectionChamber = this.inspectionChambers.get(this.inspectionChambers.size());
    }

    @Override
    public HydroGeoPoint getStartPoint() {
        return startInspectionChamber;
    }

    @Override
    public HydroGeoPoint getEndPoint() {
        return endInspectionChamber;
    }

    @Override
    public double getLength() {
        return GEOchecks.checkVariable(length);
    }

    @Override
    public double getDischarge() {
        return GEOchecks.checkVariable(discharge);
    }

    @Override
    public double getSlope() {
        return GEOchecks.checkVariable(slope);
    }

    public double getDiameter() {
        return GEOchecks.checkVariable(diameter);
    }

    @Override
    public double getGaucklerStricklerCoefficient() {
        return gaucklerStricklerCoefficient;
    }

    public double getFillCoefficient() {
        return fillCoefficient;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    public void setElevationEndPoint(final double elevationEndPoint) {
        endInspectionChamber.setElevation(elevationEndPoint);
        inspectionChambers.put(inspectionChambers.size(), endInspectionChamber);

        length = GEOgeometry.computeLength3D(startInspectionChamber.getX(), startInspectionChamber.getY(), startInspectionChamber.getElevation(),
                                             endInspectionChamber.getX(), endInspectionChamber.getY(), endInspectionChamber.getElevation());
    }

    public void buildPipe(final double elevationEndPoint, final double diameter, final double fillCoefficient, final double velocity) {
        setElevationEndPoint(elevationEndPoint);
        this.diameter = diameter;
        this.fillCoefficient = fillCoefficient;
        this.velocity = velocity;

        slope = GEOgeometry.computeSlope(startInspectionChamber.getX(), startInspectionChamber.getY(), startInspectionChamber.getElevation(),
                                         endInspectionChamber.getX(), endInspectionChamber.getY(), endInspectionChamber.getElevation());
    }

    public void setDischarge(final double discharge) {
        this.discharge = discharge;
    }

    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    public void setFillCoefficient(final double fillCoefficient) {
        this.fillCoefficient = fillCoefficient;
    }

    public void setVelocity(final double velocity) {
        this.velocity = velocity;
    }

}
