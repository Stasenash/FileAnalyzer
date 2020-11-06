import Modules.IModule;
import Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Collection;

@SpringBootApplication
public class FileAnalyzerApplication {
	private static File file = new File("C:/Users/stasi/Desktop/etc/orat.io.jpg");
	private static Collection<IModule> modules;
	private static Service service = new Service();

	@Autowired
	public FileAnalyzerApplication(Collection<IModule> modules) {
		FileAnalyzerApplication.modules = modules;
	}
	public FileAnalyzerApplication(){}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(FileAnalyzerApplication.class, args);

		String fileExtension = getFileExtension();

		for (IModule module : modules) {
			if (module.checkFileExtension(fileExtension))
				service.showOperationChoice(module, file);
		}
	}

	private static String getFileExtension() {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else if (file.isDirectory())
			return "directory";
		return "Unknown object";
	}
}
