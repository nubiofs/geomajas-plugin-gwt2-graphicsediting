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
package org.geomajas.gwt2.plugin.graphicsediting.example.client.sample;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.gwt2.client.GeomajasImpl;
import org.geomajas.gwt2.client.GeomajasServerExtension;
import org.geomajas.gwt2.client.event.MapInitializationEvent;
import org.geomajas.gwt2.client.event.MapInitializationHandler;
import org.geomajas.gwt2.client.map.MapConfiguration;
import org.geomajas.gwt2.client.map.MapPresenter;
import org.geomajas.gwt2.client.widget.MapLayoutPanel;
import org.geomajas.gwt2.example.base.client.sample.SamplePanel;
import org.geomajas.gwt2.plugin.graphicsediting.example.client.annotation.AnnotationToolBar;
import org.geomajas.gwt2.plugin.graphicsediting.example.client.annotation.SetAnnotationPresenterImpl;

/**
 * Sample panel.
 * 
 * @author Jan De Moerloose
 * 
 */
public class GraphicsEditingExample implements SamplePanel, MapInitializationHandler {

	protected DockLayoutPanel rootElement;

	private final MapPresenter mapPresenter;

	@UiField
	protected SimpleLayoutPanel contentPanel;

	@Override
	public Widget asWidget() {
		// return root layout element
		return rootElement;
	}

	/**
	 * UI binder interface.
	 * 
	 * @author Jan De Moerloose
	 */
	interface MyBinder extends UiBinder<DockLayoutPanel, GraphicsEditingExample> {

	}

	private static final MyBinder UIBINDER = GWT.create(MyBinder.class);

	public GraphicsEditingExample(EXAMPLE exampleType) {
		rootElement = UIBINDER.createAndBindUi(this);

		// Initialize the map
		mapPresenter = GeomajasImpl.getInstance().createMapPresenter();
		mapPresenter.getEventBus().addMapInitializationHandler(this);
		// Initialize the map, and return the layout:
		GeomajasServerExtension.getInstance().initializeMap(mapPresenter, "appGraphicsEditing", "mapGraphicsEditing");
		MapLayoutPanel mapLayout = new MapLayoutPanel();
		mapLayout.setPresenter(mapPresenter);
		AnnotationToolBar toolbar = new AnnotationToolBar();
		new SetAnnotationPresenterImpl(toolbar, GeomajasImpl.getInstance().getEventBus(),
				mapPresenter, exampleType);
		mapPresenter.getWidgetPane().add(toolbar.asWidget());
		contentPanel.setWidget(mapLayout);
	}

	@Override
	public void onMapInitialized(MapInitializationEvent event) {
		mapPresenter.getConfiguration().setHintValue(MapConfiguration.ANIMATION_TIME, 300);
	}

	/**
	 * To distinguish between examples of editing via a controller registration or a an action registration.
	 */
	public enum EXAMPLE {
		CONTROLLER, ACTION;
	}
}