package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
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
	
			Object[] elements = page.getCheckedElements();
			
			for (Object element: elements){
				if (element instanceof IFile){
					IFile file = (IFile) element;
							
					IFolder instantiatingSubFolder = instantiatingFolder.getFolder(file.getParent().getName());
					
					try {
						instantiatingSubFolder.create(true, true, progressMonitor);
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					IPath path_instantiatingSubFolder = instantiatingSubFolder.getFullPath();
					path_instantiatingSubFolder = path_instantiatingSubFolder.append(file.getName());
					
					try {
						file.copy(path_instantiatingSubFolder, true, progressMonitor);
							
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					org.eclipse.emf.common.util.URI modelURI;
					modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(path_instantiatingSubFolder.toString(), true);
					BPMN2Editor.openEditor(modelURI);
					
					BPMN2Editor editor = BPMN2Editor.getActiveEditor();
					final BPMNDiagram bpmnDiagram = editor.getBpmnDiagram();
					TransactionalEditingDomain editingDomain = BPMN2Editor.getActiveEditor().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							bpmnDiagram.setPhase("EPN");
						}
					});
					
					editor.doSave(null);
					
				}
			}
				
		return true;
	}

	@Override
	public void addPages() {
		setWindowTitle("BPL Instantiation");
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
