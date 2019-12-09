package com.zhangchun.zookeeper;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author zhangchun
 */
public class TestZookeeper {

    private String connectString = "hadoop1:2181,hadoop2:2181,hadoop3:2181";
    private int sessionTimeOut = 2000;
    private ZooKeeper zkClient;

    @Before
    public void m1() throws Exception{

        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
//                //监听方法，一旦监听的内容有变化，就执行这个process
//                System.out.println("------start------");
//                try {
//                    List<String> children = zkClient.getChildren("/sanguo", true);
//                    System.out.println(children);
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("-------end------");
            }
        });

//        Thread.sleep(Integer.MAX_VALUE);

    }


    @Test
    public void m2()throws Exception{

        //创建文件夹
        String result = zkClient.create("/sanguo/weiguo", "caocao".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(result);
    }

    @Test
    public void m3()throws Exception{

        //监听节点的上下线。每个节点已经在集群上创建了自己对应的文件夹，并且是"短暂"文件夹
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //监听方法，一旦监听的内容有变化，就执行这个process
                System.out.println("------start------");
                try {
                    List<String> children = zkClient.getChildren("/servers", true);
                    System.out.println(children);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-------end------");
            }
        });

        Thread.sleep(Integer.MAX_VALUE);

    }


}
