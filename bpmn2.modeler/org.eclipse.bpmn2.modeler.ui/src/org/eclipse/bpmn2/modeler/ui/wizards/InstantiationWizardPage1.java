package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
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

	private CheckboxTreeViewer viewer;

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
		layout.numColumns = 2;
		layout.verticalSpacing = 9;
		composite.setLayout(layout);
		new Label(composite, SWT.NONE);
		folderName = new Text(composite, SWT.BORDER | SWT.SINGLE);
		GridData gd_folderName = new GridData(GridData.FILL_HORIZONTAL);
		gd_folderName.grabExcessHorizontalSpace = false;
		gd_folderName.verticalAlignment = SWT.CENTER;
		folderName.setLayoutData(gd_folderName);
		
		if (project != null) {
			folderName.setText(ResourcesPlugin.getWorkspace().getRoot().getProject(project.getName()).getLocation().toOSString());
		} else {
			updateStatus("Select a valid BPL Project");
			folderName.setText(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
		}
		
		removeFolderNameSegments();
	    
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
		final Label label2 = new Label(composite, SWT.NULL);
		
	    label2.setText("&Select a BPMT file:");
		new Label(composite, SWT.NONE);
		
		GridData gridData = new GridData(GridData.FILL_VERTICAL);
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.widthHint = 334;
		viewer = new CheckboxTreeViewer(composite);
		ViewerFilter filterViewer = new ViewerFilter() {
		    @Override
		    public boolean select(Viewer viewer, Object parentElement, Object element) {
		        System.out.println(parentElement.toString()+" -> "+element.toString());
		        if (element.toString().contains("BusinessProcessModelTemplate") || element.toString().contains("Instantiated")){
			        if (element instanceof IFolder){
			        	IFolder folder = (IFolder) element;
			        	try {
							if (folder.members().length > 0)
								return true;
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			        if (element instanceof IFile){
						return true;	
			        }
		        }
     
		        //do my stuff to know if element need to be fitered or not
		        return false;
		    }
		        
		};
		
		viewer.getTree().setLayoutData(gridData);
		viewer.setContentProvider(new FileTreeContentProvider());
		viewer.setLabelProvider(new FileTreeLabelProvider());
		viewer.addFilter(filterViewer);
		if (project != null)
			viewer.setInput(ResourcesPlugin.getWorkspace().getRoot().getProject(folderName.getText()));
		viewer.expandAll();
		
		
		viewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				// TODO Auto-generated method stub
				if (event.getChecked()){
					viewer.setSubtreeChecked(event.getElement(), event.getChecked());
					updateStatus(null);
				}
				else{
					viewer.setSubtreeChecked(event.getElement(), event.getChecked());
					if (viewer.getCheckedElements().length == 0)
						updateStatus("Select at least one Business Process Model Template");
				}
			}
			
		});
		
		if (project != null && !isProject())		
			updateStatus("Select a valid BPL Project");
		else
			updateStatus("Select a BPMT file");
		
	
//		Só liberar botão Next se for um projeto válido
		setPageComplete(false);		
		setControl(composite);
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
	
	public Object[] getCheckedElements(){
		return viewer.getCheckedElements();
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
