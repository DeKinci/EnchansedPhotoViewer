package photoviewer.entity.model.core.unit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import photoviewer.entity.model.core.unit.data.content.Content;
import photoviewer.entity.model.core.unit.data.content.ContentFactory;
import photoviewer.entity.model.core.unit.data.content.ContentIO;
import photoviewer.entity.model.core.unit.data.info.Info;

import java.io.*;

public abstract class PVUnit implements Serializable {
    private transient ContentFactory contentFactory = ContentFactory.makeContentFactory();
    transient protected Content content;
    Logger log;

    private Info info;

    PVUnit(File source) throws IOException {
        this.content = contentFactory.createContent(ContentIO.read(source));
        this.info = new Info(
                source.getName().substring(0,
                        source.getName().lastIndexOf(".")));
        setLogger();
    }

    PVUnit(PVUnit source) {
        this.content = contentFactory.createContent(source.getContent());
        this.info = source.getInfo();
        setLogger();
    }

    public void saveFile(File outputFile) {
        tryCreatingNewFile(outputFile);
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            log.warn("File to output was not found and could not be created");
        }
    }

    public void exportContent(File outputFile) {
        tryCreatingNewFile(outputFile);
        try {
            ContentIO.write(this.content, outputFile);
        } catch (IOException e) {
            log.warn("File to export is not found and could not be created");
        }
    }

    public Object getContent() {
        return this.content.getContent();
    }

    private void setLogger() {
        log = LogManager.getLogger();
    }

    private void tryCreatingNewFile(File file) {
        try {
            if(file.createNewFile())
                log.trace("New file " + file.getName() + " created");
        } catch (IOException e) {
            log.trace(e.getMessage());
        }
    }

    public Info getInfo() {
        return this.info;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ContentIO.write(this.content, out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        content = ContentIO.read(in);
    }

    @Override
    public String toString() {
        return this.info.getName().toString();
    }
}
