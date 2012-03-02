/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package com.looprecur.gestures;

import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import com.looprecur.gestures.TiGesturesView;
import android.app.Activity;

@Kroll.proxy(creatableInModule=GesturesModule.class)

public class GesturesViewProxy extends TiViewProxy
{
	
	private static final String LCAT = "GesturesViewProxy";
	private static final boolean DBG = TiConfig.LOGD;
	
	public GesturesViewProxy() {
		super();
	}
	
	// Handle creation options
	@Override
	public void handleCreationDict(HashMap options) {
		super.handleCreationDict(options);
	}

	@Override
	public TiUIView createView(Activity activity) {
		return new TiGesturesView(this);
	}
}
