package Modules;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class Mp3Module implements IModule {


    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("mp3");
    }

    public void showOptions() {
        System.out.println("1 - Get track duration");
        System.out.println("2 - Get track name");
        System.out.println("3 - Get track author");
    }


    public void executeOption(File file, int optionIndex) throws Exception {
        Mp3Module.class.getMethod("executeOption" + optionIndex, File.class).invoke(this, file);
    }

    public void executeOption1(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = fileFormat.properties();
            String key = "duration";
            Long microSec = (Long) properties.get(key);
            int sec = (int) (microSec / (1000 * 1000));
            System.out.println("Duration of the song: " + sec + " seconds");
        }
    }

    public void executeOption2(File file) throws IOException, ImageProcessingException {
        Metadata metadata = Mp4MetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            if (directory.getName().equals("File")) {
                System.out.println("Track name: " + directory.getDescription(1));
            }
        }
    }

    public void executeOption3(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = fileFormat.properties();
            String key = "author";
            String author = (String) properties.get(key);
            System.out.println("Author: " + author);
        }
    }
}