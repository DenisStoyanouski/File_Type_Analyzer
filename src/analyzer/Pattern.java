package analyzer;

public class Pattern {

    private final String priority;
    private final String pattern;
    private final String fileType;

    public Pattern(String priority, String pattern, String fileType) {
        this.priority = priority;
        this.pattern = pattern;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return String.format("%s;\"%s\";\"%s\"", priority, pattern, fileType);
    }

    public String getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFileType() {
        return fileType;
    }
}
