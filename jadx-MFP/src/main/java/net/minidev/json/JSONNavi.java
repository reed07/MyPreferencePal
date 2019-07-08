package net.minidev.json;

import java.util.Stack;
import net.minidev.json.parser.ContainerFactory;

public class JSONNavi<T> {
    private static final JSONStyle ERROR_COMPRESS = new JSONStyle(2);
    private ContainerFactory factory;
    private boolean failure;
    private String failureMessage;
    private Object missingKey;
    private Stack<Object> path;
    private boolean readonly;
    private T root;
    private Stack<Object> stack;

    public JSONNavi() {
        this(ContainerFactory.FACTORY_ORDERED);
    }

    public JSONNavi(ContainerFactory containerFactory) {
        this.stack = new Stack<>();
        this.path = new Stack<>();
        this.failure = false;
        this.readonly = false;
        this.missingKey = null;
        this.factory = containerFactory;
        this.readonly = false;
    }

    public String toString() {
        if (this.failure) {
            return JSONValue.toJSONString(this.failureMessage, ERROR_COMPRESS);
        }
        return JSONValue.toJSONString(this.root);
    }
}
