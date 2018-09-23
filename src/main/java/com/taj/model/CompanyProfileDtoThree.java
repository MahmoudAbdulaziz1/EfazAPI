package com.taj.model;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 9/20/2018.
 */
public class CompanyProfileDtoThree {

    Map<CompanyProfileDtoTwo, List<CompanyProfileDtoOne>> res;
    private List<CompanyProfileDtoTwo> companies;

    public CompanyProfileDtoThree(Map<CompanyProfileDtoTwo, List<CompanyProfileDtoOne>> res, List<CompanyProfileDtoTwo> companies) {
        this.res = res;
        this.companies = companies;
    }

    public CompanyProfileDtoThree(List<CompanyProfileDtoTwo> companies) {
        this.companies = companies;
    }

    public CompanyProfileDtoThree() {
    }

    public Map<CompanyProfileDtoTwo, List<CompanyProfileDtoOne>> getRes() {
        return res;
    }

    public void setRes(Map<CompanyProfileDtoTwo, List<CompanyProfileDtoOne>> res) {
        this.res = res;
    }

    public List<CompanyProfileDtoTwo> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyProfileDtoTwo> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyProfileDtoThree that = (CompanyProfileDtoThree) o;

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
        return "CompanyProfileDtoThree{" +
                "res=" + res +
                ", companies=" + companies +
                '}';
    }
}
