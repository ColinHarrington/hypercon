/*
 * @(#)Blurb.java
 *
 * $Date: 2014-06-01 21:16:06 -0500 (Sun, 01 Jun 2014) $
 *
 * Copyright (c) 2014 by Jeremy Wood.
 * All rights reserved.
 *
 * The copyright of this software is owned by Jeremy Wood. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Jeremy Wood. For details see accompanying license terms.
 * 
 * This software is probably, but not necessarily, discussed here:
 * https://javagraphics.java.net/
 * 
 * That site should also contain the most recent official version
 * of this software.  (See the SVN repository for more details.)
 */
package com.bric.blog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** This is used by the WriteBlurbsJob to write a "blurb"
 * about apps/projects.
 * <p>This was made possible with the help of <a href="http://tutorials.jenkov.com/java-reflection/annotations.html">this article</a>.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Blurb {
	/** This is filename of the jar to be created (without the file extension). 
	 * For example "ColorPicker" will result in the file "ColorPicker.jar". 
	 * If this returns an empty string then no jar is built. */
	String filename();
	
	/** This is the short human-readable title of this blurb. This usually is exactly the same as a blog article's title. */
	String title();
	
	/** A short string identifying the release date of this project. */
	String releaseDate();
	
	/** This is one or two short paragraphs that appear below the title of this blurb. This should give a short summary/preview
	 * of the article identified in the "link" attribute.
	 */
	String summary();
	
	/** This optional attribute is used to create the HTML page that hosts the applet of this project (if possible). This
	 * may be one sentence or it may be several paragraphs of HTML-formatted text.
	 * <p>Although applets are indefinitely discontinued, this may be useful to keep around
	 * for other mediums someday in the future.
	 */
	String instructions() default "";
	
	/** This optional attribute links to an article (or other site) further discussing this project. */
	String link() default "";
	
	/** This optional attribute describes why a project was scrapped. If this is an empty
	 * string then the project is not scrapped.
	 */
	String scrapped() default "";
}
