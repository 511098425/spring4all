package com.qzj.ddq.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Curator {
    private CuratorFramework curatorFramework = getCuratorFramework();

    public static void main(String[] args) throws InterruptedException {
        Curator curator = new Curator();
        CuratorFramework curatorFramework = curator.getCuratorFramework();
        curatorFramework.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 创建curator
     */
    public CuratorFramework getCuratorFramework() {
        //RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //RetryPolicy retryPolicy = new RetryNTimes(5, 1000);
        //CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.1.105:2181",5000,5000, retryPolicy);

        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.179.8:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        return curatorFramework;
    }

    /**
     * 创建节点
     */
    public void create() {
        try {
            curatorFramework.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath("/curator", "curator".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建节点并且设置acl权限
     */
    public void createWithAcl() {
        ACL aclIp = new ACL(ZooDefs.Perms.READ, new Id("ip", "192.168.179.8"));
        ACL aclDigest = null;
        try {
            aclDigest = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("curator:curator")));
            ArrayList<ACL> acls = new ArrayList<ACL>();
            acls.add(aclDigest);
            acls.add(aclIp);
            try {
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(acls).forPath("/curator", "curator".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除节点
     */
    public void delete() {
        try {
            curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath("/curator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取子节点
     */
    public List<String> getChildren() {
        List<String> cList = Collections.EMPTY_LIST;
        try {
            cList = curatorFramework.getChildren().forPath("/curator");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cList;
    }

    /**
     * 获取节点中的数据
     */
    public byte[] getData() {

        try {
            return curatorFramework.getData().storingStatIn(new Stat()).forPath("/curator");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断节点是否存在
     */
    public void isExist() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        curatorFramework.start();
        try {
            curatorFramework.checkExists().inBackground(new BackgroundCallback() {
                public void processResult(CuratorFramework arg0, CuratorEvent arg1)
                        throws Exception {
                    // TODO Auto-generated method stub
                    Stat stat = arg1.getStat();
                    System.out.println(stat);
                    System.out.println(arg1.getContext());
                }
            }, "curator", executorService).forPath("/curator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监控节点
     */
    public void listener() {
        curatorFramework.start();
        final NodeCache nodeCache = new NodeCache(curatorFramework, "/curator");
        try {
            nodeCache.start();
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    // TODO Auto-generated method stub
                    byte[] data = nodeCache.getCurrentData().getData();
                    System.out.println("new data:" + new String(data));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监控当前节点以及所有子节点
     */
    public void listen() {
        curatorFramework.start();
        final PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/curator", true);
        try {
            pathChildrenCache.start();
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {

                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
                        throws Exception {
                    // TODO Auto-generated method stub
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            System.out.println("CHILD_ADDED:" + event.getData());
                            break;
                        case CHILD_UPDATED:
                            System.out.println("CHILD_UPDATED:" + event.getData());
                            break;
                        case CHILD_REMOVED:
                            System.out.println("CHILD_REMOVED:" + event.getData());
                            break;
                        default:
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
