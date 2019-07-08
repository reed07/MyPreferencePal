package org.apache.commons.io;

public class FileDeleteStrategy {
    public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();
    public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy("Normal");
    private final String name;

    static class ForceFileDeleteStrategy extends FileDeleteStrategy {
        ForceFileDeleteStrategy() {
            super("Force");
        }
    }

    protected FileDeleteStrategy(String str) {
        this.name = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileDeleteStrategy[");
        sb.append(this.name);
        sb.append("]");
        return sb.toString();
    }
}
