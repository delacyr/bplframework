package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class FileTreeLabelProvider implements ILabelProvider {

	public FileTreeLabelProvider() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IProject) {
            String text = ((IProject) element).getName();
            return text;
        }
        if (element instanceof IFolder) {
            String text = ((IFolder) element).getName();
            return text;
        }
        if (element instanceof IFile) {
            String text = ((IFile) element).getName();
            return text;
        }
		return null;
	}


	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}
	


}
