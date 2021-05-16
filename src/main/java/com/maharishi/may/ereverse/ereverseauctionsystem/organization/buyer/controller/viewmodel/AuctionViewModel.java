package com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuctionViewModel {
    @NotNull
    @Pattern(regexp = "\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d",message = "please use this format yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", lenient = OptBoolean.FALSE)
    private String closureDate;
    @NotEmpty
    private List<String> specification;

    public AuctionViewModel(String closureDate, List<String> specification) {
        this.closureDate = closureDate;
        this.specification = specification;
    }

    public Date getClosureDate() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat.parse(closureDate);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public List<String> getSpecification() {
        return specification;
    }
}
