package com.qzj.ddq.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class IChildren2Callback implements AsyncCallback.Children2Callback {
    @Override
    public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
        StringBuffer stringBuffer = new StringBuffer().append("\n");
        stringBuffer.append("i=").append(i).append("\n");
        stringBuffer.append("o=").append(o).append("\n");
        stringBuffer.append("list=").append(list).append("\n");
        stringBuffer.append("stat=").append(stat);
        System.out.println(stringBuffer.toString());
    }
}
