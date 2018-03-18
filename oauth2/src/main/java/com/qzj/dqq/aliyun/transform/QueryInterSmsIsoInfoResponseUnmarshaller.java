package com.qzj.dqq.aliyun.transform;

/**
 * Created by qzj on 2018/1/12
 */

import com.aliyuncs.transform.UnmarshallerContext;
import com.qzj.dqq.aliyun.model.QueryInterSmsIsoInfoResponse;

import java.util.ArrayList;
import java.util.List;


public class QueryInterSmsIsoInfoResponseUnmarshaller {
    public QueryInterSmsIsoInfoResponseUnmarshaller() {
    }

    public static QueryInterSmsIsoInfoResponse unmarshall(QueryInterSmsIsoInfoResponse queryInterSmsIsoInfoResponse, UnmarshallerContext context) {
        queryInterSmsIsoInfoResponse.setRequestId(context.stringValue("QueryInterSmsIsoInfoResponse.RequestId"));
        queryInterSmsIsoInfoResponse.setCode(context.stringValue("QueryInterSmsIsoInfoResponse.Code"));
        queryInterSmsIsoInfoResponse.setMessage(context.stringValue("QueryInterSmsIsoInfoResponse.Message"));
        queryInterSmsIsoInfoResponse.setTotalCount(context.stringValue("QueryInterSmsIsoInfoResponse.TotalCount"));
        List<QueryInterSmsIsoInfoResponse.IsoSupportDTO> isoSupportDTOs = new ArrayList();

        for(int i = 0; i < context.lengthValue("QueryInterSmsIsoInfoResponse.IsoSupportDTOs.Length"); ++i) {
            QueryInterSmsIsoInfoResponse.IsoSupportDTO isoSupportDTO = new QueryInterSmsIsoInfoResponse.IsoSupportDTO();
            isoSupportDTO.setCountryName(context.stringValue("QueryInterSmsIsoInfoResponse.IsoSupportDTOs[" + i + "].CountryName"));
            isoSupportDTO.setCountryCode(context.stringValue("QueryInterSmsIsoInfoResponse.IsoSupportDTOs[" + i + "].CountryCode"));
            isoSupportDTO.setIsoCode(context.stringValue("QueryInterSmsIsoInfoResponse.IsoSupportDTOs[" + i + "].IsoCode"));
            isoSupportDTOs.add(isoSupportDTO);
        }

        queryInterSmsIsoInfoResponse.setIsoSupportDTOs(isoSupportDTOs);
        return queryInterSmsIsoInfoResponse;
    }
}