/*******************************************************************************
 * Copyright (c) 2008-2010 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Sonatype, Inc. - initial API and implementation
 *******************************************************************************/

package com.cmaxinc.test;

import java.net.URL;
import java.net.URLClassLoader;

import junit.framework.TestCase;

public class TestLibraryClass extends TestCase {

	public void testLibary(){
		URLClassLoader cl = (URLClassLoader) this.getClass().getClassLoader();
		URL[] urls = cl.getURLs();
		for (URL url : urls) {
			if(url.toExternalForm().indexOf("hibernate")!=-1)
				System.out.println(url.toExternalForm());
		}
		System.out.println("");

		new VersionPrinter();
	}
}
