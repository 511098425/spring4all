package com.qzj.ddq.zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * Created by Administrator on 2017/7/26.
 */
public class IStringCallback implements AsyncCallback.StringCallback {
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        StringBuilder sb = new StringBuilder();
        sb.append("i=").append(i).append("\n");
        sb.append("s=").append(s).append("\n");
        sb.append("o=").append(o).append("\n");
        sb.append("sl=").append(s1);
        System.out.println(sb.toString());
    }
}
