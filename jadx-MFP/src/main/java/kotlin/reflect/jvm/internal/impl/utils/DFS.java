package kotlin.reflect.jvm.internal.impl.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

public class DFS {

    public static abstract class AbstractNodeHandler<N, R> implements NodeHandler<N, R> {
        public void afterChildren(N n) {
        }

        public boolean beforeChildren(N n) {
            return true;
        }
    }

    public static abstract class CollectingNodeHandler<N, R, C extends Iterable<R>> extends AbstractNodeHandler<N, C> {
        @NotNull
        protected final C result;

        protected CollectingNodeHandler(@NotNull C c) {
            this.result = c;
        }

        @NotNull
        public C result() {
            return this.result;
        }
    }

    public interface Neighbors<N> {
        @NotNull
        Iterable<? extends N> getNeighbors(N n);
    }

    public interface NodeHandler<N, R> {
        void afterChildren(N n);

        boolean beforeChildren(N n);

        R result();
    }

    public static abstract class NodeHandlerWithListResult<N, R> extends CollectingNodeHandler<N, R, LinkedList<R>> {
        protected NodeHandlerWithListResult() {
            super(new LinkedList());
        }
    }

    public interface Visited<N> {
        boolean checkAndMarkVisited(N n);
    }

    public static class VisitedWithSet<N> implements Visited<N> {
        private final Set<N> visited;

        public VisitedWithSet() {
            this(new HashSet());
        }

        public VisitedWithSet(@NotNull Set<N> set) {
            this.visited = set;
        }

        public boolean checkAndMarkVisited(N n) {
            return this.visited.add(n);
        }
    }

    public static <N, R> R dfs(@NotNull Collection<N> collection, @NotNull Neighbors<N> neighbors, @NotNull Visited<N> visited, @NotNull NodeHandler<N, R> nodeHandler) {
        for (N doDfs : collection) {
            doDfs(doDfs, neighbors, visited, nodeHandler);
        }
        return nodeHandler.result();
    }

    public static <N, R> R dfs(@NotNull Collection<N> collection, @NotNull Neighbors<N> neighbors, @NotNull NodeHandler<N, R> nodeHandler) {
        return dfs(collection, neighbors, new VisitedWithSet(), nodeHandler);
    }

    public static <N> Boolean ifAny(@NotNull Collection<N> collection, @NotNull Neighbors<N> neighbors, @NotNull final Function1<N, Boolean> function1) {
        final boolean[] zArr = new boolean[1];
        return (Boolean) dfs(collection, neighbors, new AbstractNodeHandler<N, Boolean>() {
            public boolean beforeChildren(N n) {
                if (((Boolean) function1.invoke(n)).booleanValue()) {
                    zArr[0] = true;
                }
                return !zArr[0];
            }

            public Boolean result() {
                return Boolean.valueOf(zArr[0]);
            }
        });
    }

    public static <N> void doDfs(@NotNull N n, @NotNull Neighbors<N> neighbors, @NotNull Visited<N> visited, @NotNull NodeHandler<N, ?> nodeHandler) {
        if (visited.checkAndMarkVisited(n) && nodeHandler.beforeChildren(n)) {
            for (Object doDfs : neighbors.getNeighbors(n)) {
                doDfs(doDfs, neighbors, visited, nodeHandler);
            }
            nodeHandler.afterChildren(n);
        }
    }
}
