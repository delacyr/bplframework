package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

@SuppressWarnings("restriction")
public class InstantiationWizard extends Wizard implements INewWizard{
	private InstantiationWizardPage1 page;
	
	public boolean performFinish() {
		IProgressMonitor progressMonitor = new NullProgressMonitor();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(page.folderName.getText());
	        try {
				project.open(progressMonitor);
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	        

			IFolder instantiatingFolder = project.getFolder("Instantiating");
			try {
				instantiatingFolder.create(true, true, progressMonitor);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			IFile bpmt_file = page.bpmt_file;
			IPath path_instantiatingFolder = project.getFolder("Instantiating").getFullPath();
			path_instantiatingFolder = path_instantiatingFolder.append(bpmt_file.getName());
			
			try {
				bpmt_file.copy(path_instantiatingFolder, true, progressMonitor);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		return true;
	}
//
//	private void open(IFile file) {
////		IWorkbenchWindow dw = FMUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
////		IWorkbenchPage page = dw.getActivePage();
//		if (page != null) {
//			IContentType contentType = null;
//			try {
//				IContentDescription description = file
//						.getContentDescription();
//				if (description != null) {
//					contentType = description.getContentType();
//				}
//				IEditorDescriptor desc = null;
//				if (contentType != null) {
//					desc = PlatformUI.getWorkbench().getEditorRegistry()
//							.getDefaultEditor(file.getName(), contentType);
//				} else {
//					desc = PlatformUI.getWorkbench().getEditorRegistry()
//							.getDefaultEditor(file.getName());
//				}
//
//				if (desc != null) {
////					page.openEditor(new FileEditorInput(file), desc.getId());
//				}
//			} catch (CoreException e) {
////				FMUIPlugin.getDefault().logError(e);
//			}
//		}
//	}

	@Override
	public void addPages() {
		setWindowTitle("BPL Instatiation");
		addPage(page);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		Object obj = selection.getFirstElement();
		if (obj instanceof IResource) {
			page = new InstantiationWizardPage1("", ((IResource) obj).getProject());
		} else if (obj instanceof JavaElement) {
			JavaElement javaElement = (JavaElement)obj;
			page = new InstantiationWizardPage1("", javaElement.getResource().getProject());
		} else {
			page = new InstantiationWizardPage1("", null);
		}
		
	}


}
