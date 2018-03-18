package com.qzj.ddq.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * Created by Administrator on 2017/7/27.
 */
public class IDataCallback implements AsyncCallback.DataCallback {
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("i=").append(i).append("\n");
        stringBuffer.append("s=").append(s).append("\n");
        stringBuffer.append("bytes=").append(bytes).append("\n");
        stringBuffer.append("stat=").append(stat);
        System.out.println(stringBuffer.toString());
    }
}
