package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/29/2018.
 */
public class GetTakatafTenderForScoolRequestDTO {

    private GetSingleCollectiveByIdPartOneDTO data;
    private List<GetGetTakatafTenderSchollPrt2DTO> categories;

    public GetTakatafTenderForScoolRequestDTO(GetSingleCollectiveByIdPartOneDTO data, List<GetGetTakatafTenderSchollPrt2DTO> categories) {
        this.data = data;
        this.categories = categories;
    }

    public GetTakatafTenderForScoolRequestDTO() {
    }

    public GetSingleCollectiveByIdPartOneDTO getData() {
        return data;
    }

    public void setData(GetSingleCollectiveByIdPartOneDTO data) {
        this.data = data;
    }

    public List<GetGetTakatafTenderSchollPrt2DTO> getCategories() {
        return categories;
    }

    public void setCategories(List<GetGetTakatafTenderSchollPrt2DTO> categories) {
        this.categories = categories;
    }
}
