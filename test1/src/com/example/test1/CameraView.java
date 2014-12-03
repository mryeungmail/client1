package com.example.test1;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
	private Camera camera;
	private SurfaceHolder surfaceHolder;

	@SuppressWarnings("deprecation")
	public CameraView(Context context, Camera camera) {
		super(context);

		this.camera = camera;
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if(camera != null)
			camera.startPreview();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if(camera == null || surfaceHolder == null)
			return;
		
		Camera.Parameters cameraParameters = camera.getParameters();
		
		List<Camera.Size> previewSizes = cameraParameters.getSupportedPreviewSizes();
		
		Camera.Size best = previewSizes.get(0);
		for(Camera.Size cs : previewSizes) {
			if(cs.width * cs.height > best.width * best.height) {
				best = cs;
			}
		}
		
		cameraParameters.setPreviewSize(best.width, best.height);
		camera.setParameters(cameraParameters);
		
		try {
			camera.setPreviewDisplay(surfaceHolder);
		} catch (IOException e) {
			e.printStackTrace();
			camera.release();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public void onPause() {
		camera.stopPreview();
		camera.release();
		camera = null;
	}
}
