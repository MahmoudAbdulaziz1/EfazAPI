package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/29/2018.
 */
public class GetSingCollectiveTenderById {

    private GetSingleCollectiveByIdPartOneDTO tender_data;
    private List<GetSingleCollectiveByIdPartTwoDTO> schools;

    public GetSingCollectiveTenderById(GetSingleCollectiveByIdPartOneDTO tender_data, List<GetSingleCollectiveByIdPartTwoDTO> schools) {
        this.tender_data = tender_data;
        this.schools = schools;
    }

    public GetSingCollectiveTenderById() {
    }


    public GetSingleCollectiveByIdPartOneDTO getTender_data() {
        return tender_data;
    }

    public void setTender_data(GetSingleCollectiveByIdPartOneDTO tender_data) {
        this.tender_data = tender_data;
    }

    public List<GetSingleCollectiveByIdPartTwoDTO> getCategories() {
        return schools;
    }

    public void setCategories(List<GetSingleCollectiveByIdPartTwoDTO> schools) {
        this.schools = schools;
    }
}
