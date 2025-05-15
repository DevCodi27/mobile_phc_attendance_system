package com.code_red.phc_attendance_system.util;

import com.code_red.phc_attendance_system.dto.LocationDTO;
import org.locationtech.jts.geom.*;
import java.util.List;

public class PolygonConverter {

	private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326); // SRID 4326
																											// for GPS

	public static Polygon convertToPolygon(List<LocationDTO> coords) {
		if (coords == null || coords.size() < 3) {
			throw new IllegalArgumentException("At least 3 coordinates are required to create a polygon.");
		}

		Coordinate[] coordinates = new Coordinate[coords.size() + 1];

		for (int i = 0; i < coords.size(); i++) {
			coordinates[i] = new Coordinate(coords.get(i).getLongitude(), coords.get(i).getLatitude()); // (x, y) =>
																										// (lng, lat)
		}

		coordinates[coords.size()] = coordinates[0]; // Close the polygon loop

		LinearRing shell = geometryFactory.createLinearRing(coordinates);
		return geometryFactory.createPolygon(shell);
	}
}
