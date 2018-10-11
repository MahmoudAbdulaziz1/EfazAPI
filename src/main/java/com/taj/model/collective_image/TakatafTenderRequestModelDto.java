package com.taj.model.collective_image;

import com.taj.model.Takataf_schoolApplayCollectiveTender;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by User on 10/11/2018.
 */
public class TakatafTenderRequestModelDto {

    private int request_id;
    private byte[] tender_logo;
    @Min(1)
    private int request_school_id;
    @Min(1)
    private int request_tender_id;
    //    @Min(0)
    private int is_aproved;
    private long date;
    private @NotNull @Valid List<Takataf_schoolApplayCollectiveTender> category;
}
