/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.ext.awt.image.rendered;

import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;


import org.apache.batik.ext.awt.image.renderable.Light;
import org.apache.batik.ext.awt.image.renderable.BumpMap;
import org.apache.batik.ext.awt.image.GraphicsUtil;

/**
 * 
 * @author <a href="mailto:vincent.hardy@eng.sun.com">Vincent Hardy</a>
 * @version $Id$
 */
public class DiffuseLightingRed extends AbstractRed{
    /**
     * Diffuse lighting constant
     */
    private double kd;

    /**
     * Light used for diffuse lighting
     */
    private Light light;

    /**
     * BumpMap source
     */
    private BumpMap bumpMap;

    /**
     * Device space to user space scale factors, along
     * each axis
     */
    private double scaleX, scaleY;

    /**
     * LitRegion
     */
    private Rectangle litRegion;

    public DiffuseLightingRed(double kd,
                              Light light,
                              BumpMap bumpMap,
                              Rectangle litRegion,
                              double scaleX, double scaleY){
        this.kd = kd;
        this.light = light;
        this.bumpMap = bumpMap;
        this.litRegion = litRegion;
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        ColorModel cm = GraphicsUtil.Linear_sRGB_Unpre;

        SampleModel sm = 
            cm.createCompatibleSampleModel(litRegion.width,
                                           litRegion.height);
                                             
        init((CachableRed)null, litRegion, cm, sm,
             litRegion.x, litRegion.y, null);
    }

    public WritableRaster copyData(WritableRaster wr){
        final double[] lightColor = light.getColor();
        
        final int w = wr.getWidth();
        final int h = wr.getHeight();
        final int minX = wr.getMinX();
        final int minY = wr.getMinY();

        final DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
        final int[] pixels = db.getBankData()[0];

        final SinglePixelPackedSampleModel sppsm;
        sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
        
        final int offset = 
            (db.getOffset() +
             sppsm.getOffset(minX-wr.getSampleModelTranslateX(), 
                             minY-wr.getSampleModelTranslateY()));

        final int scanStride = sppsm.getScanlineStride();
        final int adjust = scanStride - w;
        int p = offset;
        int r=0, g=0, b=0;
        int i=0, j=0;

        // System.out.println("Getting diffuse red : " + minX + "/" + minY + "/" + w + "/" + h);
        double x = scaleX*minX;
        double y = scaleY*minY;
        double NL = 0;

        // final double[] L = new double[3];
        double[] N;
        final double[][][] NA = bumpMap.getNormalArray(minX, minY, w, h);

        if(!light.isConstant()){
            double[] L;
            final double[][][] LA = light.getLightMap(x, y, scaleX, scaleY, w, h, NA);

            for(i=0; i<h; i++){
                for(j=0; j<w; j++){
                    // Get Normal 
                    N = NA[i][j];
                    
                    // Get Light Vector
                    // light.getLight(x, y, N[3], L);
                    L = LA[i][j];
                    
                    NL = 255.*kd*(N[0]*L[0] + N[1]*L[1] + N[2]*L[2]);
                    
                    r = (int)(NL*lightColor[0]);
                    g = (int)(NL*lightColor[1]);
                    b = (int)(NL*lightColor[2]);
                    
                    // If any high bits are set we are not in range.
                    // If the highest bit is set then we are negative so
                    // clamp to zero else we are > 255 so clamp to 255.
                    if ((r & 0xFFFFFF00) != 0)
                        r = ((r & 0x80000000) != 0)?0:255;
                    if ((g & 0xFFFFFF00) != 0)
                        g = ((g & 0x80000000) != 0)?0:255;
                    if ((b & 0xFFFFFF00) != 0)
                        b = ((b & 0x80000000) != 0)?0:255;
                    
                    pixels[p++] = (0xff000000
                                   |
                                   r << 16
                                   |
                                   g << 8
                                   |
                                   b);
                    
                    x += scaleX;
                }
                p += adjust;
                x = scaleX*minX;
                y += scaleY;
            }
        }
        else{
            // System.out.println(">>>>>>>> Processing constant light ...");
            final double[] L = new double[3];

            // Constant light
            light.getLight(0, 0, 0, L);

            for(i=0; i<h; i++){
                for(j=0; j<w; j++){
                    // Get Normal 
                    N = NA[i][j];
                    
                    NL = 255.*kd*(N[0]*L[0] + N[1]*L[1] + N[2]*L[2]);
                    
                    r = (int)(NL*lightColor[0]);
                    g = (int)(NL*lightColor[1]);
                    b = (int)(NL*lightColor[2]);
                    
                    // If any high bits are set we are not in range.
                    // If the highest bit is set then we are negative so
                    // clamp to zero else we are > 255 so clamp to 255.
                    if ((r & 0xFFFFFF00) != 0)
                        r = ((r & 0x80000000) != 0)?0:255;
                    if ((g & 0xFFFFFF00) != 0)
                        g = ((g & 0x80000000) != 0)?0:255;
                    if ((b & 0xFFFFFF00) != 0)
                        b = ((b & 0x80000000) != 0)?0:255;
                    
                    pixels[p++] = (0xff000000
                                   |
                                   r << 16
                                   |
                                   g << 8
                                   |
                                   b);
                    
                    x += scaleX;
                }
                p += adjust;
                x = scaleX*minX;
                y += scaleY;
            }
        }
        
        return wr;
    }

}
