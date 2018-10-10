package com.taj.model.new_school_request;

import com.taj.model.GetCollectiveTenderPartYTwoDTO;
import com.taj.model.school.request.image.GetCollectiveTenderPartOneDTO2;

import java.util.List;

/**
 * Created by User on 10/1/2018.
 */
public class GetCollectiveTenders2Model {

    private GetCollectiveTenderPartOneDTO2Model tender;
    private List<GetCollectiveTenderPartYTwoDTO> companies;

    public GetCollectiveTenders2Model(GetCollectiveTenderPartOneDTO2Model tender, List<GetCollectiveTenderPartYTwoDTO> companies) {
        this.tender = tender;
        this.companies = companies;
    }

    public GetCollectiveTenders2Model() {
    }

    public GetCollectiveTenderPartOneDTO2Model getTender() {
        return tender;
    }

    public void setTender(GetCollectiveTenderPartOneDTO2Model tender) {
        this.tender = tender;
    }

    public List<GetCollectiveTenderPartYTwoDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<GetCollectiveTenderPartYTwoDTO> companies) {
        this.companies = companies;
    }
}
