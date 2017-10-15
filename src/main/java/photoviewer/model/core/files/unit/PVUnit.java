package photoviewer.model.core.files.unit;

import photoviewer.model.core.files.unit.data.content.Content;
import photoviewer.model.core.files.unit.data.content.ContentFactory;
import photoviewer.model.core.files.unit.data.content.ContentIO;
import photoviewer.model.core.files.unit.data.info.Info;

import java.io.*;

public abstract class PVUnit implements Serializable {
    private transient ContentFactory contentFactory = ContentFactory.makeContentFactory();
    transient protected Content content;

    private Info info;

    public PVUnit(File source) throws IOException {
        this.content = contentFactory.createContent(ContentIO.read(source));
        this.info = new Info(
                source.getName().substring(0,
                        source.getName().lastIndexOf(".")));

    }

    public PVUnit(PVUnit source) {
        this.content = contentFactory.createContent(source.getContent());
        this.info = source.getInfo();
    }

    public void saveFile(File outputFile) {
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.err.println("File to output is not found");
        }
    }

    public void exportContent(File outputFile) {
        try {
            ContentIO.write(this.content, outputFile);
        } catch (IOException e) {
            System.err.println("File to export is not found");
        }
    }

    public Object getContent() {
        return this.content.getContent();
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
