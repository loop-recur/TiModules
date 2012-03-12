/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package com.looprecur.gestures;

import java.io.IOException;

import org.appcelerator.titanium.TiApplication;

import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.view.TiUIView;

import android.content.Context;

import com.looprecur.gestures.GesturesViewProxy;


public class TiGesturesView extends TiUIView
{
	private static final String LCAT = "GesturesView";
	private static final boolean DBG = TiConfig.LOGD;

	public TiGesturesView(final GesturesViewProxy proxy) {
		super(proxy);
		Context context = TiApplication.getInstance().getApplicationContext();
		GesturesView view = new GesturesView(context, proxy);
		setNativeView(view);
	}

}
