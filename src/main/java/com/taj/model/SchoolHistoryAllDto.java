package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/30/2018.
 */
public class SchoolHistoryAllDto {

    private SchoolHistoryRequestDTO requestData;
    private List<SchoolHistoryResponseDTO> responseDTO;

    public SchoolHistoryAllDto(SchoolHistoryRequestDTO requestData, List<SchoolHistoryResponseDTO> responseDTO) {
        this.requestData = requestData;
        this.responseDTO = responseDTO;
    }

    public SchoolHistoryAllDto() {
    }

    public SchoolHistoryRequestDTO getRequestData() {
        return requestData;
    }

    public void setRequestData(SchoolHistoryRequestDTO requestData) {
        this.requestData = requestData;
    }

    public List<SchoolHistoryResponseDTO> getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(List<SchoolHistoryResponseDTO> responseDTO) {
        this.responseDTO = responseDTO;
    }
}
