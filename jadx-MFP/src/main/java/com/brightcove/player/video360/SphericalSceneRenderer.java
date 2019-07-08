package com.brightcove.player.video360;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.brightcove.player.C;
import com.brightcove.player.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;

@TargetApi(17)
public class SphericalSceneRenderer {
    private static final float GOOGLE_VR_ZOOM_OUT = 1.0f;
    private static final int SPHERE_INDICES_PER_VERTEX = 1;
    private static final float SPHERE_RADIUS = 500.0f;
    public static final int SPHERE_SLICES = 180;
    private int aPositionLocation;
    private int aTextureCoordLocation;
    private float[] mvpMatrix = new float[16];
    private float[] pvMatrix = new float[16];
    private ShaderProgram shaderProgram;
    private Sphere sphere;
    private int uMVPMatrixLocation;
    private int uTextureMatrixLocation;
    private boolean vrMode = false;

    public SphericalSceneRenderer(Context context) {
        this.shaderProgram = new ShaderProgram(GlUtil.readRawTextFile(context, R.raw.video_vertex_shader), GlUtil.readRawTextFile(context, R.raw.video_fragment_shader));
        this.aPositionLocation = this.shaderProgram.getAttribute("aPosition");
        this.uMVPMatrixLocation = this.shaderProgram.getUniform("uMVPMatrix");
        this.uTextureMatrixLocation = this.shaderProgram.getUniform("uTextureMatrix");
        this.aTextureCoordLocation = this.shaderProgram.getAttribute("aTextureCoord");
        GLES20.glDisable(2929);
        Sphere sphere2 = new Sphere(180, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, SPHERE_RADIUS, 1);
        this.sphere = sphere2;
        GLES20.glUseProgram(this.shaderProgram.getShaderHandle());
        GLES20.glEnableVertexAttribArray(this.aPositionLocation);
        GlUtil.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.aPositionLocation, 3, 5126, false, this.sphere.getVerticesStride(), this.sphere.getVertices());
        GlUtil.checkGlError("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(this.aTextureCoordLocation);
        GlUtil.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.aTextureCoordLocation, 2, 5126, false, this.sphere.getVerticesStride(), this.sphere.getVertices().duplicate().position(3));
        GlUtil.checkGlError("glVertexAttribPointer");
    }

    public boolean isVrMode() {
        return this.vrMode;
    }

    public void setVrMode(boolean z) {
        this.vrMode = z;
    }

    public void onDrawFrame(int i, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        GLES20.glClear(C.DASH_ROLE_CAPTION_FLAG);
        GLES20.glBindTexture(36197, i);
        Matrix.translateM(fArr, 0, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED);
        GLES20.glUniformMatrix4fv(this.uTextureMatrixLocation, 1, false, fArr, 0);
        if (this.vrMode) {
            int[] iArr = new int[4];
            GLES20.glGetIntegerv(2978, iArr, 0);
            GlUtil.checkGlError("Failed to get current view port size!");
            int i2 = iArr[2];
            int i3 = iArr[3];
            int i4 = i2 / 2;
            Matrix.perspectiveM(Arrays.copyOf(fArr4, fArr4.length), 0, 70.0f, ((float) i4) / ((float) i3), 1.0f, 1000.0f);
            GLES20.glViewport(0, 0, i4, i3);
            drawScene(fArr2, fArr3, fArr4);
            GLES20.glViewport(i4, 0, i4, i3);
            drawScene(fArr2, fArr3, fArr4);
            GLES20.glViewport(0, 0, i2, i3);
            return;
        }
        drawScene(fArr2, fArr3, fArr4);
    }

    private void drawScene(float[] fArr, float[] fArr2, float[] fArr3) {
        Matrix.multiplyMM(this.pvMatrix, 0, fArr3, 0, fArr2, 0);
        Matrix.multiplyMM(this.mvpMatrix, 0, this.pvMatrix, 0, fArr, 0);
        GLES20.glUniformMatrix4fv(this.uMVPMatrixLocation, 1, false, this.mvpMatrix, 0);
        this.sphere.draw();
    }

    public void release() {
        this.shaderProgram.release();
    }
}
