/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.css.svg;

import org.apache.batik.css.CSSDOMExceptionFactory;
import org.apache.batik.css.value.AbstractValueFactory;
import org.apache.batik.css.value.ImmutableValue;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.Parser;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * This class provides a factory for the 'glyph-orientation-vertical'
 * property values.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class GlyphOrientationVerticalFactory
    extends GlyphOrientationHorizontalFactory {
    /**
     * Creates a new GlyphOrientationVerticalFactory object.
     * @param p The CSS parser used to parse the CSS texts.
     */
    public GlyphOrientationVerticalFactory(Parser p) {
	super(p);
    }

    /**
     * Returns the name of the property handled.
     */
    public String getPropertyName() {
	return SVGValueConstants.CSS_GLYPH_ORIENTATION_VERTICAL_PROPERTY;
    }
    
    /**
     * Creates a value from a lexical unit.
     * @param lu The SAC lexical unit used to create the value.
     */
    public ImmutableValue createValue(LexicalUnit lu) throws DOMException {
	if (lu.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
	    if (!lu.getStringValue().equalsIgnoreCase(CSS_AUTO_VALUE)) {
		throw CSSDOMExceptionFactory.createDOMException
		    (DOMException.INVALID_ACCESS_ERR,
		     "invalid.identifier",
		     new Object[] { lu.getStringValue() });
	    }
	    return AUTO_VALUE;
	}
	return super.createValue(lu);
    }

    /**
     * Creates and returns a new string value.
     * @param type   A string code as defined in CSSPrimitiveValue. The string
     *               code can only be a string unit type.
     * @param value  The new string value. 
     */
    public ImmutableValue createStringValue(short type, String value)
	throws DOMException {
	if (type == CSSPrimitiveValue.CSS_IDENT &&
	    value.equalsIgnoreCase(CSS_AUTO_VALUE)) {
	    return AUTO_VALUE;
	}
	throw CSSDOMExceptionFactory.createDOMException
	    (DOMException.INVALID_ACCESS_ERR,
	     "invalid.identifier",
	     new Object[] { value });
    }
}
