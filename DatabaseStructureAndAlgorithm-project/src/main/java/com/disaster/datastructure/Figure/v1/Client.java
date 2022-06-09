package com.disaster.datastructure.Figure.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 以无向图为例
实现图的方式由两种：
1.邻接矩阵： 使用数组 vertices 存储顶点，邻接矩阵 edges 存储边； edges[i][j]代表节点 i + 和 节点 j + 1之间是否有边。
2.邻接表： 使用数组 vertices 存储顶点，邻接表 edges存储边。 edges为一个二维容器，第一维 i 代表顶点索引，
第二维 edges[i] 存储此顶点对应的边集和；例如 edges[0] = [1, 2, 3, 4] 代表 vertices[0] 的边集合为 [1, 2, 3, 4]。

邻接矩阵的大小只与节点数量有关，即 N^2，其中N为节点数量。因此，当边数量明显少于节点数量时，使用邻接矩阵存储图会造成较大的内存浪费。
因此，邻接表 适合存储稀疏图（顶点较多、边较少）； 邻接矩阵 适合存储稠密图（顶点较少、边较多）。
 */
public class Client {
    public static void main(String[] args) {
        while (true){
            try {
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
            }catch (Exception e){
                System.out.println("你好");
                e.printStackTrace();
            }
        }

    }
    //通过一个一维数组加一个集合实现
    public static void funV1(){
        int[] vertices = {1, 2, 3, 4, 5};
        List<List<Integer>> edges = new ArrayList<>();//邻接表中下标表示数组中的顶点，
        List<Integer> edge_1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> edge_2 = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> edge_3 = new ArrayList<>(Arrays.asList(0, 4));
        List<Integer> edge_4 = new ArrayList<>(Arrays.asList(0, 1, 4));
        List<Integer> edge_5 = new ArrayList<>(Arrays.asList(0, 2, 3));
        edges.add(edge_1);
        edges.add(edge_2);
        edges.add(edge_3);
        edges.add(edge_4);
        edges.add(edge_5);
    }
    //通过一维数组加一个二维数组实现
    public static void funV2(){
        int[] vertices = {1, 2, 3, 4, 5};//一维数组表示
        int[][] edges = {{0, 1, 1, 1, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {1, 0, 1, 1, 0}};
    }
}