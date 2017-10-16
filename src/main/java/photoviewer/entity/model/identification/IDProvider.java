package photoviewer.entity.model.identification;

public class IDProvider {
    private static int allIDs = 0;
    public static int requestID() {
        return allIDs++;
    }
}
