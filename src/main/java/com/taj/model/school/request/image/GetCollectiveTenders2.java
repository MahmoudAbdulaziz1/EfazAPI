package com.taj.model.school.request.image;

import com.taj.model.GetCollectiveTenderPartOneDTO;
import com.taj.model.GetCollectiveTenderPartYTwoDTO;

import java.util.List;

/**
 * Created by User on 9/25/2018.
 */
public class GetCollectiveTenders2 {

    private GetCollectiveTenderPartOneDTO2 tender;
    private List<GetCollectiveTenderPartYTwoDTO> companies;

    public GetCollectiveTenders2(GetCollectiveTenderPartOneDTO2 tender, List<GetCollectiveTenderPartYTwoDTO> companies) {
        this.tender = tender;
        this.companies = companies;
    }

    public GetCollectiveTenders2() {
    }

    public GetCollectiveTenderPartOneDTO2 getTender() {
        return tender;
    }

    public void setTender(GetCollectiveTenderPartOneDTO2 tender) {
        this.tender = tender;
    }

    public List<GetCollectiveTenderPartYTwoDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<GetCollectiveTenderPartYTwoDTO> companies) {
        this.companies = companies;
    }
}
