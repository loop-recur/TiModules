package com.looprecur.gestures;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.kroll.KrollDict;

import com.looprecur.gestures.GesturesViewProxy;


public class GesturesView extends View {
	private static final String LCAT = "GesturesView";
	private static final boolean DBG = TiConfig.LOGD;
	
    private GestureDetector gestures;
    private Matrix translate;
    private float totalAnimDx;
    private float totalAnimDy;


    public GesturesView(Context context, GesturesViewProxy proxy) {
        super(context);
        translate = new Matrix();
        gestures = new GestureDetector(context,
                new GestureListener(this, proxy));
    }

    @Override
    protected void onDraw(Canvas canvas) {
				canvas.drawColor(Color.TRANSPARENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestures.onTouchEvent(event);
    }

		private class GestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
				private static final int SWIPE_MIN_DISTANCE = 120;
				private static final int SWIPE_MAX_OFF_PATH = 250;
				private static final int SWIPE_THRESHOLD_VELOCITY = 200;

		    GesturesView view;
				GesturesViewProxy proxy;

		    public GestureListener(GesturesView view, GesturesViewProxy proxy) {
		        this.view = view;
						this.proxy = proxy;
		    }
		
				private Object getDictFromMotion(MotionEvent e) {
						Object obj = new Object();
						obj.x = e.getX();
						obj.y = e.getY();
						return obj;
				}
				
				private void putDictFromMotion(String key, MotionEvent e, KrollDict dict) {
						String prefix = key+"_coords_";
						dict.put(prefix+"x", e.getX());
						dict.put(prefix+"y", e.getY());
				}

		    @Override
		    public boolean onDown(MotionEvent e) {
						proxy.fireEvent("onDown", getDictFromMotion(e));
		        return true;
		    }

		    @Override
		    public boolean onFling(MotionEvent e1, MotionEvent e2, final float velocityX, final float velocityY) {
			    final float distanceTimeFactor = 0.4f;
	        final float totalDx = (distanceTimeFactor * velocityX / 2);
	        final float totalDy = (distanceTimeFactor * velocityY / 2);
					String direction = null;
	
					KrollDict dict = new KrollDict();
					
					try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    direction = "left";
										
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
										direction = "right";
                }
								dict.put("direction", direction);
								proxy.fireEvent("onSwipe", dict);
            } catch (Exception e) {
                // nothing
            }

						putDictFromMotion("start", e1, dict);
						putDictFromMotion("end", e2, dict);
						dict.put("x", totalDx);
						dict.put("y", totalDy);
						proxy.fireEvent("onFling", dict);
		        return true;
		    }

		    @Override
		    public boolean onDoubleTap(MotionEvent e) {
						proxy.fireEvent("onDoubleTap", getDictFromMotion(e));
		        return true;
		    }

		    @Override
		    public void onLongPress(MotionEvent e) {
					proxy.fireEvent("onLongPress", getDictFromMotion(e));
		    }

		    @Override
		    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
						KrollDict dict = new KrollDict();
						putDictFromMotion("start", e1, dict);
						putDictFromMotion("end", e2, dict);
						dict.put("x", distanceX);
						dict.put("y", distanceY);
						proxy.fireEvent("onScroll", dict);
		        return true;
		    }

		    @Override
		    public void onShowPress(MotionEvent e) {
						proxy.fireEvent("onShowPress", getDictFromMotion(e));
		    }

		    @Override
		    public boolean onSingleTapUp(MotionEvent e) {
						proxy.fireEvent("onSingleTapUp", getDictFromMotion(e));
		        return false;
		    }

		    @Override
		    public boolean onDoubleTapEvent(MotionEvent e) {
						proxy.fireEvent("onDoubleTap", getDictFromMotion(e));
		        return false;
		    }

		    @Override
		    public boolean onSingleTapConfirmed(MotionEvent e) {
						proxy.fireEvent("onSingleTapConfirmed", getDictFromMotion(e));
		        return false;
		    }

		}

}

