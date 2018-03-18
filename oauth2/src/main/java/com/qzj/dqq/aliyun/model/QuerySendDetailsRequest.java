package com.qzj.dqq.aliyun.model;

/**
 * Created by qzj on 2018/1/12
 */
import com.aliyuncs.RpcAcsRequest;

public class QuerySendDetailsRequest extends RpcAcsRequest<QuerySendDetailsResponse> {
    private String sendDate;
    private Long pageSize;
    private String phoneNumber;
    private String resourceOwnerAccount;
    private Long currentPage;
    private String bizId;
    private Long resourceOwnerId;
    private Long ownerId;

    public QuerySendDetailsRequest() {
        super("Dysmsapi", "2017-05-25", "QuerySendDetails");
    }

    public String getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
        if (sendDate != null) {
            this.putQueryParameter("SendDate", sendDate);
        }

    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        if (pageSize != null) {
            this.putQueryParameter("PageSize", pageSize.toString());
        }

    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        if (phoneNumber != null) {
            this.putQueryParameter("PhoneNumber", phoneNumber);
        }

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

    public Long getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
        if (currentPage != null) {
            this.putQueryParameter("CurrentPage", currentPage.toString());
        }

    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
        if (bizId != null) {
            this.putQueryParameter("BizId", bizId);
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

    public Class<QuerySendDetailsResponse> getResponseClass() {
        return QuerySendDetailsResponse.class;
    }
}
