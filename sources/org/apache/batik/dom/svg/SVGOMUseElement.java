/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.dom.svg;

import java.lang.ref.WeakReference;

import org.apache.batik.dom.AbstractDocument;

import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGAnimatedLength;
import org.w3c.dom.svg.SVGAnimatedString;
import org.w3c.dom.svg.SVGElementInstance;
import org.w3c.dom.svg.SVGUseElement;

/**
 * This class implements {@link org.w3c.dom.svg.SVGUseElement}.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class SVGOMUseElement
    extends    SVGURIReferenceGraphicsElement
    implements SVGUseElement {

    /**
     * The reference to the x attribute.
     */
    protected transient WeakReference xReference;

    /**
     * The reference to the y attribute.
     */
    protected transient WeakReference yReference;

    /**
     * The reference to the width attribute.
     */
    protected transient WeakReference widthReference;

    /**
     * The reference to the height attribute.
     */
    protected transient WeakReference heightReference;

    /**
     * Creates a new SVGOMUseElement object.
     */
    protected SVGOMUseElement() {
    }

    /**
     * Creates a new SVGOMUseElement object.
     * @param prefix The namespace prefix.
     * @param owner The owner document.
     */
    public SVGOMUseElement(String prefix, AbstractDocument owner) {
        super(prefix, owner);
    }

    /**
     * <b>DOM</b>: Implements {@link org.w3c.dom.Node#getLocalName()}.
     */
    public String getLocalName() {
        return SVG_USE_TAG;
    }

    /**
     * <b>DOM</b>: Implements {@link org.w3c.dom.svg.SVGUseElement#getX()}.
     */
    public SVGAnimatedLength getX() {
	SVGAnimatedLength result;
	if (xReference == null ||
	    (result = (SVGAnimatedLength)xReference.get()) == null) {
	    result = new SVGOMAnimatedLength(this, null, SVG_X_ATTRIBUTE, null);
	    xReference = new WeakReference(result);
	}
	return result;
    } 

    /**
     * <b>DOM</b>: Implements {@link org.w3c.dom.svg.SVGUseElement#getY()}.
     */
    public SVGAnimatedLength getY() {
	SVGAnimatedLength result;
	if (yReference == null ||
	    (result = (SVGAnimatedLength)yReference.get()) == null) {
	    result = new SVGOMAnimatedLength(this, null, SVG_Y_ATTRIBUTE, null);
	    yReference = new WeakReference(result);
	}
	return result;
    } 

    /**
     * <b>DOM</b>: Implements {@link org.w3c.dom.svg.SVGUseElement#getWidth()}.
     */
    public SVGAnimatedLength getWidth() {
	SVGAnimatedLength result;
	if (widthReference == null ||
	    (result = (SVGAnimatedLength)widthReference.get()) == null) {
	    result = new SVGOMAnimatedLength(this, null, SVG_WIDTH_ATTRIBUTE, null);
	    widthReference = new WeakReference(result);
	}
	return result;
    } 

    /**
     * <b>DOM</b>: Implements {@link
     * org.w3c.dom.svg.SVGUseElement#getHeight()}.
     */
    public SVGAnimatedLength getHeight() {
	SVGAnimatedLength result;
	if (heightReference == null ||
	    (result = (SVGAnimatedLength)heightReference.get()) == null) {
	    result = new SVGOMAnimatedLength(this, null, SVG_HEIGHT_ATTRIBUTE, null);
	    heightReference = new WeakReference(result);
	}
	return result;
    } 

    /**
     * <b>DOM</b>: Implements {@link
     * org.w3c.dom.svg.SVGUseElement#getInstanceRoot()}.
     */
    public SVGElementInstance getInstanceRoot() {
	throw new RuntimeException(" !!! TODO: SVGOMUseElement.getInstanceRoot()");
    }
 
    /**
     * <b>DOM</b>: Implements {@link
     * org.w3c.dom.svg.SVGUseElement#getAnimatedInstanceRoot()}.
     */
    public SVGElementInstance getAnimatedInstanceRoot() {
	throw new RuntimeException(" !!! TODO: SVGOMUseElement.getAnimatedInstanceRoot()");
    }

    /**
     * Returns a new uninitialized instance of this object's class.
     */
    protected Node newNode() {
        return new SVGOMUseElement();
    }
}
