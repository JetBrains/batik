/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.svggen;

import java.util.Map;
import java.util.Hashtable;
import java.util.Set;
import java.util.HashSet;

import org.w3c.dom.*;

/**
 * Describes an SVG font
 *
 * @author <a href="mailto:vincent.hardy@eng.sun.com">Vincent Hardy</a>
 * @version $Id$
 * @see             org.apache.batik.svggen.SVGFont
 */
public class SVGFontDescriptor implements SVGDescriptor, SVGSyntax {
    private String fontSize;
    private String fontWeight;
    private String fontStyle;
    private String fontFamily;

    /**
     * Constructor
     */
    public SVGFontDescriptor(String fontSize,
                             String fontWeight,
                             String fontStyle,
                             String fontFamily){
        if (fontSize == null ||
            fontWeight == null ||
            fontStyle == null ||
            fontFamily == null)
            throw new SVGGraphics2DRuntimeException(ErrorConstants.ERR_FONT_NULL);

        this.fontSize = fontSize;
        this.fontWeight = fontWeight;
        this.fontStyle = fontStyle;
        this.fontFamily = fontFamily;
    }

    public Map getAttributeMap(Map attrMap){
        if(attrMap == null)
            attrMap = new Hashtable();

        attrMap.put(SVG_FONT_SIZE_ATTRIBUTE, fontSize);
        attrMap.put(SVG_FONT_WEIGHT_ATTRIBUTE, fontWeight);
        attrMap.put(SVG_FONT_STYLE_ATTRIBUTE, fontStyle);
        attrMap.put(SVG_FONT_FAMILY_ATTRIBUTE, fontFamily);

        return attrMap;
    }

    public Set getDefinitionSet(Set defSet){
        if(defSet == null)
            defSet = new HashSet();

        return defSet;
    }
}
