package com.taj.model;

import java.util.List;

/**
 * Created by User on 9/12/2018.
 */
public class GetTakatafTenderForScoolRequestDTO2 {

    private GetSingleCollectiveByIdPartOneDTO data;
    private List<GetGetTakatafTenderSchollPrt2DTO2> categories;

    public GetTakatafTenderForScoolRequestDTO2(GetSingleCollectiveByIdPartOneDTO data, List<GetGetTakatafTenderSchollPrt2DTO2> categories) {
        this.data = data;
        this.categories = categories;
    }

    public GetTakatafTenderForScoolRequestDTO2() {
    }

    public GetSingleCollectiveByIdPartOneDTO getData() {
        return data;
    }

    public void setData(GetSingleCollectiveByIdPartOneDTO data) {
        this.data = data;
    }

    public List<GetGetTakatafTenderSchollPrt2DTO2> getCategories() {
        return categories;
    }

    public void setCategories(List<GetGetTakatafTenderSchollPrt2DTO2> categories) {
        this.categories = categories;
    }
}
