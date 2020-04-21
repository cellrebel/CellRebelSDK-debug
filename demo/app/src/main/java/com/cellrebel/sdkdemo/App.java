package com.cellrebel.sdkdemo;

import androidx.lifecycle.LifecycleObserver;
import androidx.multidex.MultiDexApplication;

import com.cellrebel.sdk.workers.TrackingManager;

public class App extends MultiDexApplication implements LifecycleObserver {

	@Override
	public void onCreate() {
		super.onCreate();

		TrackingManager.init(this, "udhf563r8k");
	}
}
