package com.airbnb.lottie.animation.content;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.os.Build.VERSION;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@TargetApi(19)
public class MergePathsContent implements GreedyContent, PathContent {
    private final Path firstPath = new Path();
    private final MergePaths mergePaths;
    private final String name;
    private final Path path = new Path();
    private final List<PathContent> pathContents = new ArrayList();
    private final Path remainderPath = new Path();

    public MergePathsContent(MergePaths mergePaths2) {
        if (VERSION.SDK_INT >= 19) {
            this.name = mergePaths2.getName();
            this.mergePaths = mergePaths2;
            return;
        }
        throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
    }

    public void absorbContent(ListIterator<Content> listIterator) {
        while (listIterator.hasPrevious()) {
            if (listIterator.previous() == this) {
                break;
            }
        }
        while (listIterator.hasPrevious()) {
            Content content = (Content) listIterator.previous();
            if (content instanceof PathContent) {
                this.pathContents.add((PathContent) content);
                listIterator.remove();
            }
        }
    }

    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < this.pathContents.size(); i++) {
            ((PathContent) this.pathContents.get(i)).setContents(list, list2);
        }
    }

    public Path getPath() {
        this.path.reset();
        switch (this.mergePaths.getMode()) {
            case Merge:
                addPaths();
                break;
            case Add:
                opFirstPathWithRest(Op.UNION);
                break;
            case Subtract:
                opFirstPathWithRest(Op.REVERSE_DIFFERENCE);
                break;
            case Intersect:
                opFirstPathWithRest(Op.INTERSECT);
                break;
            case ExcludeIntersections:
                opFirstPathWithRest(Op.XOR);
                break;
        }
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    private void addPaths() {
        for (int i = 0; i < this.pathContents.size(); i++) {
            this.path.addPath(((PathContent) this.pathContents.get(i)).getPath());
        }
    }

    @TargetApi(19)
    private void opFirstPathWithRest(Op op) {
        this.remainderPath.reset();
        this.firstPath.reset();
        for (int size = this.pathContents.size() - 1; size >= 1; size--) {
            PathContent pathContent = (PathContent) this.pathContents.get(size);
            if (pathContent instanceof ContentGroup) {
                ContentGroup contentGroup = (ContentGroup) pathContent;
                List pathList = contentGroup.getPathList();
                for (int size2 = pathList.size() - 1; size2 >= 0; size2--) {
                    Path path2 = ((PathContent) pathList.get(size2)).getPath();
                    path2.transform(contentGroup.getTransformationMatrix());
                    this.remainderPath.addPath(path2);
                }
            } else {
                this.remainderPath.addPath(pathContent.getPath());
            }
        }
        PathContent pathContent2 = (PathContent) this.pathContents.get(0);
        if (pathContent2 instanceof ContentGroup) {
            ContentGroup contentGroup2 = (ContentGroup) pathContent2;
            List pathList2 = contentGroup2.getPathList();
            for (int i = 0; i < pathList2.size(); i++) {
                Path path3 = ((PathContent) pathList2.get(i)).getPath();
                path3.transform(contentGroup2.getTransformationMatrix());
                this.firstPath.addPath(path3);
            }
        } else {
            this.firstPath.set(pathContent2.getPath());
        }
        this.path.op(this.firstPath, this.remainderPath, op);
    }
}
