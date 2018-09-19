package com.taj.model;

import java.util.List;

/**
 * Created by User on 9/19/2018.
 */
public class GetSingCollectiveTenderById2 {


    private GetSingleCollectiveByIdPartOneDTO2 tender_data;
    private List<GetSingleCollectiveByIdPartTwoDTO> schools;

    public GetSingCollectiveTenderById2(GetSingleCollectiveByIdPartOneDTO2 tender_data, List<GetSingleCollectiveByIdPartTwoDTO> schools) {
        this.tender_data = tender_data;
        this.schools = schools;
    }

    public GetSingCollectiveTenderById2() {
    }


    public GetSingleCollectiveByIdPartOneDTO2 getTender_data() {
        return tender_data;
    }

    public void setTender_data(GetSingleCollectiveByIdPartOneDTO2 tender_data) {
        this.tender_data = tender_data;
    }

    public List<GetSingleCollectiveByIdPartTwoDTO> getCategories() {
        return schools;
    }

    public void setCategories(List<GetSingleCollectiveByIdPartTwoDTO> schools) {
        this.schools = schools;
    }
}
