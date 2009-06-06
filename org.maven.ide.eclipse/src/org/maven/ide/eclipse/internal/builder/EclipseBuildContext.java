/*******************************************************************************
 * Copyright (c) 2008 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.maven.ide.eclipse.internal.builder;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import org.sonatype.plexus.build.incremental.EmptyScanner;
import org.codehaus.plexus.util.Scanner;

/**
 * EclipseBuildContext
 *
 * @author igor
 */
public class EclipseBuildContext extends AbstractEclipseBuildContext {

  protected final IProject project;
  
  public EclipseBuildContext(IProject project, Map<String, Object> context) {
    super(context);
    this.project = project;
  }

  public boolean hasDelta(String relpath) {
    return true;
  }

  @SuppressWarnings("unchecked")
  public boolean hasDelta(List relpath) {
    return true;
  }

  public Scanner newDeleteScanner(File basedir) {
    return new EmptyScanner(basedir);
  }

  public Scanner newScanner(File basedir) {
    IPath relpath = getRelativePath(basedir);
    return new ResourceScanner(project.findMember(relpath));
  }

  public Scanner newScanner(File basedir, boolean ignoreDelta) {
    return newScanner(basedir);
  }

  protected IResource getBaseResource() {
    return project;
  }

  public boolean isIncremental() {
    return false;
  }

}
