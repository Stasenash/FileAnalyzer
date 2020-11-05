package Modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageModule implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("jpg");
    }

    public void showOptions() {
        System.out.println("1 - Get exif information");
        System.out.println("2 - Get image resolution");
        System.out.println("3 - Get image orientation");
    }

    @Override
    public void executeOption(File file, int optionIndex) throws Exception {
        ImageModule.class.getMethod("executeOption" + optionIndex, File.class).invoke(this, file);
    }

    public void executeOption1(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        System.out.println("EXIF information:");
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }

    public void executeOption2(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Image resolution: " + width + "x" + height + "px");
    }

    public void executeOption3(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        String orientation = "not defined";
        if (width == height)
            orientation = "square";
        else
            orientation = width > height ? "horizontal" : "vertical";
        System.out.println("Image orientation: " + orientation);
    }
}
