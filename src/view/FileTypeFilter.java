package view;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter {

	private String extension;
	private String description;
	
	public FileTypeFilter(String extension, String description) {
		super();
		this.extension = extension;
		this.description = description;
	}

	@Override
	public boolean accept(File f) {
		if(f.isDirectory() || f.getName().endsWith(this.extension)) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return this.description + " (" + this.extension + ")";
	}
	
}