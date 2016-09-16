/*
 * GNU GPL v3 License
 *
 * Copyright 2016 GEOframe (Riccardo Rigon)
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
package it.blogspot.geoframe.utils.mesh;

import static org.jgrasstools.gears.libs.modules.JGTConstants.doubleNovalue;
import static org.jgrasstools.gears.libs.modules.JGTConstants.isNovalue;

import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.LinkedList;

import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import javax.media.jai.iterator.WritableRandomIter;

import oms3.annotations.Author;
import oms3.annotations.Description;
import oms3.annotations.Execute;
import oms3.annotations.In;
import oms3.annotations.Keywords;
import oms3.annotations.Label;
import oms3.annotations.License;
import oms3.annotations.Name;
import oms3.annotations.Out;
import oms3.annotations.Status;

import org.geotools.coverage.grid.GridCoverage2D;
import org.jgrasstools.gears.libs.modules.JGTModel;
import org.jgrasstools.gears.utils.coverage.CoverageUtilities;

@Description()
@Author(name = "Giuseppe Formetta", contact = "giuseppe.formetta@unical.it")
@Keywords()
@Label("")
@Name("")
@Status()
@License("")
public class DoGridMesh extends JGTModel {

    @Description()
    @In
    public GridCoverage2D inSlope = null;

    @Description()
    @In
    public boolean doSimmetricMatrix = true;

    @Description()
    @Out
    public GridCoverage2D outShalstab = null;

    public final double EPS = 0.01;

    @Execute
    public void process() throws Exception {
        checkNull(inSlope);
        RenderedImage slopeRI = inSlope.getRenderedImage();
        qcrit(slopeRI);
    }

    /**
     * Calculates the trasmissivity in every pixel of the map.
     */
    private void qcrit(RenderedImage slope) {

        HashMap<String, Double> regionMap = CoverageUtilities
                .getRegionParamsFromGridCoverage(inSlope);
        int cols = regionMap.get(CoverageUtilities.COLS).intValue();
        int rows = regionMap.get(CoverageUtilities.ROWS).intValue();
        WritableRaster classiWR = CoverageUtilities.createDoubleWritableRaster(
                cols, rows, null, null, null);
        WritableRandomIter classiIter = RandomIterFactory.createWritable(
                classiWR, null);
        RandomIter slopeRI = RandomIterFactory.create(slope, null);
        int contaPoligoni = 0;
        LinkedList<Integer> rowP = new LinkedList<Integer>();
        LinkedList<Integer> colP = new LinkedList<Integer>();
        LinkedList<Integer> valueP = new LinkedList<Integer>();

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {

                double slopeValue = slopeRI.getSampleDouble(i, j, 0);

                if (!isNovalue(slopeValue)) {
                    contaPoligoni += 1;
                    rowP.add(j);
                    colP.add(i);
                    valueP.add(contaPoligoni);
                    classiIter.setSample(i, j, 0, contaPoligoni);

                } else {
                    classiIter.setSample(i, j, 0, doubleNovalue);
                }
            }
        }
        pm.done();

        outShalstab = CoverageUtilities.buildCoverage("classi", classiWR,
                regionMap, inSlope.getCoordinateReferenceSystem());

        LinkedList<Integer> rowL = new LinkedList<Integer>();
        LinkedList<Integer> colL = new LinkedList<Integer>();
        LinkedList<Integer> valueL = new LinkedList<Integer>();

        RenderedImage p = outShalstab.getRenderedImage();
        RandomIter pRI = RandomIterFactory.create(p, null);

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                System.out.println(pRI.getSampleDouble(i, j, 0));
                if (!isNovalue(pRI.getSampleDouble(i, j, 0))) {
                    if ((i + 1) < cols) {

                        if (pRI.getSampleDouble(i + 1, j, 0) != doubleNovalue) {

                            rowL.add((int) pRI.getSampleDouble(i, j, 0));
                            colL.add((int) pRI.getSampleDouble(i + 1, j, 0));
                            valueL.add(2);

                        }
                    }
                    if ((i - 1) > 0) {
                        if (pRI.getSampleDouble(i - 1, j, 0) != doubleNovalue) {

                            rowL.add((int) pRI.getSampleDouble(i, j, 0));
                            colL.add((int) pRI.getSampleDouble(i - 1, j, 0));
                            valueL.add(2);

                        }
                    }
                    if ((j + 1) < rows) {
                        if (pRI.getSampleDouble(i, j + 1, 0) != doubleNovalue) {

                            rowL.add((int) pRI.getSampleDouble(i, j, 0));
                            colL.add((int) pRI.getSampleDouble(i, j + 1, 0));
                            valueL.add(2);

                        }
                    }
                    if ((j - 1) > 0) {
                        if (pRI.getSampleDouble(i, j - 1, 0) != doubleNovalue) {

                            rowL.add((int) pRI.getSampleDouble(i, j, 0));
                            colL.add((int) pRI.getSampleDouble(i, j - 1, 0));
                            valueL.add(2);

                        }
                    }

                }
            }
        }

        if (doSimmetricMatrix) {
            for (int i = 0; i < colL.size(); i++) {
                if (rowL.get(i) > colL.get(i)) {
                    System.out.println(rowL.get(i));
                    System.out.println(colL.get(i));

                    valueL.set(i, -9999);

                }
            }

        }
        int cnt = 0;
        for (int i = 0; i < valueL.size(); i++) {
            if (valueL.get(i) != -9999) {
                cnt++;
                valueL.set(i, cnt);
            }

        }

        LinkedList<Integer> rowLnew = new LinkedList<Integer>();
        LinkedList<Integer> colLnew = new LinkedList<Integer>();
        LinkedList<Integer> valueLnew = new LinkedList<Integer>();

        if (doSimmetricMatrix) {
            for (int i = 0; i < valueL.size(); i++) {
                if (valueL.get(i) != -9999) {
                    rowLnew.add(rowL.get(i));
                    colLnew.add(colL.get(i));
                    valueLnew.add(valueL.get(i));

                    rowLnew.add(colL.get(i));
                    colLnew.add(rowL.get(i));
                    valueLnew.add(valueL.get(i));

                }

            }

        } else {
            rowLnew = rowL;
            colLnew = colL;
            valueLnew = valueL;
        }

        for (int i = 1; i < contaPoligoni + 1; i++) {
            rowLnew.add(i);
            colLnew.add(i);
            valueLnew.add(-1);
        }
        System.out.println("LinkedList contains : " + colLnew);
        System.out.println("LinkedList contains : " + rowLnew);
        System.out.println("LinkedList contains : " + valueLnew);

        System.out.print("ciao");
    }
}
