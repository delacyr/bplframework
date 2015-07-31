package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class NewBPLProject extends Wizard implements INewWizard {

	private static final String WIZARD_NAME = "Create a new BPL Framework Project";
	private static final String TITLE_PAGE = "BPL Framework Project";
	private static final String DESCRIPTION_PAGE = "Create a new BPL Framework Project";
	private static final String PAGE_NAME = "Create a new BPL Frameork Project";
	
	private WizardNewProjectCreationPage _page;
	
	public NewBPLProject() {
		setWindowTitle(WIZARD_NAME);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {}

	@Override
	public boolean performFinish() {
		IProgressMonitor progressMonitor = new NullProgressMonitor();
	    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    IProject project = root.getProject(_page.getProjectName());
	    try {
	        project.create(progressMonitor);
	        project.open(progressMonitor);
	        
	    
	         
	         IFolder firstFolder = project.getFolder("FeatureModel");
	         firstFolder.create(true, true, progressMonitor);
	         IFolder secondFolder = project.getFolder("BusinessProcessDiagram");
	         secondFolder.create(true, true, progressMonitor);
	         IFolder thirdFolder = project.getFolder("BusinessProcessModelTemplate");
	         thirdFolder.create(true, true, progressMonitor);
	               
	    } catch (CoreException e) {
	        e.printStackTrace();
	    }
		return true;
	}
	
	@Override
	public void addPages() {
	    super.addPages();
	    _page = new WizardNewProjectCreationPage(PAGE_NAME);
	    _page.setTitle(TITLE_PAGE);
	    _page.setDescription(DESCRIPTION_PAGE);
	    addPage(_page);
	}

}
