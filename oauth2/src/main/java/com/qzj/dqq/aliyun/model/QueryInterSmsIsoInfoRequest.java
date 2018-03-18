package com.qzj.dqq.aliyun.model;

/**
 * Created by qzj on 2018/1/12
 */
import com.aliyuncs.RpcAcsRequest;

public class QueryInterSmsIsoInfoRequest extends RpcAcsRequest<QueryInterSmsIsoInfoResponse> {
    private String resourceOwnerAccount;
    private String countryName;
    private Long resourceOwnerId;
    private Long ownerId;

    public QueryInterSmsIsoInfoRequest() {
        super("Dysmsapi", "2017-05-25", "QueryInterSmsIsoInfo");
    }

    public String getResourceOwnerAccount() {
        return this.resourceOwnerAccount;
    }

    public void setResourceOwnerAccount(String resourceOwnerAccount) {
        this.resourceOwnerAccount = resourceOwnerAccount;
        if (resourceOwnerAccount != null) {
            this.putQueryParameter("ResourceOwnerAccount", resourceOwnerAccount);
        }

    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
        if (countryName != null) {
            this.putQueryParameter("CountryName", countryName);
        }

    }

    public Long getResourceOwnerId() {
        return this.resourceOwnerId;
    }

    public void setResourceOwnerId(Long resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
        if (resourceOwnerId != null) {
            this.putQueryParameter("ResourceOwnerId", resourceOwnerId.toString());
        }

    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        if (ownerId != null) {
            this.putQueryParameter("OwnerId", ownerId.toString());
        }

    }

    public Class<QueryInterSmsIsoInfoResponse> getResponseClass() {
        return QueryInterSmsIsoInfoResponse.class;
    }
}

