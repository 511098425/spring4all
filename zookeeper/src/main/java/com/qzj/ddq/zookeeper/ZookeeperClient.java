package com.qzj.ddq.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 * 由ZookeeperClient连接Zookeeper服务器
 */
public class ZookeeperClient implements ZookeeperWatcher {
    private final static String ZOOKEEPERADDR = "192.168.179.8:2181";
    private final static int SESSION_TIMEOUT = 6000;
    private static ZooKeeper zooKeeper;
    private volatile Watcher.Event.KeeperState keeperState = Watcher.Event.KeeperState.SyncConnected;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.179.8:2181", SESSION_TIMEOUT, new ZookeeperClient());
        System.out.println(zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getPath() == null) {
                /**
                 * 当与zookeeper服务器连接并且监控到没有任何事件变化
                 * 可以执行下面的任何方法
                 * eg:createNode();
                 * 当监控到的节点发生变化时可以继续执行方法，下面的例子表示 继续获取子节点
                 */
                try {
                    zooKeeper.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    zooKeeper.getChildren(watchedEvent.getPath(), true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void createNode() {
        /**
         * 异步创建node
         */
        zooKeeper.create("/dubbo", "dubbo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new IStringCallback(), "异步创建node");
        /**
         * 同步创建node
         */
        try {
            zooKeeper.create("/dubbo", "dubbo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * 权限模式(scheme): ip, digest
	     * 授权对象(ID)
	     * 		ip权限模式:  具体的ip地址
	     * 		digest权限模式: username:Base64(SHA-1(username:password))
	     * 权限(permission): create(C), DELETE(D),READ(R), WRITE(W), ADMIN(A)
	     * 		注：单个权限，完全权限，复合权限
	     *
	     * 权限组合: scheme + ID + permission
	     *
	     */
        ACL aclIp = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.179.8"));
        try {
            ACL aclDigest = new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.READ, new Id("digest", DigestAuthenticationProvider.generateDigest("root:root")));
            List<ACL> acls = new ArrayList<ACL>();
            acls.add(aclIp);
            acls.add(aclDigest);
            try {
                zooKeeper.create("/dubbo", "dubbo".getBytes(), acls, CreateMode.EPHEMERAL);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除zookeeper服务器上的节点时注意版本
     * 当version为-1时表明指定所以版本
     */
    @Override
    public void deleteNode() {
        /**
         *
         * 同步删除
         */
        try {
            zooKeeper.delete("/dubbo", -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        /**
         * 异步删除,ctx表示回调方法返回的参数可以为null
         */
        zooKeeper.delete("/dubbo", -1, new IVoidCallback(), "异步删除");
    }

    /**
     * 获取子节点 无返回值
     */
    @Override
    public void getChildrenNode2() {
        zooKeeper.getChildren("/", true, new IChildren2Callback(), null);
    }

    /**
     * 获取子节点  并返回值
     *
     * @return
     */
    @Override
    public List<String> getChildrenNode() {
        List<String> nodeList = Collections.EMPTY_LIST;
        try {
            nodeList = zooKeeper.getChildren("/", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nodeList;
    }

    /**
     * 从节点中获取数据
     */
    @Override
    public byte[] getData() {
        byte[] data = null;
        try {
            data = zooKeeper.getData("/Zookeeper", true, new Stat());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }

    /***
     * 异步获取节点中的数据是没有返回值的
     */
    @Override
    public void getData2() {
        byte[] data = null;
        zooKeeper.getData("/Zookeeper", true, new IDataCallback(), "异步获取节点数据");
    }

    /**
     * 向zookeeper服务器中的指定节点添加认证信息
     * 在创建节点中的acl权限中有ip和digest
     */
    @Override
    public void addAuthInfo() {
        zooKeeper.addAuthInfo("digest", "root:root".getBytes());
    }

    /**
     * 同步判断节点是否存在
     */
    @Override
    public void isExist() {
        try {
            zooKeeper.exists("/dubbo", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步判断节点是否存在
     */
    @Override
    public void isExist2() {
        zooKeeper.exists("/dubbo", true, new IStatCallback(), "判断节点是否存在");
    }

    /**
     * 同步设置节点中的数据
     */
    @Override
    public void setData() {
        try {
            zooKeeper.setData("/dubbo", "dubbo".getBytes(), -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步设置节点中的数据
     */
    @Override
    public void setData2() {
        zooKeeper.setData("/dubbo", "dubbo".getBytes(), -1, new IStatCallback(), null);
    }
}
