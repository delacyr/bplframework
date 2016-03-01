package org.eclipse.bpmn2.modeler.ui.wizards;

import javax.annotation.CheckForNull;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class InstantiationWizardPage1 extends WizardPage{
	@CheckForNull
	private TreeViewer viewer;

	IProject project;
	IFile bpmt_file;
	Text fileName;
	Text folderName;

	public InstantiationWizardPage1(String project, IProject res) {
		super(project);
		this.project = res;
		setTitle("Selecting a BPL Project");
	}

	@Override
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		composite.setLayout(layout);
		Label label = new Label(composite, SWT.NULL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label.setText("&Folder:");
		folderName = new Text(composite, SWT.BORDER | SWT.SINGLE);
		GridData gd_folderName = new GridData(GridData.FILL_HORIZONTAL);
		gd_folderName.verticalAlignment = SWT.CENTER;
		folderName.setLayoutData(gd_folderName);
		
		if (project != null) {
			folderName.setText(ResourcesPlugin.getWorkspace().getRoot().getProject(project.getName()).getLocation().toOSString());
		} else {
			updateStatus("Select a valid BPL Project");
			folderName.setText(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
		}
		
		removeFolderNameSegments();
		
		Button browseButton = new Button(composite, SWT.NONE);
		browseButton.setText("     Browse...     ");
		browseButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				String selectedPath = openDirectoryDialog();
				if (selectedPath != null) {
					folderName.setText(selectedPath);
					removeFolderNameSegments();
				}
			}
		});
		new Label(composite, SWT.NONE);
	    
		//Checar se é um projeto válido!
//		fileName.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e) {
//				checkFileName();
//			}
//		});
		folderName.addModifyListener(new ModifyListener() {	
			@Override
			public void modifyText(ModifyEvent e) {
				if (project != null && isProject()){
					viewer.setInput(ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()));
					updateStatus("Select a BPMT file");
				}
				else
					updateStatus("Select a valid BPL Project");
			}
		});
		
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		final Label label2 = new Label(composite, SWT.NULL);
		
	    label2.setText("&Select a BPMT file:");
		
		GridData gridData = new GridData(GridData.FILL_VERTICAL);
	    gridData.horizontalAlignment = SWT.CHECK;
	    gridData.widthHint = 334;
		viewer = new TreeViewer(composite);
		viewer.getTree().setLayoutData(gridData);
		viewer.setContentProvider(new FileTreeContentProvider());
		viewer.setLabelProvider(new FileTreeLabelProvider());
		if (project != null)
			viewer.setInput(ResourcesPlugin.getWorkspace().getRoot().getProject(folderName.getText()));
		viewer.expandAll();
		
		viewer.addSelectionChangedListener(
	            new ISelectionChangedListener(){
	                public void selectionChanged(SelectionChangedEvent event) {
	                    if(event.getSelection() instanceof IStructuredSelection) {
	                        IStructuredSelection selection = (IStructuredSelection)event.getSelection();            
	                        Object o = selection.getFirstElement();    

	                        if (o instanceof IFile){
	                        	IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getProject(folderName.getText()).getFolder("BusinessProcessModelTemplate");                
	                        	if (!folder.contains((IFile)o)){
	                        			updateStatus("Select a valid BPMT file");
	                        	}
	                        	else{
	                        		bpmt_file = (IFile)o;
	                        		updateStatus(null);
//	                        		System.out.println("Arquivo BPMT selecionado corretamente!");
	                        	}
	                        }else {
	                        	updateStatus("Select a valid BPMT file");
	                        }
	                    }
	                }
	            }
	    );
		
		if (project != null && !isProject())		
			updateStatus("Select a valid BPL Project");
		else
			updateStatus("Select a BPMT file");
		
	
