package view;
import java.io.File;

import javax.swing.filechooser.FileFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class FileTypeFilter.
 */
public class FileTypeFilter extends FileFilter {

	/** The extension. */
	private String extension;
	
	/** The description. */
	private String description;
	
	/**
	 * Instantiates a new file type filter.
	 *
	 * @param extension the extension
	 * @param description the description
	 */
	public FileTypeFilter(String extension, String description) {
		super();
		this.extension = extension;
		this.description = description;
	}

	/**
	 * Accept.
	 *
	 * @param f the f
	 * @return true, if successful
	 */
	@Override
	public boolean accept(File f) {
		if(f.isDirectory() || f.getName().endsWith(this.extension)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return this.description + " (" + this.extension + ")";
	}
	
}