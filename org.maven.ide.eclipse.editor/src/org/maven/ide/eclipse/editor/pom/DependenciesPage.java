/*******************************************************************************
 * Copyright (c) 2008 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.maven.ide.eclipse.editor.pom;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.maven.ide.components.pom.Dependencies;
import org.maven.ide.components.pom.DependencyManagement;
import org.maven.ide.components.pom.PomFactory;
import org.maven.ide.eclipse.MavenPlugin;
import org.maven.ide.eclipse.editor.composites.DependenciesComposite;

/**
 * @author Eugene Kuleshov
 */
public class DependenciesPage extends MavenPomEditorPage {
  
  private DependenciesComposite dependenciesComposite;
  
  public DependenciesPage(MavenPomEditor pomEditor) {
    super(pomEditor, MavenPlugin.PLUGIN_ID + ".pom.dependencies", "Dependencies");
  }

  public void dispose() {
    if(dependenciesComposite!=null) {
      dependenciesComposite.dispose();
    }
    super.dispose();
  }
  
  protected void createFormContent(IManagedForm managedForm) {
    FormToolkit toolkit = managedForm.getToolkit();
    
    ScrolledForm form = managedForm.getForm();
    form.setText("Dependencies");
    
    form.getBody().setLayout(new GridLayout(1, true));

    dependenciesComposite = new DependenciesComposite(form.getBody(), SWT.NONE);
    dependenciesComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    toolkit.adapt(dependenciesComposite);
    
//    form.pack();

    super.createFormContent(managedForm);
  }

  public void loadData() {
    ValueProvider<Dependencies> dependenciesProvider = new ValueProvider<Dependencies>() {
      public Dependencies getValue() {
        return model.getDependencies();
      }

      public Dependencies create(EditingDomain editingDomain, CompoundCommand compoundCommand) {
        Dependencies dependencies = PomFactory.eINSTANCE.createDependencies();
        Command createDependenciesCommand = SetCommand.create(editingDomain, model,
            POM_PACKAGE.getModel_Dependencies(), dependencies);
        compoundCommand.append(createDependenciesCommand);
        return dependencies;
      }
    };

    ValueProvider<DependencyManagement> dependencyManagementProvider = new ValueProvider<DependencyManagement>() {
      public DependencyManagement getValue() {
        return model.getDependencyManagement();
      }

      public DependencyManagement create(EditingDomain editingDomain, CompoundCommand compoundCommand) {
        DependencyManagement dependencyManagement = PomFactory.eINSTANCE.createDependencyManagement();
        Command createDependenciesCommand = SetCommand.create(editingDomain, model,
            POM_PACKAGE.getModel_DependencyManagement(), dependencyManagement);
        compoundCommand.append(createDependenciesCommand);
        return dependencyManagement;
      }
    };

    dependenciesComposite.loadData(this, dependenciesProvider, dependencyManagementProvider);
  }
  
  public void updateView(Notification notification) {
    if(dependenciesComposite!=null) {
      dependenciesComposite.updateView(this, notification);
    }
  }
  
}
