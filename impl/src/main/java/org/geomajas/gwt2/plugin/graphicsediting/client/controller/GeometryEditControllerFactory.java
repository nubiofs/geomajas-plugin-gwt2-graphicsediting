/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.gwt2.plugin.graphicsediting.client.controller;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsController;
import org.geomajas.graphics.client.service.GraphicsControllerFactory;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.gwt2.plugin.graphicsediting.client.object.GeometryEditable;
import org.geomajas.gwt2.client.map.MapPresenter;

/**
 * Factory for the {@link GeometryEditController}.
 * 
 * @author Jan De Moerloose
 * @since 2.0.0
 * 
 */
@Api(allMethods = true)
public class GeometryEditControllerFactory implements GraphicsControllerFactory {

	private final MapPresenter mapPresenter;

	private final double offsetX;

	private final double offsetY;

	/**
	 * Default constructor.
	 * @param mapPresenter
	 */
	public GeometryEditControllerFactory(MapPresenter mapPresenter) {
		this(mapPresenter, 1.3, 1.3);
	}

	/**
	 * Constructor with offset parameters for offset of pencil image.
	 * @param mapPresenter
	 * @param offsetX
	 * @param offsetY
	 */
	public GeometryEditControllerFactory(MapPresenter mapPresenter, double offsetX, double offsetY) {
		this.mapPresenter = mapPresenter;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(GeometryEditable.TYPE);
	}

	@Override
	public GraphicsController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new GeometryEditController(object, graphicsService, mapPresenter, offsetX, offsetY);
	}

}
