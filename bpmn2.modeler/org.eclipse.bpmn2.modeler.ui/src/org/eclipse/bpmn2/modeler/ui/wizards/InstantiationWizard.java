package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.VrProcess;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2MultiPageEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

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
			
//			URI modelURI = URI.createPlatformResourceURI(path_instantiatingFolder.toString(), true);
//			IEditorPart editorPart = BPMN2Editor.openEditor(modelURI);
//			IEditorInput editorInput = editorPart.getEditorInput();
//			final BPMN2Editor editorBPMN = BPMN2Editor.findOpenEditor(editorPart, editorInput);
//			
//			TransactionalEditingDomain domain = editorBPMN.getEditingDomain();
//			domain.getCommandStack().execute(new RecordingCommand(domain) {
//			   public void doExecute() {
//				   BPMNDiagram bpmnDiagram = editorBPMN.getBpmnDiagram();
//					BPMNPlane plane = bpmnDiagram.getPlane();
//					BaseElement be = plane.getBpmnElement();
//					VrProcess vrProcess = null;
//					if (be instanceof VrProcess){
//						vrProcess = (VrProcess)be;
//						vrProcess.setPhase("instantiation");
//					}
//			   }
//			});
			
		return true;
	}

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
