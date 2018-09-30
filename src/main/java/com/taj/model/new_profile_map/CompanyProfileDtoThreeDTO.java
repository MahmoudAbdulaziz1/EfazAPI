package com.taj.model.new_profile_map;

import com.taj.model.CompanyProfileDtoOne;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 9/30/2018.
 */
public class CompanyProfileDtoThreeDTO {

    Map<CompanyProfileDtoTwoDTO, List<CompanyProfileDtoOne>> res;
    private List<CompanyProfileDtoTwoDTO> companies;

    public CompanyProfileDtoThreeDTO(Map<CompanyProfileDtoTwoDTO, List<CompanyProfileDtoOne>> res, List<CompanyProfileDtoTwoDTO> companies) {
        this.res = res;
        this.companies = companies;
    }

    public CompanyProfileDtoThreeDTO(List<CompanyProfileDtoTwoDTO> companies) {
        this.companies = companies;
    }

    public CompanyProfileDtoThreeDTO() {
    }

    public Map<CompanyProfileDtoTwoDTO, List<CompanyProfileDtoOne>> getRes() {
        return res;
    }

    public void setRes(Map<CompanyProfileDtoTwoDTO, List<CompanyProfileDtoOne>> res) {
        this.res = res;
    }

    public List<CompanyProfileDtoTwoDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyProfileDtoTwoDTO> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyProfileDtoThreeDTO that = (CompanyProfileDtoThreeDTO) o;

        if (res != null ? !res.equals(that.res) : that.res != null) return false;
        return !(companies != null ? !companies.equals(that.companies) : that.companies != null);

    }

    @Override
    public int hashCode() {
        int result = res != null ? res.hashCode() : 0;
        result = 31 * result + (companies != null ? companies.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyProfileDtoThreeDTO{" +
                "res=" + res +
                ", companies=" + companies +
                '}';
    }
}
