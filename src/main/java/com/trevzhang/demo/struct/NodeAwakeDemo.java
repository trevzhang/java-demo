package com.trevzhang.demo.struct;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 节点依赖数据处理
 *
 * @author trevor
 * @since 2023/11/17 10:56
 **/
public class NodeAwakeDemo {

    public static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        initNodes();

        nodes.get(0).execute(new Object());
    }

    private static void initNodes() throws InterruptedException {
        Node a = new Node("a");

        Node b = new Node(Lists.newCopyOnWriteArrayList(Arrays.asList(a)), "b");

        Node c = new Node(Lists.newCopyOnWriteArrayList(Arrays.asList(a)), "c");

        Node d = new Node(Lists.newCopyOnWriteArrayList(Arrays.asList(b, c)), "d");

        nodes.addAll(Lists.newCopyOnWriteArrayList(Arrays.asList(a, b, c, d)));
    }

    public static class Node {

        String name;

        // 运行结果
        Object data;
        List<Node> depends;
        Map<Node, Boolean> executeFinishMap = new HashMap<>();

        public boolean dependOn(Node node) {
            return depends.contains(node);
        }

        public void execute(Object sourceData) throws InterruptedException {
            // do something
            data = new Object();
            System.out.println("Node: " + name + " executed.");

            awakeNexts();
        }

        public void awakeNexts() throws InterruptedException {
            Iterator<Node> iterator = nodes.iterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();

                if (node.dependOn(this)) {

                    node.executeFinishMap.put(this, true);

                    boolean flag = true;
                    for (Node n : node.executeFinishMap.keySet()) {
                        if (node.executeFinishMap.get(n) == null || !node.executeFinishMap.get(n)) {
                            flag = false;
                        }
                    }
                    // 所有依赖都执行过了
                    if (flag && node.data == null) {
                        node.execute(this.data);
                    }
                }
            }
        }

        private void initMap() {
            for (Node depend : depends) {
                executeFinishMap.put(depend, false);
            }
        }

        Node(List<Node> depends, String name) {
            this.depends = depends;
            this.name = name;
            initMap();
        }

        Node(String name) {
            this.name = name;
            this.depends = new ArrayList<>();
            initMap();
        }
    }
}