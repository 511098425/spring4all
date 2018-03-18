package com.qzj.ddq.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public interface ZookeeperWatcher extends Watcher {
    @Override
    void process(WatchedEvent watchedEvent);

    /**
     * 创建zookeeper服务器节点
     */
    public void createNode();

    /**
     * 删除zookeeper服务器节点
     */
    public void deleteNode();

    /**
     * 获取子节点,并返回
     */
    public List<String> getChildrenNode();

    /**
     * 获取子节点，不返回
     */
    public void getChildrenNode2();

    /**
     * 同步获取节点中的数据
     */
    public byte[] getData();

    /**
     * 异步获取节点中的数据
     */
    public void getData2();

    /**
     * 向zookeeper服务器中的指定节点添加认证信息
     * 在创建节点中的acl权限中有ip和digest
     */
    public void addAuthInfo();

    /**
     * 同步判断节点是否存在
     */
    public void isExist();

    /**
     * 异步判断节点是否存在
     */
    public void isExist2();

    /**
     * 同步设置节点中的数据
     */
    public void setData();

    /**
     * 异步设置节点中的数据
     */
    public void setData2();
}
