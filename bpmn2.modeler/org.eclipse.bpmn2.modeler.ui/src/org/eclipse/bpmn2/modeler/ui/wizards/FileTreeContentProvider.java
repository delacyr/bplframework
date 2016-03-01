package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileTreeContentProvider implements ITreeContentProvider {
//	private InstantiationWizardPage1 page;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
//		if (page.getProject() != null)
			return getChildren(inputElement);
//		else
//			return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if (parentElement instanceof IProject) {
            IProject projects = (IProject) parentElement;
            try {
                return projects.members();
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        if (parentElement instanceof IFolder) {
            IFolder ifolder = (IFolder) parentElement;
            try {
                return ifolder.members();
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof IProject) {
            IProject projects = (IProject) element;
            return projects.getParent();
        }
        if (element instanceof IFolder) {
            IFolder folder = (IFolder) element;
            return folder.getParent();
        }
        if (element instanceof IFile) {
            IFile file = (IFile) element;
            return file.getParent();
        }
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
        if (element instanceof IProject) {
            IProject projects = (IProject) element;
            try {
                return projects.members().length > 0;
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (element instanceof IFolder) {
            IFolder folder = (IFolder) element;
            try {
                return folder.members().length > 0;
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

		return false;
	}
}