//		Só liberar botão Next se for um projeto válido
		setPageComplete(false);		
		setControl(composite);
		new Label(composite, SWT.NONE);
	}
	
	protected boolean isProject(){
		removeFolderNameSegments();
		IProject project_temp = ResourcesPlugin.getWorkspace().getRoot().getProject(folderName.getText());
		if (project_temp != null)
			if (isBPL(project_temp))
				return true;
		return false;
	}
	
	//Verificar características necessárias para um Projeto ser BPL Project
	protected boolean isBPL(IProject project) {
		IFolder folder1 = project.getFolder("FeatureModel");
//		System.out.printf("FeatureModel is %b", folder1.exists());
		IFolder folder2 = project.getFolder("BusinessProcessDiagram");
//		System.out.printf("BusinessProcessDiagram is %b", folder1.exists());
		IFolder folder3 = project.getFolder("BusinessProcessModelTemplate");
//		System.out.printf("BusinessProcessModelTemplate is %b", folder1.exists());
		if (folder1.exists() && folder2.exists() && folder3.exists()){
//			System.out.printf("This folder is a BPL Project");
			return true;
		}	
		return false;
	}

//	protected void checkFileName() {
//		String text = fileName.getText();
//		IPath path = new Path(text);
//		if (path.isEmpty()) {
//			updateStatus("File name must be specified.");
//			return;
//		}
//		if (!path.isValidPath(text)) {
//			updateStatus(text + " is no valid path.");
//			return;
//		}
//		String fileExtension = path.getFileExtension();
//		if (fileExtension == null || !fileExtension.equals("xml")) {
//			updateStatus("New model file must have xml as file extension.");
//			return;
//		}
//		if (path.toFile().exists()) {
//			updateStatus("Selected file already exists.");
//			return;
//		}
//		updateStatus(null);
//	}
	
	private void updateStatus(String message) {
		if (message == "none")
			setErrorMessage(null);
		else
			setErrorMessage(message);
		setPageComplete(message == null);
	}

//	private String openFileDialog() {
//		FileDialog dialog = new FileDialog(getShell(), SWT.MULTI);
//		dialog.setText("New Feature Model");
//		dialog.setFileName("model.xml");
//		dialog.setFilterExtensions(new String [] {"*.xml"});
//		dialog.setFilterNames(new String[]{ "XML *.xml"});
//		dialog.setFilterPath(fileName.getText());
//		
//		return dialog.open();
//	}
	
	private String openDirectoryDialog() {
		DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.MULTI);
		dialog.setText("Select a directory");
		dialog.setFilterPath(folderName.getText());
	
		return dialog.open();
	}

	public String getProjectName() {
		return folderName.getText();
	}
	
//	public IProject getProject(){
//		return project;
//	}
	
	public void removeFolderNameSegments(){
		Path fullFolderPath = new Path(folderName.getText());
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath rootPath = root.getLocation();
		if (rootPath.isPrefixOf(fullFolderPath)) {
			// case: is file in workspace
			int matchingFirstSegments = rootPath.matchingFirstSegments(fullFolderPath);
			IPath localFolderPath = fullFolderPath.removeFirstSegments(matchingFirstSegments);
			String[] segments = localFolderPath.segments();
			localFolderPath = new Path("");
			for (String segment : segments) {
				localFolderPath = localFolderPath.append(segment);
			}
			folderName.setText(localFolderPath.toOSString());
		}
	}
	
//	private void getTree(Composite composite){
//		GridData gridData = new GridData(GridData.FILL_VERTICAL);
//	    gridData.horizontalAlignment = SWT.FILL;
//	    gridData.widthHint = 334;
//		Label label2 = new Label(composite, SWT.NULL);
//		label2.setText("&Select a BPMT file:");
//		viewer = new TreeViewer(composite);
//		viewer.getTree().setLayoutData(gridData);
//		viewer.setContentProvider(new FileTreeContentProvider());
//		viewer.setLabelProvider(new FileTreeLabelProvider());
//		viewer.setInput(ResourcesPlugin.getWorkspace().getRoot().getProject(folderName.getText()));
//	}
}
