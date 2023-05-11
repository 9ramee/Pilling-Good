package ks46team04.admin.dto;

import java.util.List;

import lombok.Data;

@Data
public class FundingDetail {
    private String fundingCode;
    private String fundingName;
    private String fundingDescription;
    private String foundationName;
    private String goodsCode;
    private int fundingGoalAmount;
    private String fundingStartDate;
    private String fundingEndDate;
    private String fundingProgress;
    private String goodsName;
    private int achievedAmount;
    private int achievedRate;
    
    private List<FundingDetail> fundingDetailList;
}
