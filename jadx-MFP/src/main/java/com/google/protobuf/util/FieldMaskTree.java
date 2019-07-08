package com.google.protobuf.util;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.util.FieldMaskUtil.MergeOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

final class FieldMaskTree {
    private static final String FIELD_PATH_SEPARATOR_REGEX = "\\.";
    private static final Logger logger = Logger.getLogger(FieldMaskTree.class.getName());
    private final Node root = new Node();

    private static final class Node {
        final SortedMap<String, Node> children;

        private Node() {
            this.children = new TreeMap();
        }
    }

    FieldMaskTree() {
    }

    FieldMaskTree(FieldMask fieldMask) {
        mergeFromFieldMask(fieldMask);
    }

    public String toString() {
        return FieldMaskUtil.toString(toFieldMask());
    }

    /* access modifiers changed from: 0000 */
    public FieldMaskTree addFieldPath(String str) {
        String[] split = str.split(FIELD_PATH_SEPARATOR_REGEX);
        if (split.length == 0) {
            return this;
        }
        Node node = this.root;
        boolean z = false;
        for (String str2 : split) {
            if (!z && node != this.root && node.children.isEmpty()) {
                return this;
            }
            if (node.children.containsKey(str2)) {
                node = (Node) node.children.get(str2);
            } else {
                Node node2 = new Node();
                node.children.put(str2, node2);
                node = node2;
                z = true;
            }
        }
        node.children.clear();
        return this;
    }

    /* access modifiers changed from: 0000 */
    public FieldMaskTree mergeFromFieldMask(FieldMask fieldMask) {
        for (String addFieldPath : fieldMask.getPathsList()) {
            addFieldPath(addFieldPath);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public FieldMask toFieldMask() {
        if (this.root.children.isEmpty()) {
            return FieldMask.getDefaultInstance();
        }
        ArrayList arrayList = new ArrayList();
        getFieldPaths(this.root, "", arrayList);
        return FieldMask.newBuilder().addAllPaths(arrayList).build();
    }

    private void getFieldPaths(Node node, String str, List<String> list) {
        String str2;
        if (node.children.isEmpty()) {
            list.add(str);
            return;
        }
        for (Entry entry : node.children.entrySet()) {
            if (str.isEmpty()) {
                str2 = (String) entry.getKey();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(".");
                sb.append((String) entry.getKey());
                str2 = sb.toString();
            }
            getFieldPaths((Node) entry.getValue(), str2, list);
        }
    }

    /* access modifiers changed from: 0000 */
    public void intersectFieldPath(String str, FieldMaskTree fieldMaskTree) {
        if (!this.root.children.isEmpty()) {
            String[] split = str.split(FIELD_PATH_SEPARATOR_REGEX);
            if (split.length != 0) {
                Node node = this.root;
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String str2 = split[i];
                    if (node != this.root && node.children.isEmpty()) {
                        fieldMaskTree.addFieldPath(str);
                        return;
                    } else if (node.children.containsKey(str2)) {
                        node = (Node) node.children.get(str2);
                        i++;
                    } else {
                        return;
                    }
                }
                ArrayList<String> arrayList = new ArrayList<>();
                getFieldPaths(node, str, arrayList);
                for (String addFieldPath : arrayList) {
                    fieldMaskTree.addFieldPath(addFieldPath);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void merge(Message message, Builder builder, MergeOptions mergeOptions) {
        if (message.getDescriptorForType() != builder.getDescriptorForType()) {
            throw new IllegalArgumentException("Cannot merge messages of different types.");
        } else if (!this.root.children.isEmpty()) {
            merge(this.root, "", message, builder, mergeOptions);
        }
    }

    private void merge(Node node, String str, Message message, Builder builder, MergeOptions mergeOptions) {
        String str2;
        if (message.getDescriptorForType() == builder.getDescriptorForType()) {
            Descriptor descriptorForType = message.getDescriptorForType();
            for (Entry entry : node.children.entrySet()) {
                FieldDescriptor findFieldByName = descriptorForType.findFieldByName((String) entry.getKey());
                if (findFieldByName == null) {
                    Logger logger2 = logger;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cannot find field \"");
                    sb.append((String) entry.getKey());
                    sb.append("\" in message type ");
                    sb.append(descriptorForType.getFullName());
                    logger2.warning(sb.toString());
                } else if (!((Node) entry.getValue()).children.isEmpty()) {
                    if (findFieldByName.isRepeated() || findFieldByName.getJavaType() != JavaType.MESSAGE) {
                        Logger logger3 = logger;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Field \"");
                        sb2.append(findFieldByName.getFullName());
                        sb2.append("\" is not a singluar message field and cannot have sub-fields.");
                        logger3.warning(sb2.toString());
                    } else if (message.hasField(findFieldByName) || builder.hasField(findFieldByName)) {
                        if (str.isEmpty()) {
                            str2 = (String) entry.getKey();
                        } else {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str);
                            sb3.append(".");
                            sb3.append((String) entry.getKey());
                            str2 = sb3.toString();
                        }
                        merge((Node) entry.getValue(), str2, (Message) message.getField(findFieldByName), builder.getFieldBuilder(findFieldByName), mergeOptions);
                    }
                } else if (findFieldByName.isRepeated()) {
                    if (mergeOptions.replaceRepeatedFields()) {
                        builder.setField(findFieldByName, message.getField(findFieldByName));
                    } else {
                        for (Object addRepeatedField : (List) message.getField(findFieldByName)) {
                            builder.addRepeatedField(findFieldByName, addRepeatedField);
                        }
                    }
                } else if (findFieldByName.getJavaType() == JavaType.MESSAGE) {
                    if (mergeOptions.replaceMessageFields()) {
                        if (!message.hasField(findFieldByName)) {
                            builder.clearField(findFieldByName);
                        } else {
                            builder.setField(findFieldByName, message.getField(findFieldByName));
                        }
                    } else if (message.hasField(findFieldByName)) {
                        builder.getFieldBuilder(findFieldByName).mergeFrom((Message) message.getField(findFieldByName));
                    }
                } else if (message.hasField(findFieldByName) || !mergeOptions.replacePrimitiveFields()) {
                    builder.setField(findFieldByName, message.getField(findFieldByName));
                } else {
                    builder.clearField(findFieldByName);
                }
            }
            return;
        }
        throw new IllegalArgumentException(String.format("source (%s) and destination (%s) descriptor must be equal", new Object[]{message.getDescriptorForType(), builder.getDescriptorForType()}));
    }
}
